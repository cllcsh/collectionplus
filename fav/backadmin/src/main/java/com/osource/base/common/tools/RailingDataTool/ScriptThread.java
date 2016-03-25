package com.osource.base.common.tools.RailingDataTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class ScriptThread implements Runnable {
	
	
	private final String remoteUrl = "http://202.102.112.26:8089/map.aspx?userinfo=sifa%20%20%20%20sifa%20%20%20%20&command=1";
	private final String filepath = "osource/module/map/railingData/";
	private String areaCode;
	
	
	public static void main(String[] args){
		new Thread(new ScriptThread("110000")).start();
	}
	
	
	public ScriptThread(String areaCode){
		this.areaCode = areaCode;
	}
	

	public void run() {
		StringBuffer sb = new StringBuffer("");
		String url = remoteUrl+"&AreaCode="+areaCode;
		String sourceHtml = getRemoteHtml(url);
		
		String[] cpStrArr = getCenterPointInfo(sourceHtml);//中心点和缩放比例
		String[] areaXYArr = getAreaXY(sourceHtml);//区域围栏x,y值
		
		//拼js文件内容
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

	/**
	 * 把内容content写入文件file中
	 * @param file
	 * @param content
	 * @param isAppended
	 */
	private void writeToFile(File file,String content, Boolean isAppended){
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
	
	/**
	 * 获得url对应html页面源码
	 * @param url
	 * @return
	 */
	private String getRemoteHtml(String url){
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
	
	/**
	 * 获得行政区域围栏信息：xs,ys
	 * @param source
	 * @return
	 */
	private String[] getAreaXY(String source){
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
	
	/**
	 * 获得中心点信息：x,y和缩放比例
	 * @param source
	 * @return
	 */
	private String[] getCenterPointInfo(String source){
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
