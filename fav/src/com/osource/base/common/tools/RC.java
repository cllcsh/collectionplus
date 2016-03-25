package com.osource.base.common.tools;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 该类用于获取行政区域围栏数据（单线程模式）
 * 
 * @author Fun
 * @date 2010-6-28
 *
 */
public class RC {
	@SuppressWarnings("unused")
	private static String filename = "osource/resource/map/railingData2.js";//写到一个文件时，围栏数据写入的文件名
	private static String filepath = "osource/module/map/";//写到多个文件时，围栏数据写入的文件路径
	private static String remoteUrl = "http://202.102.112.26:8082/map.aspx?userinfo=sifa%20%20%20%20sifa%20%20%20%20&command=1";
	
	
	 
	public static void main(String[] args) {
		//writeRailingInfoToSingleFile(filename, remoteUrl);//写入单个文件
		writeRailingInfoToMultiFile(filepath,remoteUrl);//写入多个文件
	}
	
	/**
	 * 说明：该类获得的围栏数据写入多个文件，单线程模式，每个行政区域围栏对应一个文件
	 * @param filename
	 * @param remoteUrl
	 */
	private static void writeRailingInfoToMultiFile(String filepath,String remoteUrl){
		
		StringBuffer sb = new StringBuffer("");
		
		List<String> areaCodeList = getAreaCodeList();
//		List<String> areaCodeList = new ArrayList();
//		areaCodeList.add("110103");
//		areaCodeList.add("110104");
		
		Long startTime = System.currentTimeMillis();
		System.out.println("共有数据 "+areaCodeList.size()+" 条，开始时间："+new Date());
		
		for(int i=0;i<areaCodeList.size();i++){
			String areaCode = areaCodeList.get(i);
			String url = remoteUrl+"&AreaCode="+areaCode;
			String sourceHtml = getRemoteHtml(url);
			
			System.out.println("现在为第 "+(i+1)+" 条，区域编码为："+areaCode);
			
			String[] cpStrArr = getCenterPointInfo(sourceHtml);//中心点和缩放比例
			String[] areaXYArr = getAreaXY(sourceHtml);//区域围栏x,y值
			
			sb.append("AreaData.A"+areaCode+"={");
			sb.append("id:'"+areaCode+"',");
			
			sb.append("xs:"+areaXYArr[0]);
			if(areaXYArr[0] == null)//正则表达式没匹配到的情况
				sb.append(",");
			
			sb.append("ys:"+areaXYArr[1]);
			if(areaXYArr[1] == null)//正则表达式没匹配到的情况
				sb.append(",");
			
			sb.append("xCenter:"+cpStrArr[0]+",");
			sb.append("yCenter:"+cpStrArr[1]+",");
			sb.append("mapScale:"+cpStrArr[2]+"}");
			
			String filename = filepath+"A"+areaCode+".js";
			File file = new File(filename);
			writeToFile(file,sb.toString(),false);//写入文件
			
			sb.delete(0,sb.length());//清空对象，为下一次迭代做准备
			
		}
		System.out.println("数据写入成功！共耗时 "+(System.currentTimeMillis()-startTime)/(1000*60)+" 分钟");
		//writeToFile(file,sb.toString());
		
	}

	/**
	 * 说明：该类获得的围栏数据写入单个文件，单线程模式，文件较大，有40M左右，运行耗时253分钟
	 * @param filename
	 * @param remoteUrl
	 */
	@SuppressWarnings("unused")
	private static void writeRailingInfoToSingleFile(String filename,String remoteUrl){
		File file = new File(filename);
		
		//String content = "var AreaData = {";
		StringBuffer sb = new StringBuffer("var AreaData = {");
		
		List<String> areaCodeList = getAreaCodeList();
//		List<String> areaCodeList = new ArrayList();
//		areaCodeList.add("110101");
//		areaCodeList.add("110102");
		
		Long startTime = System.currentTimeMillis();
		System.out.println("共有数据 "+areaCodeList.size()+" 条，开始时间："+new Date());
		
		for(int i=0;i<areaCodeList.size();i++){
			String areaCode = areaCodeList.get(i);
			String url = remoteUrl+"&AreaCode="+areaCode;
			String sourceHtml = getRemoteHtml(url);
			
			System.out.println("现在为第 "+(i+1)+" 条，区域编码为："+areaCode);
			
			String[] cpStrArr = getCenterPointInfo(sourceHtml);//中心点和缩放比例
			String[] areaXYArr = getAreaXY(sourceHtml);//区域围栏x,y值
			/*String content = "A"+areaCode+":{"
							+"id:'"+areaCode+"',"
							+"xs:"+areaXYArr[0]+","
							+"ys:"+areaXYArr[1]+","
							+"xCenter:"+cpStrArr[0]+","
							+"yCenter:"+cpStrArr[1]+","
							+"mapScale:"+cpStrArr[2]+"},";*/
			
			sb.append("A"+areaCode+":{");
			sb.append("id:'"+areaCode+"',");
			
			sb.append("xs:"+areaXYArr[0]);
			if(areaXYArr[0] == null)//正则表达式没匹配到的情况
				sb.append(",");
			
			sb.append("ys:"+areaXYArr[1]);
			if(areaXYArr[1] == null)//正则表达式没匹配到的情况
				sb.append(",");
			
			sb.append("xCenter:"+cpStrArr[0]+",");
			sb.append("yCenter:"+cpStrArr[1]+",");
			sb.append("mapScale:"+cpStrArr[2]+"}");
			
			if(i != (areaCodeList.size()-1))
				sb.append(",");
			else
				sb.append("};");
			sb.append("\r\n");
			
			writeToFile(file,sb.toString(),true);//写入文件
			
			sb.delete(0,sb.length());//清空对象，为下一次迭代做准备
			
		}
		System.out.println("数据写入成功！共耗时 "+(System.currentTimeMillis()-startTime)/(1000*60)+" 分钟");
		//writeToFile(file,sb.toString());
		
	}
	
