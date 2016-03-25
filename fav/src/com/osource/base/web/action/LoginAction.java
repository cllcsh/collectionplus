/**
 * @author luoj
 * @create 2009-3-30
 * @file LoginAction.java
 * @since v0.1
 * 
 */
package com.osource.base.web.action;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;

import com.osource.base.Constants;
import com.osource.base.constants.Codebook;
import com.osource.base.listener.OnlineUserBindingListener;
import com.osource.base.model.LoginLog;
import com.osource.base.service.LoginService;
import com.osource.base.web.UserSession;
import com.osource.base.web.form.LoginForm;
import com.osource.base.web.session.UsInitializer;
import com.osource.client.SmsManager;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.ip.IPSeeker;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.service.UserService;
import com.osource.util.Md5PwdEncoder;
import com.osource.util.PwdEncoder;

/**
 *
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class LoginAction extends BaseAction {

    public static final String RESULT_AJAXLOGIN = "ajaxlogin";

    private LoginForm loginForm;
    private int errorCode;
    private String tourl = "main.do";
    private String style = "default";
    private String source = "back";// 请求来源：back为后台登录，portal为前台网页登录
    private boolean isauth = true; // 是否需要校验验证码
    private boolean isAutoLogin = false; // 是否自动登录
    /* private PageList pageList; */

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskExecutor threadPoolTaskExecutor;

    // @Autowired
    // private LoginLogService loginLogService;
    @Autowired
    @Qualifier("usInitializerContainer")
    private UsInitializer usInitializer; // 用户登陆时初始化用户信息，由Spring自动注入UsInitializer实例
    @Autowired
    private PwdEncoder pwdEncoder;

    @Override
    public String execute() throws Exception {
        if (loginForm == null)
            return init();
        return validateLogin();
    }

    public String init() {
        String companyInfo = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "company_info", "");
        pushValueStack("company_info", companyInfo);
        return "input";
    }

    public String ajaxLogin() {
        return SUCCESS;
    }

    /** 网页前台登录 */
    public String portalInit() {
        this.setTourl("portal/login.jsp");
        return SUCCESS;
    }

    public String ajaxLoginValidate() throws Exception {
        if (validateLogin().equals("success")) {
            this.getAjaxMessagesJson().setMessage("0", "登陆成功");
            logger.info("用户ajax登陆成功");
        } else {
            this.getAjaxMessagesJson().setMessage("E_LOGIN_FAILURE", "登陆失败，请重新登陆！");
            logger.info("用户ajax登陆失败");
        }
        return RESULT_AJAXJSON;
    }

    public String logout() {
        session.remove(Constants.USER_SESSION_KEY);
        request.getSession().removeAttribute(Constants.ONLINE_USERS_LISTENER_KEY);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_LATITUDE);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_LONGITITUDE);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_SCALE);
        logger.info("用户退出");
        return SUCCESS;
    }

    /** 短信登陆验证 */
    public String smlogin() throws Exception {
        String authCodeSM = (String) session.get(Constants.AUTH_CODE_SM);
        session.put(Constants.AUTH_CODE_SM, null);// 清空用户Session的随机验证码字符串

        if (authCodeSM == null || StringUtils.isBlank(authCodeSM) || !authCodeSM.equals(loginForm.getSmpassword())) {
            addActionError("短信验证码错误！");
            return ERROR;
        }
        this.setTourl("main.do");
        return SUCCESS;
    }

    /** 获取短信验证码 */
    public String authsm() throws Exception {
        String rand = RandomStringUtils.randomNumeric(6);

        // 手机号
        String phoneNo = request.getParameter(Constants.USER_TEL_NO);

        if (phoneNo == null || StringUtils.isEmpty(phoneNo)) {
            addActionError("用户没有关联手机号！");
            return ERROR;
        }

        // 发送短信，短信ID为0
        SmsManager.send(phoneNo, rand, Codebook.COMMON_SMS_SOURCE_TYPE_7, Constants.SYSTEM_USER_ID, 1);

        // 放入Session，解决Cookie出错的问题
        HttpSession seesion = request.getSession();
        seesion.setAttribute(Constants.AUTH_CODE_SM, rand);

        this.setTourl("jsp/" + style + "/loginSM.jsp");

        return SUCCESS;
    }

    /* methods */

    private String validateLogin() throws Exception {
        // String validAuthCode = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "VALID_AUTH_CODE", "true");
        String validAuthCode = "false";
        if (isauth && !"false".equals(validAuthCode.toLowerCase())) {
            String authRandCode = (String) session.get("authCode");
            session.put("authCode", null);// 清空用户Session的随机验证码字符串
            if (authRandCode == null || StringUtils.isBlank(authRandCode)
                    || !authRandCode.equals(loginForm.getAuthcode())) {
                addActionError("验证码错误！");
                this.setErrorCode(4);
                loginlog(this.getErrorCode());
                return init();
            }
        }
        if (loginForm.getLoginname() == null || StringUtils.isBlank(loginForm.getLoginname())) {
            addActionError("用户名或密码错误，请重新输入！");
            this.setErrorCode(-1);
            loginlog(this.getErrorCode());
        } else if (loginForm.getPassword() == null || StringUtils.isBlank(loginForm.getPassword())) {
            addActionError("用户名或密码错误，请重新输入！");
            this.setErrorCode(-1);
            loginlog(this.getErrorCode());
        } else {
            UserInfo loginInfo = this.getLoginService().getByLoginName(loginForm.getLoginname());
            if (loginInfo == null) {
                addActionError("用户名或密码错误，请重新输入！");
                if (!loginForm.getLoginname().equals(""))
                    this.setErrorCode(2);
                loginlog(this.getErrorCode());
            } else if (getPwdEncoder().isPasswordValid(loginInfo.getPassword(), loginForm.getPassword())) {
                this.setErrorCode(1);
                try {
                	session.put(Constants.USER_SESSION_KEY, initUserSession(loginInfo));
				} catch (Exception e) {
					e.printStackTrace();
				}
                if (isAutoLogin) { // 如果设置了自动登录，写入cookie
                    addCookie("isAutoLogin", String.valueOf(isAutoLogin), 0);
                    addCookie("loginName", loginInfo.getLoginName(), 0);
                } else {
                    addCookie("isAutoLogin", null, 0);
                    addCookie("loginName", null, 0);
                }
                // add by nyl 增加短信认证
                // String authSMFlag = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "AUTHSM", "0");
                // if ("1".equals(authSMFlag) && !Constants.SYSTEM_ADMIN.equals(loginForm.getLoginname())) {
                // if (StringUtil.isEmpty(loginInfo.getPhoneNo())) {
                // addActionError("用户没有关联手机号！");
                // this.setErrorCode(3);
                // loginlog(this.getErrorCode());
                // return init();
                // }
                //
                // this.setTourl("login_authsm.do?" + Constants.USER_TEL_NO + "=" + loginInfo.getPhoneNo());
                // }

                if (source != null && source.equals("portal")) {
                    this.setTourl("portal/index.jsp");
                }

                Integer loginId = loginlog(this.getErrorCode());
                OnlineUserBindingListener onlineUserBindingListenler = new OnlineUserBindingListener(loginInfo.getId(),
                        threadPoolTaskExecutor, loginService, loginId);
                if (!onlineUserBindingListenler.equals(request.getSession()
                        .getAttribute(Constants.ONLINE_USERS_LISTENER_KEY)))
                    request.getSession().setAttribute(Constants.ONLINE_USERS_LISTENER_KEY, onlineUserBindingListenler);
            } else {
                addActionError("用户名或密码错误，请重新输入！");
                this.setErrorCode(3);
                loginlog(this.getErrorCode());
            }
        }

        if (this.getErrorCode() == 1) {
            logger.info("用户登陆");
            return SUCCESS;
        } else {
            return init();
        }
    }

    private Integer loginlog(Integer code) throws IctException {
        LoginLog bean = new LoginLog();
        bean.setLoginName(loginForm.getLoginname());
        bean.setLoginIp(this.getRequest().getRemoteAddr());
        bean.setLoginAddr(IPSeeker.getAddress(this.getRequest().getRemoteAddr()));
        bean.setLoginResult(String.valueOf(code));
        // bean.setDeptId((getUserSession() == null)? 0 : getUserSession().getDeptId());
        loginService.saveLoginLog(bean);
        return bean.getId();
    }

    private UserSession initUserSession(UserInfo loginInfo) {
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
        style = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "STYLE", "default").toLowerCase();
        userSession.setThemeName(style);

        userSession.setRemoteAddr(request.getRemoteAddr());

        this.getUsInitializer().initialize(userSession);
        return userSession;
    }

    /* setter and getter */

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    public String getTourl() {
        return tourl;
    }

    public void setTourl(String tourl) {
        this.tourl = tourl;
    }

    public boolean isIsauth() {
        return isauth;
    }

    public void setIsauth(boolean isauth) {
        this.isauth = isauth;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public PwdEncoder getPwdEncoder() {
        if (pwdEncoder == null)
            this.pwdEncoder = new Md5PwdEncoder();
        return pwdEncoder;
    }

    public void setPwdEncoder(PwdEncoder pwdEncoder) {
        this.pwdEncoder = pwdEncoder;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public UsInitializer getUsInitializer() {
        return usInitializer;
    }

    public void setUsInitializer(UsInitializer usInitializer) {
        this.usInitializer = usInitializer;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public TaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(TaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the isAutoLogin
     */
    public boolean getIsAutoLogin() {
        return isAutoLogin;
    }

    /**
     * @param isAutoLogin
     *            the isAutoLogin to set
     */
    public void setIsAutoLogin(boolean isAutoLogin) {
        this.isAutoLogin = isAutoLogin;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // public LoginLogService getLoginLogService() {
    // return loginLogService;
    // }
    //
    // public void setLoginLogService(LoginLogService loginLogService) {
    // this.loginLogService = loginLogService;
    // }

}
