package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DynamicLikeInfo;
import com.osource.module.fav.service.DynamicLikeService;
import com.osource.module.fav.web.form.DynamicLikeForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicLikeAction extends BaseAction {
	
	@Autowired
	private DynamicLikeService dynamicLikeService;
	
	private DynamicLikeForm dynamicLikeForm;

	private String startDate;
    private String endDate;
    
	/** action methods **/
	
	public DynamicLikeAction(){
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
		try {
			Map condition = new HashMap();
			putConditonMap(condition, "startDate", startDate, false);
			putConditonMap(condition, "endDate", endDate, false);	
			putConditonMap(condition, "dynamicContent", dynamicLikeForm.getDynamicContent(), false);		
			putConditonMap(condition, "friendName", dynamicLikeForm.getFriendName(), false);		
			Pages pages = new Pages(this.getPage(),this.getLimit());
			this.setPageList(dynamicLikeService.findByCondition(condition, pages));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加动态点赞信息页面
	 */
	public String add() {
		this.setActionName("dynamicLike_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态点赞信息信息
	 */
	public String save() {
		DynamicLikeInfo dynamicLikeInfo = new DynamicLikeInfo();
	
		try {
			if(dynamicLikeForm != null)
				IctUtil.copyProperties(dynamicLikeInfo, dynamicLikeForm);
			dynamicLikeInfo.setInsertId(getUserSession().getUserId());
			dynamicLikeService.save(dynamicLikeInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加动态点赞信息成功");
			logger.debug("添加动态点赞信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加动态点赞信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态点赞信息页面
	 */
	public String edit() throws IctException {
		DynamicLikeInfo dynamicLikeInfo;
		dynamicLikeInfo = dynamicLikeService.findById(this.getId());
		
		DynamicLikeForm dynamicLikeForm = new DynamicLikeForm(); 
		IctUtil.copyProperties(dynamicLikeForm, dynamicLikeInfo);
		
		this.setDynamicLikeForm(dynamicLikeForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态点赞信息页面
	 */
	public String view() throws IctException {
		DynamicLikeInfo dynamicLikeInfo;
		dynamicLikeInfo = dynamicLikeService.findById(Integer.valueOf(this.getId()));
		
		DynamicLikeForm dynamicLikeForm = new DynamicLikeForm(); 
		IctUtil.copyProperties(dynamicLikeForm, dynamicLikeInfo);
		
		this.setDynamicLikeForm(dynamicLikeForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态点赞信息信息
	 */
	public String update() {
		DynamicLikeInfo dynamicLikeInfo = new DynamicLikeInfo();
		
		try {
			IctUtil.copyProperties(dynamicLikeInfo, dynamicLikeForm);
			dynamicLikeInfo.setUpdateId(getUserSession().getUserId());
			
			dynamicLikeService.update(dynamicLikeInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改动态点赞信息成功");
			logger.debug("修改动态点赞信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改动态点赞信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态点赞信息信息
	 */
	public String deletes() {
		try {
			dynamicLikeService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicLikeService getDynamicLikeService() {
		return dynamicLikeService;
	}

	public void setDynamicLikeService(DynamicLikeService dynamicLikeService) {
		this.dynamicLikeService = dynamicLikeService;
	}

	public DynamicLikeForm getDynamicLikeForm() {
		return dynamicLikeForm;
	}

	public void setDynamicLikeForm(DynamicLikeForm dynamicLikeForm) {
		this.dynamicLikeForm = dynamicLikeForm;
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