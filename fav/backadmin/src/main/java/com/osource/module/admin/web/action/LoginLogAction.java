package com.osource.module.admin.web.action;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.admin.model.LoginLogBean;
import com.osource.module.admin.service.LoginLogService;
import com.osource.module.admin.web.form.LoginLogForm;
import com.osource.util.IctUtil;

/**
 * 
 * 项目名称：osource 类名称：LoginLogAction 类描述： 创建人：zhangyan 创建时间：Nov 4, 2009 5:43:06 PM 修改人：Administrator 修改时间：Nov 4, 2009
 * 5:43:06 PM 修改备注：
 * 
 * @version
 * 
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class LoginLogAction extends BaseAction {

    public static final String RESULT_LIST = "list";
    public static final String RESULT_SET = "set";
    public static final String RESULT_INIT = "init";

    @Autowired
    private LoginLogService loginLogService;

    private LoginLogForm loginLogForm;
    private String userId;
    private String actionName;

    public LoginLogAction() {
        super();
    }

    /**
     * 用户管理
     * 
     * @return
     */
    public String init() {
        return RESULT_INIT;
    }

    /**
     * 查询用户列表
     * 
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String query() throws IctException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        LoginLogBean loginLogBean = new LoginLogBean();
        IctUtil.copyProperties(loginLogBean, loginLogForm);
        // if(loginLogBean.getDeptId() == null)
        // loginLogBean.setDeptId(getUserSession().getDeptId());

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(loginLogService.findLoginLogListByCondition(loginLogBean, pages));
        return RESULT_LIST;
    }

    /**
     * 删除用户
     * 
     * @return
     * @throws IctException
     */
    public String deletes() throws IctException {

        loginLogService.deleteLoginLogByUserId(getUserId());
        this.getAjaxMessagesJson().setMessage("0", "删除成功");

        return RESULT_AJAXJSON;
    }

    /* getter and setter */

    public LoginLogService getLoginLogService() {
        return loginLogService;
    }

    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    public LoginLogForm getLoginLogForm() {
        return loginLogForm;
    }

    public void setLoginLogForm(LoginLogForm loginLogForm) {
        this.loginLogForm = loginLogForm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

}