	public static void writeToFile(File file,String content, Boolean isAppended){
		FileWriter fw=null; 
		BufferedWriter bw=null;
		
		try{
			fw = new FileWriter(file, isAppended); 
			bw = new BufferedWriter(fw); 
			bw.write(content); 
			bw.newLine(); 
			bw.flush(); 
			bw.close(); 

		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{ 
			bw = null; 
			fw = null; 
		} 
		
	}
	public static List getAreaCodeList(){
		List areaCodeList = new ArrayList<String>();
/*		
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
*/
		areaCodeList.add("451301");
		return areaCodeList;
	}
		
	public static String getRemoteHtml(String url){
		String source = "";//用来保存返回的html页面内容
		
		//构造HttpClient的实例 　　
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例 　　
		GetMethod getMethod = new GetMethod(url); 
		//使用系统提供的默认的恢复策略 　　
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler()); 
		
		try {//执行getMethod 　　
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine()); 
			}
			//读取内容 　　
			byte[] responseBody = getMethod.getResponseBody(); 
			source = new String(responseBody);
			
			//处理内容 　　
			//System.out.println(source); 
			return source;
			
		} catch (HttpException e) { //发生致命的异常，可能是协议不对或者返回的内容有问题 　　
			System.out.println("Please check your provided http address!"); 
			e.printStackTrace(); 
		} catch (IOException e) { //发生网络异常 　　
			e.printStackTrace();
		} finally { //释放连接 　　
			getMethod.releaseConnection();
		}	
		
		return source;
	}
	
	private static String[] getAreaXY(String source){
		String[] areaXYArr = new String[2];
		
		String regx = "<input(.*?)id=\"MapControl1_hiddenPolygons\"(.*?)xs(.*?)ys(.*?)strokeWeight(.*?)/>";
		Pattern pattern = Pattern.compile(regx);
	    Matcher matcher = pattern.matcher(source);

	    while (matcher.find()) {
//	    	String s3 = matcher.group(0);
//		    String s4 = matcher.group(1);
//		    String s5 = matcher.group(2);
	    	String xs = matcher.group(3);
	        String ys = matcher.group(4);
	        
	        xs = xs.replaceAll("&quot;", "");
	        xs = xs.replaceAll(":", "");
	        //xs = xs.replaceAll(",", "");
	       
	        ys = ys.replaceAll("&quot;", "");
	        ys = ys.replaceAll(":", "");
	        //ys = ys.replaceAll(",", "");
	        	        
	        areaXYArr[0] = xs;
	        areaXYArr[1] = ys;
	        
	        
	        System.out.println("xs:"+xs);
	        System.out.println("ys:"+ys);
//	        System.out.println("s3:"+s3);
//	        System.out.println("s4:"+s4);
//	        System.out.println("s5:"+s5);
	    }
	    
	    return areaXYArr;
	}
	
	private static String[] getCenterPointInfo(String source){
		String[] cpStrArr = new String[3];
		
		String regx = "<input(.*?)id=\"MapControl1_hiddenMapParam\"(.*?)x(.*?)y(.*?)mapScale(.*?),(.*?)/>";
		Pattern pattern = Pattern.compile(regx);
	    Matcher matcher = pattern.matcher(source);

	    while (matcher.find()) {
//	    	String s3 = matcher.group(0);
//		    String s4 = matcher.group(1);
//		    String s5 = matcher.group(2);
	    	String xCenter = matcher.group(3);
	        String yCenter = matcher.group(4);
	        String mapScale = matcher.group(5);
	       
	        
	        xCenter = xCenter.replaceAll("&quot;", "");
	        xCenter = xCenter.replaceAll(":", "");
	        xCenter = xCenter.replaceAll(",", "");
	       
	        yCenter = yCenter.replaceAll("&quot;", "");
	        yCenter = yCenter.replaceAll(":", "");
	        yCenter = yCenter.replaceAll(",", "");
	        yCenter = yCenter.replaceAll("}", "");
	        
	        mapScale = mapScale.replaceAll("&quot;", "");
	        mapScale = mapScale.replaceAll(":", "");
	        mapScale = mapScale.replaceAll(",", "");
	        
	        cpStrArr[0] = xCenter;
	        cpStrArr[1] = yCenter;
	        cpStrArr[2] = mapScale;
	        
	        
	        System.out.println("xCenter:"+xCenter);
	        System.out.println("yCenter:"+yCenter);
	        System.out.println("mapScale:"+mapScale);
//	        System.out.println("s3:"+s3);
//	        System.out.println("s4:"+s4);
//	        System.out.println("s5:"+s5);
	    }
	    
	    return cpStrArr;
	}
	
	
}
