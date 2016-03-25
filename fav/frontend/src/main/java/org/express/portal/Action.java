package org.express.portal;

import java.lang.reflect.Method;

/**
 * Internal class which holds object instance, method and arguments' types.
 * 
 */
class Action
{
	/**
	 * Object instance.
	 */
	public final Object instance;

	/**
	 * Method instance.
	 */
	public final Method method;

	/**
	 * Method's arguments' types.
	 */
	public final Class<?>[] arguments;
	
	/**
	 * Init Key
	 */
	public final String key; 

	public Action(Object instance, Method method , String key)
	{
		this.instance = instance;
		this.method = method;
		this.arguments = method.getParameterTypes();
		this.key = key;
	}
}
