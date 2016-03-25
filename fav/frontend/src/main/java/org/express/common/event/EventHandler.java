package org.express.common.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *事件句柄
 *@date 2012-02-09
 *@author ZhengMin Chen
 */
public class EventHandler
{
	private List<Event> list;
	private HashMap<String, Event> eventList;
	private Object object;
	
	/**
	 *构造函数
	 */
	public EventHandler()
	{
		list = new ArrayList<Event>();
		eventList = new HashMap<String, Event>();
	}
	
	/**
	 * 清空事件句柄
	 */
	public void ClearEvent()
	{
		list.clear();
		eventList.clear();
	}
	
	/*****************************************单一对象，单一事件，一次性调用****************************************/
	
	/**
	 * 设置句柄的调用对象
	 *@param Object
	 * 设置对象
	 */
	public void setObject(Object obj)
	{
		this.object = obj;
	}
	
	/**
	 * 注册事件
	 *@param 调用对象
	 *@param 方法名称
	 */
	public void registerHandler(Object obj , String methodName)
	{
		Event e = new Event();
		e.setObject(obj);
		e.setMethod(methodName);
		eventList.put(methodName, e);
	}
	
	/**
	 * 注册事件
	 *@param 方法名称
	 */
	public void registerHandler(String methodName)
	{
		registerHandler(this.object ,methodName);
	}
	
	/**
	 * 执行指定对象，指定方法的调用
	 *@param 方法名称
	 *@param 传递参数
	 */
	public void Invoke(String methodName , Object... arg) throws Exception
	{
		if(!"".equals(methodName))
		{
			Event e = eventList.get(methodName);
			
			if(e != null)
			{
				if(null != arg)
				{
					e.setParam(arg);
				}
				e.invoke();
			}
		}
	}
	
	/*****************************************多对象，多事件，循环通知调用****************************************/

	/**
	 * 设置事件
	 *@param 事件
	 */
	public void AddEvent(Event e)
	{
		list.add(e);
	}
	
	/**
	 * 设置事件 new事件(xx,xxx,xxxx)
	 *@param xx类
	 *@param xxx方法名称
	 *@param xxxx参数
	 * 生成事件 xx类xxx方法，传递xxxx参数
	 */
	public  void AddEvent(Object object,String methodName,Object... arg)
	{
		list.add(new Event(object,methodName,arg));
	}
	
	/**
	 * 循环通知
	 * Add了多少的事件就执行多少
	 * @exception 抛出异常
	 */
	public void Notify() throws Exception
	{
		for(Event event : list)
		{
			event.invoke();
		}
	}
}