package com.osource.module.fav.web.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.util.IctUtil;

import com.osource.module.fav.model.AuctionDynamicImagesInfo;
import com.osource.module.fav.model.AuctionDynamicLiveInfo;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.service.AuctionDynamicImagesService;
import com.osource.module.fav.service.AuctionDynamicsService;
import com.osource.module.fav.service.CollectionService;
import com.osource.module.fav.web.form.AuctionDynamicImagesForm;
import com.osource.base.util.StringUtil;
import com.osource.base.web.action.BaseAction;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class AuctionDynamicImagesAction extends BaseAction {
	
	@Autowired
	private AuctionDynamicImagesService auctionDynamicImagesService;
	
	private AuctionDynamicImagesForm auctionDynamicImagesForm;

	@Autowired
	private AuctionDynamicsService auctionDynamicsService;
	
	private Map<Integer, String> dynamicMap;
	
	private List<String> imgs;
	
	/** action methods **/
	
	public AuctionDynamicImagesAction(){
		super();
	}
	
	/**
	 *  功能初始页面跳转
	 */
	public String init(){
		dynamicMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 1);
		PageList<AuctionDynamicsInfo> dlist = auctionDynamicsService.findByCondition(condition);
		if (null != dlist){
			for (AuctionDynamicsInfo info : dlist) {
				dynamicMap.put(info.getId(), info.getTitle());
			}
		}
		return RESULT_INIT;
	}
	
	/**
	 *  根据查询条件进行查询
	 */
	public String query(){
		Map condition = new HashMap();
		putConditonMap(condition, "auctionDynamicTitle", auctionDynamicImagesForm.getAuctionDynamicTitle(), false);
		putConditonMap(condition, "content", auctionDynamicImagesForm.getContent(), false);
		Pages pages = new Pages(this.getPage(),this.getLimit());
		this.setPageList(auctionDynamicImagesService.findByCondition(condition, pages));
		
		return RESULT_LIST;
	}

	/**
	 *  跳转到添加拍卖行动态拍品征集信息页面
	 */
	public String add() {
		dynamicMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 1);
		condition.put("sourceId", 0);
		PageList<AuctionDynamicsInfo> dlist = auctionDynamicsService.findByCondition(condition);
		if (null != dlist){
			for (AuctionDynamicsInfo info : dlist) {
				dynamicMap.put(info.getId(), info.getTitle());
			}
		}
		this.setActionName("auctionDynamicImages_save");
		return RESULT_SET;
	}

	/**
	 *  添加拍卖行动态拍品征集信息信息
	 */
	public String save() {
		AuctionDynamicImagesInfo auctionDynamicImagesInfo = new AuctionDynamicImagesInfo();
	
		try {
			if(auctionDynamicImagesForm != null)
				IctUtil.copyProperties(auctionDynamicImagesInfo, auctionDynamicImagesForm);
			auctionDynamicImagesInfo.setInsertId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					auctionDynamicImagesInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			auctionDynamicImagesService.save(auctionDynamicImagesInfo);
			// 修改拍卖行动态表
			AuctionDynamicsInfo info = auctionDynamicsService.findById(auctionDynamicImagesInfo.getAuctionDynamicId());
			info.setSourceId(auctionDynamicImagesInfo.getId());
			auctionDynamicsService.update(info);
			this.getAjaxMessagesJson().setMessage("0", "添加拍卖行动态拍品征集信息成功");
			logger.debug("添加拍卖行动态拍品征集信息成功");
		} catch (Exception e) { 
			this.getAjaxMessagesJson().setMessage("E_ADDFAILED", "添加拍卖行动态拍品征集信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;
	}

	/**
	 *  跳转到编辑拍卖行动态拍品征集信息页面
	 */
	public String edit() throws IctException {
		dynamicMap = new LinkedHashMap<Integer, String>();
		Map condition = new HashMap();
		condition.put("auctionDynamicTypeId", 1);
		condition.put("sourceId", 0);
		PageList<AuctionDynamicsInfo> dlist = auctionDynamicsService.findByCondition(condition);
		if (null != dlist){
			for (AuctionDynamicsInfo info : dlist) {
				dynamicMap.put(info.getId(), info.getTitle());
			}
		}
		AuctionDynamicImagesInfo auctionDynamicImagesInfo;
		auctionDynamicImagesInfo = auctionDynamicImagesService.findById(this.getId());
		AuctionDynamicsInfo info = auctionDynamicsService.findById(auctionDynamicImagesInfo.getAuctionDynamicId());
		dynamicMap.put(info.getId(), info.getTitle());
		AuctionDynamicImagesForm auctionDynamicImagesForm = new AuctionDynamicImagesForm(); 
		IctUtil.copyProperties(auctionDynamicImagesForm, auctionDynamicImagesInfo);
		String images = auctionDynamicImagesForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setAuctionDynamicImagesForm(auctionDynamicImagesForm);
		
		return RESULT_SET;
	}

	/**
	 *  跳转到查看拍卖行动态拍品征集信息页面
	 */
	public String view() throws IctException {
		AuctionDynamicImagesInfo auctionDynamicImagesInfo;
		auctionDynamicImagesInfo = auctionDynamicImagesService.findById(Integer.valueOf(this.getId()));
		
		AuctionDynamicImagesForm auctionDynamicImagesForm = new AuctionDynamicImagesForm(); 
		IctUtil.copyProperties(auctionDynamicImagesForm, auctionDynamicImagesInfo);
		String images = auctionDynamicImagesForm.getImages();
		if (StringUtil.isNotEmptyAndNotNull(images)){
			imgs = Arrays.asList(images.split(","));
		}
		this.setAuctionDynamicImagesForm(auctionDynamicImagesForm);
		
		return RESULT_VIEW;
	}
	
	/**
	 *  修改拍卖行动态拍品征集信息信息
	 */
	public String update() {
		AuctionDynamicImagesInfo auctionDynamicImagesInfo = new AuctionDynamicImagesInfo();
		AuctionDynamicImagesInfo oldInfo = auctionDynamicImagesService.findById(auctionDynamicImagesForm.getId());
		try {
			IctUtil.copyProperties(auctionDynamicImagesInfo, auctionDynamicImagesForm);
			auctionDynamicImagesInfo.setUpdateId(getUserSession().getUserId());
			if (null != imgs && imgs.size() != 0){
				String images = "";
				for (String img : imgs) {
					if (StringUtil.isNotEmptyAndNotNull(img)){
						images += img + ",";
					}
				}
				if (images.length() > 1){
					auctionDynamicImagesInfo.setImages(images.substring(0, images.length() - 1));
				}
			}
			// 修改拍卖行动态表
			if (oldInfo.getAuctionDynamicId() != auctionDynamicImagesInfo.getAuctionDynamicId()){
				AuctionDynamicsInfo info = auctionDynamicsService.findById(oldInfo.getAuctionDynamicId());
				info.setSourceId(oldInfo.getId());
				auctionDynamicsService.update(info);
				
				AuctionDynamicsInfo info2 = auctionDynamicsService.findById(auctionDynamicImagesInfo.getAuctionDynamicId());
				info2.setSourceId(auctionDynamicImagesInfo.getId());
				auctionDynamicsService.update(info2);
			}
			auctionDynamicImagesService.update(auctionDynamicImagesInfo);
			this.getAjaxMessagesJson().setMessage("0", "修改拍卖行动态拍品征集信息成功");
			logger.debug("修改拍卖行动态拍品征集信息成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_MODFAILED", "修改拍卖行动态拍品征集信息失败");
			logger.debug(e);
		}
		
		return RESULT_AJAXJSON;	
	}
	
	/**
	 *  删除拍卖行动态拍品征集信息信息
	 */
	public String deletes() {
		try {
			for (String id : this.getIds().split(",")) {
				AuctionDynamicImagesInfo oldInfo = auctionDynamicImagesService.findById(id);
				AuctionDynamicsInfo info = auctionDynamicsService.findById(oldInfo.getAuctionDynamicId());
				info.setSourceId(0);
				auctionDynamicsService.update(info);
			}
			auctionDynamicImagesService.deleteById(this.getIds());
			this.getAjaxMessagesJson().setMessage("0", "删除成功");
			logger.debug("删除成功");
		} catch (Exception e) {
			this.getAjaxMessagesJson().setMessage("E_DELFAILED", "删除失败");
			logger.debug(e);
		}
		return RESULT_AJAXJSON;
	}
	
	/** getter and setter methods **/
	
	public AuctionDynamicImagesService getAuctionDynamicImagesService() {
		return auctionDynamicImagesService;
	}

	public void setAuctionDynamicImagesService(AuctionDynamicImagesService auctionDynamicImagesService) {
		this.auctionDynamicImagesService = auctionDynamicImagesService;
	}

	public AuctionDynamicImagesForm getAuctionDynamicImagesForm() {
		return auctionDynamicImagesForm;
	}

	public void setAuctionDynamicImagesForm(AuctionDynamicImagesForm auctionDynamicImagesForm) {
		this.auctionDynamicImagesForm = auctionDynamicImagesForm;
	}

	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	public Map<Integer, String> getDynamicMap() {
		return dynamicMap;
	}

	public void setDynamicMap(Map<Integer, String> dynamicMap) {
		this.dynamicMap = dynamicMap;
	}
}