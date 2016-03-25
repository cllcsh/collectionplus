package com.osource.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* Title:日期处理工具 </p>
* Description:对日期的相关操作 </p>
* Copyright: Copyright (c) 2003 </p>
* @version 1.0
*/
public class DateUtilString {

/**
* 得到当前的日期字符串，方便存放文件夹
* @param date 日期对象
* @return 返回日期字符串
*/
public static final String getDateString(Date date){
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy-MM-dd");
return formattxt.format(date);
}


/**
* 得到日期/时间字符串
* @param date 日期对象
* @return 返回日期/时间字符串
*/
public static final String getDateTimeString(Date date){
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return formattxt.format(date);
}

/**
* 得到当前日期/时间字符串
* @return 返回日期/时间字符串
*/
public static final String getNowDateTimeString(){
Date date=new Date();
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return formattxt.format(date);
}

/**
* 得到当前日期字符串
* @return 返回当前日期字符串
*/
public static final String getNowDateString(){
Date date=new Date();
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy-MM-dd");
return formattxt.format(date);
}

/**
* 得到日期对象
* @param dateTimeString 日期字符串
* @return 返回日期对象
*/
public static final Date getDate(String dateTimeString){
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date date = null;
try{
date = formattxt.parse(dateTimeString);
}catch(Exception e){
System.out.println(e.getMessage());
date = new Date();
}
return date;
}

/**
*得到上一年的日期字符串
* @param date 日期对象
* @return 上一年的日期字符串
*/
public static final String getLastYearString(Date date){
Calendar lastYear = Calendar.getInstance();
lastYear.setTime(date);
lastYear.add(Calendar.YEAR,-1);
return DateUtilString.getDateString(lastYear.getTime());
}

/**
*得到上一月的日期字符串
* @param date 日期对象
* @return 上一月的日期字符串
*/
public static final String getLastMonthString(Date date){
Calendar lastMonth = Calendar.getInstance();
lastMonth.setTime(date);
lastMonth.add(Calendar.MONTH,-1);
return DateUtilString.getDateString(lastMonth.getTime());
}

/**
*得到上一周的日期字符串
* @param date 日期对象
* @return 上一月周日期字符串
*/
public static final String getLastWeekString(Date date){
Calendar lastWeek = Calendar.getInstance();
lastWeek.setTime(date);
lastWeek.add(Calendar.HOUR_OF_DAY ,-168);
return DateUtilString.getDateString(lastWeek.getTime());
}

/**
*得到昨天的日期字符串
* @param date 日期对象
* @return 昨天的日期字符串
*/
public static final String getYesterdayString(Date date){
Calendar yesterday = Calendar.getInstance();
yesterday.setTime(date);
yesterday.add(Calendar.HOUR_OF_DAY ,-24);
return DateUtilString.getDateString(yesterday.getTime());
}

/**
* 得到当前日期字符串，没有连接线的
* @return 返回当前日期字符串
*/
public static final String getNoLineNowDateString(){
Date date=new Date();
SimpleDateFormat formattxt=new SimpleDateFormat("yyyyMMdd");
return formattxt.format(date);
}

/**
* 取得当时的时间戳。年（四位）_月（两位）_日（两位）_时（两位）_分（两位）_秒（两位）_毫秒（三位）
* @return 当时的时间戳
*/
public static final String getNowTimeStamp(){
Date date=new Date();
SimpleDateFormat formattxt=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
return formattxt.format(date);

}
public static void main(String[] args){
System.out.println(getNowTimeStamp());
}
}

