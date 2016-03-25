package com.osource.module.fav.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;
import com.osource.cache.CommonCache;
import com.osource.cons.EnumTypeCons;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.CollectionImagesService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.web.form.CollectionForm;
import com.osource.util.IctUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CollectionAction extends BaseAction {
	
	@Autowired
	private CollectionService collectionService;
	
	private CollectionForm collectionForm;
	
	@Autowired
	private CollectionImagesService collectionImagesService;
	
	// 收藏时期
	private static Map<Integer, String> collectionPeriods = CommonCache.getCollectionPeriods();
	
	// 标签
	private static Map<Integer, String> collectionLables = CommonCache.getCollectionLables();
	
	// 分类
	private static Map<Integer, String> collectionCategorys = CommonCache.getCollectionCategorys();
	
	// 是否
	private static Map<String, String> whethers = CommonCache.getEnumInfos(EnumTypeCons.WHETHER_TYPE);
	
	// 状态
	private static Map<String, String> statusMap = CommonCache.getEnumInfos(EnumTypeCons.COLLECTION_STATUS_TYPE);
	
	// 拍卖行集合
	private static Map<Integer, String> auctionMap = CommonCache.getAuctions();
	
	// 价格单位
	private static Map<String, String> moneyMap = CommonCache.getEnumInfos(EnumTypeCons.MONEY_TYPE);
	
	// 藏品图片
	private List<String> imgs;
	
	private String searchName;
	
	/** action methods **/
	
	public CollectionAction(){
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
			putConditonMap(condition, "title", collectionForm.getTitle(), false);
			putConditonMap(condition, "collectionPeriodId", collectionForm.getCollectionPeriodId(), true);
			putConditonMap(condition, "isSendRacket", collectionForm.getIsSendRacket(), true);
			putConditonMap(condition, "isIdentify", collectionForm.getIsIdentify(), true);
			putConditonMap(condition, "categoryId", collectionForm.getCategoryId(), true);
			putConditonMap(condition, "labelId", collectionForm.getLabelId(), true);
			putConditonMap(condition, "isSold", collectionForm.getIsSold(), true);
			putConditonMap(condition, "status", collectionForm.getStatus(), true);
			putConditonMap(condition, "insertUserName", collectionForm.getInsertUserName(), false);
			Pages pages = new Pages(this.getPage(),this.getLimit());
			PageList<CollectionInfo> list = (PageList)collectionService.findByCondition(condition, pages);
			if (null != list) {
				for (CollectionInfo bean : list) {
					dealCollectionInfo(bean);
				}
			}
			this.setPageList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RESULT_LIST;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String queryForBrowse(){
		Map condition = new HashMap();
		putConditonMap(condition, "title", searchName, false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		PageList<CollectionInfo> list = (PageList)collectionService.findByCondition(condition, pages);
		JSONArray jsonArray = new JSONArray();
		if (null != list) {
			for (CollectionInfo bean : list) {
				dealCollectionInfo(bean);
				JSONObject object = new JSONObject();
				object.put("id", bean.getId());
				object.put("name", bean.getTitle());
				jsonArray.add(object);
			}
		}
		JSONObject result = new JSONObject();
		result.put("list", jsonArray);
		JSONObject pageJsonObject = new JSONObject();
		pageJsonObject.put("count", pages.getTotal());
		pageJsonObject.put("currentPage", pages.getPage());
		pageJsonObject.put("pageSize", pages.getLimit());
		pageJsonObject.put("countPage", pages.getAllPage());
		result.put("page", pageJsonObject);
		HttpServletResponse response = (HttpServletResponse) ServletActionContext.getResponse();
		try {
			response.getWriter().write(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *  跳转到添加藏品信息页面
	 */
	public String add() {
		this.setActionName("collection_save");
		return RESULT_SET;
	}

	/**
	 *  添加藏品信息信息
	 */
	public String save() {
		CollectionInfo collectionInfo = new CollectionInfo();
		try {
			if(collectionForm != null)
				IctUtil.copyProperties(collectionInfo, collectionForm);
			collectionInfo.setUpdateId(getUserSession().getUserId());
			collectionService.save(collectionInfo, imgs);
			this.getAjaxMessagesJson().setMessage("0", "添加藏品信息成功");
			logger.debug("添加藏品信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加藏品信息失败");
			logger.debug(e);
			e.printStackTrace();
		}
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑藏品信息页面
	 */
	public String edit() throws IctException {
		CollectionInfo collectionInfo;
		collectionInfo = collectionService.findById(this.getId());
		CollectionForm collectionForm = new CollectionForm(); 
		IctUtil.copyProperties(collectionForm, collectionInfo);
		this.setCollectionForm(collectionForm);
		
		Map condition = new HashMap();
		putConditonMap(condition, "collectionId", collectionForm.getId(), false);
		List<CollectionImagesInfo> collectionImagesInfos = collectionImagesService.findByCondition(condition);
		if (null != collectionImagesInfos){
			imgs = new ArrayList<String>();
			for (CollectionImagesInfo collectionImagesInfo : collectionImagesInfos) {
				imgs.add(collectionImagesInfo.getImageUrl());
			}
		}
		return RESULT_SET;
	}

	/**
	 *  跳转到查看藏品信息页面
	 */
	public String view() throws IctException {
		CollectionInfo collectionInfo;
		collectionInfo = collectionService.findById(Integer.valueOf(this.getId()));
		dealCollectionInfo(collectionInfo);
		CollectionForm collectionForm = new CollectionForm(); 
		IctUtil.copyProperties(collectionForm, collectionInfo);
		
		this.setCollectionForm(collectionForm);
		
		Map condition = new HashMap();
		putConditonMap(condition, "collectionId", collectionForm.getId(), false);
		List<CollectionImagesInfo> collectionImagesInfos = collectionImagesService.findByCondition(condition);
		if (null != collectionImagesInfos){
			imgs = new ArrayList<String>();
			for (CollectionImagesInfo collectionImagesInfo : collectionImagesInfos) {
				imgs.add(collectionImagesInfo.getImageUrl());
			}
		}
		return RESULT_VIEW;
	}
	
	/**
	 *  修改藏品信息信息
	 */
	public String update() {
		CollectionInfo collectionInfo = new CollectionInfo();
		try {
			IctUtil.copyProperties(collectionInfo, collectionForm);
			collectionInfo.setUpdateId(getUserSession().getUserId());
			collectionService.update(collectionInfo, imgs);
			this.getAjaxMessagesJson().setMessage("0", "修改藏品信息成功");
			logger.debug("修改藏品信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改藏品信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除藏品信息信息
	 */
	public String deletes() {
		try {
			collectionService.delete(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	private void dealCollectionInfo(CollectionInfo bean){
		if (null == bean){
			return;
		}
		String appraisalDesc = "";
		if (StringUtil.isNotEmptyAndNotNull(bean.getAppraisal())){
			appraisalDesc += bean.getAppraisal();
			if (StringUtil.isNotEmptyAndNotNull(bean.getAppraisalUnit())){
				appraisalDesc += StringUtil.trim(moneyMap.get(bean.getAppraisalUnit()));
			}
		}
		bean.setAppraisalDesc(appraisalDesc);
		
		String transactionPriceDesc = "";
		if (StringUtil.isNotEmptyAndNotNull(bean.getTransactionPrice())){
			transactionPriceDesc += bean.getTransactionPrice();
			if (StringUtil.isNotEmptyAndNotNull(bean.getTransactionPriceUnit())){
				transactionPriceDesc += StringUtil.trim(moneyMap.get(bean.getTransactionPriceUnit()));
			}
		}
		bean.setTransactionPriceDesc(transactionPriceDesc);
		
		bean.setAuctionName(StringUtil.trim(auctionMap.get(bean.getAuctionId())));
		bean.setCollectionPeriodName(StringUtil.trim(collectionPeriods.get(bean.getCollectionPeriodId())));
		bean.setIsIdentifyDesc(StringUtil.trim(whethers.get(bean.getIsIdentify())));
		bean.setIsSendRacketDesc(StringUtil.trim(whethers.get(bean.getIsSendRacket())));
		bean.setIsSoldDesc(StringUtil.trim(whethers.get(bean.getIsSold())));
		bean.setLabelName(StringUtil.trim(collectionLables.get(bean.getLabelId())));
		bean.setStatusDesc(StringUtil.trim(statusMap.get(bean.getStatus())));
	}
	
	/** getter and setter methods **/
	
	public CollectionService getCollectionService() {
		return collectionService;
	}

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}

	public CollectionForm getCollectionForm() {
		return collectionForm;
	}

	public void setCollectionForm(CollectionForm collectionForm) {
		this.collectionForm = collectionForm;
	}

	public Map<Integer, String> getCollectionPeriods() {
		return collectionPeriods;
	}

	public void setCollectionPeriods(Map<Integer, String> collectionPeriods) {
		this.collectionPeriods = collectionPeriods;
	}

	public Map<Integer, String> getCollectionLables() {
		return collectionLables;
	}

	public void setCollectionLables(Map<Integer, String> collectionLables) {
		this.collectionLables = collectionLables;
	}

	public Map<Integer, String> getCollectionCategorys() {
		return collectionCategorys;
	}

	public void setCollectionCategorys(Map<Integer, String> collectionCategorys) {
		this.collectionCategorys = collectionCategorys;
	}

	public Map<String, String> getWhethers() {
		return whethers;
	}

	public void setWhethers(Map<String, String> whethers) {
		this.whethers = whethers;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}

	public Map<Integer, String> getAuctionMap() {
		return auctionMap;
	}

	public void setAuctionMap(Map<Integer, String> auctionMap) {
		this.auctionMap = auctionMap;
	}

	public Map<String, String> getMoneyMap() {
		return moneyMap;
	}

	public void setMoneyMap(Map<String, String> moneyMap) {
		this.moneyMap = moneyMap;
	}
	
	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public static void main(String[] args) {
		CollectionInfo collectionInfo = new CollectionInfo();
		CollectionForm collectionForm = new CollectionForm(); 
		collectionForm.setCategoryId(11);
		collectionForm.setCategoryName("aa");
		try {
			IctUtil.copyProperties(collectionInfo, collectionForm);
		} catch (IctException e) {
			e.printStackTrace();
		}
		System.out.println(collectionInfo.getCategoryId());
	}
}