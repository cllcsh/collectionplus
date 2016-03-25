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

import com.osource.module.fav.model.TodayAppreciationInfo;
import com.osource.module.fav.service.TodayAppreciationService;
import com.osource.module.fav.web.form.TodayAppreciationForm;
import com.osource.base.web.action.BaseAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class TodayAppreciationAction extends BaseAction {
	
	@Autowired
	private TodayAppreciationService todayAppreciationService;
	
	private TodayAppreciationForm todayAppreciationForm;
	
	private static Map<String, String> isShowMap = CommonCache.getEnumInfos(EnumTypeCons.TODAY_APPRECIATION_IS_SHOW_TYPE);
	
	private static Map<Integer, Integer> orderMap = new LinkedHashMap<Integer, Integer>();
	static{
		for (int i = 1; i < 10; i++) {
			orderMap.put(i, i);
		}
	}
	private String startDate;
	private String endDate;
	
	/** action methods **/
	
	public TodayAppreciationAction(){
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
	    putConditonMap(condition, "title", todayAppreciationForm.getTitle(), false);
	    putConditonMap(condition, "content", todayAppreciationForm.getContent(), false);
	    putConditonMap(condition, "displayOrder", todayAppreciationForm.getDisplayOrder(), true);
	    putConditonMap(condition, "isShow", todayAppreciationForm.getIsShow(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(todayAppreciationService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加今日鉴赏信息页面
	 */
	public String add() {
		this.setActionName("todayAppreciation_save");
		return RESULT_SET;
	}

	/**
	 *  添加今日鉴赏信息信息
	 */
	public String save() {
		TodayAppreciationInfo todayAppreciationInfo = new TodayAppreciationInfo();
	
		try {
			if(todayAppreciationForm != null)
				IctUtil.copyProperties(todayAppreciationInfo, todayAppreciationForm);
			todayAppreciationInfo.setInsertId(getUserSession().getUserId());
			todayAppreciationService.save(todayAppreciationInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加今日鉴赏信息成功");
			logger.debug("添加今日鉴赏信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加今日鉴赏信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑今日鉴赏信息页面
	 */
	public String edit() throws IctException {
		TodayAppreciationInfo todayAppreciationInfo;
		todayAppreciationInfo = todayAppreciationService.findById(this.getId());
		
		TodayAppreciationForm todayAppreciationForm = new TodayAppreciationForm(); 
		IctUtil.copyProperties(todayAppreciationForm, todayAppreciationInfo);
		
		this.setTodayAppreciationForm(todayAppreciationForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看今日鉴赏信息页面
	 */
	public String view() throws IctException {
		TodayAppreciationInfo todayAppreciationInfo;
		todayAppreciationInfo = todayAppreciationService.findById(Integer.valueOf(this.getId()));
		
		TodayAppreciationForm todayAppreciationForm = new TodayAppreciationForm(); 
		IctUtil.copyProperties(todayAppreciationForm, todayAppreciationInfo);
		
		this.setTodayAppreciationForm(todayAppreciationForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改今日鉴赏信息信息
	 */
	public String update() {
		TodayAppreciationInfo todayAppreciationInfo = new TodayAppreciationInfo();
		
		try {
			IctUtil.copyProperties(todayAppreciationInfo, todayAppreciationForm);
			todayAppreciationInfo.setUpdateId(getUserSession().getUserId());
			
			todayAppreciationService.update(todayAppreciationInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改今日鉴赏信息成功");
			logger.debug("修改今日鉴赏信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改今日鉴赏信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除今日鉴赏信息信息
	 */
	public String deletes() {
		try {
			todayAppreciationService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public TodayAppreciationService getTodayAppreciationService() {
		return todayAppreciationService;
	}

	public void setTodayAppreciationService(TodayAppreciationService todayAppreciationService) {
		this.todayAppreciationService = todayAppreciationService;
	}

	public TodayAppreciationForm getTodayAppreciationForm() {
		return todayAppreciationForm;
	}

	public void setTodayAppreciationForm(TodayAppreciationForm todayAppreciationForm) {
		this.todayAppreciationForm = todayAppreciationForm;
	}

	public Map<String, String> getIsShowMap() {
		return isShowMap;
	}

	public void setIsShowMap(Map<String, String> isShowMap) {
		this.isShowMap = isShowMap;
	}

	public Map<Integer, Integer> getOrderMap() {
		return orderMap;
	}

	public void setOrderMap(Map<Integer, Integer> orderMap) {
		this.orderMap = orderMap;
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