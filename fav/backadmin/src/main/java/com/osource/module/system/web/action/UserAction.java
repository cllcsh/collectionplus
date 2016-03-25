package com.osource.module.system.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.service.LoginService;
import com.osource.base.web.action.BaseAction;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.model.UserRole;
import com.osource.module.system.service.UserService;
import com.osource.module.system.web.form.UserForm;
import com.osource.util.IctUtil;
import com.osource.util.PwdEncoder;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class UserAction extends BaseAction {
    @Autowired
    private UserService userService;
    @Autowired
    private PwdEncoder pwdEncoder;
    @Autowired
    private LoginService loginService;

    private UserForm userForm;

    /** action methods * */

    public UserAction() {
        super();
    }

    /**
     * 功能初始页面跳转
     */
    public String init() {

        return RESULT_INIT;
    }

    /**
     * 根据查询条件进行查询
     */
    @SuppressWarnings("unchecked")
    public String query() {
        Map condition = new HashMap();
        if (userForm != null) {
            condition.put("loginName", userForm.getLoginName());
            condition.put("name", userForm.getName());
            condition.put("companyName", userForm.getCompanyName());
            if (!StringUtils.equals("省份", userForm.getProvince())) {
                condition.put("province", userForm.getProvince());
                if (!StringUtils.equals("市", userForm.getCity())) {
                    condition.put("city", userForm.getCity());
                    if (!StringUtils.equals("市/县", userForm.getArea())) {
                        condition.put("area", userForm.getArea());
                    }
                }
            }
            condition.put("userType", userForm.getUserType());
            condition.put("approveFlag", userForm.getApproveFlag());
        }

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(userService.findByCondition(condition, pages));

        return RESULT_LIST;
    }

    /**
     * 跳转到添加系列信息页面
     */
    public String add() {
        this.setActionName("user_save");
        return RESULT_SET;
    }

    /**
     * 添加系列信息信息
     */
    public String save() {
        UserInfo userInfo = this.getLoginService().getByLoginName(userForm.getLoginName());
        if (userInfo != null) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户失败，登录名已存在");
            return RESULT_AJAXJSON;
        } else {
            userInfo = new UserInfo();
        }
        try {
            if (userForm != null)
                IctUtil.copyProperties(userInfo, userForm);

            userInfo.setFormerPassword(userInfo.getPassword());
            userInfo.setPassword(getPwdEncoder().encodePassword(userInfo.getPassword()));
            userInfo.setInsertId(getUserSession().getUserId());
            userInfo.setApproveFlag("1");

            userService.save(userInfo);
            
            
            UserRole userRole = new UserRole();
            userRole.setId(IDgenerator.gettNextID("tb_user_role"));
            userRole.setUserId(userInfo.getId());
            userRole.setRoleId(1);
            userRole.setDeptId(0);
            userRole.setInsertId(userInfo.getId());
            userService.saveUserRole(userRole);
            
            this.getAjaxMessagesJson().setMessage("0", "添加用户信息成功");
            logger.debug("添加系列信息成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加用户信息失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 跳转到编辑系列信息页面
     */
    public String edit() throws IctException {
        UserInfo userInfo;
        userInfo = userService.findById(this.getId());

        UserForm userForm = new UserForm();
        IctUtil.copyProperties(userForm, userInfo);

        this.setUserForm(userForm);

        return RESULT_SET;
    }

    /**
     * 跳转到编辑系列信息页面
     */
    public String approve() throws IctException {
        String userid = request.getParameter("userid");
        String approveType = request.getParameter("approveType");
        String reason = request.getParameter("reason");
        try {
            UserInfo userInfo;
            if (!StringUtils.isBlank(userid) && !StringUtils.isBlank(approveType)) {
                userInfo = userService.findById(Integer.parseInt(userid));
                userInfo.setApproveFlag(approveType);
                if (!StringUtils.isBlank(reason)) {
                    userInfo.setReason(reason);
                }
                userService.update(userInfo);
                this.getAjaxMessagesJson().setMessage("0", "审核成功!");

                /**
                 * // 用户审核通过，向用户发送短信通知 if (Integer.parseInt(userInfo.getApproveFlag()) == 1) {
                 * 
                 * String content = "亲爱的" + userInfo.getName() + " ，您的账户申请已通过资质审核，获得相应功能的使用权限。"; SMSEntry smsEntry = new
                 * SMSEntry(); smsEntry.setContent(content); smsEntry.setId(3); // 3表示注册信息审核通过
                 * smsEntry.setPhoneNo(userInfo.getLoginName()); SMSQueue.add(smsEntry); }
                 **/
            } else {
                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "参数传入错误，审核失败!");

            }
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "审核失败!");
            logger.error(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 跳转到查看系列信息页面
     */
    public String view() throws IctException {
        UserInfo userInfo;
        userInfo = userService.findById(Integer.valueOf(this.getId()));

        UserForm userForm = new UserForm();
        IctUtil.copyProperties(userForm, userInfo);

        this.setUserForm(userForm);

        return RESULT_VIEW;
    }

    public String modifyPassword() throws Exception {
        String oldpassword = getRequest().getParameter("oldpassword");
        String password = getRequest().getParameter("password");
        UserInfo userInfo;
        try {
            userInfo = userService.findById(getUserSession().getUserId());
            if (!StringUtils.equals(getPwdEncoder().encodePassword(oldpassword), userInfo.getPassword())) {

                this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "输入旧密码不正确，请重新输入！");
            } else {
                userInfo.setPassword(getPwdEncoder().encodePassword(password));
                userInfo.setFormerPassword(password);
                userService.update(userInfo);
                this.getAjaxMessagesJson().setMessage("0", "更新密码成功!");
            }
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "更新密码失败!");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 修改系列信息信息
     */
    public String update() {
        UserInfo userInfo = this.getLoginService().getByLoginName(userForm.getLoginName());
        if (userInfo == null) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "修改用户失败，用户不存在");
            return RESULT_AJAXJSON;
        }

        int loginCount = userInfo.getLoginCount();

        try {
            String approveFlag = userInfo.getApproveFlag();

            userForm.setFormerPassword(userInfo.getFormerPassword());
            userForm.setApproveFlag(userInfo.getApproveFlag());
            IctUtil.copyProperties(userInfo, userForm);
            userInfo.setUpdateId(getUserSession().getUserId());
            userInfo.setLoginCount(loginCount);
            userService.update(userInfo);

            this.getAjaxMessagesJson().setMessage("0", "修改用户信息成功");
            logger.debug("修改系列信息成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改用户信息失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 删除系列信息信息
     */
    public String deletes() {
        try {
            userService.deleteById(this.getIds());
            this.getAjaxMessagesJson().setMessage("0", "删除成功");
            logger.debug("删除成功");
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
            logger.debug(e);
        }
        return RESULT_AJAXJSON;
    }

    public String mview() {
        try {
            UserInfo userInfo = userService.findById(getUserSession().getUserId());
            Map resultMap = new HashMap();
            resultMap.put("code", "0");
            resultMap.put("msg", "成功");
            resultMap.put("message", userInfo);
            String[] excludes = {};
            return this.returnJsonStringDateStr(resultMap, excludes);
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("E_DELFAILED", "操作失败");
            logger.debug(e);
        }

        return RESULT_AJAXJSON;

    }

    /** getter and setter methods * */

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserForm getUserForm() {
        return userForm;
    }

    public void setUserForm(UserForm userForm) {
        this.userForm = userForm;
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
}