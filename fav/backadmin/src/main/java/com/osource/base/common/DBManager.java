/**
 * 
 */
package com.osource.base.common;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

/**
 * @author Fun
 *
 */
public class DBManager {
	private static Properties props = null;
	private static String filename = "jdbc.properties";
	
	public DBManager(){
		props = new Properties();
		try{
          ClassLoader cl = this.getClass().getClassLoader();
          InputStream fis = cl.getResourceAsStream(filename);
            
//			File file = new File(filename); 
//            FileInputStream fis = null; 
//           
//            /*输入流和属性文件关联*/ 
//            fis = new FileInputStream(file); 
            props.load(fis);
            if(fis != null)
            	fis.close();
        }catch(Exception e){
        	e.printStackTrace(); 
        }
	}
	
	public static String getProperty(String field){
		if(props == null)
			new DBManager();
		
        return props.getProperty(field);
    }
	
	public static Connection getConnection() throws SQLException, java.lang.ClassNotFoundException {
		if(props == null)
			new DBManager();
		
		//取得连接的url
		String url = props.getProperty("jdbc.url");
		//加载MySQL的jdbc驱动
		Class.forName(props.getProperty("jdbc.driver"));
		//使用能访问MySQL数据库的用户名root
		String userName = props.getProperty("jdbc.username");
		//使用口令
		String password = props.getProperty("jdbc.password");
		//打开数据库连接
		Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
  
		return conn;
	 }


}
