package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.SmsInfo;
import com.osource.module.fav.service.SmsService;
import com.osource.module.fav.web.form.SmsForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class SmsAction extends BaseAction {
	
	@Autowired
	private SmsService smsService;
	
	private SmsForm smsForm;
	
	private String startDate;
    private String endDate;

	/** action methods **/
	
	public SmsAction(){
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
	    putConditonMap(condition, "startDate", startDate, false);
	    putConditonMap(condition, "endDate", endDate, false);
		putConditonMap(condition, "phone", smsForm.getPhone(), false);
		putConditonMap(condition, "content", smsForm.getContent(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(smsService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加短信信息页面
	 */
	public String add() {
		this.setActionName("sms_save");
		return RESULT_SET;
	}

	/**
	 *  添加短信信息信息
	 */
	public String save() {
		SmsInfo smsInfo = new SmsInfo();
	
		try {
			if(smsForm != null)
				IctUtil.copyProperties(smsInfo, smsForm);
			smsInfo.setInsertId(getUserSession().getUserId());
			smsService.save(smsInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加短信信息成功");
			logger.debug("添加短信信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加短信信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑短信信息页面
	 */
	public String edit() throws IctException {
		SmsInfo smsInfo;
		smsInfo = smsService.findById(this.getId());
		
		SmsForm smsForm = new SmsForm(); 
		IctUtil.copyProperties(smsForm, smsInfo);
		
		this.setSmsForm(smsForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看短信信息页面
	 */
	public String view() throws IctException {
		SmsInfo smsInfo;
		smsInfo = smsService.findById(Integer.valueOf(this.getId()));
		
		SmsForm smsForm = new SmsForm(); 
		IctUtil.copyProperties(smsForm, smsInfo);
		
		this.setSmsForm(smsForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改短信信息信息
	 */
	public String update() {
		SmsInfo smsInfo = new SmsInfo();
		
		try {
			IctUtil.copyProperties(smsInfo, smsForm);
			smsInfo.setUpdateId(getUserSession().getUserId());
			
			smsService.update(smsInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改短信信息成功");
			logger.debug("修改短信信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改短信信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除短信信息信息
	 */
	public String deletes() {
		try {
			smsService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	public SmsForm getSmsForm() {
		return smsForm;
	}

	public void setSmsForm(SmsForm smsForm) {
		this.smsForm = smsForm;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}