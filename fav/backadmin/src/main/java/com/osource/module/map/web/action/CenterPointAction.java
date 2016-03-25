/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointAction.java
 * @since v0.1
 */
package com.osource.module.map.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.module.map.model.CenterPointBean;
import com.osource.module.map.service.CenterPointService;
import com.osource.module.map.web.form.CenterPointForm;
import com.osource.module.map.web.session.CenterPointUserSession;

/**
 * 中心点Action
 * 
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointAction.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CenterPointAction extends BaseAction {
    @Autowired
    private CenterPointService centerPointService;
    private CenterPointForm form;

    public CenterPointAction() {
        super();
    }

    /**
     * 中心点整个页面跳转
     * 
     * @return String
     * @throws IctException
     * 
     */
    public String frame() throws IctException {
        if (getUserSession().match(CenterPointUserSession.class)) {
            CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession()
                    .getUserSession(CenterPointUserSession.class);
            this.pushValueStack("centerPointUserSession", centerPointUserSession);
        }

        return "frame";
    }

    /**
     * 中心点初始化
     * 
     * @return String
     * @throws IctException
     * 
     */
    public String init() throws IctException {
        if (form == null) {
            form = new CenterPointForm();
        }
        if (getUserSession().getUserSession(CenterPointUserSession.class) != null) {
            CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession()
                    .getUserSession(CenterPointUserSession.class);
            CenterPointBean cpi = centerPointUserSession.getCenterPointInfo();
            form.setLongitude(cpi.getLongitude());
            form.setLatitude(cpi.getLatitude());
            form.setZoomLevel(cpi.getZoomLevel());
        }
        return RESULT_DEFAULT;
    }

    /**
     * 中心点保存
     * 
     * @return String
     * @throws IctException
     * 
     */
    public String save() throws IctException {
        if (form == null) {
            form = new CenterPointForm();
        }
        // 查找数据库
        CenterPointBean cpi = centerPointService.findCenterPointInfoByUserId(getUserSession().getUserId());
        form.setInsertId(getUserSession().getUserId());
        // form.setInsertDate(new Date());
        form.setDeptId(getUserSession().getDeptId());

        try {
            if (cpi == null) { // 如果没有记录，插入操作
                cpi = cloneForm(form);
                cpi = centerPointService.saveCenterPointInfo(cpi);
            } else { // 更新操作
                cpi.setUpdateId(getUserSession().getUserId());
                cpi.setLongitude(form.getLongitude());
                cpi.setLatitude(form.getLatitude());
                cpi.setZoomLevel(form.getZoomLevel());
                cpi = centerPointService.updateCenterPointInfo(cpi);
            }
            if (getUserSession().getUserSession(CenterPointUserSession.class) != null) {
                CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession()
                        .getUserSession(CenterPointUserSession.class);
                centerPointUserSession.setCenterPointInfo(cpi);
            }

            form.setId(cpi.getId());

            this.ajaxMessagesJson.setMessage("0", "保存成功");
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            this.ajaxMessagesJson.setMessage("-1", "保存失败");
        }

        return RESULT_AJAXJSON;
    }

    /**
     * 中心点弹出对话框
     * 
     * @return String
     * @throws IctException
     * 
     */
    public String dialog() {
        if (form == null) {
            form = new CenterPointForm();
        }
        try {
            form.setLongitude(Double.parseDouble(request.getParameter("log")));
            form.setLatitude(Double.parseDouble(request.getParameter("lat")));
            form.setType(request.getParameter("laytype"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "dialog";
    }

    /**
     * 中心点地图初始化
     * 
     * @return String
     * @throws IctException
     * 
     */
    public String map() {
        if (getUserSession().match(CenterPointUserSession.class)) {
            CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession()
                    .getUserSession(CenterPointUserSession.class);
            this.pushValueStack("centerPointUserSession", centerPointUserSession);
        }
        return RESULT_MAP;
    }

    /**
     * @return the form
     */
    public CenterPointForm getForm() {
        return form;
    }

    /**
     * @param form
     *            the form to set
     */
    public void setForm(CenterPointForm form) {
        this.form = form;
    }

    /**
     * 将form克隆到module中
     * 
     * @param cpf
     * @return CenterPointInfo
     * @throws IctException
     * 
     */
    private CenterPointBean cloneForm(CenterPointForm cpf) throws IctException {
        CenterPointBean cpi = new CenterPointBean();
        // cpi.setDeptId(cpf.getDeptId());
        cpi.setId(cpf.getId());
        cpi.setInsertDate(cpf.getInsertDate());
        cpi.setInsertId(cpf.getInsertId());
        cpi.setLatitude(cpf.getLatitude());
        cpi.setLongitude(cpf.getLongitude());
        cpi.setUpdateDate(cpf.getUpdateDate());
        cpi.setUpdateId(cpf.getUpdateId());
        cpi.setUseFlag(cpf.getUseFlag());
        cpi.setZoomLevel(cpf.getZoomLevel());
        return cpi;
    }

    /**
     * 将module克隆到form中
     * 
     * @param cpi
     * @return CenterPointForm
     * @throws IctException
     * 
     */
    @SuppressWarnings("unused")
    private CenterPointForm cloneModule(CenterPointBean cpi) throws IctException {
        CenterPointForm cpf = new CenterPointForm();
        // cpf.setDeptId(cpi.getDeptId());
        cpf.setId(cpi.getId());
        cpf.setInsertDate(cpi.getInsertDate());
        cpf.setInsertId(cpi.getInsertId());
        cpf.setLatitude(cpi.getLatitude());
        cpf.setLongitude(cpi.getLongitude());
        cpf.setUpdateDate(cpi.getUpdateDate());
        cpf.setUpdateId(cpi.getUpdateId());
        cpf.setUseFlag(cpi.getUseFlag());
        cpf.setZoomLevel(cpi.getZoomLevel());
        return cpf;
    }
}
