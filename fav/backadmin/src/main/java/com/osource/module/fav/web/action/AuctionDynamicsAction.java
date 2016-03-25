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

import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.service.AuctionDynamicsService;
import com.osource.module.fav.web.form.AuctionDynamicsForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionDynamicsAction extends BaseAction {
	
	@Autowired
	private AuctionDynamicsService auctionDynamicsService;
	
	private AuctionDynamicsForm auctionDynamicsForm;
	
	private static Map<Integer, String> typeMap = CommonCache.getAuctionDynamicsTypes();
	
	private static Map<Integer, String> auctionMap = CommonCache.getAuctions();
	/** action methods **/
	
	public AuctionDynamicsAction(){
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
		putConditonMap(condition, "title", auctionDynamicsForm.getTitle(), false);
		putConditonMap(condition, "auctionDynamicTypeId", auctionDynamicsForm.getAuctionDynamicTypeId(), true);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionDynamicsService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖行动态信息页面
	 */
	public String add() {
		this.setActionName("auctionDynamics_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖行动态信息信息
	 */
	public String save() {
		AuctionDynamicsInfo auctionDynamicsInfo = new AuctionDynamicsInfo();
	
		try {
			if(auctionDynamicsForm != null)
				IctUtil.copyProperties(auctionDynamicsInfo, auctionDynamicsForm);
			auctionDynamicsInfo.setInsertId(getUserSession().getUserId());
			auctionDynamicsService.save(auctionDynamicsInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖行动态信息成功");
			logger.debug("添加拍卖行动态信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖行动态信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖行动态信息页面
	 */
	public String edit() throws IctException {
		AuctionDynamicsInfo auctionDynamicsInfo;
		auctionDynamicsInfo = auctionDynamicsService.findById(this.getId());
		
		AuctionDynamicsForm auctionDynamicsForm = new AuctionDynamicsForm(); 
		IctUtil.copyProperties(auctionDynamicsForm, auctionDynamicsInfo);
		
		this.setAuctionDynamicsForm(auctionDynamicsForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖行动态信息页面
	 */
	public String view() throws IctException {
		AuctionDynamicsInfo auctionDynamicsInfo;
		auctionDynamicsInfo = auctionDynamicsService.findById(Integer.valueOf(this.getId()));
		
		AuctionDynamicsForm auctionDynamicsForm = new AuctionDynamicsForm(); 
		IctUtil.copyProperties(auctionDynamicsForm, auctionDynamicsInfo);
		
		this.setAuctionDynamicsForm(auctionDynamicsForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖行动态信息信息
	 */
	public String update() {
		AuctionDynamicsInfo auctionDynamicsInfo = new AuctionDynamicsInfo();
		
		try {
			IctUtil.copyProperties(auctionDynamicsInfo, auctionDynamicsForm);
			auctionDynamicsInfo.setUpdateId(getUserSession().getUserId());
			
			auctionDynamicsService.update(auctionDynamicsInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖行动态信息成功");
			logger.debug("修改拍卖行动态信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖行动态信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖行动态信息信息
	 */
	public String deletes() {
		try {
			auctionDynamicsService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AuctionDynamicsService getAuctionDynamicsService() {
		return auctionDynamicsService;
	}

	public void setAuctionDynamicsService(AuctionDynamicsService auctionDynamicsService) {
		this.auctionDynamicsService = auctionDynamicsService;
	}

	public AuctionDynamicsForm getAuctionDynamicsForm() {
		return auctionDynamicsForm;
	}

	public void setAuctionDynamicsForm(AuctionDynamicsForm auctionDynamicsForm) {
		this.auctionDynamicsForm = auctionDynamicsForm;
	}

	public Map<Integer, String> getTypeMap() {
		return typeMap;
	}

	public void setTypeMap(Map<Integer, String> typeMap) {
		this.typeMap = typeMap;
	}

	public Map<Integer, String> getAuctionMap() {
		return auctionMap;
	}

	public void setAuctionMap(Map<Integer, String> auctionMap) {
		this.auctionMap = auctionMap;
	}
}