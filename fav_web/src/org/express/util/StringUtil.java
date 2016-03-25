package org.express.util;

/**
 * 字符串工具函数
 * @author Rei Ayanami
 *
 */
public class StringUtil 
{
	/**
	 * 是否为Null或者空字符串
	 * @param s
	 * @return
	 */
	public static boolean isNullorEmpty(String s)
	{
		if(s == null || s.length() == 0)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * 是否为Null或者空字符串
	 * @param o
	 * @return
	 */
	public static boolean isNullorEmpty(Object o)
	{
		if(o == null || Convert.toString(o).length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 删除首尾空格
	 * @param s
	 * @return
	 */
	public static String Trim(String s)
	{
		if(isNullorEmpty(s))
		{
			return "";
		}
		else
		{
			return s.trim();
		}
	}
}
