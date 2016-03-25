package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionPeriodInfo;
import com.osource.module.fav.service.CollectionPeriodService;
import com.osource.module.fav.web.form.CollectionPeriodForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionPeriodAction extends BaseAction {
	
	@Autowired
	private CollectionPeriodService collectionPeriodService;
	
	private CollectionPeriodForm collectionPeriodForm;

	/** action methods **/
	
	public CollectionPeriodAction(){
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
		this.setPageList(collectionPeriodService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加藏品时期信息页面
	 */
	public String add() {
		this.setActionName("collectionPeriod_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品时期信息信息
	 */
	public String save() {
		CollectionPeriodInfo collectionPeriodInfo = new CollectionPeriodInfo();
	
		try {
			if(collectionPeriodForm != null)
				IctUtil.copyProperties(collectionPeriodInfo, collectionPeriodForm);
			collectionPeriodInfo.setInsertId(getUserSession().getUserId());
			collectionPeriodService.save(collectionPeriodInfo);
			CommonCache.getCollectionPeriods().put(collectionPeriodInfo.getId(), collectionPeriodInfo.getName());
			this.getAjaxMessagesJson().setMessage("0", "添加藏品时期信息成功");
			logger.debug("添加藏品时期信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加藏品时期信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品时期信息页面
	 */
	public String edit() throws IctException {
		CollectionPeriodInfo collectionPeriodInfo;
		collectionPeriodInfo = collectionPeriodService.findById(this.getId());
		
		CollectionPeriodForm collectionPeriodForm = new CollectionPeriodForm(); 
		IctUtil.copyProperties(collectionPeriodForm, collectionPeriodInfo);
		
		this.setCollectionPeriodForm(collectionPeriodForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品时期信息页面
	 */
	public String view() throws IctException {
		CollectionPeriodInfo collectionPeriodInfo;
		collectionPeriodInfo = collectionPeriodService.findById(Integer.valueOf(this.getId()));
		
		CollectionPeriodForm collectionPeriodForm = new CollectionPeriodForm(); 
		IctUtil.copyProperties(collectionPeriodForm, collectionPeriodInfo);
		
		this.setCollectionPeriodForm(collectionPeriodForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品时期信息信息
	 */
	public String update() {
		CollectionPeriodInfo collectionPeriodInfo = new CollectionPeriodInfo();
		
		try {
			IctUtil.copyProperties(collectionPeriodInfo, collectionPeriodForm);
			collectionPeriodInfo.setUpdateId(getUserSession().getUserId());
			
			collectionPeriodService.update(collectionPeriodInfo);
			CommonCache.getCollectionPeriods().put(collectionPeriodInfo.getId(), collectionPeriodInfo.getName());
			this.getAjaxMessagesJson().setMessage("0", "修改藏品时期信息成功");
			logger.debug("修改藏品时期信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改藏品时期信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品时期信息信息
	 */
	public String deletes() {
		try {
			collectionPeriodService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			String[] ids = this.getIds().split(",");
			for (String id : ids) {
				CommonCache.getCollectionPeriods().remove(Integer.valueOf(id.trim()));
			}
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CollectionPeriodService getCollectionPeriodService() {
		return collectionPeriodService;
	}

	public void setCollectionPeriodService(CollectionPeriodService collectionPeriodService) {
		this.collectionPeriodService = collectionPeriodService;
	}

	public CollectionPeriodForm getCollectionPeriodForm() {
		return collectionPeriodForm;
	}

	public void setCollectionPeriodForm(CollectionPeriodForm collectionPeriodForm) {
		this.collectionPeriodForm = collectionPeriodForm;
	}

}