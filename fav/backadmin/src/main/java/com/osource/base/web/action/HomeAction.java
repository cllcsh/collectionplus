/**
 * 
 */
package com.osource.base.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.osource.base.Constants;
import com.osource.base.listener.OnlineUserBindingListener;
import com.osource.base.model.LoginLog;
import com.osource.base.service.LoginService;
import com.osource.base.web.UserSession;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.ip.IPSeeker;
//import com.osource.module.babystory.model.PhoneMessageInfo;
//import com.osource.module.babystory.service.PhoneMessageService;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.service.UserService;
import com.osource.module.system.web.form.UserForm;
import com.osource.sms.SMSEntry;
import com.osource.sms.SMSQueue;
import com.osource.util.IctUtil;
import com.osource.util.PwdEncoder;

/**
 * 站点首页
 * 
 * @author yangsen
 * 
 */
public class HomeAction extends BaseAction {
    private static final long serialVersionUID = -3401279040681059266L;
    @Autowired
    private LoginService loginService;
//    @Autowired
//    private PhoneMessageService phoneMessageService;
    @Autowired
    private UserService userService;
    @Autowired
    private PwdEncoder pwdEncoder;
    @Autowired
    private TaskExecutor threadPoolTaskExecutor;

    private UserForm userForm;

    private String actionType = "home";

    public String execute() throws Exception {
        getRequest().setAttribute("interceptError", getRequest().getSession().getAttribute("interceptError"));
        getRequest().getSession().setAttribute("interceptError", "");
        String autoLoginStr = getCookieByName("isAutoLogin");
        UserSession userSession = (UserSession) session.get(Constants.USER_SESSION_KEY);
        if (userSession != null) {
            UserInfo loginInfo = this.getUserService().findById(userSession.getUserId());
            session.put(Constants.USER_SESSION_KEY, initUserSession(loginInfo));
        } else if (StringUtils.equals("true", autoLoginStr)) {
            String loginName = getCookieByName("loginName");
            UserInfo loginInfo = this.getLoginService().getByLoginName(loginName);
            if (loginInfo != null) {
                session.put(Constants.USER_SESSION_KEY, initUserSession(loginInfo));

                Integer loginId = loginlog(1);
                OnlineUserBindingListener onlineUserBindingListenler = new OnlineUserBindingListener(loginInfo.getId(),
                        threadPoolTaskExecutor, loginService, loginId);
                if (!onlineUserBindingListenler.equals(request.getSession()
                        .getAttribute(Constants.ONLINE_USERS_LISTENER_KEY)))
                    request.getSession().setAttribute(Constants.ONLINE_USERS_LISTENER_KEY, onlineUserBindingListenler);
            }
        }

        return SUCCESS;
    }

    private Integer loginlog(Integer code) throws IctException {
        LoginLog bean = new LoginLog();
        bean.setLoginName(getUserSession().getLoginName());
        bean.setLoginIp(this.getRequest().getRemoteAddr());
        bean.setLoginAddr(IPSeeker.getAddress(this.getRequest().getRemoteAddr()));
        bean.setLoginResult(String.valueOf(code));
        // bean.setDeptId((getUserSession() == null)? 0 : getUserSession().getDeptId());
        loginService.saveLoginLog(bean);
        return bean.getId();
    }

    public String password() throws Exception {
        return "password";
    }

    public String login() throws Exception {
        return "login";
    }

    public String reg() throws Exception {
        return "regstep1";
    }

    public String contact() throws Exception {
        return "contact";
    }

