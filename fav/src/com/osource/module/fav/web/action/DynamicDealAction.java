package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.AuctionDynamicPreviewInfo;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.model.DynamicDealInfo;
import com.osource.module.fav.service.AuctionDynamicsService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.service.DynamicDealService;
import com.osource.module.fav.web.form.DynamicDealForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class DynamicDealAction extends BaseAction {
	
	@Autowired
	private DynamicDealService dynamicDealService;
	
	private DynamicDealForm dynamicDealForm;

	@Autowired
	private AuctionDynamicsService auctionDynamicsService;
	
	@Autowired
	private CollectionService collectionService;
	
	private Map<Integer, String> dynamicMap;
	private Map<Integer, String> collectionMap;
	
	/** action methods **/
	
	public DynamicDealAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
		dynamicMap = new LinkedHashMap<Integer, String>();
		collectionMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 4);
		PageList<AuctionDynamicsInfo> dlist = auctionDynamicsService.findByCondition(condition);
		if (null != dlist){
			for (AuctionDynamicsInfo info : dlist) {
				dynamicMap.put(info.getId(), info.getTitle());
			}
		}
		
		PageList<CollectionInfo> clist = collectionService.findByCondition(new HashMap());
		if (null != clist){
			for (CollectionInfo info : clist) {
				collectionMap.put(info.getId(), info.getTitle());
			}
		}
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
//		putConditonMap(condition, "auctionDynamicId", dynamicDealForm.getAuctionDynamicId(), true);
//		putConditonMap(condition, "collectionId", dynamicDealForm.getCollectionId(), true);
		putConditonMap(condition, "auctionDynamicTitle", dynamicDealForm.getAuctionDynamicTitle(), false);
		putConditonMap(condition, "collectionTitle", dynamicDealForm.getCollectionTitle(), false);
		if (0 != dynamicDealForm.getDisplayOrder()){
			putConditonMap(condition, "displayOrder", dynamicDealForm.getDisplayOrder(), true);
		}
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(dynamicDealService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖动态成交信息页面
	 */
	public String add() {
		init();
		this.setActionName("dynamicDeal_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖动态成交信息信息
	 */
	public String save() {
		DynamicDealInfo dynamicDealInfo = new DynamicDealInfo();
	
		try {
			if(dynamicDealForm != null)
				IctUtil.copyProperties(dynamicDealInfo, dynamicDealForm);
			dynamicDealInfo.setInsertId(getUserSession().getUserId());
			dynamicDealService.save(dynamicDealInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖动态成交信息成功");
			logger.debug("添加拍卖动态成交信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖动态成交信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖动态成交信息页面
	 */
	public String edit() throws IctException {
		init();
		DynamicDealInfo dynamicDealInfo;
		dynamicDealInfo = dynamicDealService.findById(this.getId());
		AuctionDynamicsInfo info = auctionDynamicsService.findById(dynamicDealInfo.getAuctionDynamicId());
		DynamicDealForm dynamicDealForm = new DynamicDealForm(); 
		IctUtil.copyProperties(dynamicDealForm, dynamicDealInfo);
		
		this.setDynamicDealForm(dynamicDealForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖动态成交信息页面
	 */
	public String view() throws IctException {
		DynamicDealInfo dynamicDealInfo;
		dynamicDealInfo = dynamicDealService.findById(Integer.valueOf(this.getId()));
		
		DynamicDealForm dynamicDealForm = new DynamicDealForm(); 
		IctUtil.copyProperties(dynamicDealForm, dynamicDealInfo);
		
		this.setDynamicDealForm(dynamicDealForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖动态成交信息信息
	 */
	public String update() {
		DynamicDealInfo dynamicDealInfo = new DynamicDealInfo();
		DynamicDealInfo oldInfo = dynamicDealService.findById(dynamicDealForm.getId());
		try {
			IctUtil.copyProperties(dynamicDealInfo, dynamicDealForm);
			dynamicDealInfo.setUpdateId(getUserSession().getUserId());
			dynamicDealService.update(dynamicDealInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖动态成交信息成功");
			logger.debug("修改拍卖动态成交信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖动态成交信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖动态成交信息信息
	 */
	public String deletes() {
		try {
			dynamicDealService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public DynamicDealService getDynamicDealService() {
		return dynamicDealService;
	}

	public void setDynamicDealService(DynamicDealService dynamicDealService) {
		this.dynamicDealService = dynamicDealService;
	}

	public DynamicDealForm getDynamicDealForm() {
		return dynamicDealForm;
	}

	public void setDynamicDealForm(DynamicDealForm dynamicDealForm) {
		this.dynamicDealForm = dynamicDealForm;
	}

	public Map<Integer, String> getDynamicMap() {
		return dynamicMap;
	}

	public void setDynamicMap(Map<Integer, String> dynamicMap) {
		this.dynamicMap = dynamicMap;
	}

	public Map<Integer, String> getCollectionMap() {
		return collectionMap;
	}

	public void setCollectionMap(Map<Integer, String> collectionMap) {
		this.collectionMap = collectionMap;
	}
}