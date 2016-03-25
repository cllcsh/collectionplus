package com.osource.module.map.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.web.action.BaseAction;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.module.map.model.ActualBean;
import com.osource.module.map.service.ActualService;
import com.osource.module.map.web.form.ActualForm;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class ActualAction extends BaseAction {
	
	@Autowired
	private ActualService actualService;
	
	private ActualForm actualForm;

	/** action methods **/
	
	public ActualAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
	
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
		
		if(null == actualForm){
			actualForm = new ActualForm();
		}
		if(actualForm.getDeptId() == null){
			condition.put("deptId", getUserSession().getDeptId());
		}
		else{
			condition.put("deptId", actualForm.getDeptId());
		}
			
		condition.put("criminalName", actualForm.getCriminalName());
		if (!"0".equals(actualForm.getSex())){
			condition.put("sex", actualForm.getSex());
		}
		condition.put("status", actualForm.getStatus());
		if ("0".equals(actualForm.getLocCode())){
			condition.put("locCode", actualForm.getLocCode());
		}
		else if ("1".equals(actualForm.getLocCode())){
			condition.put("locFailCode", actualForm.getLocCode());
		}
		else{
			condition.put("locCode", actualForm.getLocCode());
		}
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(actualService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加实时监控页面
	 */
	public String add() {
		this.setActionName("actual_save");
		return RESULT_SET;
	}

	/**
	 *  添加实时监控信息
	 */
	public String save() {
		ActualBean actualBean = new ActualBean();
	
		try {
			if(actualForm != null)
				IctUtil.copyProperties(actualBean, actualForm);
			
			actualService.save(actualBean);
			this.getAjaxMessagesJson().setMessage("0", "添加实时监控成功");
			logger.debug("添加实时监控成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加实时监控失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑实时监控页面
	 */
	public String edit() throws IctException {
		ActualBean actualBean;
		actualBean = actualService.findById(this.getId());
		
		ActualForm actualForm = new ActualForm(); 
		IctUtil.copyProperties(actualForm, actualBean);
		
		this.setActualForm(actualForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看实时监控页面
	 */
	public String view() throws IctException {
		ActualBean actualBean;
		actualBean = actualService.findById(Integer.valueOf(this.getId()));
		
		ActualForm actualForm = new ActualForm(); 
		IctUtil.copyProperties(actualForm, actualBean);
		
		this.setActualForm(actualForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改实时监控信息
	 */
	public String update() {
		ActualBean actualBean = new ActualBean();
		
		try {
			IctUtil.copyProperties(actualBean, actualForm);
			actualBean.setUpdateId(getUserSession().getUserId());
			
			actualService.update(actualBean);
			this.getAjaxMessagesJson().setMessage("0", "修改实时监控成功");
			logger.debug("修改实时监控成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改实时监控失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除实时监控信息
	 */
	public String deletes() {
		try {
			actualService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	public String export() throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();

		if(null == actualForm){
			actualForm = new ActualForm();
		}
		if(actualForm.getDeptId() == null){
			condition.put("deptId", getUserSession().getDeptId());
		}
		else{
			condition.put("deptId", actualForm.getDeptId());
		}
			
		condition.put("criminalName", actualForm.getCriminalName());
//		if (!"0".equals(actualForm.getSex())){
//			condition.put("sex", actualForm.getSex());
//		}
		condition.put("status", 1);
		
		List<ActualBean> exportList = actualService.findByCondition(condition);
		Map dataMap = new HashMap();
		dataMap.put("exportList", exportList);
		return exportExcel("ActualExport", dataMap, "ActualExport.xls");
	}
	
	/** getter and setter methods **/
	
	public ActualService getActualService() {
		return actualService;
	}

	public void setActualService(ActualService actualService) {
		this.actualService = actualService;
	}

	public ActualForm getActualForm() {
		return actualForm;
	}

	public void setActualForm(ActualForm actualForm) {
		this.actualForm = actualForm;
	}

}