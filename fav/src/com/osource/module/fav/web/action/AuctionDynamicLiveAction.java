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

import com.osource.module.fav.model.AuctionDynamicLiveInfo;
import com.osource.module.fav.model.AuctionDynamicPreviewInfo;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.AuctionDynamicLiveService;
import com.osource.module.fav.service.AuctionDynamicsService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.web.form.AuctionDynamicLiveForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionDynamicLiveAction extends BaseAction {
	
	@Autowired
	private AuctionDynamicLiveService auctionDynamicLiveService;
	
	private AuctionDynamicLiveForm auctionDynamicLiveForm;

	@Autowired
	private AuctionDynamicsService auctionDynamicsService;
	
	@Autowired
	private CollectionService collectionService;
	
	private Map<Integer, String> dynamicMap;
	private Map<Integer, String> collectionMap;
	
	/** action methods **/
	
	public AuctionDynamicLiveAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
//		dynamicMap = new LinkedHashMap<Integer, String>();
//		collectionMap = new LinkedHashMap<Integer, String>();
//		Map condition = new HashMap();
//		condition.put("auctionDynamicTypeId", 3);
//		PageList<AuctionDynamicsInfo> dlist = auctionDynamicsService.findByCondition(condition);
//		if (null != dlist){
//			for (AuctionDynamicsInfo info : dlist) {
//				dynamicMap.put(info.getId(), info.getTitle());
//			}
//		}
//		
//		PageList<CollectionInfo> clist = collectionService.findByCondition(new HashMap());
//		if (null != clist){
//			for (CollectionInfo info : clist) {
//				collectionMap.put(info.getId(), info.getTitle());
//			}
//		}
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
//		putConditonMap(condition, "auctionDynamicId", auctionDynamicLiveForm.getAuctionDynamicId(), true);
//		putConditonMap(condition, "collectionId", auctionDynamicLiveForm.getCollectionId(), true);
		putConditonMap(condition, "auctionDynamicTitle", auctionDynamicLiveForm.getAuctionDynamicTitle(), false);
		putConditonMap(condition, "collectionTitle", auctionDynamicLiveForm.getCollectionTitle(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionDynamicLiveService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖行藏品动态直播表信息页面
	 */
	public String add() {
		dynamicMap = new LinkedHashMap<Integer, String>();
		collectionMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 3);
		condition.put("sourceId", 0);
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
		this.setActionName("auctionDynamicLive_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖行藏品动态直播表信息信息
	 */
	public String save() {
		AuctionDynamicLiveInfo auctionDynamicLiveInfo = new AuctionDynamicLiveInfo();
	
		try {
			if(auctionDynamicLiveForm != null)
				IctUtil.copyProperties(auctionDynamicLiveInfo, auctionDynamicLiveForm);
			auctionDynamicLiveInfo.setInsertId(getUserSession().getUserId());
			auctionDynamicLiveService.save(auctionDynamicLiveInfo);
			// 修改拍卖行动态表
			AuctionDynamicsInfo info = auctionDynamicsService.findById(auctionDynamicLiveInfo.getAuctionDynamicId());
			info.setSourceId(auctionDynamicLiveInfo.getId());
			auctionDynamicsService.update(info);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖行藏品动态直播表信息成功");
			logger.debug("添加拍卖行藏品动态直播表信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖行藏品动态直播表信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖行藏品动态直播表信息页面
	 */
	public String edit() throws IctException {
		dynamicMap = new LinkedHashMap<Integer, String>();
		collectionMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 3);
		condition.put("sourceId", 0);
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
		AuctionDynamicLiveInfo auctionDynamicLiveInfo;
		auctionDynamicLiveInfo = auctionDynamicLiveService.findById(this.getId());
		AuctionDynamicsInfo info = auctionDynamicsService.findById(auctionDynamicLiveInfo.getAuctionDynamicId());
		dynamicMap.put(info.getId(), info.getTitle());
		AuctionDynamicLiveForm auctionDynamicLiveForm = new AuctionDynamicLiveForm(); 
		IctUtil.copyProperties(auctionDynamicLiveForm, auctionDynamicLiveInfo);
		
		this.setAuctionDynamicLiveForm(auctionDynamicLiveForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖行藏品动态直播表信息页面
	 */
	public String view() throws IctException {
		AuctionDynamicLiveInfo auctionDynamicLiveInfo;
		auctionDynamicLiveInfo = auctionDynamicLiveService.findById(Integer.valueOf(this.getId()));
		
		AuctionDynamicLiveForm auctionDynamicLiveForm = new AuctionDynamicLiveForm(); 
		IctUtil.copyProperties(auctionDynamicLiveForm, auctionDynamicLiveInfo);
		
		this.setAuctionDynamicLiveForm(auctionDynamicLiveForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖行藏品动态直播表信息信息
	 */
	public String update() {
		AuctionDynamicLiveInfo auctionDynamicLiveInfo = new AuctionDynamicLiveInfo();
		AuctionDynamicLiveInfo oldInfo = auctionDynamicLiveService.findById(auctionDynamicLiveForm.getId());
		try {
			IctUtil.copyProperties(auctionDynamicLiveInfo, auctionDynamicLiveForm);
			auctionDynamicLiveInfo.setUpdateId(getUserSession().getUserId());
			// 修改拍卖行动态表
			if (oldInfo.getAuctionDynamicId() != auctionDynamicLiveInfo.getAuctionDynamicId()){
				AuctionDynamicsInfo info = auctionDynamicsService.findById(oldInfo.getAuctionDynamicId());
				info.setSourceId(oldInfo.getId());
				auctionDynamicsService.update(info);
				
				AuctionDynamicsInfo info2 = auctionDynamicsService.findById(auctionDynamicLiveInfo.getAuctionDynamicId());
				info2.setSourceId(auctionDynamicLiveInfo.getId());
				auctionDynamicsService.update(info2);
			}
			auctionDynamicLiveService.update(auctionDynamicLiveInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖行藏品动态直播表信息成功");
			logger.debug("修改拍卖行藏品动态直播表信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖行藏品动态直播表信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖行藏品动态直播表信息信息
	 */
	public String deletes() {
		try {
			for (String id : this.getIds().split(",")) {
				AuctionDynamicLiveInfo oldInfo = auctionDynamicLiveService.findById(id);
				AuctionDynamicsInfo info = auctionDynamicsService.findById(oldInfo.getAuctionDynamicId());
				info.setSourceId(0);
				auctionDynamicsService.update(info);
			}
			auctionDynamicLiveService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AuctionDynamicLiveService getAuctionDynamicLiveService() {
		return auctionDynamicLiveService;
	}

	public void setAuctionDynamicLiveService(AuctionDynamicLiveService auctionDynamicLiveService) {
		this.auctionDynamicLiveService = auctionDynamicLiveService;
	}

	public AuctionDynamicLiveForm getAuctionDynamicLiveForm() {
		return auctionDynamicLiveForm;
	}

	public void setAuctionDynamicLiveForm(AuctionDynamicLiveForm auctionDynamicLiveForm) {
		this.auctionDynamicLiveForm = auctionDynamicLiveForm;
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