package org.express.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.express.data.DataRow;
import org.express.data.DataRow.DataRowStatus;
import org.express.data.DataColumn;
import org.express.data.DataSet;
import org.express.data.DataTable;
import org.express.data.DataTypes;
import org.express.data.DataTypes.DataType;
import org.express.util.Convert;
import org.express.util.DateUtil;


/**
 * 数据库访问提供类
 * @author Rei Ayanami
 *@version 1.0
 */
public class Query
{
	/**
	 * 性能设置
	 * @author Rei Ayanami
	 *
	 */
	public enum PerformanceSetting {
		/**
		 * 普通（性能和花销平衡）
		 */
		Normal,
		/**
		 * 高性能读取
		 */
		HighPerformanceRead,
		/**
		 * 高性能写入
		 */
		HighPerformanceWrite
	}
	
	/**
	 * fetchsize default 10000
	 */
	private Integer queryFetchSize = 10000;
	
	/**
	 * 得到查询的FetchSize
	 * @return
	 */
	public Integer getQueryFetchSize() {
		try 
		{
			String dvName = this.session.getConnection().getMetaData().getDriverName().toUpperCase();
			if(dvName.indexOf("ORACLE") >= 0)
			{
				if(queryFetchSize == null || queryFetchSize < 0)
					return 10000;
				else
					return queryFetchSize;
			}
			else if(dvName.indexOf("MYSQL") >= 0)
			{
				return Integer.MIN_VALUE;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 10000;
		}
		return queryFetchSize;
	}

	/**
	 * 设置FetchSize，配合PerformanceSetting.HighPerformanceRead使用
	 * @param queryFetchSize
	 */
	public void setQueryFetchSize(Integer queryFetchSize) {
		this.queryFetchSize = queryFetchSize;
	}

	DBSession session = null;
	
	/**
	 * 初始化一个数据访问（默认不关闭，连接池资源）
	 */
	public Query()
	{
		session = new DBSession();
	}
	
	/**
	 * 初始化一个是否关闭的连接
	 * @param isNotCloseMode
	 */
	public Query(boolean isNotCloseMode)
	{
		session = new DBSession(isNotCloseMode);
	}
	
	/**
	 * 初始化一个是否关闭和是否独立的连接
	 * @param isNotCloseMode
	 * @param isNewConnection
	 */
	public Query(boolean isNotCloseMode, boolean isNewConnection)
	{
		session = new DBSession(isNotCloseMode , isNewConnection);
	}
	
	/**
	 * 初始化一个已有的连接的访问
	 * @param s
	 */
	public Query(DBSession s)
	{
		session = s;
	}
	
	/**
	 * 得到数据库连接代理
	 * @return
	 */
	public DBSession getDBSession()
	{
		return session;
	}
	
	/**
	 * 设置数据库连接代理
	 * @param s
	 */
	public void SetDBSession(DBSession s)
	{
		this.session = s;
	}
	
	/**
	 * 执行DQL查询返回数据表
	 * @param sql
	 * @throws QueryException
	 * @return
	 */
	public DataTable ExecuteQuery(String sql) throws QueryException
	{
		return ExecuteQuery(sql, PerformanceSetting.Normal);
	}
	
	/**
	 * 执行DQL查询返回数据表
	 * @param sql
	 * @param ps
	 * @return
	 * @throws QueryException
	 */
	public DataTable ExecuteQuery(String sql , PerformanceSetting ps) throws QueryException
	{
		DataTable dt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try
		{
			this.session.getConnection();
			psmt = getPreparedStatement(sql , ps);
			rs = executeQuery(psmt);
			dt = fillDataTable(rs);
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dt;
	}
	
	/**
	 * 执行DQL查询返回数据表
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public DataTable ExecuteQuery(String sql, Object... params) throws QueryException
	{
		return ExecuteQuery(sql, PerformanceSetting.Normal, params);
	}
	
	/**
	 * 执行DQL查询返回数据表
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public DataTable ExecuteQuery(String sql, PerformanceSetting ps, Object... params) throws QueryException
	{
		DataTable dt = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			this.session.getConnection();
			psmt = getParamPreparedStatement(sql, ps, params);
			rs = executeQuery(psmt);
			dt = fillDataTable(rs);
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			System.out.println("ERROR PARAMS :: " + ToStringParams(params));
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dt;
	}
	
	/**
	 * 执行DQL查询返回第一行第一列
	 * @param sql
	 * @return
	 * @throws QueryException
	 */
	public Object ExecuteScalar(String sql) throws QueryException
	{
		Object o = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try
		{
			this.session.getConnection();
			psmt = getPreparedStatement(sql);
			rs = executeQuery(psmt);
			
			while(rs.next())
			{
				o = rs.getObject(1);
				break;
			}
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return o;
	}
	
	/**
	 * 执行DQL查询返回第一行第一列
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public Object ExecuteScalar(String sql , Object... params) throws QueryException
	{
		Object o = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			this.session.getConnection();
			psmt = getParamPreparedStatement(sql , params);
			rs = executeQuery(psmt);
			while(rs.next())
			{
				o = rs.getObject(0);
				break;
			}
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			System.out.println("ERROR PARAMS :: " + ToStringParams(params));
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return o;
	}

	/**
	 * 执行DML语句返回受影响的行数
	 * @param sql
	 * @return
	 * @throws QueryException
	 */
	public  int ExecuteNonQuery(String sql) throws QueryException
	{
		int i = 0;
		PreparedStatement psmt = null;
		try
		{
			this.session.getConnection();
			psmt = getPreparedStatement(sql);
			i = executeUpdate(psmt);
		}
		catch(QueryException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			throw err;
		}
		finally
		{
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return i;
	}
	
	/**
	 * 执行DML语句返回受影响的行数
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public  int ExecuteNonQuery(String sql , Object... params ) throws QueryException
	{
		int i = 0;
		PreparedStatement psmt = null;
		try
		{
			this.session.getConnection();
			psmt = getParamPreparedStatement(sql , params);
			i = executeUpdate(psmt);
		}
		catch(QueryException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			System.out.println("ERROR PARAMS :: " + ToStringParams(params));
			throw err;
		}
		finally
		{
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return i;
	}
	
	/**
	 * 插入数据返回自动增长的数据
	 * @param sql
	 * @return
	 * @throws QueryException
	 */
	public  Object Insert(String sql) throws QueryException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			this.session.getConnection();
			pstmt = getSetPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
			executeUpdate(pstmt);
			rs = pstmt.getGeneratedKeys();
			rs.next();
			return rs.getObject(1);
		}
		catch (SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 插入数据返回自动增长的数据
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public  Object Insert(String sql, Object...  params) throws QueryException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			this.session.getConnection();
			pstmt = getSetPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(pstmt, params);
			executeUpdate(pstmt);
			rs = pstmt.getGeneratedKeys();
			rs.next();
			return rs.getObject(1);
		}
		catch (SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQL :: " + sql);
			System.out.println("ERROR PARAMS :: " + ToStringParams(params));
			throw new QueryException(err);
		}
		finally
		{
			try {
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 批处理DML SQL语句
	 * @param sqls
	 * @return
	 * @throws QueryException
	 */
	public int[] ExecuteBatch(String[]  sqls) throws QueryException
	{
		int[] result = null;
		PreparedStatement pstmt = null;
		try
		{
			this.session.getConnection();
			if(sqls != null && sqls.length > 0)
			{
				for(int i=0 ; i < sqls.length; i++)
				{
					if(i== 0)
					{
						pstmt = getPreparedStatement(sqls[i]);
					}
					else
					{
						pstmt.addBatch(sqls[i]);
					}
				}
				
				result = pstmt.executeBatch();
			}
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQLS :: " + ToStringSQL(sqls));
			throw new QueryException(err);
		}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 批处理DML SQL语句
	 * @param sqls
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	public int[] ExecuteBatch(String sql, Object[]... params) throws QueryException
	{
		int[] result = null;
		PreparedStatement pstmt = null;
		try
		{
			this.session.getConnection();
			if(sql != null && sql.length() > 0)
			{
				for(int i=0 ; i < params.length; i++)
				{
					if(i== 0)
					{
						pstmt = getPreparedStatement(sql);
					}
					
					setParameters(pstmt, params[i]);
					pstmt.addBatch();
				}
				
				result = pstmt.executeBatch();
			}
		}
		catch(SQLException err)
		{
			System.out.println("ERROR MESSAGE :: " + err.getMessage());
			System.out.println("ERROR SQLS :: " + sql);
			System.out.println("ERROR PARAMS :: " + ToStringParams(params));
			throw new QueryException(err);
		}
		finally
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 读取表结构
	 * @param tableName 表名
	 * @return 数据表
	 */
	public DataTable LoadTableSchema(String tableName)
	{
		DataTable dt = null;
		String sql = "";
		if(tableName != null)
		{
			try
			{
				sql = "select * from " + tableName + " where 0=1";
				dt = ExecuteQuery(sql);
				if(dt != null) dt.setTableName(tableName);
			}
			catch(QueryException err)
			{
				System.out.println("ERROR MESSAGE :: " + err.getMessage());
				System.out.println("ERROR SQLS :: " + sql);
				throw err;
			}
		}
		
		return dt;
	}
	
	/**
	 * 读取表（全表）
	 * @param tableName 表名
	 * @return 数据表
	 */
	public DataTable LoadTable(String tableName)
	{
		DataTable dt = null;
		String sql = "";
		if(tableName != null)
		{
			try
			{
				sql = "select * from " + tableName;
				dt = ExecuteQuery(sql , PerformanceSetting.HighPerformanceRead);
				if(dt != null) dt.setTableName(tableName);
			}
			catch(QueryException err)
			{
				System.out.println("ERROR MESSAGE :: " + err.getMessage());
				System.out.println("ERROR SQLS :: " + sql);
				throw err;
			}
		}
		
		return dt;
	}
	
	/**
	 * 保存数据表集合
	 * @param ds
	 * @return 数据集
	 * @throws SQLException 
	 */
	public boolean SaveData(DataSet ds)
	{
		return SaveData(ds, true);
	}
	
	/**
	 * 保存数据表集合
	 * @param ds
	 * @param isPrimaryKeyUnique 是否主键唯一性操作
	 * @return 数据集
	 * @throws SQLException 
	 */
	public boolean SaveData(DataSet ds , boolean isPrimaryKeyUnique)
	{
		boolean result = false;
		for(DataTable dt : ds.getDataTables())
		{
			result = SaveData(dt , isPrimaryKeyUnique);
		}
		return result;
	}
	
	/**
	 * 保存表数据
	 * @param dt 数据表
	 * @return 保存成功标识
	 * @throws SQLException 
	 */
	public boolean SaveData(DataTable dt)
	{
		return SaveData(dt, true);
	}
	
	/**
	 * 保存表数据
	 * @param dt 数据表
	 * @param isPrimaryKeyUnique 是否主键唯一性操作
	 * @return 保存成功标识
	 * @throws SQLException 
	 */
	public boolean SaveData(DataTable dt , boolean isPrimaryKeyUnique)
	{
		/**
		 * 函数解释
		 * 根据DataRowStatus的状态，来完成DataTable的DDL操作。
		 * 优先删除，其次插入，最后更新。 状态Normal为的数据行不会执行。
		 * 
		 * isPrimaryKeyUnique 参数解释如下
		 * 在本函数中默认将保证主键的唯一性操作。即相同主键只有一次DDL操作。（仅针对delete和update）
		 * 例如：相同主键下的重复删除、更新是无意义的。将只会第一次成功执行，后面的跳过。（避免重复的执行）
		 * 另外相同主键下的先删除再更新，或者先更新再删除，也是只有删除才有最终效果。（避免无意义的更新）
		 * 插入的DataRow将不会受到主键唯一性操作的约束。（如果主键重复的插入则会引起报错）
		 * isPrimaryKeyUnique设置为false后，上述校验将不会执行。
		 */
		boolean result = false;
		if(dt!=null && dt.getTotalCount() > 0)
		{
			if(dt.getTableName() == null || "".equals(dt.getTableName()))
				throw new QueryException("数据表没有表名无法操作");
						
			PreparedStatement pstmt_del = null;
			PreparedStatement pstmt_insert = null;
			PreparedStatement pstmt_update = null;
			
			try
			{
				String insertSQL = null;
				String deleteSQL = null;
				String modifiedSQL = null;
				Integer executeCount_del = 0;
				Integer executeCount_insert = 0;
				Integer executeCount_update = 0;
				Set<String> key = new HashSet<String>();
				String primarykeyString = null;
				
				for(DataRow dr : dt.getRows())
				{
					//1.处理删除
					if(dr.getStatus() == DataRowStatus.Delete)
					{
						executeCount_del++;
						if(dt.getColumns().getPrimaryColumn().size() == 0)
							throw new QueryException("数据表没有设置主键无法进行删除操作");
						
						if(deleteSQL == null)
							deleteSQL = groupDeleteSQL(dt);
						
						if(pstmt_del == null)
							pstmt_del = getPreparedStatement(deleteSQL);
						
						if(isPrimaryKeyUnique)
						{
							primarykeyString = dr.getPrimaryColumnValue();
							if(key.contains(primarykeyString))
							{
								continue;
							}
							else
							{
								key.add(primarykeyString);
							}
						}
						
						int i=1;
						for(DataColumn dc : dt.getColumns())
						{
							if(dc.isPrimaryColumn())
							{
								setTableParam(i, pstmt_del , dr , dc);
								i++;
							}
						}
						
						pstmt_del.addBatch();
					}
					//2.处理插入
					else if(dr.getStatus() == DataRowStatus.Add)
					{
						executeCount_insert++;
						if(dt.getColumns().size() == 0)
							throw new QueryException("数据表没有数据列用于添加");
						
						if(insertSQL == null)
							insertSQL = groupInsertSQL(dt);
						
						if(pstmt_insert == null)
							pstmt_insert = getPreparedStatement(insertSQL);
						
						int i=1;
						for(DataColumn dc : dt.getColumns())
						{
							setTableParam(i, pstmt_insert , dr , dc);
							i++;
						}
						
						pstmt_insert.addBatch();
					}
					//3.处理修改
					else if(dr.getStatus() == DataRowStatus.Modified)
					{
						executeCount_update++;
						if(dt.getColumns().getPrimaryColumn().size() == 0)
							throw new QueryException("数据表没有设置主键无法进行修改操作");
						
						if(modifiedSQL == null)
							modifiedSQL = groupModifiedSQL(dt);
						
						if(pstmt_update == null)
							pstmt_update = getPreparedStatement(modifiedSQL);
						
						if(isPrimaryKeyUnique)
						{
							primarykeyString = dr.getPrimaryColumnValue();
							if(key.contains(primarykeyString))
							{
								continue;
							}
							else
							{
								key.add(primarykeyString);
							}
						}
						
						int i=1;
						for(DataColumn dc : dt.getColumns())
						{
							if(!dc.isPrimaryColumn())
							{
								setTableParam(i, pstmt_update , dr , dc);
								i++;
							}
						}
						for(DataColumn dc : dt.getColumns())
						{
							if(dc.isPrimaryColumn())
							{
								setTableParam(i, pstmt_update , dr , dc);
								i++;
							}
						}
						
						pstmt_update.addBatch();
					}
				}
				
				//提交删除
				if(executeCount_del > 0)
				{
					System.out.println("delete info :: " + executeCount_del + " records delete");
					pstmt_del.executeBatch();
				}
				//提交插入
				if(executeCount_insert > 0)
				{
					System.out.println("insert info :: " + executeCount_insert + " records insert");
					pstmt_insert.executeBatch();
				}
				//提交修改
				if(executeCount_update > 0)
				{
					System.out.println("update info :: " + executeCount_update + " records update");
					pstmt_update.executeBatch();
				}
				
				result = true;
			}
			catch(Exception err)
			{
				throw new QueryException(err);
			}
			finally
			{
				try {
					if(pstmt_del != null)
					{
						pstmt_del.close();
						pstmt_del = null;
					}
					if(pstmt_insert != null)
					{
						pstmt_insert.close();
						pstmt_insert = null;
					}
					if(pstmt_update != null)
					{
						pstmt_update.close();
						pstmt_update = null;
					}
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	/************************************************私有方法********************************************************/
	/**
	 * 生成删除语句
	 * @param dt
	 * @return
	 */
	private String groupDeleteSQL(DataTable dt)
	{
		StringBuilder sbmain= new StringBuilder();
		StringBuilder sbwhere = new StringBuilder();
		
		sbmain.append("Delete From ").append(dt.getTableName()).append(" ");
		sbwhere.append(" Where 0=0");
		
		for(DataColumn dc : dt.getColumns())
		{
			if(dc.isPrimaryColumn())
			{
				sbwhere.append(" and ").append(dc.getColumnName()).append("=").append("?");
			}
		}
		
		return sbmain.append(sbwhere).toString();
	}
	
	/**
	 * 生成插入语句
	 * @param dt
	 * @return
	 */
	private String groupInsertSQL(DataTable dt)
	{
		StringBuilder sbmain= new StringBuilder();
		StringBuilder sbwhere = new StringBuilder();
		
		sbmain.append("Insert into ").append(dt.getTableName()).append(" (");
		sbwhere.append(" values( ");
		
		for(DataColumn dc : dt.getColumns())
		{
			sbmain.append(dc.getColumnName()).append(",");
			sbwhere.append("?").append(",");
		}
		sbmain.delete(sbmain.length()-1 , sbmain.length());
		sbwhere.delete(sbwhere.length()-1 , sbwhere.length());
		sbmain.append(")");
		sbwhere.append(")");
		
		return sbmain.append(sbwhere).toString();
	}
	
	/**
	 * 生成修改语句
	 * @param dt
	 * @return
	 */
	private String groupModifiedSQL(DataTable dt)
	{
		StringBuilder sbmain= new StringBuilder();
		StringBuilder sbwhere = new StringBuilder();
		sbmain.append("Update ").append(dt.getTableName()).append(" Set ");
		sbwhere.append(" Where 0=0");
		
		for(DataColumn dc : dt.getColumns())
		{
			if(dc.isPrimaryColumn())
			{
				sbwhere.append(" and ").append(dc.getColumnName()).append("=").append("?");
			}
			else
			{
				sbmain.append(dc.getColumnName()).append("=").append("?,");
			}
		}
		sbmain.delete(sbmain.length()-1 , sbmain.length());
		return sbmain.append(sbwhere).toString();
	}
	
	/**
	 * 设置参数
	 * @param index 索引
	 * @param pstmt 
	 * @param dr 数据行
	 * @param dc 数据列
	 */
	private void setTableParam(Integer index, PreparedStatement pstmt , DataRow dr , DataColumn dc)
	{
		try
		{
			DataType dtype = dc.getDataType();
			if(dr.getValue(dc.getColumnIndex()) == null)
			{
				pstmt.setNull(index , java.sql.Types.NULL);
			}
			else
			{
				switch (dtype) {
					case String:
						pstmt.setString(index , Convert.toString(dr.getValue(dc.getColumnIndex())));
						break;
					case Long:
						pstmt.setLong(index , Convert.toLong(dr.getValue(dc.getColumnIndex())));
						break;
					case Integer:
						pstmt.setInt(index , Convert.toInt(dr.getValue(dc.getColumnIndex())));					
						break;
					case Double:
						pstmt.setDouble(index , Convert.toDouble(dr.getValue(dc.getColumnIndex())));
						break;
					case Float:
						pstmt.setFloat(index , Convert.toFloat(dr.getValue(dc.getColumnIndex())));
						break;
					case Number:
						pstmt.setBigDecimal(index , Convert.toDecimal(dr.getValue(dc.getColumnIndex())));
						break;
					case Date:
						Date date = new Date(DateUtil.parseObject(dr.getValue(dc.getColumnIndex())).getTime());
						pstmt.setDate(index,  date);
						break;
					case Boolean:
						Object o = dr.getValue(dc.getColumnIndex());
						if(o.equals(1) || o.equals("1") || o.equals(true) ||
							Convert.toString(o).toLowerCase().equals("true")  || Convert.toString(o).toLowerCase().equals("yes"))
							o = 1;
						else
							o= 0;
						pstmt.setInt(index , Convert.toInt(o));
						break;
					case Bytes:
						pstmt.setBytes(index , Convert.toBytes(dr.getValue(dc.getColumnIndex())));
						break;
					case Time:
						Time time = new Time(DateUtil.parseObject(dr.getValue(dc.getColumnIndex())).getTime());
						pstmt.setTime(index , time);
						break;
					case TimeStamp:
						Timestamp stamp = new Timestamp(DateUtil.parseObject(dr.getValue(dc.getColumnIndex())).getTime());
						pstmt.setTimestamp(index , stamp);
						break;
					default:
						pstmt.setString(index , Convert.toString(dr.getValue(dc.getColumnIndex())));
						break;
				}
			}
		}
		catch (Exception e)
		{
			throw new QueryException(e);
		}
	}
	
	/**
	 * 得到PreparedStatement
	 * @param sql
	 * @return
	 * @throws QueryException
	 */
	private PreparedStatement getPreparedStatement(String sql)
	{
		return getPreparedStatement(sql, PerformanceSetting.Normal);
	}
	
	/**
	 * 得到性能优化后的PreparedStatement
	 * @param sql
	 * @param ps
	 * @return
	 * @throws QueryException
	 */
	private PreparedStatement getPreparedStatement(String sql , PerformanceSetting ps) throws QueryException
	{
		try
		{
			if(ps == PerformanceSetting.HighPerformanceRead)
			{
				//System.out.println(this.session.getConnection().getMetaData().getDriverName()); 
				PreparedStatement psmt  = this.session.getConnection().prepareStatement(sql , ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				psmt.setFetchSize(getQueryFetchSize());
				psmt.setFetchDirection(ResultSet.FETCH_REVERSE);
				return psmt;
			}
			else
				return this.session.getConnection().prepareStatement(sql);
		}
		catch (SQLException e)
		{
			throw new QueryException(e);
		}
	}
	
	/**
	 * 得到可设置的PreparedStatement
	 * @param sql
	 * @param statement
	 * @return
	 * @throws QueryException
	 */
	private PreparedStatement getSetPreparedStatement(String sql , Integer statement) throws QueryException
	{
		try
		{
			return this.session.getConnection().prepareStatement(sql , statement);
		}
		catch (SQLException e)
		{
			throw new QueryException(e);
		}
	}
	
	/**
	 * 得到性能优化后的参数化查询的ParamPreparedStatement
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	private PreparedStatement getParamPreparedStatement(String sql,Object... params) throws QueryException
	{
		return getParamPreparedStatement(sql, PerformanceSetting.Normal, params);
	}
	
	/**
	 * 得到参数化查询的ParamPreparedStatement
	 * @param sql
	 * @param params
	 * @return
	 * @throws QueryException
	 */
	private PreparedStatement getParamPreparedStatement(String sql, PerformanceSetting ps, Object... params) throws QueryException
	{
		PreparedStatement pstmt = getPreparedStatement(sql , ps);
		setParameters(pstmt, params);
		return pstmt;
	}

	/**
	 * 执行DML语句
	 * @param pstmt
	 * @return
	 * @throws QueryException
	 */
	private int executeUpdate(PreparedStatement pstmt) throws QueryException
	{
		try
		{		
			return pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new QueryException(e);
		}
	}
	
	
	/**
	 * 设置参数
	 * @param pstmt
	 * @param params
	 * @throws QueryException
	 */
	private void setParameters(PreparedStatement pstmt, Object... params) throws QueryException
	{
		try
		{
			for (int i = 0; i < params.length; i++)
			{
				pstmt.setObject(i + 1, params[i]);
			}
		}
		catch (SQLException e)
		{
			throw new QueryException(e);
		}
	}
	
	/**
	 * 执行DQL 返回结果集
	 * @param pstmt
	 * @return
	 * @throws QueryException
	 */
	private ResultSet executeQuery(PreparedStatement pstmt) throws QueryException
	{
		try
		{	
			return pstmt.executeQuery();
		}
		catch (SQLException e)
		{
			throw new QueryException(e);
		}
	}
	
	/**
	 * 将结果集填充至数据表
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static DataTable fillDataTable(ResultSet rs) throws SQLException
	{
		DataTable dt = new DataTable();
		
		if(rs != null)
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			
			for(int i=1 ; i <= rsmd.getColumnCount(); i ++)
			{
				dt.addColumn(rsmd.getColumnLabel(i).toLowerCase(),  DataTypes.getDataType(rsmd.getColumnType(i)));
				//System.out.println("COLNAME:"+ rsmd.getColumnLabel(i) + ",TYPE:" + rsmd.getColumnType(i));
			}
			
			while(rs.next())
			{
				DataRow dr = dt.newRow();
				for(int i=0 ; i < dt.getColumns().size(); i++)
				{
					dr.setValue(dt.getColumns().get(i).getColumnName(), rs.getObject(dt.getColumns().get(i).getColumnName()));
				}
				dt.addRow(dr);
			}
			
			dt.resetStatus();
		}
		else
		{
			throw new QueryException("ResultSet is NULL");
		}
		
		return dt;
	}
	
	/**
	 * 参数SQL拼接（DEBUG）
	 * @param objects
	 * @return
	 */
	private String ToStringParams(Object... objects)
	{
		StringBuilder sb = new StringBuilder();
		for(Object o : objects)
		{
			sb.append(o.toString()).append(" ,");
		}
		
		if(sb.length() > 0)
		{
			return sb.substring(0 , sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 参数SQL拼接（DEBUG）
	 * @param objects
	 * @return
	 */
	private String ToStringParams(Object[]... objects)
	{
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(Object o : objects)
		{
			sb.append("PARAM").append(i).append(" :: ").append(ToStringParams(o));
		}
		
		if(sb.length() > 0)
		{
			return sb.substring(0 , sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 参数SQL拼接（DEBUG）
	 * @param sql
	 * @return
	 */
	private String ToStringSQL(String[] sql)
	{
		StringBuilder sb = new StringBuilder();
		int i = 1;
		for(String o : sql)
		{
			sb.append("SQL").append(i).append(" :: [").append(o.toString()).append("],");
			i++;
		}
		
		if(sb.length() > 0)
		{
			return sb.substring(0 , sb.length() - 1);
		}
		return sb.toString();
	}
}
