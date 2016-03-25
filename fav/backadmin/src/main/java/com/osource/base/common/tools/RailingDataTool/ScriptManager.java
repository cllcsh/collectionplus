package com.osource.base.common.tools.RailingDataTool;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.osource.common.developer.DBManager;


public class ScriptManager {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new ScriptQueue()).start();
		List<String> list = getAreaCodeList();
		for(String code : list){
			ScriptQueue.add(code);
		}
		

	}

	private static List getAreaCodeList(){
		List areaCodeList = new ArrayList<String>();
		
		try {
			Connection conn = DBManager.getConnection();//取得数据库的连接
			Statement sql = conn.createStatement();//创建一个声明，用来执行sql语句
			ResultSet rs = (ResultSet) sql.executeQuery("SELECT id from tb_area");
			
			while(rs.next()){
				areaCodeList.add(rs.getString("id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return areaCodeList;
	}
}
