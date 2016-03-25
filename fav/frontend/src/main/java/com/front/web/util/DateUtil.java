package com.front.web.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 日期工具类
 * @author gaoxiang
 *
 */
public final class DateUtil {
	
	/**
	  * 将Date型格式化yyyy-MM-dd HH:mm:ss String
	  * @param date
	  * @return String (yyyy-MM-dd HH:mm:ss)
	  */
	 public static String convertDateToString(Date date){
		  if (null == date){
			  date = new Date();
		  }
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String strDate = format.format(date);
		  return strDate;
	 }
	 
	 /**
      * 将Date型格式化
      * @param date
      * @return String 
      */
     public static String convertDateToString(Date date, String fromat){
          if (null == date){
              date = new Date();
          }
          SimpleDateFormat format = new SimpleDateFormat(fromat);
          String strDate = format.format(date);
          return strDate;
     }
	 
	/**
	  * 将Date型格式化yyyy-MM-dd String
	  * @param date
	  * @return String (yyyy-MM-dd)
	  */
	 public static String convertDateToyyyyMMdd(Date date){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  String strDate = format.format(date);
		  return strDate;
	 }
	 
	 /**
	  * 将Date型格式化yyyy-MM-dd String
	  * @param date
	  * @return String (yyyy-MM-dd)
	  */
	 public static String convertDateToyyyyMM(Date date){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		  String strDate = format.format(date);
		  return strDate;
	 }
	 
	 public static Date convertStrToyyyyMMddHHmmss(String dateStr){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 try {
			 return format.parse(dateStr);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return new Date();
	 }
	 
	 public static Date convertStrToyyyyMMddHHmm(String dateStr){
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         try {
             return format.parse(dateStr);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return new Date();
     }
	 
	 /**
	  * yyyyMMddHHmmss字符串转换成时间
	  * @param dateStr 格式为yyyyMMddHHmmss
	  * @return Date
	  */
	 public static Date convertyyyyMMddHHmmssToDate(String dateStr)
	 {
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
         try {
             return format.parse(dateStr);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return new Date();
     }
	 
	 /**
      * yyyyMMddHHmm字符串转换成时间
      * @param dateStr 格式为yyyyMMddHHmm
      * @return Date
      */
     public static Date convertyyyyMMddHHmmToDate(String dateStr)
     {
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
         try {
             return format.parse(dateStr);
         } catch (ParseException e) {
             e.printStackTrace();
         }
         return new Date();
     }
	 
	 public static Date convertStrToyyyyMMdd(String dateStr){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 return format.parse(dateStr);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return new Date();
	 }
	 
	 
	 /**
	  * 将Date型格式化yyyy-MM-dd HH:mm:ss String
	  * @param date
	  * @return String (yyyy-MM-dd HH:mm:ss)
	  */
	 public static Date convertSqlDateToUtilDate(java.sql.Date date){
		  return new Date(date.getTime());
	 }
	 
	 /**
	  * 将Date型格式化yyyy-MM-dd HH:mm:ss String
	  * @param date
	  * @return String (yyyy-MM-dd HH:mm:ss)
	  */
	 public static java.sql.Date convertUtilDateToSqlDate(Date date){
		  return new java.sql.Date(date.getTime());
	 }
	 
	 public static Timestamp convertUtilDateToTimestamp(Date date){
		  return new Timestamp(date.getTime());
	 }
}
