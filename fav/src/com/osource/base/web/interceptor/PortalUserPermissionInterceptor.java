/**
 * 
 */
package com.osource.base.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.views.util.DefaultUrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.osource.base.Constants;
import com.osource.base.common.IctmapUtil;
import com.osource.base.listener.OnlineUserBindingListener;
import com.osource.base.model.LoginLog;
import com.osource.base.service.LoginService;
import com.osource.base.web.UserSession;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.ip.IPSeeker;
import com.osource.module.system.model.UserInfo;

/**
 * @author yangsen
 * 
 */
@SuppressWarnings("unchecked")
public class PortalUserPermissionInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PortalUserPermissionInterceptor.class);
    @Autowired
    private LoginService loginService;
    @Autowired
    private TaskExecutor threadPoolTaskExecutor;

    /*
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ac = invocation.getInvocationContext();
        String reqUrl = invocation.getProxy().getNamespace() + "/" + invocation.getProxy().getActionName() + ".do";
        reqUrl = reqUrl.replaceAll("//", "/");
        Map map = ac.getParameters();

        boolean hasPermission = false;

        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
        if (ac.getSession().get(Constants.USER_SESSION_KEY) != null) {
            UserSession us = (UserSession) ac.getSession().get(Constants.USER_SESSION_KEY);

            ac.getSession().put(Constants.USER_SESSION_KEY, us);

            request.getSession().setAttribute("userName", us.getUserName());
            java.util.Locale locale = new java.util.Locale(request.getRemoteAddr());
            request.getSession().setAttribute("Locale", locale);
            hasPermission = true;
            if (StringUtils.equals("2", us.getApproveFlag())) {
                if (!StringUtils.equals("/userportal_profile.do", reqUrl)
                        && !StringUtils.equals("/userportal_updateProfile.do", reqUrl)
                        && !StringUtils.equals("/userportal_company.do", reqUrl)
                        && !StringUtils.equals("/userportal_updateCompany.do", reqUrl)
                        && !StringUtils.equals("/userportal_password.do", reqUrl)
                        && !StringUtils.equals("/userportal_avatar.do", reqUrl)
                        && !StringUtils.equals("/userportal_saveAvatar.do", reqUrl)) {
                    ac.getSession().put("interceptError", "您的会员审核未通过，请联系客服专员!");
                    hasPermission = false;
                }
            } else if (StringUtils.equals("3", us.getApproveFlag())) {
                ac.getSession().put("interceptError", "您的会员资格已被冻结，请联系客服专员!");
                hasPermission = false;
            } else if (StringUtils.equals("0", us.getApproveFlag())) {
                ac.getSession().put("interceptError", "您的会员资格还未审核，请耐心等待!");
                hasPermission = false;
            }
        } else {
            ac.getSession().put("interceptError", "您还没有登录，请先登录!");
            String autoLoginStr = getCookieByName(request, "isAutoLogin");
            if (StringUtils.equals("true", autoLoginStr)) {
                String loginName = getCookieByName(request, "loginName");
                UserInfo loginInfo = this.getLoginService().getByLoginName(loginName);
                if (loginInfo != null) {
                    UserSession userSession = initUserSession(request, loginInfo);
                    ac.getSession().put(Constants.USER_SESSION_KEY, userSession);
                    hasPermission = true;

                    Integer loginId = loginlog(1, request, userSession);
                    OnlineUserBindingListener onlineUserBindingListenler = new OnlineUserBindingListener(loginInfo
                            .getId(), threadPoolTaskExecutor, loginService, loginId);
                    if (!onlineUserBindingListenler.equals(request.getSession()
                            .getAttribute(Constants.ONLINE_USERS_LISTENER_KEY)))
                        request.getSession().setAttribute(Constants.ONLINE_USERS_LISTENER_KEY,
                                                          onlineUserBindingListenler);

                    if (StringUtils.equals("2", userSession.getApproveFlag())) {
                        if (!StringUtils.equals("/userportal_profile.do", reqUrl)
                                && !StringUtils.equals("/userportal_updateProfile.do", reqUrl)
                                && !StringUtils.equals("/userportal_company.do", reqUrl)
                                && !StringUtils.equals("/userportal_updateCompany.do", reqUrl)
                                && !StringUtils.equals("/userportal_password.do", reqUrl)
                                && !StringUtils.equals("/userportal_avatar.do", reqUrl)
                                && !StringUtils.equals("/userportal_saveAvatar.do", reqUrl)) {
                            ac.getSession().put("interceptError", "您的会员审核未通过，请联系客服专员!");
                            hasPermission = false;
                        }
                    } else if (StringUtils.equals("3", userSession.getApproveFlag())) {
                        ac.getSession().put("interceptError", "您的会员资格已被冻结，请联系客服专员!");
                        hasPermission = false;
                    } else if (StringUtils.equals("0", userSession.getApproveFlag())) {
                        ac.getSession().put("interceptError", "您的会员资格还未审核，请耐心等待!");
                        hasPermission = false;
                    } else {
                        ac.getSession().put("interceptError", "");
                    }
                }
            }
        }

        if (hasPermission) {
            ac.getSession().put("interceptError", "");
            return invocation.invoke();
        } else {
            StringBuilder curlBuffer = new StringBuilder();
            curlBuffer.append(IctmapUtil.getWebRealPath(request));
            curlBuffer.append(request.getContextPath());

            curlBuffer.append(reqUrl);
            new DefaultUrlHelper().buildParametersString(map, curlBuffer, "&");
            String curl = curlBuffer.toString();
            logger.info("越权请求:" + curl);
            String jumptourl = "/home.do"; // ?tourl= + URLEncoder.encode(curl, "UTF-8");
            ac.getValueStack().set("interceptError", "您还没有登录，请先登录!");
            if (getMode(map).equalsIgnoreCase("ajaxJson")) {
                String jsonLoginUrl = request.getContextPath() + Constants.AJAX_JSON_LOGIN_URL;
                jsonLoginUrl = jsonLoginUrl.replaceAll("//", "/");
                Map resultMap = new HashMap();
                resultMap.put("status", Constants.NO_PERMISSION_STATUS);
                resultMap.put("login", "");
                resultMap.put("msg", "您没有登录!");
                JSON json = JSONSerializer.toJSON(resultMap);
                ac.getValueStack().set("jsonToString", json.toString());
                return "jsonstring";
            } else if (getMode(map).equalsIgnoreCase("ajax")) {
                return "ajaxlogin";
            }
            ac.getValueStack().set("jumptourl", jumptourl);
            ac.getValueStack().set("tourl", curl);
            return "portalintercepthtmlpass";
        }
    }

    private Integer loginlog(Integer code, HttpServletRequest request, UserSession userSession) throws IctException {
        LoginLog bean = new LoginLog();
        bean.setLoginName(userSession.getLoginName());
        bean.setLoginIp(request.getRemoteAddr());
        bean.setLoginAddr(IPSeeker.getAddress(request.getRemoteAddr()));
        bean.setLoginResult(String.valueOf(code));
        // bean.setDeptId((getUserSession() == null)? 0 : getUserSession().getDeptId());
        loginService.saveLoginLog(bean);
        return bean.getId();
    }

    private String getMode(Map map) {
        String modeStr = "";
        if (map.get("mode") != null) {
            Object mode = map.get("mode");
            if (mode instanceof Iterable) {
                modeStr = ((Iterable) mode).iterator().next().toString();
            } else if (mode instanceof Object[]) {
                Object[] array = (Object[]) mode;
                modeStr = array[0].toString();
            } else {
                modeStr = mode.toString();
            }
        }
        return modeStr;
    }

    private UserSession initUserSession(HttpServletRequest request, UserInfo loginInfo) {
        UserSession userSession = new UserSession(null);
        Integer userId = loginInfo.getId();

        userSession.setUserId(userId);
        userSession.setUserName(loginInfo.getName());
        userSession.setLoginName(loginInfo.getLoginName());
        // userSession.setDeptId(loginInfo.getDeptId());
        // userSession.setDeptName(loginInfo.getDeptName());
        userSession.setUserType(loginInfo.getUserType());
        // userSession.setDeptNode(loginInfo.getDeptNode());
        userSession.setApproveFlag(loginInfo.getApproveFlag());

        // 设置用户界面模式
        String style = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "STYLE", "default").toLowerCase();
        userSession.setThemeName(style);

        userSession.setRemoteAddr(request.getRemoteAddr());

        return userSession;
    }

    public String getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * @return the loginService
     */
    public LoginService getLoginService() {
        return loginService;
    }

    /**
     * @param loginService
     *            the loginService to set
     */
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * @return the threadPoolTaskExecutor
     */
    public TaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    /**
     * @param threadPoolTaskExecutor
     *            the threadPoolTaskExecutor to set
     */
    public void setThreadPoolTaskExecutor(TaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }
}