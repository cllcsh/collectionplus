package com.osource.base.web.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.osource.base.Constants;
import com.osource.base.web.UserSession;

public class UserSessionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = -9072128944935284872L;
	private static final Logger logger = Logger.getLogger(UserSessionInterceptor.class);
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		Object action = invocation.getAction();
		if (action instanceof UserSessionAware) {
			UserSession us = (UserSession) ac.getSession().get(Constants.USER_SESSION_KEY);
			((UserSessionAware) action).setUserSession(us);
		}
		logger.info("UserSessionInterceptor");
		return invocation.invoke();
	}

}
