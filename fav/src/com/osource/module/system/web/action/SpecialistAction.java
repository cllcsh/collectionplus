/**   
 * 文件名：SpecialistAction.java   

 *   
 */
package com.osource.module.system.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.base.web.ajax.AjaxMessagesJson;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.SpecialistInfo;
import com.osource.module.system.service.SpecialistService;
import com.osource.module.system.web.form.SpecialistForm;

/**
 * 
 * 项目名称：osource 类名称：SpecialistAction 类描述： 创建人：Fun 创建时间：2009-11-5 上午10:20:05 修改人：Fun 修改时间：2009-11-5 上午10:20:05 修改备注：
 * 
 * @version
 * 
 */

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class SpecialistAction extends BaseAction {

    private static final long serialVersionUID = -1385737429651528948L;

    private static final Log logger = LogFactory.getLog(SpecialistAction.class);

    @Autowired
    private SpecialistService specialistService;
    private SpecialistForm specialistForm;

    private PageList pageList;
    private int page = 1; // 要查看页码数
    private int limit; // 每页显示条数

    private String specialistId;
    private String actionName;

    private AjaxMessagesJson ajaxMessagesJson = new AjaxMessagesJson();

    public SpecialistAction() {
        super();
    }

    public String init() {

        return RESULT_INIT;
    }

    public String query() {
        Map condition = new HashMap();

        condition.put("certificateId", specialistForm.getCertificateId()); // 证书编号
        condition.put("name", specialistForm.getName()); // 名称
        condition.put("sex", specialistForm.getSex()); // 性别
        condition.put("idNum", specialistForm.getIdNum()); // 身份证号码
        condition.put("workUnit", specialistForm.getWorkUnit()); // 工作单位
        condition.put("useFlag", specialistForm.getUseFlag()); // 状态，包括"正常"和"删除"

        if (specialistForm.getDeptId() == null)
            condition.put("deptId", getUserSession().getDeptId());
        else
            condition.put("deptId", specialistForm.getDeptId());

        Pages pages = new Pages(this.getPage(), this.getLimit());
        this.setPageList(specialistService.findSpecialistListByCondition(condition, pages));

        return RESULT_LIST;
    }

    public String add() {
        this.setActionName("specialist_save");
        return RESULT_SET;

    }

    /**
     * 
     * 
     * save(简要说明)
     * 
     * @param name
     * @param @return 设定文件
     * @return String DOM对象
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @Exception 异常对象
     */
    public String save() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        SpecialistInfo specialistInfo = new SpecialistInfo();
        PropertyUtils.copyProperties(specialistInfo, specialistForm);

        try {
            specialistInfo.setId(IDgenerator.gettNextID("tb_specialist"));
        } catch (IctException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        specialistInfo.setInsertId(getUserSession().getUserId());
        // specialistInfo.setDeptId(getUserSession().getDeptId());

        try {
            specialistService.saveSpecialistInfo(specialistInfo);
            this.getAjaxMessagesJson().setMessage("0", "添加成功");
            logger.debug("增加成功");
        } catch (IctException ex1) {
            this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加失败");
        }
        return RESULT_AJAXJSON;

    }

    /**
     * 
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IctException
     */
    public String edit() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IctException {

        SpecialistInfo tmpSpecialistInfo;
        tmpSpecialistInfo = specialistService.findSpecialistInfoById(Integer.valueOf(this.getSpecialistId()));

        SpecialistForm tmpSpecialistForm = new SpecialistForm();
        PropertyUtils.copyProperties(tmpSpecialistForm, tmpSpecialistInfo);

        this.setSpecialistForm(tmpSpecialistForm);

        this.setActionName("specialist_update");
        return RESULT_SET;
    }

    /**
     * 
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public String view() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        SpecialistInfo tmpSpecialistInfo;
        tmpSpecialistInfo = specialistService.findSpecialistInfoById(Integer.valueOf(this.getSpecialistId()));

        SpecialistForm tmpSpecialistForm = new SpecialistForm();
        PropertyUtils.copyProperties(tmpSpecialistForm, tmpSpecialistInfo);

        this.setSpecialistForm(tmpSpecialistForm);

        this.setActionName("specialist_view");
        return RESULT_VIEW;
    }

    /**
     * 
     * @return
     * @throws IctException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public String update() throws IctException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        SpecialistInfo specialistInfo = new SpecialistInfo();
        PropertyUtils.copyProperties(specialistInfo, specialistForm);
        specialistInfo.setUpdateId(getUserSession().getUserId());
        // System.out.println("(update)userId:"+userInfo.getId());

        try {
            specialistService.updateSpecialistInfo(specialistInfo);
            this.getAjaxMessagesJson().setMessage("0", "修改成功");
            logger.debug("更新成功");
        } catch (Exception ex1) {
            this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改失败");
            ex1.printStackTrace();
        }

        return RESULT_AJAXJSON;

    }

    /**
     * 
     * @return
     * @throws IctException
     */
    public String delete() throws IctException {

        if (Integer.parseInt(this.getSpecialistId()) <= 1) {
            this.getAjaxMessagesJson().setMessage("E_CANNOTDEL", this.getText("error.admin.role.cannotdel"));
            return RESULT_AJAXJSON;
        } else {
            try {
                specialistService.deleteSpecialistInfoById(this.getSpecialistId());
                this.getAjaxMessagesJson().setMessage("0", "删除成功");
            } catch (IctException ex1) {
                this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
            }
        }
        return RESULT_AJAXJSON;
    }

    public String deletes() throws IctException {
        try {
            specialistService.deleteSpecialistInfoById(this.getSpecialistId());
            this.getAjaxMessagesJson().setMessage("0", "删除成功");
        } catch (IctException ex1) {
            this.getAjaxMessagesJson().setMessage("E_SPECIALIST_DELFAILED", "删除失败");
        }
        return RESULT_AJAXJSON;
    }

    /* getter and setter */
    public SpecialistService getSpecialistService() {
        return specialistService;
    }

    public void setSpecialistService(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }

    public SpecialistForm getSpecialistForm() {
        return specialistForm;
    }

    public void setSpecialistForm(SpecialistForm specialistForm) {
        this.specialistForm = specialistForm;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

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

    public PageList getPageList() {
        return pageList;
    }

    public void setPageList(PageList pageList) {
        this.pageList = pageList;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

}
