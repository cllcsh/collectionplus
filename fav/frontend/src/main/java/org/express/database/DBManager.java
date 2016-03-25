package org.express.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBManager
{
	private static final Logger log = LoggerFactory.getLogger(DBManager.class);
	private final static ThreadLocal<DBConnection> conns = new ThreadLocal<DBConnection>();
	private static DataSource dataSource;
	private static boolean show_sql = false;

	static
	{
		initDataSource(null);
	}

	/**
	 * 初始化连接
	 * 
	 * @param dbProperties
	 */
	private final static void initDataSource(Properties dbProperties)
	{
		try
		{
			if (dbProperties == null)
			{
				dbProperties = new Properties();
				dbProperties.load(DBManager.class.getResourceAsStream("/config/jdbc.properties"));
			}
			Properties cp_props = new Properties();
			for (Object key : dbProperties.keySet())
			{
				String skey = (String) key;
				if (skey.startsWith("jdbc."))
				{
					String name = skey.substring(5);
					cp_props.put(name, dbProperties.getProperty(skey));
					if ("show_sql".equalsIgnoreCase(name))
					{
						show_sql = "true".equalsIgnoreCase(dbProperties.getProperty(skey));
					}
				}
			}
			dataSource = (DataSource) Class.forName(cp_props.getProperty("dataSource")).newInstance();
			if (dataSource.getClass().getName().indexOf("c3p0") > 0)
			{
				// Disable JMX in C3P0
				System.setProperty("com.mchange.v2.c3p0.management.ManagementCoordinator", "com.mchange.v2.c3p0.management.NullManagementCoordinator");
			}
			log.info("Using DataSource : " + dataSource.getClass().getName());
			BeanUtils.populate(dataSource, cp_props);

			Connection conn = getDBConnection().getConn();
			DatabaseMetaData mdm = conn.getMetaData();
			log.info("Connected to " + mdm.getDatabaseProductName() + " " + mdm.getDatabaseProductVersion());
			closeConnection();
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}

	/**
	 * 断开连接池
	 */
	final static void closeDataSource()
	{
		try
		{
			dataSource.getClass().getMethod("close").invoke(dataSource);
		}
		catch (NoSuchMethodException e)
		{
		}
		catch (Exception e)
		{
			log.error("Unabled to destroy DataSource!!! ", e);
		}
	}
	
	/**
	 * 得到线程中的唯一Connection
	 * @return
	 * @throws SQLException
	 */
	public final static DBConnection getDBConnection() throws SQLException
	{
		DBConnection dbConnection = conns.get();
		if (dbConnection == null || dbConnection.getConn() == null || dbConnection.getConn().isClosed())
		{
			if(dbConnection == null)
				dbConnection = new DBConnectionImpl();
				
			Connection  conn = dataSource.getConnection();
			dbConnection.setConn(conn);
			conns.set(dbConnection);
		}
		
		if(show_sql && !Proxy.isProxyClass(dbConnection.getConn().getClass()))
		{
			dbConnection.setConn(new _DebugConnection(dbConnection.getConn()).getConnection());
		}
		
		return dbConnection;
	}

	/**
	 * 关闭连接
	 */
	public final static void closeConnection()
	{
		DBConnection dbConnection = conns.get();
		if(dbConnection != null)
		{
			Connection conn = dbConnection.getConn();
			try
			{
				if (conn != null && !conn.isClosed())
				{
					dbConnection.setIsused(false);
					conn.setAutoCommit(true);
					conn.close();
					dbConnection = null;
				}
			}
			catch (SQLException e)
			{
				log.error("Unabled to close connection!!! ", e);
				e.printStackTrace();
			}
		}
		conns.remove();
	}
	
	/**
	 * 从连接池得到一个新的Connection,一定记得关闭
	 * @return
	 * @throws SQLException
	 */
	final static DBConnection openNewDBConnection() throws SQLException
	{
		DBConnection dbConnection = new DBConnectionImpl();
		Connection conn = dataSource.getConnection();
		dbConnection.setConn(conn);
		if(show_sql && !Proxy.isProxyClass(dbConnection.getConn().getClass()))
		{
			dbConnection.setConn(new _DebugConnection(dbConnection.getConn()).getConnection());
		}
		return dbConnection;
	}
	
	/**
	 * 关闭指定的Connection
	 * @param conn
	 */
	final static void closeConnection(Connection conn) throws SQLException
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.setAutoCommit(true);
				conn.close();
			}
		}
		catch (SQLException e)
		{
			log.error("Unabled to close connection!!! ", e);
			throw e;
		}
	}
	
	/**
	 * 开始事务
	 * @return 是否开始事务
	 * @throws SQLException 
	 */
	public static void BeginTrans() throws SQLException{
		DBConnection dbConnection = getDBConnection();
		if (null != dbConnection){
			Connection connection = dbConnection.getConn();
			if (null != connection && !connection.isClosed()){
				connection.setAutoCommit(false);
			}
			dbConnection.setTrans(true);
		}
	}
	
	public static void rollback() throws SQLException{
		DBConnection dbConnection = getDBConnection();
		if (null != dbConnection){
			Connection connection = dbConnection.getConn();
			if (null != connection && !connection.isClosed()){
				connection.rollback();
			}
		}
	}
	
	public static boolean isTrans() throws SQLException{
		DBConnection dbConnection = getDBConnection();
		if (null != dbConnection){
			return dbConnection.isTrans();
		}
		return false;
	}

	/**
	 * Debug模式Connection代理对象（用于打印SQL）
	 * @author Rei Ayanami
	 *
	 */
	static class _DebugConnection implements InvocationHandler
	{
		private final static Logger log = LoggerFactory.getLogger(_DebugConnection.class);
		private Connection conn = null;
		
		_DebugConnection(Connection conn)
		{
			this.conn = conn;
		}

		/**
		 * Returns the conn.
		 * 
		 * @return Connection
		 */
		Connection getConnection()
		{
			ClassLoader classLoader = conn.getClass().getClassLoader();
			Class<?>[] interClasses = conn.getClass().getInterfaces();
			return (Connection) Proxy.newProxyInstance(classLoader, interClasses, this);
		}

		public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
		{
			try
			{
				String method = m.getName();
				if ("prepareStatement".equals(method) || "createStatement".equals(method))
					log.debug("[SQL] >>> " + args[0]);
				return m.invoke(conn, args);
			}
			catch (InvocationTargetException e)
			{
				throw e.getTargetException();
			}
		}
	}
}
