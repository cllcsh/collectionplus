package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.UserFansInfo;
import com.osource.module.fav.service.UserFansService;
import com.osource.module.fav.web.form.UserFansForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class UserFansAction extends BaseAction {
	
	@Autowired
	private UserFansService userFansService;
	
	private UserFansForm userFansForm;

	private String startDate;
    private String endDate;
    
	/** action methods **/
	
	public UserFansAction(){
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
		putConditonMap(condition, "userName", userFansForm.getUserName(), false);
		putConditonMap(condition, "fanName", userFansForm.getFanName(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(userFansService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加粉丝信息页面
	 */
	public String add() {
		this.setActionName("userFans_save");
		return RESULT_SET;
	}

	/**
	 *  添加粉丝信息信息
	 */
	public String save() {
		UserFansInfo userFansInfo = new UserFansInfo();
	
		try {
			if(userFansForm != null)
				IctUtil.copyProperties(userFansInfo, userFansForm);
			userFansInfo.setInsertId(getUserSession().getUserId());
			userFansService.save(userFansInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加粉丝信息成功");
			logger.debug("添加粉丝信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加粉丝信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑粉丝信息页面
	 */
	public String edit() throws IctException {
		UserFansInfo userFansInfo;
		userFansInfo = userFansService.findById(this.getId());
		
		UserFansForm userFansForm = new UserFansForm(); 
		IctUtil.copyProperties(userFansForm, userFansInfo);
		
		this.setUserFansForm(userFansForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看粉丝信息页面
	 */
	public String view() throws IctException {
		UserFansInfo userFansInfo;
		userFansInfo = userFansService.findById(Integer.valueOf(this.getId()));
		
		UserFansForm userFansForm = new UserFansForm(); 
		IctUtil.copyProperties(userFansForm, userFansInfo);
		
		this.setUserFansForm(userFansForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改粉丝信息信息
	 */
	public String update() {
		UserFansInfo userFansInfo = new UserFansInfo();
		
		try {
			IctUtil.copyProperties(userFansInfo, userFansForm);
			userFansInfo.setUpdateId(getUserSession().getUserId());
			
			userFansService.update(userFansInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改粉丝信息成功");
			logger.debug("修改粉丝信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改粉丝信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除粉丝信息信息
	 */
	public String deletes() {
		try {
			userFansService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public UserFansService getUserFansService() {
		return userFansService;
	}

	public void setUserFansService(UserFansService userFansService) {
		this.userFansService = userFansService;
	}

	public UserFansForm getUserFansForm() {
		return userFansForm;
	}

	public void setUserFansForm(UserFansForm userFansForm) {
		this.userFansForm = userFansForm;
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