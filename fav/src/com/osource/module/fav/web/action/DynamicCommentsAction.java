package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DynamicCommentsInfo;
import com.osource.module.fav.service.DynamicCommentsService;
import com.osource.module.fav.web.form.DynamicCommentsForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicCommentsAction extends BaseAction {
	
	@Autowired
	private DynamicCommentsService dynamicCommentsService;
	
	private DynamicCommentsForm dynamicCommentsForm;

	/** action methods **/
	
	public DynamicCommentsAction(){
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
		
		/*
	 		TODO:此处把查询条件添加到Map中
	 	*/
		
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dynamicCommentsService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加动态评论信息页面
	 */
	public String add() {
		this.setActionName("dynamicComments_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态评论信息信息
	 */
	public String save() {
		DynamicCommentsInfo dynamicCommentsInfo = new DynamicCommentsInfo();
	
		try {
			if(dynamicCommentsForm != null)
				IctUtil.copyProperties(dynamicCommentsInfo, dynamicCommentsForm);
			dynamicCommentsInfo.setInsertId(getUserSession().getUserId());
			dynamicCommentsService.save(dynamicCommentsInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加动态评论信息成功");
			logger.debug("添加动态评论信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加动态评论信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态评论信息页面
	 */
	public String edit() throws IctException {
		DynamicCommentsInfo dynamicCommentsInfo;
		dynamicCommentsInfo = dynamicCommentsService.findById(this.getId());
		
		DynamicCommentsForm dynamicCommentsForm = new DynamicCommentsForm(); 
		IctUtil.copyProperties(dynamicCommentsForm, dynamicCommentsInfo);
		
		this.setDynamicCommentsForm(dynamicCommentsForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态评论信息页面
	 */
	public String view() throws IctException {
		DynamicCommentsInfo dynamicCommentsInfo;
		dynamicCommentsInfo = dynamicCommentsService.findById(Integer.valueOf(this.getId()));
		
		DynamicCommentsForm dynamicCommentsForm = new DynamicCommentsForm(); 
		IctUtil.copyProperties(dynamicCommentsForm, dynamicCommentsInfo);
		
		this.setDynamicCommentsForm(dynamicCommentsForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态评论信息信息
	 */
	public String update() {
		DynamicCommentsInfo dynamicCommentsInfo = new DynamicCommentsInfo();
		
		try {
			IctUtil.copyProperties(dynamicCommentsInfo, dynamicCommentsForm);
			dynamicCommentsInfo.setUpdateId(getUserSession().getUserId());
			
			dynamicCommentsService.update(dynamicCommentsInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改动态评论信息成功");
			logger.debug("修改动态评论信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改动态评论信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态评论信息信息
	 */
	public String deletes() {
		try {
			dynamicCommentsService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicCommentsService getDynamicCommentsService() {
		return dynamicCommentsService;
	}

	public void setDynamicCommentsService(DynamicCommentsService dynamicCommentsService) {
		this.dynamicCommentsService = dynamicCommentsService;
	}

	public DynamicCommentsForm getDynamicCommentsForm() {
		return dynamicCommentsForm;
	}

	public void setDynamicCommentsForm(DynamicCommentsForm dynamicCommentsForm) {
		this.dynamicCommentsForm = dynamicCommentsForm;
	}

}