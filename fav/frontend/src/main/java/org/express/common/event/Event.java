package org.express.common.event;

import java.lang.reflect.Method;

/**
 *事件
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public class Event
{
	private Object object;
	private String methodName;
	private Object[] param;
	@SuppressWarnings("rawtypes")
	private Class[] paramType;
	
	public Event()
	{
	}
	
	/**
	 *构造函数
	 *@param xx.xxx(xxxx);
	 * 调用xx类的xxx方法，xxxx作为参数；
	 * 多个参数使用 Object[] 对象数组
	 */
	public Event(Object object,String methodName,Object... arg)
	{
		this.object = object;
		this.methodName = methodName;
		
		if(arg != null)
		{
			this.param = arg;
			this.convertParamType(arg);
		}
	}
	
	/**
	 *设置事件
	 *@param xx.xxx(xxxx);
	 * 调用xx类的xxx方法，xxxx作为参数；
	 * 多个参数使用 Object[] 对象数组
	 */
	public void setEvent(Object object,String methodName,Object... arg)
	{
		this.object = object;
		this.methodName = methodName;
		if(arg != null)
		{
			this.param = arg;
			this.convertParamType(arg);
		}
	}
	
	/**
	 * 得到参数的参数类型
	 *@param 参数
	 */
	public void convertParamType(Object[] obj)
	{
		this.paramType = new Class[obj.length];
		
		for (int i = 0; i < obj.length; i++)
		{
			this.paramType[i] = obj[i].getClass();
		}
	}
	
	/**
	 * 得到调用对象
	 *@return 调用对象
	 */
	public Object getObject()
	{
		return this.object;
	}
	
	/**
	 * 设置调用对象
	 *@param 调用对象
	 */
	public void setObject(Object object)
	{
		this.object = object;
	}
	
	/**
	 * 得到方法名称
	 *@return 方法名称
	 */
	public String getMethod()
	{
		return this.methodName;
	}
	
	/**
	 * 设置方法名称
	 *@param 方法名称
	 */
	public void setMethod(String methodString)
	{
		this.methodName = methodString;
	}
	
	/**
	 * 得到参数
	 *@return 参数
	 */
	public Object getParam()
	{
		return this.param;
	}
	
	/**
	 * 设置参数
	 *@param 参数（参数数组）
	 */
	public void setParam(Object... param)
	{
		this.param = param;
		this.convertParamType(param);
	}
	
	/**
	 * 调用
	 * 调用xx类的xxx方法，xxxx作为参数；
	 * @exception 抛出异常
	 */
	public void invoke() throws Exception
	{
		if(this.paramType == null || this.param == null)
		{
			Method method = this.object.getClass().getMethod(this.methodName);
			
			if(null == method)
				return;
			
			method.invoke(this.object);
		}
		else
		{
			Method method = this.object.getClass().getMethod(this.methodName, this.paramType);
			
			if(null == method)
				return;
			
			method.invoke(this.object, this.param);
		}
	}
}