package com.osource.base.web.interceptor;


import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.osource.base.Constants;
import com.osource.base.web.UserSession;

public class ManageLogListener implements PreResultListener {
	
	private static final Log logger = LogFactory.getLog(ManageLogListener.class);

	public void beforeResult(ActionInvocation invocation, String resultCode) {
		// TODO 记录系统管理日志
		ActionContext ac = invocation.getInvocationContext();
		String reqUrl = invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName() + ".do";
		reqUrl = reqUrl.replaceAll("//", "/");
		String user = "";
		if(ac.getSession().get(Constants.USER_SESSION_KEY) != null){
			UserSession us = (UserSession)ac.getSession().get(Constants.USER_SESSION_KEY);
			user = us.getUserName() + "[" + us.getUserId() + "]";
		}
		
		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
		String ip = request.getRemoteAddr();
		logger.debug(MessageFormat.format("系统管理日志:url:{0};用户:{1};用户ip:{2};", reqUrl, user, ip));
	}

}
