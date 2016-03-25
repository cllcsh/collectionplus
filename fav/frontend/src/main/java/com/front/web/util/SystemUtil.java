package com.front.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class SystemUtil 
{
	/**
	 * 生成序列号
	 * @return
	 */
	public static String getSerialNumber()
	{
		StringBuffer serialNumber = new StringBuffer();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		serialNumber.append(sdf.format(date));
		
		//增加6位随机数
		String randomString = getLastSixNum();
		serialNumber.append(randomString);
		
		return serialNumber.toString();
	}
	
	/**
	 * 生成六位随机数
	 * @return
	 */
	public static String getLastSixNum()
	{
		int count = 0;  
        char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
        StringBuffer pwd = new StringBuffer("");  
        Random r = new Random();  
        while (count < 6) 
        {  
            int i = Math.abs(r.nextInt(10));  
            if (i >= 0 && i < str.length) 
            {  
                pwd.append(str[i]);  
                count++;  
            }  
        }  
        return pwd.toString();  
	}
}
