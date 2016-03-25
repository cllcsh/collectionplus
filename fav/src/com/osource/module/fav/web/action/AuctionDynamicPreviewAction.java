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

import com.osource.module.fav.model.AuctionDynamicImagesInfo;
import com.osource.module.fav.model.AuctionDynamicPreviewInfo;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.AuctionDynamicPreviewService;
import com.osource.module.fav.service.AuctionDynamicsService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.web.form.AuctionDynamicPreviewForm;
import com.osource.base.web.action.BaseAction;
import com.sun.org.apache.bcel.internal.generic.NEW;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionDynamicPreviewAction extends BaseAction {
	
	@Autowired
	private AuctionDynamicPreviewService auctionDynamicPreviewService;
	
	private AuctionDynamicPreviewForm auctionDynamicPreviewForm;
	
	@Autowired
	private AuctionDynamicsService auctionDynamicsService;
	
	@Autowired
	private CollectionService collectionService;
	
	private Map<Integer, String> dynamicMap;
	private Map<Integer, String> collectionMap;
	
	/** action methods **/
	
	public AuctionDynamicPreviewAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
		dynamicMap = new LinkedHashMap<Integer, String>();
		collectionMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 2);
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
//		putConditonMap(condition, "auctionDynamicId", auctionDynamicPreviewForm.getAuctionDynamicId(), true);
//		putConditonMap(condition, "collectionId", auctionDynamicPreviewForm.getCollectionId(), true);
		putConditonMap(condition, "auctionDynamicTitle", auctionDynamicPreviewForm.getAuctionDynamicTitle(), false);
		putConditonMap(condition, "collectionTitle", auctionDynamicPreviewForm.getCollectionTitle(), false);
		if (0 != auctionDynamicPreviewForm.getDisplayOrder()){
			putConditonMap(condition, "displayOrder", auctionDynamicPreviewForm.getDisplayOrder(), true);
		}
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionDynamicPreviewService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖动态预展信息页面
	 */
	public String add() {
		init();
		this.setActionName("auctionDynamicPreview_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖动态预展信息信息
	 */
	public String save() {
		AuctionDynamicPreviewInfo auctionDynamicPreviewInfo = new AuctionDynamicPreviewInfo();
	
		try {
			if(auctionDynamicPreviewForm != null)
				IctUtil.copyProperties(auctionDynamicPreviewInfo, auctionDynamicPreviewForm);
			auctionDynamicPreviewInfo.setInsertId(getUserSession().getUserId());
			auctionDynamicPreviewService.save(auctionDynamicPreviewInfo);
			// 修改拍卖行动态表
//			AuctionDynamicsInfo info = auctionDynamicsService.findById(auctionDynamicPreviewInfo.getAuctionDynamicId());
//			info.setSourceId(auctionDynamicPreviewInfo.getId());
//			auctionDynamicsService.update(info);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖动态预展信息成功");
			logger.debug("添加拍卖动态预展信息成功");
		} catch (Exception e) { 
			e.printStackTrace();
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖动态预展信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖动态预展信息页面
	 */
	public String edit() throws IctException {
		init();
		AuctionDynamicPreviewInfo auctionDynamicPreviewInfo;
		auctionDynamicPreviewInfo = auctionDynamicPreviewService.findById(this.getId());
		AuctionDynamicPreviewForm auctionDynamicPreviewForm = new AuctionDynamicPreviewForm(); 
		IctUtil.copyProperties(auctionDynamicPreviewForm, auctionDynamicPreviewInfo);
		
		this.setAuctionDynamicPreviewForm(auctionDynamicPreviewForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖动态预展信息页面
	 */
	public String view() throws IctException {
		AuctionDynamicPreviewInfo auctionDynamicPreviewInfo;
		auctionDynamicPreviewInfo = auctionDynamicPreviewService.findById(Integer.valueOf(this.getId()));
		
		AuctionDynamicPreviewForm auctionDynamicPreviewForm = new AuctionDynamicPreviewForm(); 
		IctUtil.copyProperties(auctionDynamicPreviewForm, auctionDynamicPreviewInfo);
		
		this.setAuctionDynamicPreviewForm(auctionDynamicPreviewForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖动态预展信息信息
	 */
	public String update() {
		AuctionDynamicPreviewInfo auctionDynamicPreviewInfo = new AuctionDynamicPreviewInfo();
		try {
			IctUtil.copyProperties(auctionDynamicPreviewInfo, auctionDynamicPreviewForm);
			auctionDynamicPreviewInfo.setUpdateId(getUserSession().getUserId());
			auctionDynamicPreviewService.update(auctionDynamicPreviewInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖动态预展信息成功");
			logger.debug("修改拍卖动态预展信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖动态预展信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖动态预展信息信息
	 */
	public String deletes() {
		try {
			auctionDynamicPreviewService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AuctionDynamicPreviewService getAuctionDynamicPreviewService() {
		return auctionDynamicPreviewService;
	}

	public void setAuctionDynamicPreviewService(AuctionDynamicPreviewService auctionDynamicPreviewService) {
		this.auctionDynamicPreviewService = auctionDynamicPreviewService;
	}

	public AuctionDynamicPreviewForm getAuctionDynamicPreviewForm() {
		return auctionDynamicPreviewForm;
	}

	public void setAuctionDynamicPreviewForm(AuctionDynamicPreviewForm auctionDynamicPreviewForm) {
		this.auctionDynamicPreviewForm = auctionDynamicPreviewForm;
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