    public String validPhone() throws Exception {
        String mobile = getRequest().getParameter("mobile");
        UserInfo userInfo = loginService.getByLoginName(mobile);
        if (userInfo != null) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "手机号码已被注册");
            return RESULT_AJAXJSON;
        }
        String rand = RandomStringUtils.randomNumeric(6);
        String content = "尊敬的用户，验证码" + rand + "(用户注册)，请勿向他人泄漏您的验证码。";
        SMSEntry smsEntry = new SMSEntry();
        smsEntry.setContent(content);
        smsEntry.setId(0); // 0表示注册
        smsEntry.setPhoneNo(mobile);
        SMSQueue.add(smsEntry);

        this.getAjaxMessagesJson().setMessage("0", "验证码已发送至您的手机");
        return RESULT_AJAXJSON;
    }

    public String validPasswordPhone() throws Exception {
        String mobile = getRequest().getParameter("mobile");
        String rand = RandomStringUtils.randomNumeric(6);
        String content = "尊敬的用户，验证码" + rand + "(忘记密码)，请勿向他人泄漏您的验证码。";
        SMSEntry smsEntry = new SMSEntry();
        smsEntry.setContent(content);
        smsEntry.setId(2); // 2表示忘记密码
        smsEntry.setPhoneNo(mobile);
        SMSQueue.add(smsEntry);

        this.getAjaxMessagesJson().setMessage("0", "验证码已发送至您的手机");
        return RESULT_AJAXJSON;
    }

    public String validCode() throws Exception {

        return RESULT_AJAXJSON;
    }

    public String checkIDCardOrRegNumberExist() throws Exception {
        boolean isIdcardExist = false;
        boolean isRegNumberExist = false;

        Map<String, String> condition = new HashMap<String, String>();
        condition.put("regNumber", userForm.getRegNumber());
        long num = userService.countByIdCardOrRegNumber(condition);
        if (num > 0)
            isRegNumberExist = true;

        if (userForm.getIdCard() != null && userForm.getIdCard().length() > 0) {
            condition.clear();
            condition.put("idCard", userForm.getIdCard());
            num = userService.countByIdCardOrRegNumber(condition);
            if (num > 0)
                isIdcardExist = true;
        }

        if (!isIdcardExist && !isRegNumberExist) {
            this.getAjaxMessagesJson().setMessage("0", "身份证和工商注册号均不存在");
        } else {
            if (isIdcardExist && isRegNumberExist) {
                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "身份证和工商注册号均已存在");
            } else if (isIdcardExist) {
                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "身份证号已存在");
            } else if (isRegNumberExist) {
                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "工商注册号已存在");
            }
        }
        return RESULT_AJAXJSON;
    }

    public String regStepTwo() throws Exception {
        String mobile = getRequest().getParameter("mobile");
        String password = getRequest().getParameter("password");

        userForm = new UserForm();
        userForm.setLoginName(mobile);
        userForm.setPassword(password);
        userForm.setFormerPassword(password);

        return "regstep2";
    }

    public String regStepThree() throws Exception {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("regNumber", userForm.getRegNumber());
        long num = userService.countByIdCardOrRegNumber(condition);
        if (num > 0)
            return "regstep3";

        if (userForm.getIdCard() != null && userForm.getIdCard().length() > 0) {
            condition.clear();
            condition.put("idCard", userForm.getIdCard());
            num = userService.countByIdCardOrRegNumber(condition);
            if (num > 0)
                return "regstep3";
        }

        UserInfo userInfo = loginService.getByLoginName(userForm.getLoginName());
        if (userInfo != null) {
            return "regstep3";
        }

        userInfo = new UserInfo();
        IctUtil.copyProperties(userInfo, userForm);
        userInfo.setPassword(getPwdEncoder().encodePassword(userForm.getPassword()));
        userInfo.setInsertId(1);
        userInfo.setApproveFlag("0");
        userInfo.setUserType("2");
        userService.save(userInfo);

        return "regstep3";
    }

    public String agree() throws Exception {
        return "agree";
    }

    public String resetPassword() throws Exception {
        String mobile = getRequest().getParameter("mobile");
        String authCode = getRequest().getParameter("authCode");
        UserInfo userInfo = loginService.getByLoginName(mobile);
        if (userInfo == null) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "该号码不存在！");
        } else {
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("messageType", 2); // 忘记密码
            condition.put("mobile", mobile);
            condition.put("content", authCode);
//            List<PhoneMessageInfo> messageList = phoneMessageService.findByCondition(condition);
//            if (messageList != null && messageList.size() > 0) {
//                PhoneMessageInfo phoneMessageInfo = messageList.get(0);
//                if (phoneMessageInfo.getStatus() != 1) {
//                    this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "短信验证码发送失败，请重新获取");
//                } else {
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(phoneMessageInfo.getInsertDate());
//                    calendar.add(Calendar.MINUTE, 5);
//                    if (calendar.getTime().before(new Date())) {
//                        this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "短信验证码已过期，请重新获取");
//                    } else {
//                        // this.getAjaxMessagesJson().setMessage("0", "短信验证正确");
//                        String password = getRequest().getParameter("password");
//                        try {
//
//                            userInfo.setPassword(getPwdEncoder().encodePassword(password));
//                            userInfo.setFormerPassword(password);
//                            userService.update(userInfo);
//                            this.getAjaxMessagesJson().setMessage("0", "初始化密码成功!");
//
//                        } catch (Exception e) {
//                            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "更新密码失败!");
//                            logger.debug(e);
//                        }
//                    }
//                }
//                // 验证过后删除
//                phoneMessageService.deleteById(phoneMessageInfo.getId());
//            } else {
//                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "短信验证码错误，请重新获取");
//            }
        }
        return RESULT_AJAXJSON;
    }

    public String logout() throws Exception {
        session.remove(Constants.USER_SESSION_KEY);
        request.getSession().removeAttribute(Constants.ONLINE_USERS_LISTENER_KEY);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_LATITUDE);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_LONGITITUDE);
        request.getSession().removeAttribute(Constants.PERSON_CENTER_SCALE);
        logger.info("用户退出");
        addCookie("isAutoLogin", null, 0);
        addCookie("loginName", null, 0);
        return "logout";
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
        String style = PropertiesManager.getProperty(Constants.COMMON_PROPERTIES, "STYLE", "default").toLowerCase();
        userSession.setThemeName(style);

        userSession.setRemoteAddr(request.getRemoteAddr());

        return userSession;
    }

    public String mhome() throws Exception {
        return SUCCESS;
    }

    public String toMlogin() throws Exception {
        return SUCCESS;
    }

    public String toMreg() throws Exception {
        return SUCCESS;
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
     * @return the phoneMessageService
     */
//    public PhoneMessageService getPhoneMessageService() {
//        return phoneMessageService;
//    }
//
//    /**
//     * @param phoneMessageService
//     *            the phoneMessageService to set
//     */
//    public void setPhoneMessageService(PhoneMessageService phoneMessageService) {
//        this.phoneMessageService = phoneMessageService;
//    }

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

    /**
     * @return the pwdEncoder
     */
    public PwdEncoder getPwdEncoder() {
        return pwdEncoder;
    }

    /**
     * @param pwdEncoder
     *            the pwdEncoder to set
     */
    public void setPwdEncoder(PwdEncoder pwdEncoder) {
        this.pwdEncoder = pwdEncoder;
    }

    /**
     * @return the userForm
     */
    public UserForm getUserForm() {
        return userForm;
    }

    /**
     * @param userForm
     *            the userForm to set
     */
    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType
     *            the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
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