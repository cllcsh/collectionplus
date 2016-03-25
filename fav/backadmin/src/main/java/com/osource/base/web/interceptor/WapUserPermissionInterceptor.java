/**
 * 
 */
package com.osource.base.web.interceptor;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.DefaultUrlHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.osource.base.Constants;
import com.osource.base.common.IctmapUtil;
import com.osource.base.web.UserSession;

/**
 * @author luoj
 *
 */
public class WapUserPermissionInterceptor extends AbstractInterceptor {
	private static final Logger logger = Logger.getLogger(WapUserPermissionInterceptor.class);
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		String reqUrl = invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName() + ".do";
		reqUrl = reqUrl.replaceAll("//", "/");
		Map map = ac.getParameters();

		boolean hasPermission = false;
		
		if(ac.getSession().get(Constants.WAP_USER_SESSION_KEY) != null){
			UserSession us = (UserSession)ac.getSession().get(Constants.WAP_USER_SESSION_KEY);
			if(hasOperatePermission(reqUrl, us))
				hasPermission = true;
		}
		
		if (hasPermission) {
			return invocation.invoke();
		} else {
			HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
			StringBuilder curlBuffer = new StringBuilder();
			curlBuffer.append(IctmapUtil.getWebRealPath(request));
			curlBuffer.append(request.getContextPath());
			
			curlBuffer.append(reqUrl);
			new DefaultUrlHelper().buildParametersString(map, curlBuffer, "&");
			String curl = curlBuffer.toString();
			logger.info("越权请求:" + curl);
			String jumptourl = "/waplogin.do?tourl=" + URLEncoder.encode(curl, "UTF-8");
			ac.getValueStack().set("interceptError", "您无权访问，请先登陆!");
			ac.getValueStack().set("jumptourl", jumptourl);
			ac.getValueStack().set("tourl", curl);
			return "intercepthtmlpass";
		}
	}
	
	private boolean hasOperatePermission(String reqUrl, UserSession userSession){
		if(reqUrl.startsWith("/module/wap")){	//判断用户是否有此请求权限
			return true;
		} else {
			return false;
		}
	}
	
}
