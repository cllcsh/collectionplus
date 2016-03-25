package com.front.web.framework.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.express.database.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;
import com.front.web.util.DateUtil;
import com.front.web.util.SqlConstant;
import com.front.web.util.StringConstant;
import com.front.web.util.StringUtil;

public class BaseDao {
	
	// 运行日志接口
    private final static Logger runLog = LoggerFactory.getLogger(BaseDao.class);

	/**
	 * 根据sql获取总记录数
	 * @param sql sql
	 * @return 总记录数
	 * @throws SQLException 
	 */
	public static int getCount(String sql, List<Object> params) throws SQLException{
		// 打印日志
		runLog.debug("getCount SQL=" + sql);
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        try {
            conn = getConnection();
            if (null != conn) {
                pstmt = conn.prepareStatement(sql);
                setParams(pstmt, params);
                if (null != pstmt) {
                    rs = pstmt.executeQuery();// 执行sql查询语句，返回查询数据的结果

                    if (null != rs && rs.next()) {
                        count = rs.getInt(1);
                    }
                }
            }
        } finally {
            // 关闭数据库连接
            close(rs, pstmt, conn);
        }
		return count;
	}
	
	/**
	 * 执行sql
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static boolean update(String sql, List<Object> params) throws Exception{
		// 打印日志
		runLog.info("execute SQL=" + sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            if (null != conn) {
                pstmt = conn.prepareStatement(sql);
                setParams(pstmt, params);
                if (null != pstmt) {
                    int i = pstmt.executeUpdate();
                    if (i > 0) {
                       return true;
                    }
                }
            }
        } finally {
            // 关闭数据库连接
            close(null, pstmt, conn);
        }
		return false;
	}
	
	/**
	 * 关闭数据库连接
	 * 
	 * @param statement
	 *            statement
	 * @param conn
	 *            conn
	 */
	public static void close(Statement statement, Connection conn) {
		// 关闭数据库连接
		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != conn) {
			try {
				if (!DBManager.getDBConnection().isTrans()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void close(ResultSet rs, Statement statement, Connection conn) {
		// 关闭数据库连接
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (null != conn) {
			try {
				if (!DBManager.getDBConnection().isTrans()){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取对象集合
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static List getList(Class objectClass, String conditionSql, List<Object> params) throws Exception{
		List list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement(getSql(objectClass, conditionSql));
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (null != rs) {
				list = new ArrayList();
				Object object = null;
				String fieldName = null;
				Class cls = null;
				while (rs.next()) {
					object = objectClass.newInstance(); 
					Method[] methods = objectClass.getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("set")){
							fieldName = getDBFieldName(objectClass, method.getName().substring(3));
							cls = method.getParameterTypes()[0];
							setField(cls, fieldName, object, method, rs);
						}
					}
					list.add(object);
				}
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 查询对象有注解的属性的值得集合
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static List getListByAnnotation(Class objectClass, String conditionSql, List<Object> params) throws Exception{
		List list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement(getSqlByAnnotation(objectClass, conditionSql));
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (null != rs) {
				list = new ArrayList();
				Object object = null;
				String fieldName = null;
				Class cls = null;
				while (rs.next()) {
					object = objectClass.newInstance(); 
					Method[] methods = objectClass.getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("set")){
							fieldName = getAnnotationName(objectClass, method.getName().substring(3));
							if (!"".equals(fieldName)){
								cls = method.getParameterTypes()[0];
								setField(cls, fieldName, object, method, rs);
							}
						}
					}
					list.add(object);
				}
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 获取对象
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectBySql(ResultSetInterface set, String sql, List<Object> params) throws Exception{
		Object object = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (null != rs && rs.next()) {
				object = set.getObject(rs);
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return object;
	}
	
	/**
	 * 获取对象
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static Object getObject(Class objectClass, String conditionSql, List<Object> params) throws Exception{
		Object object = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement(getSql(objectClass, conditionSql));
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (null != rs && rs.next()) {
				String fieldName = null;
				Class cls = null;
					object = objectClass.newInstance(); 
					Method[] methods = objectClass.getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("set")){
							fieldName = getDBFieldName(objectClass, method.getName().substring(3));
							cls = method.getParameterTypes()[0];
							setField(cls, fieldName, object, method, rs);
						}
					}
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return object;
	}
	
	/**
	 * 根据注解只查询有注解的对象属性值
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectByAnnotation(Class objectClass, String conditionSql, List<Object> params) throws Exception{
		Object object = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement(getSqlByAnnotation(objectClass, conditionSql));
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			if (null != rs && rs.next()) {
				String fieldName = null;
				Class cls = null;
					object = objectClass.newInstance(); 
					Method[] methods = objectClass.getMethods();
					for (Method method : methods) {
						if (method.getName().startsWith("set")){
							fieldName = getAnnotationName(objectClass, method.getName().substring(3));
							if (!"".equals(fieldName)) {
								cls = method.getParameterTypes()[0];
								setField(cls, fieldName, object, method, rs);
							}
						}
					}
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return object;
	}
	
	/**
	 * 
	 * @param cls 返回类型
	 * @param fieldName 数据库字段明
	 * @param object javabean对象
	 * @param method javabean set方法
	 * @param rs 结果集
	 */
	private static void setField(Class cls, String fieldName, Object object, Method method, ResultSet rs) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException{
		if (String.class == cls){
			method.invoke(object, rs.getString(fieldName));
		}else if (Integer.class == cls || int.class == cls){
			method.invoke(object, rs.getInt(fieldName));
		}else if (Double.class == cls || double.class == cls){
			method.invoke(object, rs.getDouble(fieldName));
		}else if (Float.class == cls || float.class == cls){
			method.invoke(object, rs.getFloat(fieldName));
		}else if (Long.class == cls || long.class == cls){
			method.invoke(object, rs.getLong(fieldName));
		}else if (Date.class == cls || Timestamp.class == cls){
			method.invoke(object, rs.getTimestamp(fieldName));
		}else if (BigDecimal.class == cls){
			method.invoke(object, rs.getBigDecimal(fieldName));
		}
	}
	
	/**
	 * 根据类获取查询sql
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	private static String getSql(Class objectClass, String conditionSql) throws Exception{
		Method[] methods = objectClass.getMethods();
		StringBuffer sqlBuffer = new StringBuffer("SELECT ");
		String fieldName = null;
		for (Method method : methods) {
			if (method.getName().startsWith("set")){
				fieldName = method.getName().substring(3);
				sqlBuffer.append(getDBFieldName(objectClass, fieldName)).append(",");
			}
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
		if (null != conditionSql){
			String upperCaseConditionSql = conditionSql.trim().toUpperCase();
			if (upperCaseConditionSql.startsWith("FROM")){
				sqlBuffer.append(" ").append(conditionSql);
			}else {
				String tableName = getTableName(objectClass);
				if (StringUtil.isNotEmptyAndNotNull(tableName)){
					sqlBuffer.append(" FROM ").append(tableName).append(" where 1=1 ");
					if (upperCaseConditionSql.startsWith("AND")) {
						sqlBuffer.append(conditionSql);
					}else {
						sqlBuffer.append(" AND ").append(conditionSql);
					}
				}
			}
		}
		return sqlBuffer.toString();
	}
	
	/**
	 * 根据注解获取查询sql
	 * @param objectClass
	 * @param conditionSql and field=value
	 * @return
	 * @throws Exception
	 */
	private static String getSqlByAnnotation(Class objectClass, String conditionSql) throws Exception{
		Method[] methods = objectClass.getMethods();
		StringBuffer sqlBuffer = new StringBuffer("SELECT ");
		String fieldName = null;
		for (Method method : methods) {
			if (method.getName().startsWith("set")){
				fieldName = getAnnotationName(objectClass, method.getName().substring(3));
				if (!"".equals(fieldName)){
					sqlBuffer.append(fieldName).append(",");
				}
			}
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length() - 1);
		
		if (null != conditionSql){
			String upperCaseConditionSql = conditionSql.trim().toUpperCase();
			if (upperCaseConditionSql.startsWith("FROM")){
				sqlBuffer.append(" ").append(conditionSql);
			}else {
				String tableName = getTableName(objectClass);
				if (StringUtil.isNotEmptyAndNotNull(tableName)){
					sqlBuffer.append(" FROM ").append(tableName).append(" where 1=1 ");
					if (upperCaseConditionSql.startsWith("AND")) {
						sqlBuffer.append(conditionSql);
					}else {
						sqlBuffer.append(" AND ").append(conditionSql);
					}
				}
			}
		}
		return sqlBuffer.toString();
	}
	
	/**
	 * 插入
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static boolean insert(Object object) throws Exception{
		Class objectClass = object.getClass();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = getInsertSql(object);
			List<String> fieldNameList = (List<String>)Arrays.asList(sql.substring(sql.indexOf("(") + 1, sql.indexOf(")")).split(","));
			
			pstmt = conn.prepareStatement(sql);
			Class cls = null;
			Method[] methods = objectClass.getMethods();
			String fieldName = "";
			StringBuffer psBuffer = new StringBuffer();
			int i = 0;
			for (Method method : methods) {
				if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
					fieldName = getAnnotationName(objectClass, method.getName().substring(3));
					if (!"".equals(fieldName)){
						// 没有设置值的字段不需要插入，数据库自动插入默认值
						if (null != method.invoke(object)){
							i = fieldNameList.indexOf(fieldName) + 1;
							cls = method.getReturnType();
							setPreparedStatement(psBuffer, cls, pstmt, i, object, method);
//							System.out.println(psBuffer);
						}
					}
				}
			}
//			System.out.println(psBuffer);
			if(pstmt.executeUpdate()>0){
				return true;
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return false;
	}
	
	/**
	 * 批量插入
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static boolean insertBatch(List<Object> list) throws Exception{
		if (null == list || list.isEmpty()){
			return false;
		}
		
		Class objectClass = list.get(0).getClass();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = getInsertSql(list.get(0));
			List<String> fieldNameList = (List<String>)Arrays.asList(sql.substring(sql.indexOf("(") + 1, sql.indexOf(")")).split(","));
			
			pstmt = conn.prepareStatement(sql);
			Class cls = null;
			Method[] methods = objectClass.getMethods();
			String fieldName = "";
			StringBuffer psBuffer = new StringBuffer();
			int i = 0;
			for (Object object : list) {
				for (Method method : methods) {
					if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
						fieldName = getAnnotationName(objectClass, method.getName().substring(3));
						if (!"".equals(fieldName)){
							// 没有设置值的字段不需要插入，数据库自动插入默认值
							if (null != method.invoke(object)){
								i = fieldNameList.indexOf(fieldName) + 1;
								cls = method.getReturnType();
								setPreparedStatement(psBuffer, cls, pstmt, i, object, method);
							}
						}
					}
				}
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			pstmt.clearParameters();
			pstmt.clearBatch();
		} finally {
			close(rs, pstmt, conn);
		}
		return true;
	}
	
	/**
	 * 根据类获取插入sql
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	protected static String getInsertSql(Object object) throws Exception{
		Class objectClass = object.getClass();
		Method[] methods = objectClass.getMethods();
		StringBuffer sqlBuffer = new StringBuffer("INSERT INTO ");
		String tableName = getTableName(objectClass);
		if (tableName.trim().indexOf(" ") > 0){
			tableName = tableName.substring(0, tableName.indexOf(" "));
		}
		
		if (StringUtil.isNotEmptyAndNotNull(tableName)){
			sqlBuffer.append(tableName).append("(");
		}
		
		String fieldName = null;
		int i = 0;
		for (Method method : methods) {
			if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
				fieldName = getAnnotationName(objectClass, method.getName().substring(3));
				if (!"".equals(fieldName)){
					// 去除笔名
					if (fieldName.trim().indexOf(".") > 0){
						fieldName = fieldName.substring(fieldName.indexOf(".") + 1);
					}
					// 没有设置值的字段不需要插入，数据库自动插入默认值
					if (null != method.invoke(object)){
						sqlBuffer.append(fieldName).append(",");
						i ++;
					}
				}
			}
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length() - 1).append(") values(");
		
		for (int j = 0; j < i; j++) {
			sqlBuffer.append("?,");
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length() - 1).append(")");
		return sqlBuffer.toString();
	}
	
	/**
	 * 更新
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static boolean update(Object object, String conditionSql, List<Object> params) throws Exception{
		Class objectClass = object.getClass();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = getUpdateSql(object, conditionSql);
			String setSql = sql.substring(sql.indexOf("SET ") + 4, sql.lastIndexOf("?") + 1);
			String[] fields = setSql.split(",");
			List<String> fieldNameList = new ArrayList<String>();
			for (String field : fields) {
				fieldNameList.add(field.split("=")[0]);
			}
			
			pstmt = conn.prepareStatement(sql);
			Class cls = null;
			Method[] methods = objectClass.getMethods();
			String fieldName = "";
			StringBuffer psBuffer = new StringBuffer();
			int i = 0;
			int index = 0;
			for (Method method : methods) {
				if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
					fieldName = getAnnotationName(objectClass, method.getName().substring(3));
					if (!"".equals(fieldName)){
						// 没有设置值的字段不需要插入，数据库自动插入默认值
						if (null != method.invoke(object)){
							i = fieldNameList.indexOf(fieldName) + 1;
							cls = method.getReturnType();
							setPreparedStatement(psBuffer, cls, pstmt, i, object, method);
							index ++;
//							System.out.println(psBuffer);
						}
					}
				}
			}
			setParams(pstmt, index, params);
//			System.out.println(psBuffer);
			if(pstmt.executeUpdate()>0){
				return true;
			}
			pstmt.clearParameters();
		} finally {
			close(rs, pstmt, conn);
		}
		return false;
	}
	
	/**
	 * 批量更新
	 * @param list set部分的？
	 * @param conditionSql where后面的条件
	 * @param parameters where部分的？
	 * @return
	 * @throws Exception
	 */
	public static boolean updateBatch(List<Object> list, String conditionSql, List<List<Object>> parameters) throws Exception{
		if (null == list || list.isEmpty()){
			return false;
		}
		
		Class objectClass = list.get(0).getClass();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = getUpdateSql(list.get(0), conditionSql);
			String setSql = sql.substring(sql.indexOf("SET ") + 4, sql.lastIndexOf("?") + 1);
			String[] fields = setSql.split(",");
			List<String> fieldNameList = new ArrayList<String>();
			for (String field : fields) {
				fieldNameList.add(field.split("=")[0]);
			}
			
			pstmt = conn.prepareStatement(sql);
			Class cls = null;
			Method[] methods = objectClass.getMethods();
			String fieldName = "";
			StringBuffer psBuffer = new StringBuffer();
			int i = 0;
			int index = 0;
			for (int j = 0; j < list.size(); j++) {
				Object object = list.get(j);
				for (Method method : methods) {
					if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
						fieldName = getAnnotationName(objectClass, method.getName().substring(3));
						if (!"".equals(fieldName)){
							// 没有设置值的字段不需要插入，数据库自动插入默认值
							if (null != method.invoke(object)){
								i = fieldNameList.indexOf(fieldName) + 1;
								cls = method.getReturnType();
								setPreparedStatement(psBuffer, cls, pstmt, i, object, method);
								index ++;
	//							System.out.println(psBuffer);
							}
						}
					}
				}
				
				// 动态条件参数
				if (null != parameters && parameters.size() > j){
					List parameterList = parameters.get(j);
					i = index;
					for (int k = 0; k < parameterList.size(); k++) {
						i ++;
						Object parameter = parameterList.get(k);
						Class class1 = parameter.getClass();
						if (SqlConstant.NULL == parameter){
							pstmt.setNull(i, Types.NULL);
							psBuffer.append("i=" + i + ", value=null").append(StringConstant.LINE_SEPARATOR);
						}else if (String.class == class1){
							pstmt.setString(i , (String)parameter);
							psBuffer.append("i=" + i + ", value=" + (String)parameter).append(StringConstant.LINE_SEPARATOR);
						}else if (Integer.class == class1 || int.class == class1){
							pstmt.setInt(i , (Integer)parameter);
							psBuffer.append("i=" + i + ", value=" + (Integer)parameter).append(StringConstant.LINE_SEPARATOR);
						}else if (Double.class == class1 || double.class == class1){
							pstmt.setDouble(i , (Double)parameter);
							psBuffer.append("i=" + i + ", value=" + (Double)parameter).append(StringConstant.LINE_SEPARATOR);
						}else if (Float.class == class1 || float.class == class1){
							pstmt.setFloat(i , (Float)parameter);
							psBuffer.append("i=" + i + ", value=" + (Float)parameter).append(StringConstant.LINE_SEPARATOR);
						}else if (Long.class == class1 || long.class == class1){
							pstmt.setLong(i , (Long)parameter);
							psBuffer.append("i=" + i + ", value=" + (Long)parameter).append(StringConstant.LINE_SEPARATOR);
						}else if (Date.class == class1 || Timestamp.class == class1){
							psBuffer.append("i=" + i + ", value=" + DateUtil.convertUtilDateToTimestamp((Date)parameter)).append(StringConstant.LINE_SEPARATOR);
							pstmt.setTimestamp(i, DateUtil.convertUtilDateToTimestamp((Date)parameter));
						}else if (BigDecimal.class == class1){
							psBuffer.append("i=" + i + ", value=" + (BigDecimal)parameter).append(StringConstant.LINE_SEPARATOR);
							pstmt.setBigDecimal(i, (BigDecimal)parameter);
						}
					}
					
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			pstmt.clearParameters();
			pstmt.clearBatch();
		} finally {
			close(rs, pstmt, conn);
		}
		return true;
	}
	
	/**
	 * 根据类获取更新sql
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	private static String getUpdateSql(Object object, String conditionSql) throws Exception{
		Class objectClass = object.getClass();
		Method[] methods = objectClass.getMethods();
		StringBuffer sqlBuffer = new StringBuffer("UPDATE ");
		String tableName = getTableName(objectClass);
		if (StringUtil.isNotEmptyAndNotNull(tableName)){
			sqlBuffer.append(tableName).append(" SET ");
		}
		
		String fieldName = null;
		for (Method method : methods) {
			if (method.getName().startsWith("get") && !"getClass".equals(method.getName())){
				fieldName = getAnnotationName(objectClass, method.getName().substring(3));
				if (!"".equals(fieldName)){
					// 没有设置值的字段不需要插入，数据库自动插入默认值
					if (null != method.invoke(object)){
						sqlBuffer.append(fieldName).append("=?,");
					}
				}
			}
		}
		sqlBuffer.deleteCharAt(sqlBuffer.length() - 1).append(" ");
		
		if (null != conditionSql){
			if (conditionSql.trim().toLowerCase().startsWith("where")){
				sqlBuffer.append(conditionSql.trim());
			}else {
				sqlBuffer.append(" where 1=1 ");
				if (!conditionSql.trim().toLowerCase().startsWith("and")){
					sqlBuffer.append(" and ");
				}
				sqlBuffer.append(conditionSql.trim());
			}
		}
		return sqlBuffer.toString();
	}
	
	/**
	 * 设置PreparedStatement参数值
	 * @param psBuffer 生成参数值字符串
	 * @param cls 字段返回类型
	 * @param pstmt PreparedStatement
	 * @param i 设置第几个参数
	 * @param object 对象
	 * @param method getField
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	private static void setParams(StringBuffer psBuffer, Class cls, List<Object> params, int i, Object object, Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException{
		if (SqlConstant.NULL == method.invoke(object)){
			params.add(i, null);
			psBuffer.append("i=" + i + ", value=null").append(StringConstant.LINE_SEPARATOR);
		}else if (Date.class == cls || Timestamp.class == cls){
			psBuffer.append("i=" + i + ", value=" + DateUtil.convertUtilDateToTimestamp((Date)method.invoke(object))).append(StringConstant.LINE_SEPARATOR);
			params.add(i, DateUtil.convertUtilDateToTimestamp((Date)method.invoke(object)));
		}else {
			psBuffer.append("i=" + i + ", value=" + method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
			params.add(i, method.invoke(object));
		}
	}
	
	/**
	 * 设置PreparedStatement参数值
	 * @param psBuffer 生成参数值字符串
	 * @param cls 字段返回类型
	 * @param pstmt PreparedStatement
	 * @param i 设置第几个参数
	 * @param object 对象
	 * @param method getField
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 */
	private static void setPreparedStatement(StringBuffer psBuffer, Class cls, PreparedStatement pstmt, int i, Object object, Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SQLException{
		if (SqlConstant.NULL == method.invoke(object)){
			pstmt.setNull(i, Types.NULL);
			psBuffer.append("i=" + i + ", value=null").append(StringConstant.LINE_SEPARATOR);
		}else if (String.class == cls){
			pstmt.setString(i , (String)method.invoke(object));
			psBuffer.append("i=" + i + ", value=" + (String)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
		}else if (Integer.class == cls || int.class == cls){
			pstmt.setInt(i , (Integer)method.invoke(object));
			psBuffer.append("i=" + i + ", value=" + (Integer)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
		}else if (Double.class == cls || double.class == cls){
			pstmt.setDouble(i , (Double)method.invoke(object));
			psBuffer.append("i=" + i + ", value=" + (Double)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
		}else if (Float.class == cls || float.class == cls){
			pstmt.setFloat(i , (Float)method.invoke(object));
			psBuffer.append("i=" + i + ", value=" + (Float)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
		}else if (Long.class == cls || long.class == cls){
			pstmt.setLong(i , (Long)method.invoke(object));
			psBuffer.append("i=" + i + ", value=" + (Long)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
		}else if (Date.class == cls || Timestamp.class == cls){
			psBuffer.append("i=" + i + ", value=" + DateUtil.convertUtilDateToTimestamp((Date)method.invoke(object))).append(StringConstant.LINE_SEPARATOR);
			pstmt.setTimestamp(i, DateUtil.convertUtilDateToTimestamp((Date)method.invoke(object)));
		}else if (BigDecimal.class == cls){
			psBuffer.append("i=" + i + ", value=" + (BigDecimal)method.invoke(object)).append(StringConstant.LINE_SEPARATOR);
			pstmt.setBigDecimal(i, (BigDecimal)method.invoke(object));
		}
	}
	
	/**
	 * 删除
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	public static boolean delete(Class objectClass, String conditionSql, List<Object> params) throws Exception{
		Connection conn = getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn
					.prepareStatement(getDeleteSql(objectClass, conditionSql));
			setParams(statement, params);
			statement.execute();
		} 
		finally {
			close(statement, conn);
		}
		return true;
	}
	
	/**
	 * 根据类获取更新sql
	 * @param objectClass
	 * @param conditionSql
	 * @return
	 * @throws Exception
	 */
	private static String getDeleteSql(Class objectClass, String conditionSql) throws Exception{
		Method[] methods = objectClass.getMethods();
		StringBuffer sqlBuffer = new StringBuffer("DELETE FROM ");
		String tableName = getTableName(objectClass);
		if (tableName.trim().indexOf(" ") > 0){
			tableName = tableName.substring(0, tableName.indexOf(" "));
		}
		if (StringUtil.isNotEmptyAndNotNull(tableName)){
			sqlBuffer.append(tableName);
		}
		
		if (null != conditionSql){
			if (conditionSql.trim().toLowerCase().startsWith("where")){
				sqlBuffer.append(conditionSql.trim());
			}else {
				sqlBuffer.append(" where 1=1 ");
				if (!conditionSql.trim().toLowerCase().startsWith("and")){
					sqlBuffer.append(" and ");
				}
				sqlBuffer.append(conditionSql.trim());
			}
		}
		return sqlBuffer.toString();
	}
	
	/**
	 * 获取表名
	 * @param objectClass
	 * @return
	 * @throws Exception
	 */
	private static String getTableName(Class objectClass) throws Exception {
		DBTable dbTable = (DBTable)objectClass.getAnnotation(DBTable.class);
		if (null != dbTable) {
			return dbTable.name();
		}
		return "";
	}
	
	/**
	 * 获取数据库字段名称
	 * @param objectClass
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws Exception
	 */
	private static String getDBFieldName(Class objectClass, String fieldName) throws SecurityException, NoSuchFieldException{
		String dbFieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
		Field field = null;
		Class superClass = objectClass.getSuperclass();
		if (null != superClass){
			Map<String, Field> map = new HashMap<String, Field>();
			for (Field field2 : objectClass.getDeclaredFields()) {
				map.put(field2.getName(), field2);
			}
			for (Field field2 : superClass.getDeclaredFields()) {
				map.put(field2.getName(), field2);
			}
			
			field = map.get(dbFieldName);
		}else {
			field = objectClass.getDeclaredField(dbFieldName);
		}
		
		if (null != field){
			DBField dbField = field.getAnnotation(DBField.class);
			if (null != dbField){
				return dbField.name();
			}
		}
		return dbFieldName;
	}
	
	/**
	 * 获取数据库字段名称
	 * @param objectClass
	 * @param fieldName
	 * @return
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws Exception
	 */
	protected static String getAnnotationName(Class objectClass, String fieldName) throws SecurityException, NoSuchFieldException{
		String dbFieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
		Field field = null;
		Class superClass = objectClass.getSuperclass();
		if (null != superClass){
			Map<String, Field> map = new HashMap<String, Field>();
			for (Field field2 : objectClass.getDeclaredFields()) {
				map.put(field2.getName(), field2);
			}
			for (Field field2 : superClass.getDeclaredFields()) {
				map.put(field2.getName(), field2);
			}
			
			field = map.get(dbFieldName);
		}else {
			field = objectClass.getDeclaredField(dbFieldName);
		}
		
		if (null != field){
			DBField dbField = field.getAnnotation(DBField.class);
			if (null != dbField){
				return dbField.name();
			}
		}
		return "";
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	protected static Connection getConnection() throws SQLException{
		return DBManager.getDBConnection().getConn();
	}
	
	/**
     * 根据SQL语句查询对象集合
     * @param objectClass
     * @param sql
     * @return List
     * @throws Exception
     */
    public static List getListBySql(ResultSetInterface set, String sql, List<Object> paraList) throws Exception{
    	 List list = null;
         Connection conn = null;
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         try {
             conn = getConnection();
             pstmt = conn.prepareStatement(sql);
             if (paraList != null)
             {
                 int i = 0;
                 for(Object obj : paraList)
                 {
                     i++;
                     if (String.class == obj.getClass()){
                         pstmt.setString(i , (String)obj);
                     }else if (Integer.class == obj.getClass() || int.class == obj.getClass()){
                         pstmt.setInt(i, (Integer)obj);
                     }else if (Double.class == obj.getClass() || double.class == obj.getClass()){
                         pstmt.setDouble(i , (Double)obj);
                     }else if (Float.class == obj.getClass() || float.class == obj.getClass()){
                         pstmt.setFloat(i , (Float)obj);
                     }else if (Long.class == obj.getClass() || long.class == obj.getClass()){
                         pstmt.setLong(i , (Long)obj);
                     }else if (Date.class == obj.getClass() || Timestamp.class == obj.getClass()){
                         pstmt.setDate(i , (java.sql.Date)obj);
                     }else if (BigDecimal.class == obj.getClass()){
                         pstmt.setBigDecimal(i , (BigDecimal)obj);
                     }
                 }
             }
             rs = pstmt.executeQuery();
             if (null != rs) {
                 list = new ArrayList();
                 while (rs.next()) {
                	 list.add(set.getObject(rs));
                 }
             }
             pstmt.clearParameters();
         } finally {
             close(rs, pstmt, conn);
         }
         return list;
    }
    
	/**
     * 根据SQL语句查询对象有注解的属性的值得集合
     * @param objectClass
     * @param sql
     * @return List
     * @throws Exception
     */
    public static List getListBySqlAnnotation(Class objectClass, String sql) throws Exception{
        return getListBySqlAnnotation(objectClass, sql, null);
    }
    
    /**
     * 根据SQL语句查询对象有注解的属性的值得集合
     * @param objectClass
     * @param sql
     * @return List
     * @throws Exception
     */
    public static List getListBySqlAnnotation(Class objectClass, String sql, List<Object> paraList) throws Exception{
        List list = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            if (paraList != null)
            {
                int i = 0;
                for(Object obj : paraList)
                {
                    i++;
                    if (String.class == obj.getClass()){
                        pstmt.setString(i , (String)obj);
                    }else if (Integer.class == obj.getClass() || int.class == obj.getClass()){
                        pstmt.setInt(i, (Integer)obj);
                    }else if (Double.class == obj.getClass() || double.class == obj.getClass()){
                        pstmt.setDouble(i , (Double)obj);
                    }else if (Float.class == obj.getClass() || float.class == obj.getClass()){
                        pstmt.setFloat(i , (Float)obj);
                    }else if (Long.class == obj.getClass() || long.class == obj.getClass()){
                        pstmt.setLong(i , (Long)obj);
                    }else if (Date.class == obj.getClass() || Timestamp.class == obj.getClass()){
                        pstmt.setDate(i , (java.sql.Date)obj);
                    }else if (BigDecimal.class == obj.getClass()){
                        pstmt.setBigDecimal(i , (BigDecimal)obj);
                    }
                }
            }
            rs = pstmt.executeQuery();
            if (null != rs) {
                list = new ArrayList();
                Object object = null;
                String fieldName = null;
                Class cls = null;
                while (rs.next()) {
                    object = objectClass.newInstance(); 
                    Method[] methods = objectClass.getMethods();
                    for (Method method : methods) {
                        if (method.getName().startsWith("set")){
                            fieldName = getAnnotationName(objectClass, method.getName().substring(3));
                            if (!"".equals(fieldName)){
                                cls = method.getParameterTypes()[0];
                                setField(cls, fieldName, object, method, rs);
                            }
                        }
                    }
                    list.add(object);
                }
            }
            pstmt.clearParameters();
        } finally {
            close(rs, pstmt, conn);
        }
        return list;
    }
    
    private static void setParams(PreparedStatement pstmt, List<Object> params) throws SQLException{
    	if (params != null && !params.isEmpty()){
            int i = 0; 
            Class cls = null;
            for(Object obj : params){
            	i ++;
            	cls = obj.getClass();
            	if (SqlConstant.NULL == obj){
        			pstmt.setNull(i, Types.NULL);
        		}else if (String.class == cls){
        			pstmt.setString(i , (String)obj);
        		}else if (Integer.class == cls || int.class == cls){
        			pstmt.setInt(i , (Integer)obj);
        		}else if (Double.class == cls || double.class == cls){
        			pstmt.setDouble(i , (Double)obj);
        		}else if (Float.class == cls || float.class == cls){
        			pstmt.setFloat(i , (Float)obj);
        		}else if (Long.class == cls || long.class == cls){
        			pstmt.setLong(i , (Long)obj);
        		}else if (Date.class == cls || Timestamp.class == cls){
        			pstmt.setTimestamp(i, DateUtil.convertUtilDateToTimestamp((Date)obj));
        		}else if (BigDecimal.class == cls){
        			pstmt.setBigDecimal(i, (BigDecimal)obj);
        		}
            }
        }
    }
    
    private static void setParams(PreparedStatement pstmt, int i, List<Object> params) throws SQLException{
    	if (params != null && !params.isEmpty()){
            Class cls = null;
            for(Object obj : params){
            	i ++;
            	cls = obj.getClass();
            	if (SqlConstant.NULL == obj){
        			pstmt.setNull(i, Types.NULL);
        		}else if (String.class == cls){
        			pstmt.setString(i , (String)obj);
        		}else if (Integer.class == cls || int.class == cls){
        			pstmt.setInt(i , (Integer)obj);
        		}else if (Double.class == cls || double.class == cls){
        			pstmt.setDouble(i , (Double)obj);
        		}else if (Float.class == cls || float.class == cls){
        			pstmt.setFloat(i , (Float)obj);
        		}else if (Long.class == cls || long.class == cls){
        			pstmt.setLong(i , (Long)obj);
        		}else if (Date.class == cls || Timestamp.class == cls){
        			pstmt.setTimestamp(i, DateUtil.convertUtilDateToTimestamp((Date)obj));
        		}else if (BigDecimal.class == cls){
        			pstmt.setBigDecimal(i, (BigDecimal)obj);
        		}
            }
        }
    }
}
