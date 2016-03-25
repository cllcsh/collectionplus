package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.DynamicImagesInfo;
import com.osource.module.fav.service.DynamicImagesService;
import com.osource.module.fav.web.form.DynamicImagesForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicImagesAction extends BaseAction {
	
	@Autowired
	private DynamicImagesService dynamicImagesService;
	
	private DynamicImagesForm dynamicImagesForm;

	/** action methods **/
	
	public DynamicImagesAction(){
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
			
			putConditonMap(condition, "dynamicContent", dynamicImagesForm.getDynamicContent(), false);
			Pages pages = new Pages(this.getPage(),this.getLimit());
			this.setPageList(dynamicImagesService.findByCondition(condition, pages));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加动态图片地址信息页面
	 */
	public String add() {
		this.setActionName("dynamicImages_save");
		return RESULT_SET;
	}

	/**
	 *  添加动态图片地址信息信息
	 */
	public String save() {
		DynamicImagesInfo dynamicImagesInfo = new DynamicImagesInfo();
	
		try {
			if(dynamicImagesForm != null)
				IctUtil.copyProperties(dynamicImagesInfo, dynamicImagesForm);
			dynamicImagesInfo.setInsertId(getUserSession().getUserId());
			dynamicImagesService.save(dynamicImagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加动态图片地址信息成功");
			logger.debug("添加动态图片地址信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加动态图片地址信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑动态图片地址信息页面
	 */
	public String edit() throws IctException {
		DynamicImagesInfo dynamicImagesInfo;
		dynamicImagesInfo = dynamicImagesService.findById(this.getId());
		
		DynamicImagesForm dynamicImagesForm = new DynamicImagesForm(); 
		IctUtil.copyProperties(dynamicImagesForm, dynamicImagesInfo);
		
		this.setDynamicImagesForm(dynamicImagesForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看动态图片地址信息页面
	 */
	public String view() throws IctException {
		DynamicImagesInfo dynamicImagesInfo;
		dynamicImagesInfo = dynamicImagesService.findById(Integer.valueOf(this.getId()));
		
		DynamicImagesForm dynamicImagesForm = new DynamicImagesForm(); 
		IctUtil.copyProperties(dynamicImagesForm, dynamicImagesInfo);
		
		this.setDynamicImagesForm(dynamicImagesForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改动态图片地址信息信息
	 */
	public String update() {
		DynamicImagesInfo dynamicImagesInfo = new DynamicImagesInfo();
		
		try {
			IctUtil.copyProperties(dynamicImagesInfo, dynamicImagesForm);
			dynamicImagesInfo.setUpdateId(getUserSession().getUserId());
			
			dynamicImagesService.update(dynamicImagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改动态图片地址信息成功");
			logger.debug("修改动态图片地址信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改动态图片地址信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除动态图片地址信息信息
	 */
	public String deletes() {
		try {
			dynamicImagesService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicImagesService getDynamicImagesService() {
		return dynamicImagesService;
	}

	public void setDynamicImagesService(DynamicImagesService dynamicImagesService) {
		this.dynamicImagesService = dynamicImagesService;
	}

	public DynamicImagesForm getDynamicImagesForm() {
		return dynamicImagesForm;
	}

	public void setDynamicImagesForm(DynamicImagesForm dynamicImagesForm) {
		this.dynamicImagesForm = dynamicImagesForm;
	}

}