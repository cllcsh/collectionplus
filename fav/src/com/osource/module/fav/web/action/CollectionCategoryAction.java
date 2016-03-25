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

import com.osource.module.fav.model.CollectionCategoryInfo;
import com.osource.module.fav.service.CollectionCategoryService;
import com.osource.module.fav.web.form.CollectionCategoryForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionCategoryAction extends BaseAction {
	
	@Autowired
	private CollectionCategoryService collectionCategoryService;
	
	private CollectionCategoryForm collectionCategoryForm;

	/** action methods **/
	
	public CollectionCategoryAction(){
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
		this.setPageList(collectionCategoryService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加藏品类别信息页面
	 */
	public String add() {
		this.setActionName("collectionCategory_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品类别信息信息
	 */
	public String save() {
		CollectionCategoryInfo collectionCategoryInfo = new CollectionCategoryInfo();
	
		try {
			if(collectionCategoryForm != null)
				IctUtil.copyProperties(collectionCategoryInfo, collectionCategoryForm);
			collectionCategoryInfo.setInsertId(getUserSession().getUserId());
			collectionCategoryService.save(collectionCategoryInfo);
			CommonCache.getCollectionCategorys().put(collectionCategoryInfo.getId(), collectionCategoryInfo.getCategoryName());
			this.getAjaxMessagesJson().setMessage("0", "添加藏品类别信息成功");
			logger.debug("添加藏品类别信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加藏品类别信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品类别信息页面
	 */
	public String edit() throws IctException {
		CollectionCategoryInfo collectionCategoryInfo;
		collectionCategoryInfo = collectionCategoryService.findById(this.getId());
		
		CollectionCategoryForm collectionCategoryForm = new CollectionCategoryForm(); 
		IctUtil.copyProperties(collectionCategoryForm, collectionCategoryInfo);
		
		this.setCollectionCategoryForm(collectionCategoryForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品类别信息页面
	 */
	public String view() throws IctException {
		CollectionCategoryInfo collectionCategoryInfo;
		collectionCategoryInfo = collectionCategoryService.findById(Integer.valueOf(this.getId()));
		
		CollectionCategoryForm collectionCategoryForm = new CollectionCategoryForm(); 
		IctUtil.copyProperties(collectionCategoryForm, collectionCategoryInfo);
		
		this.setCollectionCategoryForm(collectionCategoryForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品类别信息信息
	 */
	public String update() {
		CollectionCategoryInfo collectionCategoryInfo = new CollectionCategoryInfo();
		
		try {
			IctUtil.copyProperties(collectionCategoryInfo, collectionCategoryForm);
			collectionCategoryInfo.setUpdateId(getUserSession().getUserId());
			
			collectionCategoryService.update(collectionCategoryInfo);
			CommonCache.getCollectionCategorys().put(collectionCategoryInfo.getId(), collectionCategoryInfo.getCategoryName());
			this.getAjaxMessagesJson().setMessage("0", "修改藏品类别信息成功");
			logger.debug("修改藏品类别信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改藏品类别信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品类别信息信息
	 */
	public String deletes() {
		try {
			collectionCategoryService.deleteById(this.getIds());
			String[] ids = this.getIds().split(",");
			for (String id : ids) {
				CommonCache.getCollectionCategorys().remove(Integer.valueOf(id.trim()));
			}
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CollectionCategoryService getCollectionCategoryService() {
		return collectionCategoryService;
	}

	public void setCollectionCategoryService(CollectionCategoryService collectionCategoryService) {
		this.collectionCategoryService = collectionCategoryService;
	}

	public CollectionCategoryForm getCollectionCategoryForm() {
		return collectionCategoryForm;
	}

	public void setCollectionCategoryForm(CollectionCategoryForm collectionCategoryForm) {
		this.collectionCategoryForm = collectionCategoryForm;
	}

}