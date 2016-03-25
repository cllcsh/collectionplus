package org.express.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * 类型转换
 * @author Rei Ayanami
 *
 */
public class Convert 
{
	/**
	 * 转字符串
	 * @param o
	 * @return
	 */
	public static String toString(Object o)
	{
		if(o instanceof String)
			return (String)o;
		
		if(o != null)
		{
			return String.valueOf(o);
		}
		
		return "";
	}
	
	/**
	 * 转码UTF8字符串
	 * @param o
	 * @return
	 */
	public static String toUTF8String(Object o) 
	{
		try {
			return new String(toString(o).getBytes("ISO-8859-1") , "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 转Int
	 * @param o
	 * @return
	 */
	public static Integer toInt(Object o)
	{
		if(o instanceof Integer)
			return (Integer)o;
		
		if(o != null && o.hashCode() != 0)
		{
			return Integer.parseInt(toString(o));
		}
		return 0;
	}
	
	/**
	 * 转Float
	 * @param o
	 * @return
	 */
	public static Float toFloat(Object o)
	{
		if(o instanceof Float)
			return (Float)o;
		
		if(o != null && o.hashCode() != 0)
		{
			return Float.parseFloat(toString(o));
		}
		
		return 0f;
	}
	
	/**
	 * 转Double
	 * @param o
	 * @return
	 */
	public static Double toDouble(Object o)
	{
		if(o instanceof Double)
			return (Double)o;
		
		if(o != null && o.hashCode() != 0)
		{
			return Double.parseDouble(toString(o));
		}
		
		return 0d;
	}
	
	/**
	 * 转Decimal
	 * @param o
	 * @return
	 */
	public static BigDecimal toDecimal(Object o)
	{
		if(o instanceof BigDecimal)
			return (BigDecimal)o;
		
		if(o != null && o.hashCode() != 0)
		{
			return new BigDecimal(toString(o));
		}
		
		return new BigDecimal(0);
	}
	
	/**
	 * 转Long
	 * @param o
	 * @return
	 */
	public static Long toLong(Object o)
	{
		if(o instanceof Long)
			return (Long)o;
		
		if(o != null && o.hashCode() != 0)
		{			
			return Long.parseLong(toString(o));
		}
		
		return 0L;
	}
	
	/**
	 * 转字节数组
	 * @param o
	 * @return
	 */
	public static byte[] toBytes(Object o)
	{
		byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(o);      
            oos.flush();         
            bytes = bos.toByteArray ();      
            oos.close();         
            bos.close();        
        } catch (IOException ex) 
        { 
            ex.printStackTrace();   
        }      
        return bytes;
	}
	
	/**
	 * 转Boolean
	 * @param o
	 * @return
	 * @throws NullPointerException
	 */
	public static Boolean toBoolean(Object o) throws NullPointerException
	{
		if(o instanceof Boolean)
			return (Boolean)o;
		
		if(o != null && o.hashCode() != 0)
		{
			String oo = toString(o).toLowerCase();
			if(oo.equals("1") || oo.equals("yes") || oo.equals("true"))
				return true;
			else if(oo.equals("0") || oo.equals("no") || oo.equals("false"))
				return false;
			else
				throw new NullPointerException("对象的值无法转换为布尔型 value=[" + oo + "]");
		}
		else
		{
			throw new NullPointerException("对象为空");
		}
	}
	
	/**
	 * 字符转字节数组
	 * @param ch
	 * @return
	 */
	public static byte[] charToByte(char ch) {
		int temp = (int) ch;
		byte[] b = new byte[2];
		// 将高8位放在b[0],将低8位放在b[1]
		for (int i = 1; i > -1; i--) {
			b[i] = (byte) (temp & 0xFF);// 低8位
			// 向右移8位
			temp >>= 8;
		}
		return b;
	}

	/**
	 *  将字节转化为比特数组
	 * @param b
	 * @return
	 */
	public static byte[] byteToBitArray(byte b) {
		// 强制转换成int?
		int temp = (int) b;
		byte[] result = new byte[8];
		for (int i = 7; i > -1; i--) {
			result[i] = (byte) (temp & 0x01);
			temp >>= 1;
		}
		return result;
	}

	/**
	 *  将二维比特数组转化为字节
	 * @param b
	 * @return
	 */
	public static byte bitToByteArray(byte[] b) {
		byte result;
		result = (byte) (b[7] | b[6] << 1 | b[5] << 2 | b[4] << 3 | b[3] << 4
				| b[2] << 5 | b[1] << 6 | b[0] << 7);
		return result;
	}

	/**
	 *  将字节转化为字符
	 * @param b
	 * @return
	 */
	public static char byteToChar(byte[] b) {
		int s = 0;
		if (b[0] > 0) {
			s += b[0];
		}
		if (b[0] < 0) {
			s += 256 + b[0];
		}
		s *= 256;
		if (b[1] > 0) {
			s += b[1];
		}
		if (b[1] < 0) {
			s += 256 + b[1];
		}
		char ch = (char) s;
		return ch;
	}

	/**
	 * 将字节转换成16进制字符串
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte b) {
		String hex = "";
		hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex;
	}

	/**
	 * 字节数组转16进制字符串
	 * @param bs
	 * @return
	 */
	public static String bytesToHexString(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		String hex = "";
		for (int i = 0; i < bs.length; i++) {
			hex = Integer.toHexString(bs[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	/**
	 * 将字符串以16进制转换成字节数组
	 * @param in
	 * @return
	 */
	public static byte[] hexStringToBytes(String in) {
		byte[] arrB = in.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	 /**
     * 提供4种简单的值转换
     * date，datetime，roundx，floatx（x表示经度,整型）, booleanvalue （1 or 0）
     * @param o
     * @param formatString
     * @return
     */
    public static Object formatValue(Object o , String formatString)
    {
    	if(o == null)
    		return null;
    	
    	if(formatString.toLowerCase().equals("date"))
    		return  DateUtil.DateFormat(DateUtil.parseObject(o));
    	else if(formatString.toLowerCase().equals("datetime"))
    		return  DateUtil.DateTimeFormat(DateUtil.parseObject(o));
    	else if(formatString.toLowerCase().indexOf("round") >= 0)
    	{
    		formatString = formatString.replace("round" , "");
    		Integer precision = toInt(formatString); 
    		java.math.BigDecimal b = new java.math.BigDecimal(toDouble(o)); 
    		return b.setScale(precision, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
    	}
    	else if(formatString.toLowerCase().indexOf("float") >= 0)
    	{
    		formatString = formatString.replace("float" , "");
    		Integer precision = toInt(formatString);
    		java.math.BigDecimal b = new java.math.BigDecimal(toDouble(o)); 
    		return b.setScale(precision, java.math.BigDecimal.ROUND_DOWN).doubleValue();
    	}
    	else if(formatString.toLowerCase().equals("booleanvalue"))
    	{
    		return toBoolean(o) == true ? 1 : 0;
    	}
    	return o;
    }
}
