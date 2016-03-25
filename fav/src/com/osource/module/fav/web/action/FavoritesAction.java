package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.FavoritesInfo;
import com.osource.module.fav.service.FavoritesService;
import com.osource.module.fav.web.form.FavoritesForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class FavoritesAction extends BaseAction {
	
	@Autowired
	private FavoritesService favoritesService;
	
	private FavoritesForm favoritesForm;

	private String startDate;
    private String endDate;
    
	/** action methods **/
	
	public FavoritesAction(){
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
		putConditonMap(condition, "userName", favoritesForm.getUserName(), false);
		putConditonMap(condition, "title", favoritesForm.getTitle(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(favoritesService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加收藏夹信息页面
	 */
	public String add() {
		this.setActionName("favorites_save");
		return RESULT_SET;
	}

	/**
	 *  添加收藏夹信息信息
	 */
	public String save() {
		FavoritesInfo favoritesInfo = new FavoritesInfo();
	
		try {
			if(favoritesForm != null)
				IctUtil.copyProperties(favoritesInfo, favoritesForm);
			favoritesInfo.setInsertId(getUserSession().getUserId());
			favoritesService.save(favoritesInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加收藏夹信息成功");
			logger.debug("添加收藏夹信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加收藏夹信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑收藏夹信息页面
	 */
	public String edit() throws IctException {
		FavoritesInfo favoritesInfo;
		favoritesInfo = favoritesService.findById(this.getId());
		
		FavoritesForm favoritesForm = new FavoritesForm(); 
		IctUtil.copyProperties(favoritesForm, favoritesInfo);
		
		this.setFavoritesForm(favoritesForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看收藏夹信息页面
	 */
	public String view() throws IctException {
		FavoritesInfo favoritesInfo;
		favoritesInfo = favoritesService.findById(Integer.valueOf(this.getId()));
		
		FavoritesForm favoritesForm = new FavoritesForm(); 
		IctUtil.copyProperties(favoritesForm, favoritesInfo);
		
		this.setFavoritesForm(favoritesForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改收藏夹信息信息
	 */
	public String update() {
		FavoritesInfo favoritesInfo = new FavoritesInfo();
		
		try {
			IctUtil.copyProperties(favoritesInfo, favoritesForm);
			favoritesInfo.setUpdateId(getUserSession().getUserId());
			
			favoritesService.update(favoritesInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改收藏夹信息成功");
			logger.debug("修改收藏夹信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改收藏夹信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除收藏夹信息信息
	 */
	public String deletes() {
		try {
			favoritesService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public FavoritesService getFavoritesService() {
		return favoritesService;
	}

	public void setFavoritesService(FavoritesService favoritesService) {
		this.favoritesService = favoritesService;
	}

	public FavoritesForm getFavoritesForm() {
		return favoritesForm;
	}

	public void setFavoritesForm(FavoritesForm favoritesForm) {
		this.favoritesForm = favoritesForm;
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