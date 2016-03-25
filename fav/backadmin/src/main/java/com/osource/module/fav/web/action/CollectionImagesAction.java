package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.module.fav.service.CollectionImagesService;
import com.osource.module.fav.web.form.CollectionImagesForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionImagesAction extends BaseAction {
	
	@Autowired
	private CollectionImagesService collectionImagesService;
	
	private CollectionImagesForm collectionImagesForm;

	/** action methods **/
	
	public CollectionImagesAction(){
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
		putConditonMap(condition, "collectionTitle", collectionImagesForm.getCollectionTitle(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(collectionImagesService.findByCondition(condition, pages));
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加藏品图片信息页面
	 */
	public String add() {
		this.setActionName("collectionImages_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品图片信息信息
	 */
	public String save() {
		CollectionImagesInfo collectionImagesInfo = new CollectionImagesInfo();
	
		try {
			if(collectionImagesForm != null)
				IctUtil.copyProperties(collectionImagesInfo, collectionImagesForm);
			collectionImagesInfo.setInsertId(getUserSession().getUserId());
			collectionImagesService.save(collectionImagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加藏品图片信息成功");
			logger.debug("添加藏品图片信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加藏品图片信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品图片信息页面
	 */
	public String edit() throws IctException {
		CollectionImagesInfo collectionImagesInfo;
		collectionImagesInfo = collectionImagesService.findById(this.getId());
		
		CollectionImagesForm collectionImagesForm = new CollectionImagesForm(); 
		IctUtil.copyProperties(collectionImagesForm, collectionImagesInfo);
		
		this.setCollectionImagesForm(collectionImagesForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品图片信息页面
	 */
	public String view() throws IctException {
		CollectionImagesInfo collectionImagesInfo;
		collectionImagesInfo = collectionImagesService.findById(Integer.valueOf(this.getId()));
		
		CollectionImagesForm collectionImagesForm = new CollectionImagesForm(); 
		IctUtil.copyProperties(collectionImagesForm, collectionImagesInfo);
		
		this.setCollectionImagesForm(collectionImagesForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品图片信息信息
	 */
	public String update() {
		CollectionImagesInfo collectionImagesInfo = new CollectionImagesInfo();
		
		try {
			IctUtil.copyProperties(collectionImagesInfo, collectionImagesForm);
			collectionImagesInfo.setUpdateId(getUserSession().getUserId());
			
			collectionImagesService.update(collectionImagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改藏品图片信息成功");
			logger.debug("修改藏品图片信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改藏品图片信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品图片信息信息
	 */
	public String deletes() {
		try {
			collectionImagesService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public CollectionImagesService getCollectionImagesService() {
		return collectionImagesService;
	}

	public void setCollectionImagesService(CollectionImagesService collectionImagesService) {
		this.collectionImagesService = collectionImagesService;
	}

	public CollectionImagesForm getCollectionImagesForm() {
		return collectionImagesForm;
	}

	public void setCollectionImagesForm(CollectionImagesForm collectionImagesForm) {
		this.collectionImagesForm = collectionImagesForm;
	}

}