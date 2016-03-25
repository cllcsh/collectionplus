package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DailyPolemicVoteInfo;
import com.osource.module.fav.service.DailyPolemicVoteService;
import com.osource.module.fav.web.form.DailyPolemicVoteForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DailyPolemicVoteAction extends BaseAction {
	
	@Autowired
	private DailyPolemicVoteService dailyPolemicVoteService;
	
	private DailyPolemicVoteForm dailyPolemicVoteForm;

	private static Map<String, String> typeMap = CommonCache.getEnumInfos(EnumTypeCons.DAILY_POLEMIC_VOTE_TYPE);
	
	private String startDate;
	private String endDate;
	
	/** action methods **/
	
	public DailyPolemicVoteAction(){
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
        putConditonMap(condition, "dailyPolemicTitle", dailyPolemicVoteForm.getDailyPolemicTitle(), false);
        putConditonMap(condition, "userName", dailyPolemicVoteForm.getUserName(), false);
        putConditonMap(condition, "type", dailyPolemicVoteForm.getType(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dailyPolemicVoteService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加天天论战投票信息页面
	 */
	public String add() {
		this.setActionName("dailyPolemicVote_save");
		return RESULT_SET;
	}

	/**
	 *  添加天天论战投票信息信息
	 */
	public String save() {
		DailyPolemicVoteInfo dailyPolemicVoteInfo = new DailyPolemicVoteInfo();
	
		try {
			if(dailyPolemicVoteForm != null)
				IctUtil.copyProperties(dailyPolemicVoteInfo, dailyPolemicVoteForm);
			dailyPolemicVoteInfo.setInsertId(getUserSession().getUserId());
			dailyPolemicVoteService.save(dailyPolemicVoteInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加天天论战投票信息成功");
			logger.debug("添加天天论战投票信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加天天论战投票信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑天天论战投票信息页面
	 */
	public String edit() throws IctException {
		DailyPolemicVoteInfo dailyPolemicVoteInfo;
		dailyPolemicVoteInfo = dailyPolemicVoteService.findById(this.getId());
		
		DailyPolemicVoteForm dailyPolemicVoteForm = new DailyPolemicVoteForm(); 
		IctUtil.copyProperties(dailyPolemicVoteForm, dailyPolemicVoteInfo);
		
		this.setDailyPolemicVoteForm(dailyPolemicVoteForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看天天论战投票信息页面
	 */
	public String view() throws IctException {
		DailyPolemicVoteInfo dailyPolemicVoteInfo;
		dailyPolemicVoteInfo = dailyPolemicVoteService.findById(Integer.valueOf(this.getId()));
		
		DailyPolemicVoteForm dailyPolemicVoteForm = new DailyPolemicVoteForm(); 
		IctUtil.copyProperties(dailyPolemicVoteForm, dailyPolemicVoteInfo);
		
		this.setDailyPolemicVoteForm(dailyPolemicVoteForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改天天论战投票信息信息
	 */
	public String update() {
		DailyPolemicVoteInfo dailyPolemicVoteInfo = new DailyPolemicVoteInfo();
		
		try {
			IctUtil.copyProperties(dailyPolemicVoteInfo, dailyPolemicVoteForm);
			dailyPolemicVoteInfo.setUpdateId(getUserSession().getUserId());
			
			dailyPolemicVoteService.update(dailyPolemicVoteInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改天天论战投票信息成功");
			logger.debug("修改天天论战投票信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改天天论战投票信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除天天论战投票信息信息
	 */
	public String deletes() {
		try {
			dailyPolemicVoteService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DailyPolemicVoteService getDailyPolemicVoteService() {
		return dailyPolemicVoteService;
	}

	public void setDailyPolemicVoteService(DailyPolemicVoteService dailyPolemicVoteService) {
		this.dailyPolemicVoteService = dailyPolemicVoteService;
	}

	public DailyPolemicVoteForm getDailyPolemicVoteForm() {
		return dailyPolemicVoteForm;
	}

	public void setDailyPolemicVoteForm(DailyPolemicVoteForm dailyPolemicVoteForm) {
		this.dailyPolemicVoteForm = dailyPolemicVoteForm;
	}

	public Map<String, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {
		this.typeMap = typeMap;
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