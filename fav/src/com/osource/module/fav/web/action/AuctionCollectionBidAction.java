package com.osource.module.fav.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.AuctionCollectionBidInfo;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.AuctionCollectionBidService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.web.form.AuctionCollectionBidForm;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionCollectionBidAction extends BaseAction {
	
	@Autowired
	private AuctionCollectionBidService auctionCollectionBidService;
	
	private AuctionCollectionBidForm auctionCollectionBidForm;

	@Autowired
	private CollectionService collectionService;
	
	private static Map<String, String> sexMap = CommonCache.getEnumInfos(EnumTypeCons.SEX_NICK_TYPE);
	private static Map<String, String> unitMap = CommonCache.getEnumInfos(EnumTypeCons.MONEY_TYPE);
	
	private Map<Integer, String> collectionMap;
	private String startDate;
    private String endDate;
	/** action methods **/
	
	public AuctionCollectionBidAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
		collectionMap = new LinkedHashMap<Integer, String>();
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
		putConditonMap(condition, "collectionTitle", auctionCollectionBidForm.getCollectionTitle(), false);
		putConditonMap(condition, "username", auctionCollectionBidForm.getUsername(), false);
		putConditonMap(condition, "sexNick", auctionCollectionBidForm.getSexNick(), true);
		putConditonMap(condition, "priceUnit", auctionCollectionBidForm.getPriceUnit(), true);
		putConditonMap(condition, "startDate", startDate, false);
		putConditonMap(condition, "endDate", endDate, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionCollectionBidService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖行藏品竞价信息页面
	 */
	public String add() {
		init();
		this.setActionName("auctionCollectionBid_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖行藏品竞价信息信息
	 */
	public String save() {
		AuctionCollectionBidInfo auctionCollectionBidInfo = new AuctionCollectionBidInfo();
		try {
			if(auctionCollectionBidForm != null)
				IctUtil.copyProperties(auctionCollectionBidInfo, auctionCollectionBidForm);
			auctionCollectionBidInfo.setInsertId(getUserSession().getUserId());
			auctionCollectionBidService.save(auctionCollectionBidInfo);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖行藏品竞价信息成功");
			logger.debug("添加拍卖行藏品竞价信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖行藏品竞价信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖行藏品竞价信息页面
	 */
	public String edit() throws IctException {
		init();
		AuctionCollectionBidInfo auctionCollectionBidInfo;
		auctionCollectionBidInfo = auctionCollectionBidService.findById(this.getId());
		
		AuctionCollectionBidForm auctionCollectionBidForm = new AuctionCollectionBidForm(); 
		IctUtil.copyProperties(auctionCollectionBidForm, auctionCollectionBidInfo);
		
		this.setAuctionCollectionBidForm(auctionCollectionBidForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖行藏品竞价信息页面
	 */
	public String view() throws IctException {
		AuctionCollectionBidInfo auctionCollectionBidInfo;
		auctionCollectionBidInfo = auctionCollectionBidService.findById(Integer.valueOf(this.getId()));
		
		AuctionCollectionBidForm auctionCollectionBidForm = new AuctionCollectionBidForm(); 
		IctUtil.copyProperties(auctionCollectionBidForm, auctionCollectionBidInfo);
		
		this.setAuctionCollectionBidForm(auctionCollectionBidForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖行藏品竞价信息信息
	 */
	public String update() {
		AuctionCollectionBidInfo auctionCollectionBidInfo = new AuctionCollectionBidInfo();
		
		try {
			IctUtil.copyProperties(auctionCollectionBidInfo, auctionCollectionBidForm);
			auctionCollectionBidInfo.setUpdateId(getUserSession().getUserId());
			
			auctionCollectionBidService.update(auctionCollectionBidInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖行藏品竞价信息成功");
			logger.debug("修改拍卖行藏品竞价信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖行藏品竞价信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖行藏品竞价信息信息
	 */
	public String deletes() {
		try {
			auctionCollectionBidService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AuctionCollectionBidService getAuctionCollectionBidService() {
		return auctionCollectionBidService;
	}

	public void setAuctionCollectionBidService(AuctionCollectionBidService auctionCollectionBidService) {
		this.auctionCollectionBidService = auctionCollectionBidService;
	}

	public AuctionCollectionBidForm getAuctionCollectionBidForm() {
		return auctionCollectionBidForm;
	}

	public void setAuctionCollectionBidForm(AuctionCollectionBidForm auctionCollectionBidForm) {
		this.auctionCollectionBidForm = auctionCollectionBidForm;
	}

	public Map<String, String> getSexMap() {
		return sexMap;
	}

	public void setSexMap(Map<String, String> sexMap) {
		this.sexMap = sexMap;
	}

	public Map<String, String> getUnitMap() {
		return unitMap;
	}

	public void setUnitMap(Map<String, String> unitMap) {
		this.unitMap = unitMap;
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

	public Map<Integer, String> getCollectionMap() {
		return collectionMap;
	}

	public void setCollectionMap(Map<Integer, String> collectionMap) {
		this.collectionMap = collectionMap;
	}
}