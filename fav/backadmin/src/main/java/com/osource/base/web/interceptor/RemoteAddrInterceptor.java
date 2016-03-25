package com.osource.base.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class RemoteAddrInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -5590477135757976852L;
	private static final Logger logger = Logger.getLogger(RemoteAddrInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();

		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
		String userRemoteAddr = request.getRemoteAddr();
		request.getSession().setAttribute("Locale", request.getRemoteAddr());
		if (action instanceof RemoteAddrAware) {
			((RemoteAddrAware)action).setRemoteAddr(userRemoteAddr);
			logger.info(userRemoteAddr);
		}

		return invocation.invoke();
	}

}
