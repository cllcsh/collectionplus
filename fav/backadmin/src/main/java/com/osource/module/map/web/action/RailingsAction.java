/**
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingsAction.java
 * @since v0.1
 */
package com.osource.module.map.web.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.module.map.model.AreaCodeBean;
import com.osource.module.map.model.RailingsCoordinateInfo;
import com.osource.module.map.model.RailingsInfo;
import com.osource.module.map.service.RailingsCoordinateService;
import com.osource.module.map.service.RailingsService;
import com.osource.module.map.web.form.RailingsForm;
import com.osource.module.map.web.session.CenterPointUserSession;

/**
 * 中心点Action
 * 
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingsAction.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class RailingsAction extends BaseAction {

    private static final String AREACODE_PARENTCODE = PropertiesManager.getProperty("common.properties",
                                                                                    "AREACODE_PARENTCODE").toString();

    private RailingsForm form;
    // private RailingsService railingsService = BeanProvider.getBean("railingsService");
    @Autowired
    private RailingsService railingsService;
    @Autowired
    private RailingsCoordinateService rcs;

    private Integer code;
    private List<RailingsInfo> railingsList;// 下拉列表
    private String nids;

    /**
     * 围栏管理整个页面跳转
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
     * 围栏管理Eurl弹出框
     * 
     * @return String
     * 
     */
    public String addCrawl() throws IctException {
        if (form == null) {
            form = new RailingsForm();
        }
        return "pop";
    }

    /**
     * 公用围栏管理弹出框
     * 
     * @return String
     * 
     */
    public String addCrawlCommon() throws IctException {
        if (form == null) {
            form = new RailingsForm();
        }

        form.setLongitudes(request.getParameter("log"));
        form.setLatitudes(request.getParameter("lat"));
        form.setOverlayType(request.getParameter("laytype"));

        return "popcommon";
    }

    /**
     * 围栏管理初始化
     * 
     * @return String
     * @throws IctException
     * @throws Exception
     */
    public String init() throws Exception {
        if (form == null) {
            form = new RailingsForm();
        }

        if ((railingsService.getAreaCodeList() == null) || (railingsService.getAreaCodeList().size() <= 0)) {
            railingsService.getAreaCode();
        }

        form.setAreaCodeList(railingsService.getDefaultAreaCode());
        AreaCodeBean ac = railingsService.getAreaBean(AREACODE_PARENTCODE);
        if (ac == null) {
            ac = new AreaCodeBean();
            ac.setAreaCode(AREACODE_PARENTCODE);
            ac.setAreaName("中国");
        }
        form.setAreaCode(ac);
        return RESULT_INIT;
    }

    /**
     * 围栏管理地图初始化
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
     * 围栏管理删除
     * 
     * @return String
     * 
     */
    public String deletes() {
        String ids = form.getIds();
        Integer updateId = getUserSession().getUserId();
        try {
            railingsService.deletesRailingsInfo(ids, updateId);
            this.getAjaxMessagesJson().setMessage("0", "删除成功！");
        } catch (IctException e) {
            this.getAjaxMessagesJson().setMessage("TERMINIAL_SAVE_FAILED", "删除失败！");
            e.printStackTrace();
        }
        return RESULT_AJAXJSON;
    }

    /**
     * 围栏管理围栏保存
     * 
     * @return String
     * 
     */
    public String saveCrawl() {
        String longitudes = form.getLongitudes();
        String latitudes = form.getLatitudes();
        // String overlayType = form.getOverlayType();
        // 构建 ri
        RailingsInfo ri = new RailingsInfo();
        ri.setName(form.getRailingsName());
        ri.setType(form.getRailingsType());
        // ri.setDeptId(getUserSession().getDeptId());
        ri.setInsertId(getUserSession().getUserId());
        // 构建 List
        List<RailingsCoordinateInfo> coordinateList = new ArrayList<RailingsCoordinateInfo>();
        String[] longArr = longitudes.split(",");
        String[] latitArr = latitudes.split(",");
        for (int i = 0; i < longArr.length; i++) {
            RailingsCoordinateInfo rci = new RailingsCoordinateInfo();
            rci.setLongitude(Double.parseDouble(longArr[i]));
            rci.setLatitude(Double.parseDouble(latitArr[i]));
            // rci.setDeptId(getUserSession().getDeptId());
            rci.setInsertId(getUserSession().getUserId());
            coordinateList.add(rci);
        }
        ri.setCoordinateList(coordinateList);

        try {
            ri = railingsService.saveRailingsInfo(ri);
            this.getAjaxMessagesJson().setMessage("0", "保存成功！", ri.getId().toString());
        } catch (Exception e) {
            this.getAjaxMessagesJson().setMessage("TERMINIAL_SAVE_FAILED", "保存失败！");
            e.printStackTrace();
        }
        return RESULT_AJAXJSON;
    }

    /**
     * 围栏管理获取围栏数据
     * 
     * @return String
     * 
     */
    public String getData() throws IctException {
        Integer railingsId = form.getId();
        List<RailingsCoordinateInfo> list = rcs.findCoordinateInfoByRailingsId(railingsId);
        return returnJsonString(list);
    }

    /**
     * 获取围栏信息
     * 
     * @return String
     * 
     */
    public String getRailingInfo() throws IctException {
        List<RailingsInfo> list = railingsService.findRailingsInfoByDeptId(getUserSession().getDeptId());
        return returnJsonString(list);
    }

    /**
     * 围栏管理弹出框
     * 
     * @return String
     * 
     */
    public String dialog() throws IctException {
        if (form == null) {
            form = new RailingsForm();
        }
        form.setLongitude(Double.parseDouble(request.getParameter("Log")));
        form.setLatitude(Double.parseDouble(request.getParameter("Lat")));
        request.setAttribute("type", request.getParameter("type"));
        return "dialog";
    }

    /**
     * 得到行政区域围栏
     * 
     * @return String
     * 
     */
    public String getAreaData() throws Exception {

        String returnStr = "";
        String text = "";

        Integer parentCode = code;
        List<AreaCodeBean> list = railingsService.getAreaCodeByParent(parentCode.toString());
        for (int i = 0; i < list.size(); i++) {
            returnStr += list.get(i).getAreaCode() + "&" + list.get(i).getAreaName() + "&"
                    + list.get(i).getParentCode() + "#";
        }

        // 反查父行政区域
        List<AreaCodeBean> acList = new ArrayList<AreaCodeBean>();
        List<AreaCodeBean> parentList = railingsService.getParentAreaCode(parentCode.toString(), acList);
        for (int i = 0; i < parentList.size(); i++) {
            text += parentList.get(i).getAreaCode() + "&" + parentList.get(i).getAreaName() + "&"
                    + parentList.get(i).getParentCode() + "#";
        }

        // 根据code获取区域名称
        AreaCodeBean acb = railingsService.getAreaBean(code.toString());
        String name = acb.getAreaName();

        this.ajaxMessagesJson.setMessage(name, returnStr, text);
        return "ajaxjson";
    }

    /**
     * @return the form
     */
    public RailingsForm getForm() {
        return form;
    }

    /**
     * @param form
     *            the form to set
     */
    public void setForm(RailingsForm form) {
        this.form = form;
    }

    public RailingsService getRailingsService() {
        return railingsService;
    }

    public void setRailingsService(RailingsService railingsService) {
        this.railingsService = railingsService;
    }

    public List<RailingsInfo> getRailingsList() {
        return railingsList;
    }

    public void setRailingsList(List<RailingsInfo> railingsList) {
        this.railingsList = railingsList;
    }

    public RailingsCoordinateService getRcs() {
        return rcs;
    }

    public void setRcs(RailingsCoordinateService rcs) {
        this.rcs = rcs;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNids() {
        return nids;
    }

    public void setNids(String nids) {
        this.nids = nids;
    }
}
