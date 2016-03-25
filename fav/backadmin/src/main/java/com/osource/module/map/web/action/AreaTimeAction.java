package com.osource.module.map.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.model.AreaTimeBean;
import com.osource.module.map.service.AreaTimeService;
import com.osource.module.map.service.LocaQueryService;
import com.osource.module.map.web.form.AreaTimeForm;
import com.osource.module.map.web.session.CenterPointUserSession;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AreaTimeAction extends BaseAction {
	
	@Autowired
	private AreaTimeService areaTimeService;
	@Autowired
	private LocaQueryService locaQueryService;
	private String startTime;
	private String endTime;
	private String new_log;
	private String new_lat;
	
	private AreaTimeForm areaTimeForm;

	/** action methods **/
	
	public AreaTimeAction(){
		super();
	}
	/**
	 * 整个页面跳转
	 * @return String
	 * @throws IctException
	 * 
	 */
	public String frame() throws IctException {
		if(getUserSession().match(CenterPointUserSession.class)){
			CenterPointUserSession centerPointUserSession = (CenterPointUserSession) getUserSession().getUserSession(CenterPointUserSession.class);
			this.pushValueStack("centerPointUserSession", centerPointUserSession);
		}
		return "frame";
	}
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
	
		return RESULT_INIT;
	}

	/**
	 *  跳转到添加定位查询页面
	 */
	public String add() {
		this.setActionName("areaTime_save");
		return RESULT_SET;
	}
	/**
	 * 获取人员定位信息列表
	 * @return String
	 * 
	 */
	public String query() throws NumberFormatException, IctException, java.text.ParseException{
		Pages pages = new Pages(this.getPage(),this.getLimit());
		String startTime = this.getStartTime().replace("@", " ");
		String endTime = this.getEndTime().replace("@", " ");
		PageList result = locaQueryService.findLocationInfoInDateNew(new_log,new_lat,startTime, endTime, pages);
		Map map = new HashMap();
		map.put("total", result.size());
		map.put("rows", result);
		return returnJsonString(map);
	}
	/**
	 *  添加定位查询信息
	 */
	public String save() {
		AreaTimeBean areaTimeBean = new AreaTimeBean();
	
		try {
			if(areaTimeForm != null)
				IctUtil.copyProperties(areaTimeBean, areaTimeForm);
			
			areaTimeService.save(areaTimeBean);
			this.getAjaxMessagesJson().setMessage("0", "添加定位查询成功");
			logger.debug("添加定位查询成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加定位查询失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑定位查询页面
	 */
	public String edit() throws IctException {
		AreaTimeBean areaTimeBean;
		areaTimeBean = areaTimeService.findById(this.getId());
		
		AreaTimeForm areaTimeForm = new AreaTimeForm(); 
		IctUtil.copyProperties(areaTimeForm, areaTimeBean);
		
		this.setAreaTimeForm(areaTimeForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看定位查询页面
	 */
	public String view() throws IctException {
		AreaTimeBean areaTimeBean;
		areaTimeBean = areaTimeService.findById(Integer.valueOf(this.getId()));
		
		AreaTimeForm areaTimeForm = new AreaTimeForm(); 
		IctUtil.copyProperties(areaTimeForm, areaTimeBean);
		
		this.setAreaTimeForm(areaTimeForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改定位查询信息
	 */
	public String update() {
		AreaTimeBean areaTimeBean = new AreaTimeBean();
		
		try {
			IctUtil.copyProperties(areaTimeBean, areaTimeForm);
			areaTimeBean.setUpdateId(getUserSession().getUserId());
			
			areaTimeService.update(areaTimeBean);
			this.getAjaxMessagesJson().setMessage("0", "修改定位查询成功");
			logger.debug("修改定位查询成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改定位查询失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除定位查询信息
	 */
	public String deletes() {
		try {
			areaTimeService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AreaTimeService getAreaTimeService() {
		return areaTimeService;
	}

	public void setAreaTimeService(AreaTimeService areaTimeService) {
		this.areaTimeService = areaTimeService;
	}

	public AreaTimeForm getAreaTimeForm() {
		return areaTimeForm;
	}

	public void setAreaTimeForm(AreaTimeForm areaTimeForm) {
		this.areaTimeForm = areaTimeForm;
	}
	public LocaQueryService getLocaQueryService() {
		return locaQueryService;
	}
	public void setLocaQueryService(LocaQueryService locaQueryService) {
		this.locaQueryService = locaQueryService;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNew_log() {
		return new_log;
	}
	public void setNew_log(String newLog) {
		new_log = newLog;
	}
	public String getNew_lat() {
		return new_lat;
	}
	public void setNew_lat(String newLat) {
		new_lat = newLat;
	}

}