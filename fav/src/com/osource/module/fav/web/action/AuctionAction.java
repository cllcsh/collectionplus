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

import com.osource.module.fav.model.AuctionInfo;
import com.osource.module.fav.service.AuctionService;
import com.osource.module.fav.web.form.AuctionForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionAction extends BaseAction {
	
	@Autowired
	private AuctionService auctionService;
	
	private AuctionForm auctionForm;

	/** action methods **/
	
	public AuctionAction(){
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
		condition.put("name", auctionForm.getName());
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖行信息页面
	 */
	public String add() {
		this.setActionName("auction_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖行信息信息
	 */
	public String save() {
		AuctionInfo auctionInfo = new AuctionInfo();
	
		try {
			if(auctionForm != null)
				IctUtil.copyProperties(auctionInfo, auctionForm);
			auctionInfo.setInsertId(getUserSession().getUserId());
			auctionService.save(auctionInfo);
			CommonCache.putAuction(auctionInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖行信息成功");
			logger.debug("添加拍卖行信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖行信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖行信息页面
	 */
	public String edit() throws IctException {
		AuctionInfo auctionInfo;
		auctionInfo = auctionService.findById(this.getId());
		
		AuctionForm auctionForm = new AuctionForm(); 
		IctUtil.copyProperties(auctionForm, auctionInfo);
		
		this.setAuctionForm(auctionForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖行信息页面
	 */
	public String view() throws IctException {
		AuctionInfo auctionInfo;
		auctionInfo = auctionService.findById(Integer.valueOf(this.getId()));
		
		AuctionForm auctionForm = new AuctionForm(); 
		IctUtil.copyProperties(auctionForm, auctionInfo);
		
		this.setAuctionForm(auctionForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖行信息信息
	 */
	public String update() {
		AuctionInfo auctionInfo = new AuctionInfo();
		
		try {
			IctUtil.copyProperties(auctionInfo, auctionForm);
			auctionInfo.setUpdateId(getUserSession().getUserId());
			
			auctionService.update(auctionInfo);
			CommonCache.putAuction(auctionInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖行信息成功");
			logger.debug("修改拍卖行信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖行信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖行信息信息
	 */
	public String deletes() {
		try {
			auctionService.deleteById(this.getIds());
			String[] ids = this.getIds().split(",");
			for (String id : ids) {
				CommonCache.getAuctions().remove(Integer.valueOf(id.trim()));
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
	
	public AuctionService getAuctionService() {
		return auctionService;
	}

	public void setAuctionService(AuctionService auctionService) {
		this.auctionService = auctionService;
	}

	public AuctionForm getAuctionForm() {
		return auctionForm;
	}

	public void setAuctionForm(AuctionForm auctionForm) {
		this.auctionForm = auctionForm;
	}

}