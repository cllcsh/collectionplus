/**
 * @author luoj
 * @create 2009-3-23
 * @file UserAction.java
 * @since v0.1
 * 
 */
package com.osource.module.system.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.osource.base.model.ConfigInfo;
import com.osource.base.web.action.BaseAction;
import com.osource.base.web.ajax.AjaxMessagesJson;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.service.ConfigInfoService;
import com.osource.module.system.web.form.ConfigInfoForm;

/**
 * 用户管理action，包括用户的添加，修改，删除，查询操作
 */
@SuppressWarnings("unchecked")
public class ConfigAction extends BaseAction {
    private static final long serialVersionUID = -7615283777235156997L;

    // private static final Log logger = LogFactory.getLog(ConfigAction.class);

    // public static final String RESULT_LIST = "list";
    // public static final String RESULT_SET = "set";
    // public static final String RESULT_INIT = "init";
    // public static final String RESULT_PASS = "pass";

    @Autowired
    private ConfigInfoService configInfoService;

    private ConfigInfoForm configInfoForm;
    private PageList pageList;
    private String configCode; // 页面传的配置编号
    private int page = 1; // 要查看页码数
    private int limit; // 每页显示条数

    private String actionName;

    private AjaxMessagesJson ajaxMessagesJson = new AjaxMessagesJson();

    /**
     * 用户管理
     * 
     * @return
     */
    public String init() {
        return RESULT_INIT;
    }

    /**
     * 查询配置列表
     * 
     * @return
     */
    public String query() {
        Map condition = new HashMap();
        condition.put("configKey", configInfoForm.getConfigKey());
        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(configInfoService.findConfigInfoListByCondition(condition, pages));
        return RESULT_LIST;
    }

    /**
     * 添加配置
     * 
     * @return
     * @throws IctException
     */
    public String add() throws IctException {
        this.setActionName("config_save");
        return RESULT_SET;
    }

    /**
     * 保存配置
     * 
     * @return
     * @throws IctException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws com.osource.core.exception.IctException
     */
    public String save() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException,
            com.osource.core.exception.IctException {
        ConfigInfo configInfo = new ConfigInfo();
        PropertyUtils.copyProperties(configInfo, configInfoForm);
        // configInfo.setDeptId(getUserSession().getDeptId());//部门id
        configInfo.setInsertId(getUserSession().getUserId());// 与userName一起放入UserSession
        try {
            configInfoService.saveConfigInfo(configInfo);
            this.getAjaxMessagesJson().setMessage("0", "添加成功");
            logger.debug("增加配置成功");
        } catch (IctException e) {
            this.getAjaxMessagesJson().setMessage("TB_CONFIG_DELFAILED", e.getMessage());
        }
        return RESULT_AJAXJSON;
    }

    /**
     * 编辑配置
     * 
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IctException
     */
    public String edit() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        List<ConfigInfo> list;
        try {
            Map condition = new HashMap();
            condition.put("configCode", this.getConfigCode());
            list = configInfoService.findConfigInfosByCondition(condition);
            if (list == null || list.size() < 1) {
                this.getAjaxMessagesJson().setMessage("TB_CONFIG_DELFAILED", "配置信息不存在");
            }
            ConfigInfo configInfo = list.get(0);
            ConfigInfoForm configInfoForm = new ConfigInfoForm();
            PropertyUtils.copyProperties(configInfoForm, configInfo);
            this.setConfigInfoForm(configInfoForm);
        } catch (IctException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        this.setActionName("user_update");
        return RESULT_SET;
    }

    /**
     * 更新配置
     * 
     * @return
     * @throws IctException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String update() throws IctException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        ConfigInfo configInfo = new ConfigInfo();
        PropertyUtils.copyProperties(configInfo, configInfoForm);

        try {
            configInfoService.updateConfigInfo(configInfo);
            this.getAjaxMessagesJson().setMessage("0", "修改成功");
        } catch (IctException e) {
            this.getAjaxMessagesJson().setMessage("TB_CONFIG_DELFAILED", e.getMessage());
        }
        return RESULT_AJAXJSON;
    }

    /**
     * 删除配置
     * 
     * @return
     * @throws IctException
     */
    public String deletes() throws IctException {
        try {
            configInfoService.deleteConfigInfoById(getConfigCode());
            this.getAjaxMessagesJson().setMessage("0", "删除成功");
        } catch (IctException e) {
            this.getAjaxMessagesJson().setMessage("TB_CONFIG_DELFAILED", "删除失败");
        }
        return RESULT_AJAXJSON;
    }

    /* getter and setter */
    public PageList getPageList() {
        return pageList;
    }

    public ConfigInfoService getConfigInfoService() {
        return configInfoService;
    }

    public void setConfigInfoService(ConfigInfoService configInfoService) {
        this.configInfoService = configInfoService;
    }

    public ConfigInfoForm getConfigInfoForm() {
        return configInfoForm;
    }

    public void setConfigInfoForm(ConfigInfoForm configInfoForm) {
        this.configInfoForm = configInfoForm;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit
     *            the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public AjaxMessagesJson getAjaxMessagesJson() {
        return ajaxMessagesJson;
    }

    public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
        this.ajaxMessagesJson = ajaxMessagesJson;
    }
}
