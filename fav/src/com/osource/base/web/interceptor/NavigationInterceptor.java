/**   
 * 文件名：NavigationInterceptor.java   
 *   
 * 版本信息： 2.0  
 *   
 */
package com.osource.base.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.osource.base.common.FuncManager;
import com.osource.core.BeanProvider;


/**   
 *    
 * 项目名称：osource   
 * 类名称：NavigationInterceptor   
 * 类描述：   
 * 创建人：luoj   
 * 创建时间：2009-12-2 下午03:55:02   
 * 修改人：luoj   
 * 修改时间：2009-12-2 下午03:55:02   
 * 修改备注：   
 * @version    
 */
public class NavigationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -7699220331784104201L;

	/* (non-Javadoc)   
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)   
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String reqUrl = invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName() + ".do";
		reqUrl = reqUrl.replaceAll("//", "/");
//		ActionContext.getContext().put("funcNode", FuncManager.getInstance().getFuncNodeByLink(reqUrl));
		ActionContext.getContext().put("funcNode", ((FuncManager)BeanProvider.getBean("funcManager")).getFuncNodeByLink(reqUrl));
		return invocation.invoke();
	}

}
