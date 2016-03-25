package com.front.cang.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.qiniu.http.Response;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.express.database.DBManager;
import org.express.portal.Mapping;
import org.express.portal.MultipartHttpServletRequest;
import org.express.portal.PathUtil;
import org.express.portal.renderer.Renderer;
import org.express.portal.renderer.TemplateRenderer;
import org.express.util.QiniuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.cang.service.CangService;
import com.front.db.bean.ApplyRecordBean;
import com.front.db.bean.AuctionBean;
import com.front.db.bean.CollectionBean;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.CollectionImagesBean;
import com.front.db.bean.CollectionPeriodBean;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.FavoritesBean;
import com.front.fa.service.FaService;
import com.front.shou.service.ShouService;
import com.front.web.common.Constant;
import com.front.web.common.base.GenericPageAction;
import com.front.web.util.IdCreaterTool;

public class CangAction extends GenericPageAction{
	private final static Logger logger = LoggerFactory.getLogger(CangAction.class);

	@Mapping("/cangIndex")
	public Renderer goCangIndex()
	{
		Map<String, Object> out = getOutputMap();
        return new TemplateRenderer("/html/cang/index.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/cangDynamic")
	public Renderer cangDynamic()
	{
		Map<String, Object> out = getOutputMap();
		List<JSONObject> dynamicList = new ArrayList<JSONObject>();
		try {
			dynamicList = CangService.queryAuctionDynamicList(0, 100);
		} catch (Exception e) {
			logger.error("cangDynamic error", e);
		}
		out.put("dynamicList", dynamicList);
        return new TemplateRenderer("/html/cang/dynamic.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/toCangDynamicDetail")
	public Renderer toCangDynamicDetail()
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		String type = getRequest().getParameter("type");
		String auctionId = getRequest().getParameter("auctionId");
		if (Constant.AUCTION_DYNAMICS_TYPE_CALL.equalsIgnoreCase(type))
		{
			JSONObject call = new JSONObject();
			try {
				call =  CangService.queryAuctionDynamicImages(new BigDecimal(id));
			} catch (Exception e) {
				logger.error("toCangDynamicDetail CALL error.",e);
			}
			out.put("call", call);
			out.put("id", id);
			out.put("type", type);
			out.put("auctionId", auctionId);
			return new TemplateRenderer("/html/cang/product-collect.html" , Constant.DEFAULT_MODULE_STRING , out);
		}
		else if (Constant.AUCTION_DYNAMICS_TYPE_LIVE.equalsIgnoreCase(type))
		{
			JSONObject live = new JSONObject();
			try {
				live = CangService.queryAuctionDynamicLive(new BigDecimal(id));
			} catch (Exception e) {
				logger.error("toCangDynamicDetail LIVE error.",e);
			}
			out.put("live", live);
			return new TemplateRenderer("/html/cang/product-direct.html" , Constant.DEFAULT_MODULE_STRING , out);
		}
		else if (Constant.AUCTION_DYNAMICS_TYPE_PREVIEW.equalsIgnoreCase(type))
		{
			List<JSONObject> previewList = new ArrayList<JSONObject>();
			try {
				previewList = CangService.queryAuctionDynamicPreviews(new BigDecimal(id));
			} catch (Exception e) {
				logger.error("toCangDynamicDetail PREVIEW error.",e);
			}
			out.put("previewList", previewList);
			return new TemplateRenderer("/html/cang/product-show.html" , Constant.DEFAULT_MODULE_STRING , out);
		}
		else if (Constant.AUCTION_DYNAMICS_TYPE_TRANS_RECORD.equalsIgnoreCase(type))
		{
			List<JSONObject> dealList = new ArrayList<JSONObject>();
			try {
				dealList = CangService.queryAuctionDynamicDeals(new BigDecimal(id));
			} catch (Exception e) {
				logger.error("toCangDynamicDetail TRANS_RECORD error.",e);
			}
			out.put("dealList", dealList);
			return new TemplateRenderer("/html/cang/product-history.html" , Constant.DEFAULT_MODULE_STRING , out);
		}
		return null;
	}
	
	@Mapping("/cangSearch")
	public Renderer cangSearch()
	{
		Map<String, Object> out = getOutputMap();
		List<CollectionCategoryBean> collectionCategoryList = SysConfigCache.getCollectionCategorys();
		List<CollectionPeriodBean> collectionPeriodList = SysConfigCache.getCollectionPeriods();
		out.put("categoryList", collectionCategoryList);
		out.put("periodList", collectionPeriodList);
        return new TemplateRenderer("/html/cang/search.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/famous")
	public Renderer famous()
	{
		Map<String, Object> out = getOutputMap();
		String type = getRequest().getParameter("type");
		//默认为收藏大咖
		if (StringUtils.isBlank(type))
		{
			type = Constant.FAMOUS_TYPE_COLLECT;
		}
		List<JSONObject> famousList = new ArrayList<JSONObject>();
		try {
			famousList = CangService.queryFamousList(type);
		} catch (Exception e) {
			logger.error("famous error", e);
		}
		out.put("famousList", famousList);
		out.put("type", type);
        return new TemplateRenderer("/html/cang/famous.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/queryFamousByType")
	public Renderer queryFamousByType()
	{
		String type = getRequest().getParameter("type");
		JSONArray fanousList = new JSONArray();
		//默认为收藏大咖
		if (StringUtils.isBlank(type))
		{
			type = Constant.FAMOUS_TYPE_COLLECT;
		}
		List<JSONObject> famousList = new ArrayList<JSONObject>();
		try {
			famousList = CangService.queryFamousList(type);
		} catch (Exception e) {
			logger.error("famous error", e);
		}
		for(JSONObject obj : famousList)
		{
			fanousList.add(obj);
		}
		writerObjToPage(fanousList);
		return null;
	}
	@Mapping("/cangShop")
	public Renderer cangShop()
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean user = getSessionUser();
        return new TemplateRenderer("/html/cang/shop.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/songPai")
	public Renderer songPai()
	{
		String id = getRequest().getParameter("id");
		String auctionId = getRequest().getParameter("auctionId");
		Map<String, Object> out = getOutputMap();
		out.put("id", id);
		out.put("auctionId", auctionId);
        return new TemplateRenderer("/html/cang/songpai.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/addAuction")
	public Renderer addAuction()
	{
		Map<String, Object> out = getOutputMap();
		FavUserBean fuBean = getSessionUser();
		List<Object> ImagesBeanList = new ArrayList<Object>();
		CollectionImagesBean ciBean = null;
		int displayOrder = 0;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) getRequest();
		String auctionDynamicsId = multipartRequest.getParameter("auctionDynamicsId");
		String auctionId = multipartRequest.getParameter("auctionId");
		String description = multipartRequest.getParameter("description");
		CollectionBean cBean = new CollectionBean();
		BigDecimal collectionId = IdCreaterTool.getCollectionId();
		cBean.setId(collectionId);
		cBean.setIntroduction(description);
		cBean.setIs_send_racket("Y");
		BigDecimal defaultId = new BigDecimal(0);
		cBean.setCategory_id(defaultId);
		cBean.setCollection_period_id(defaultId);
		cBean.setTitle("送拍品");
		cBean.setIs_identify("N");
		cBean.setLabel_id(defaultId);
		cBean.setStatus(Constant.COLLECTION_STATUS_SEND_RACKET);
		cBean.setAuction_id(new BigDecimal(auctionId));
		cBean.setInsert_id(fuBean.getId());
		cBean.setUpdate_id(fuBean.getId());
		cBean.setAppraisal_user_id(defaultId);
		cBean.setTransaction_user_id(defaultId);
		//上传图片集合
		List<FileItem> fileItems = multipartRequest.getUpFileData();
		for (FileItem fileItem : fileItems)
		{
			String fileName = fileItem.getName();
			if (StringUtils.isBlank(fileName))
			{
				continue;
			}
			String uuid = "atn_" + UUID.randomUUID().toString(); // 重命名文件
//			File file = new File(PathUtil.getUploadImgPath()).getAbsoluteFile();
//			if (!file.exists()) {
//				file.mkdirs();
//			}
			int pos = fileName.lastIndexOf(".");// 取图片文件格式
			if (pos > 0) {
				fileName = fileName.substring(pos);
			}
			fileName = uuid + fileName;
//			String filepathString = file.getPath() + "/" + uuid  + fileName;
//			String imgUrl = PathUtil.getUploadImgFileName() + "/" + uuid  + fileName;
			try {
				// 写到磁盘
//				fileItem.write(new File(filepathString));
				Response response = QiniuUtil.upload(IOUtils.toByteArray(fileItem.getInputStream()), fileName);
				if(response != null) {
					String imgUrl = fileName;
					ciBean = new CollectionImagesBean();
					ciBean.setId(IdCreaterTool.getCollectionImagesId());
					if (displayOrder == 0) {
						cBean.setIcon_img(imgUrl);
					}
					ciBean.setDisplay_order(displayOrder);
					displayOrder++;
					ciBean.setCollection_id(collectionId);
					ciBean.setInsert_id(fuBean.getId());
					ciBean.setUpdate_id(fuBean.getId());
					ciBean.setImage_url(imgUrl);
					ImagesBeanList.add(ciBean);
				}
			} catch (Exception e) {
				logger.error("addAuction write file [" + fileItem.getName() + "]error", e);
			}
		}
		boolean result = false;
		try {
			result = FaService.saveCollection(cBean, ImagesBeanList);
		} catch (Exception e) {
			logger.error("addAuction", e);
			try {
				if (DBManager.isTrans()){
					DBManager.rollback();
				}
			} catch (SQLException e1) {
				logger.error("addAuction handleExecution", e1);
			}
		}
		String msg = "发布送拍藏品成功";
		if (!result)
		{
			msg = "发布送拍藏品失败";
		}
		else
		{
			//往申请记录表中增加一个申请记录
			ApplyRecordBean applyRecordBean = new ApplyRecordBean();
			applyRecordBean.setCollection_id(collectionId);
			applyRecordBean.setApplier_id(fuBean.getId());
			applyRecordBean.setStatus("collection_status_wait_apply");
			applyRecordBean.setApply_time(new Date());
			applyRecordBean.setApply_type(auctionDynamicsId);
			applyRecordBean.setInsert_date(new Date());
			applyRecordBean.setInsert_id(fuBean.getId());
			CangService.addApplyRecord(applyRecordBean);
		}
		out.put("isShow", true);
		out.put("msg", msg);
		out.put("result", result);
        return new TemplateRenderer("/html/cang/songpai.html" , Constant.DEFAULT_MODULE_STRING , out);
	}

	@Mapping("/cangSearchList")
	public Renderer cangSearchList()
	{
		Map<String, Object> out = getOutputMap();
		String periodId = getRequest().getParameter("periodId");
		String categoryId = getRequest().getParameter("categoryId");
		List<JSONObject> collectionList = new ArrayList<JSONObject>();
		try {
			collectionList =  CangService.queryAuctionCollectonsByCategory(new BigDecimal(categoryId), new BigDecimal(periodId), 0, Constant.MAX_PAGE_SIZE);
		} catch (Exception e) {
			logger.error("cangSearchList error", e);
		}
		out.put("category", SysConfigCache.getCollectionCategory(new BigDecimal(categoryId)));
		out.put("period", SysConfigCache.getCollectionPeriod(new BigDecimal(periodId)));
		out.put("collectionList", collectionList);
        return new TemplateRenderer("/html/cang/search-list.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/queryCangSearchList")
	public Renderer queryCangSearchList()
	{
		Map<String, Object> out = getOutputMap();
		String title = getRequest().getParameter("title");
		List<JSONObject> collectionList = new ArrayList<JSONObject>();
		try {
			collectionList =  CangService.queryAuctionCollectonsByTitle(title, 0, Constant.MAX_PAGE_SIZE);
		} catch (Exception e) {
			logger.error("queryCangSearchList error", e);
		}
		out.put("collectionList", collectionList);
		out.put("title", title);
        return new TemplateRenderer("/html/cang/searchList.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	
	@Mapping("/famousShow")
	public Renderer famousShow()
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject famous = new JSONObject();
		try {
			famous = CangService.queryFamous(new BigDecimal(id));
		} catch (Exception e) {
			logger.error("famousShow error.", e);
		}
		out.put("famous", famous);
        return new TemplateRenderer("/html/cang/famous-show.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/shopShow")
	public Renderer shopShow()
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject curiosityShop = CangService.queryCuriosityShop(new BigDecimal(id));
		out.put("shop", curiosityShop);
        return new TemplateRenderer("/html/cang/shop-show.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	@Mapping("/queryCuriosityShop")
	public Renderer queryCuriosityShop()
	{
		String city = getRequest().getParameter("city");
		String pageIdx = getRequest().getParameter("pageIdx");
		String rang = getRequest().getParameter("rang");
		String latitude = getRequest().getParameter("latitude");
		String longitude = getRequest().getParameter("longitude");
		JSONArray shopList = new JSONArray();
		if ("1".equals(rang) || "3".equals(rang) || "5".equals(rang))
		{
			shopList = CangService.queryCuriosityShopList(Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(rang) * 1000, 0, Constant.MAX_PAGE_SIZE);
		}
		else
		{
			shopList = CangService.queryCuriosityShopList(Double.parseDouble(latitude), Double.parseDouble(longitude), city, 0, Constant.MAX_PAGE_SIZE);
		}
		writerObjToPage(shopList);
		return null;
	}
	
	@Mapping("/cangSearchShow")
	public Renderer cangSearchShow()
	{
		Map<String, Object> out = getOutputMap();
		String id = getRequest().getParameter("id");
		JSONObject collection = new JSONObject();
		try {
			collection = ShouService.queryCollectionDetail(new BigDecimal(id));
			if (collection.containsKey("detail"))
			{
				JSONObject detail = (JSONObject)collection.get("detail");
				AuctionBean auctionBean = CangService.queryAuction(detail.getBigDecimal("auction_id"));	
				if (auctionBean != null)
				{
					out.put("auctionName", auctionBean.getName());			
				}
			}
		} catch (Exception e) {
			logger.error("cangSearchShow error.", e);
		} 
		out.put("collectionDetail", collection);
		FavUserBean favUserBean = getSessionUser();
		//从分享的地址进入时，如没有session则不展示收藏按钮
		out.put("isShowFav", false);
		if (favUserBean != null)
		{
			out.put("isShowFav", true);
			out.put("isFaved", false);
			FavoritesBean fbean = ShouService.queryFavoritesBean(favUserBean.getId(), new BigDecimal(id));
			if (fbean != null)
			{
				out.put("isFaved", true);
			}
		}
		
        return new TemplateRenderer("/html/cang/search-show.html" , Constant.DEFAULT_MODULE_STRING , out);
	}
	
	
}
