package org.express.common.monitor;

import java.util.*;

/**
 *时间发生器
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public class TimeStep 
{
	private Calendar calendar = Calendar.getInstance();
	// calendar field
	private int field = Calendar.MILLISECOND;
	// the amount of the date or time, Defult 10s
	private int amount = 10000;
	
	/**
	 * 构造函数 - 设置间隔（默认单位毫秒）
	 * @param 整型（毫秒）意为多少毫秒一次
	 */
	public TimeStep(int interval)
	{
		this.amount = interval;
	}
	
	/**
	 * 得到单位（默认单位毫秒）
	 * @return 单位
	 */
	public int getUnit() {
	    return field;
    }
	
	/**
	 * 设置单位（默认单位毫秒）
	 * @param 整型 Calendar.MILLISECOND; 这样取单位
	 */
	public void setUnit(int field) {
	    this.field = field;
    }

	/**
	 * 得到间隔（默认单位毫秒）
	 * @return 间隔量
	 */
	public int getInterval() {
	    return amount;
    }
	
	/**
	 * 设置间隔（默认单位毫秒）
	 * @param 整型（毫秒）意为多少毫秒一次
	 */
	public void setInterval(int amount) {
	    this.amount = amount;
    }

	/**
	 * 取得经过一次间隔后的下一个时间
	 * example 当前时间：10:00:00 间隔值：1 单位：分钟 return 10:01:00 
	 * @return 默认为 当前时间 + 间隔量(默认单位：毫秒)
	 */
	public Date next(){
		calendar.add(field, amount);
		return calendar.getTime();
	}
}