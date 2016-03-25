/**
 * @author luoj
 * @create 2009-8-26
 * @file ManageLogInterceptor.java
 * @since v0.1
 * 
 */
package com.osource.base.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author luoj
 *
 */
public class ManageLogInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247699574766281684L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		invocation.addPreResultListener(new ManageLogListener());
		return invocation.invoke();
	}

}
