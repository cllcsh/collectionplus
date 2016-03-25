/**   
 * 文件名：ServiceInterceptor.java   
 *   
 *   
 */
package com.osource.base.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


/**   
 *    
 * 项目名称：osource   
 * <p>类名称：ServiceInterceptor   
 * <p>类描述：   
 * <p>创建人：luoj   
 * <p>创建时间：2009-11-19 上午09:32:53   
 * <p>修改人：luoj   
 * <p>修改时间：2009-11-19 上午09:32:53   
 * <p>修改备注：   
 * @version    
 *    
 */
public class ServiceInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -1034896207933298639L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		if (action instanceof ServiceAware) {
//			UserSession us = (UserSession) ac.getSession().get(Constants.USER_SESSION_KEY);
			((ServiceAware) action).injectUserSession();
		}
		return invocation.invoke();
	}

}
