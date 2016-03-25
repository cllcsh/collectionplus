package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionLableInfo;
import com.osource.module.fav.service.CollectionLableService;
import com.osource.module.fav.web.form.CollectionLableForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionLableAction extends BaseAction {
	
	@Autowired
	private CollectionLableService collectionLableService;
	
	private CollectionLableForm collectionLableForm;

	/** action methods **/
	
	public CollectionLableAction(){
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
		this.setPageList(collectionLableService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加藏品标签信息页面
	 */
	public String add() {
		this.setActionName("collectionLable_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品标签信息信息
	 */
	public String save() {
		CollectionLableInfo collectionLableInfo = new CollectionLableInfo();
	
		try {
			if(collectionLableForm != null)
				IctUtil.copyProperties(collectionLableInfo, collectionLableForm);
			collectionLableInfo.setInsertId(getUserSession().getUserId());
			collectionLableService.save(collectionLableInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加藏品标签信息成功");
			logger.debug("添加藏品标签信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加藏品标签信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品标签信息页面
	 */
	public String edit() throws IctException {
		CollectionLableInfo collectionLableInfo;
		collectionLableInfo = collectionLableService.findById(this.getId());
		
		CollectionLableForm collectionLableForm = new CollectionLableForm(); 
		IctUtil.copyProperties(collectionLableForm, collectionLableInfo);
		
		this.setCollectionLableForm(collectionLableForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品标签信息页面
	 */
	public String view() throws IctException {
		CollectionLableInfo collectionLableInfo;
		collectionLableInfo = collectionLableService.findById(Integer.valueOf(this.getId()));
		
		CollectionLableForm collectionLableForm = new CollectionLableForm(); 
		IctUtil.copyProperties(collectionLableForm, collectionLableInfo);
		
		this.setCollectionLableForm(collectionLableForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品标签信息信息
	 */
	public String update() {
		CollectionLableInfo collectionLableInfo = new CollectionLableInfo();
		
		try {
			IctUtil.copyProperties(collectionLableInfo, collectionLableForm);
			collectionLableInfo.setUpdateId(getUserSession().getUserId());
			
			collectionLableService.update(collectionLableInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改藏品标签信息成功");
			logger.debug("修改藏品标签信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改藏品标签信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品标签信息信息
	 */
	public String deletes() {
		try {
			collectionLableService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CollectionLableService getCollectionLableService() {
		return collectionLableService;
	}

	public void setCollectionLableService(CollectionLableService collectionLableService) {
		this.collectionLableService = collectionLableService;
	}

	public CollectionLableForm getCollectionLableForm() {
		return collectionLableForm;
	}

	public void setCollectionLableForm(CollectionLableForm collectionLableForm) {
		this.collectionLableForm = collectionLableForm;
	}

}