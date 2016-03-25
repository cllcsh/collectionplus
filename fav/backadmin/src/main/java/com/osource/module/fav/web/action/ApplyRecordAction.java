package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.ApplyRecordInfo;
import com.osource.module.fav.service.ApplyRecordService;
import com.osource.module.fav.web.form.ApplyRecordForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class ApplyRecordAction extends BaseAction {
	
	@Autowired
	private ApplyRecordService applyRecordService;
	
	private ApplyRecordForm applyRecordForm;

	private static Map<String, String> statusMap = CommonCache.getEnumInfos(EnumTypeCons.COLLECTION_STATUS_TYPE);
	
	private String startDate;
    private String endDate;
    
	/** action methods **/
	
	public ApplyRecordAction(){
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
		putConditonMap(condition, "applierName", applyRecordForm.getApplierName(), false);
		putConditonMap(condition, "collectionTitle", applyRecordForm.getCollectionTitle(), false);
		putConditonMap(condition, "status", applyRecordForm.getStatus(), true);
		putConditonMap(condition, "applyType", applyRecordForm.getApplyType(), false);
		putConditonMap(condition, "startDate", startDate, false);
	    putConditonMap(condition, "endDate", endDate, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(applyRecordService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加审批记录信息页面
	 */
	public String add() {
		this.setActionName("applyRecord_save");
		return RESULT_SET;
	}

	/**
	 *  添加审批记录信息信息
	 */
	public String save() {
		ApplyRecordInfo applyRecordInfo = new ApplyRecordInfo();
	
		try {
			if(applyRecordForm != null)
				IctUtil.copyProperties(applyRecordInfo, applyRecordForm);
			applyRecordInfo.setInsertId(getUserSession().getUserId());
			applyRecordService.save(applyRecordInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加审批记录信息成功");
			logger.debug("添加审批记录信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加审批记录信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑审批记录信息页面
	 */
	public String edit() throws IctException {
		ApplyRecordInfo applyRecordInfo;
		applyRecordInfo = applyRecordService.findById(this.getId());
		
		ApplyRecordForm applyRecordForm = new ApplyRecordForm(); 
		IctUtil.copyProperties(applyRecordForm, applyRecordInfo);
		
		this.setApplyRecordForm(applyRecordForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看审批记录信息页面
	 */
	public String view() throws IctException {
		ApplyRecordInfo applyRecordInfo;
		applyRecordInfo = applyRecordService.findById(Integer.valueOf(this.getId()));
		
		ApplyRecordForm applyRecordForm = new ApplyRecordForm(); 
		IctUtil.copyProperties(applyRecordForm, applyRecordInfo);
		
		this.setApplyRecordForm(applyRecordForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改审批记录信息信息
	 */
	public String update() {
		ApplyRecordInfo applyRecordInfo = new ApplyRecordInfo();
		
		try {
			IctUtil.copyProperties(applyRecordInfo, applyRecordForm);
			applyRecordInfo.setUpdateId(getUserSession().getUserId());
			
			applyRecordService.update(applyRecordInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改审批记录信息成功");
			logger.debug("修改审批记录信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改审批记录信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除审批记录信息信息
	 */
	public String deletes() {
		try {
			applyRecordService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public ApplyRecordService getApplyRecordService() {
		return applyRecordService;
	}

	public void setApplyRecordService(ApplyRecordService applyRecordService) {
		this.applyRecordService = applyRecordService;
	}

	public ApplyRecordForm getApplyRecordForm() {
		return applyRecordForm;
	}

	public void setApplyRecordForm(ApplyRecordForm applyRecordForm) {
		this.applyRecordForm = applyRecordForm;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
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