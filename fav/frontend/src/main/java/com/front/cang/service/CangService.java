package com.front.cang.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.express.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.cang.util.CoordinatesUtil;
import com.front.db.bean.ApplyRecordBean;
import com.front.db.bean.AuctionBean;
import com.front.web.common.Constant;
import com.front.web.framework.database.BaseDao;
import com.front.web.framework.database.ResultSetInterface;
import com.front.web.util.IdCreaterTool;

public class CangService {
	private final static Logger logger = LoggerFactory.getLogger(CangService.class);
	
	/**
	 * 拍卖行动态
	 * @param startIdx
	 * @param endIdx
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAuctionDynamicList(int startIdx, int endIdx) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(startIdx);
		params.add(endIdx);
		String sql = "SELECT tad.*, ta.icon, tadt.name FROM tb_auction_dynamics tad " +
				"INNER JOIN tb_auction ta ON tad.auction_id = ta.id AND ta.use_flag = 1 " +
				"LEFT JOIN tb_auction_dynamics_type tadt ON tadt.id = tad.auction_dynamic_type_id " +
				"WHERE tad.use_flag = 1 ORDER BY tad.insert_date DESC LIMIT ?,?";
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("auction_id", rs.getBigDecimal("auction_id"));
				json.put("title", rs.getString("title"));
				json.put("source_id", rs.getString("source_id"));
				json.put("auction_dynamic_type_id", rs.getBigDecimal("auction_dynamic_type_id"));
				json.put("icon", rs.getString("icon"));
				json.put("name", rs.getString("name"));
				return json;
			}
		}, sql, params);
	}
	
	/**
	 * 拍卖行动态拍卖预展
	 * @param auctionDynamicId
	 * @return List<JSONObject>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAuctionDynamicPreviews(BigDecimal auctionDynamicId) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(auctionDynamicId);
		String sql = "SELECT tc.* FROM tb_auction_dynamic_preview tadp " +
				"INNER JOIN tb_collection tc ON tc.id = tadp.collection_id AND tc.use_flag = 1 " +
				"WHERE tadp.auction_dynamic_id = ? " +
				"AND tadp.use_flag = 1 ORDER BY  tadp.display_order ASC";
		List<JSONObject> collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				return parseCollection(rs);
			}
		}, sql, params);
		
		StringBuffer ids = new StringBuffer();
		for(JSONObject json : collectionList)
		{
			ids.append(",").append("'").append(json.getBigDecimal("id").toString()).append("'");
		}
		
		if (ids.length() > 0)
		{
			String collectionIds = ids.substring(1);
			Map<BigDecimal, List<String>> imgMap = queryCollectionImageMap(collectionIds);
			if (imgMap != null && !imgMap.isEmpty())
			{
				for(JSONObject json : collectionList)
				{
					json.put("images", imgMap.get(json.getBigDecimal("id")));
				}
			}
		}
		
		return collectionList;
	}
	
	/**
	 * 拍卖行动态拍卖成交
	 * @param auctionDynamicId
	 * @return List<JSONObject>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAuctionDynamicDeals(BigDecimal auctionDynamicId) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(auctionDynamicId);
		String sql = "SELECT tc.* FROM tb_auction_dynamic_deal tadp " +
				"INNER JOIN tb_collection tc ON tc.id = tadp.collection_id AND tc.use_flag = 1 " +
				"WHERE tadp.auction_dynamic_id = ? " +
				"AND tadp.use_flag = 1 ORDER BY  tadp.display_order ASC";
		List<JSONObject> collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				return parseCollection(rs);
			}
		}, sql, params);
		
		StringBuffer ids = new StringBuffer();
		for(JSONObject json : collectionList)
		{
			ids.append(",").append("'").append(json.getBigDecimal("id").toString()).append("'");
		}
		
		if (ids.length() > 0)
		{
			String collectionIds = ids.substring(1);
			Map<BigDecimal, List<String>> imgMap = queryCollectionImageMap(collectionIds);
			if (imgMap != null && !imgMap.isEmpty())
			{
				for(JSONObject json : collectionList)
				{
					json.put("images", imgMap.get(json.getBigDecimal("id")));
				}
			}
		}
		
		return collectionList;
	}
	
	/**
	 * 拍卖行动态拍卖成交
	 * @param auctionDynamicId
	 * @return List<JSONObject>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject queryAuctionDynamicLive(BigDecimal auctionDynamicId) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		JSONObject collection = new JSONObject();
		params.add(auctionDynamicId);
		String sql = "SELECT tc.* FROM tb_auction_dynamic_live tadl " +
				"INNER JOIN tb_collection tc ON tc.id = tadl.collection_id AND tc.use_flag = 1 " +
				"WHERE tadl.auction_dynamic_id = ? AND tadl.use_flag = 1;";
		List<JSONObject> collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				return parseCollection(rs);
			}
		}, sql, params);
		
		//藏品图片
		if (CollectionUtils.isNotEmpty(collectionList))
		{
			collection = collectionList.get(0);
			Map<BigDecimal, List<String>> imgMap = queryCollectionImageMap("'" + collection.getBigDecimal("id").toString() + "'");
			if (imgMap != null && !imgMap.isEmpty())
			{
				collection.put("images", imgMap.get(collection.getBigDecimal("id")));
			}
			
			//藏品价格
			sql = "SELECT * FROM tb_auction_collection_bid WHERE collection_id = ? AND use_flag = 1 ORDER BY bid_date DESC";
			params = new ArrayList<Object>();
			params.add(collection.getBigDecimal("id"));
			List<JSONObject> bidList = BaseDao.getListBySql(
				new ResultSetInterface() {
					public JSONObject getObject(ResultSet rs)throws SQLException {
						JSONObject json = new JSONObject();
						json.put("id", rs.getBigDecimal("id"));
					    json.put("username", rs.getString("username")
								+ SysConfigCache.getEnum(Constant.ENUM_SEX_NICK_TYPE+ rs.getString("sex_nick")));
						json.put("sex_nick", rs.getString("sex_nick"));
						json.put("price", rs.getDouble("price"));
						json.put("price_unit", SysConfigCache.getEnum(Constant.ENUM_MONEY_TYPE + rs.getString("price_unit")));
						//json.put("bid_date", DateUtil.DateTimeFormat(rs.getTimestamp("bid_date")));
						json.put("bid_date", DateUtil.format(rs.getTimestamp("bid_date"), "HH:mm:ss"));
						
						return json;
					}
				}, sql, params);
			
			collection.put("bidList", bidList);
		}
		return collection;
	}

	private static JSONObject parseCollection(ResultSet rs) throws SQLException {
		JSONObject json = new JSONObject();
		json.put("id", rs.getBigDecimal("id"));
		json.put("title", rs.getString("title"));
		json.put("introduction", rs.getString("introduction"));
		json.put("image_url", rs.getString("icon_img"));
		json.put("appraisal", rs.getString("appraisal"));
		json.put("appraisal_unit", SysConfigCache.getEnum(Constant.ENUM_MONEY_TYPE + rs.getString("appraisal_unit")));
		json.put("transaction_price", rs.getString("transaction_price"));
		json.put("transaction_price_unit", SysConfigCache.getEnum(Constant.ENUM_MONEY_TYPE + rs.getString("transaction_price_unit")));
		json.put("insert_date", DateUtil.DateFormat(rs.getTimestamp("insert_date")));
		json.put("lable", SysConfigCache.getCollectionLable(rs.getBigDecimal("label_id")));
		json.put("category", SysConfigCache.getCollectionCategory(rs.getBigDecimal("category_id")));
		json.put("collection_period", SysConfigCache.getCollectionPeriod(rs.getBigDecimal("collection_period_id")));
		return json;
	}
	/**
	 * 查询藏品集合的图片
	 * @param collectionIds 藏品id集合
	 * @return Map<BigDecimal, List<String>>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<BigDecimal, List<String>> queryCollectionImageMap(String collectionIds) throws Exception
	{
		Map<BigDecimal, List<String>> collectionImgMap = new HashMap<BigDecimal, List<String>>();
		String sql = "SELECT collection_id, image_url, display_order FROM tb_collection_images " +
				"WHERE collection_id IN (" + collectionIds + ") ORDER BY collection_id, display_order ASC";
		List<JSONObject> imgList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("collection_id"));
				json.put("img", rs.getString("image_url"));
				return json;
			}
		}, sql, null);
		
		for(JSONObject json : imgList)
		{
			if (collectionImgMap.containsKey(json.getBigDecimal("id")))
			{
				collectionImgMap.get(json.getBigDecimal("id")).add(json.getString("img"));
			}
			else
			{
				List<String> temp = new ArrayList<String>();
				temp.add(json.getString("img"));
				collectionImgMap.put(json.getBigDecimal("id"), temp);
			}
		}
		return collectionImgMap;
	}
	
	/**
	 * 查询拍卖行动态拍品征集
	 * @param auctionDynamicId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject queryAuctionDynamicImages(BigDecimal auctionDynamicId) throws Exception
	{
		JSONObject auctionCall = new JSONObject();
		List<Object> params = new ArrayList<Object>();
		params.add(auctionDynamicId);
		String sql = "SELECT * FROM tb_auction_dynamic_images WHERE auction_dynamic_id = ? AND use_flag = 1 ORDER BY insert_date DESC";
		List<JSONObject> auctionDynamicList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("auction_dynamic_id", rs.getBigDecimal("auction_dynamic_id"));
				json.put("content", rs.getString("content"));
				String image = rs.getString("images");
				JSONArray imgs = new JSONArray();
				JSONObject img = null;
				if (StringUtils.isNotBlank(image))
				{
					String[] images = image.split(",");
					for(String str : images)
					{
						if (StringUtils.isNotBlank(str))
						{
							img = new JSONObject();
							img.put("image", str);
							imgs.add(img);
						}
					}
				}
				json.put("images", imgs);
				return json;
			}
		}, sql, params);
		
		if (CollectionUtils.isNotEmpty(auctionDynamicList))
		{
			auctionCall = auctionDynamicList.get(0);
		}
		return auctionCall;
	}
	
	/**
	 * 名人堂列表
	 * @param famousType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryFamousList(String famousType) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(famousType);
		String sql = "SELECT t.id, t.name, t.icon, t.status, t.specialids, t.type FROM tb_famous_home t WHERE t.type = ? AND t.use_flag = 1;";
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("name", rs.getString("name"));
				json.put("icon", rs.getString("icon"));
				json.put("status", rs.getString("status"));
				json.put("specialids", SysConfigCache.getSpecialTitle(rs.getString("specialids")));
				json.put("type", rs.getString("type"));
				return json;
			}
		}, sql, params);
	}
	
	/**
	 * 名人堂详情
	 * @param id
	 * @return JSONObject
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject queryFamous(BigDecimal id) throws Exception
	{
		JSONObject famous = new JSONObject();
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT t.* FROM tb_famous_home t WHERE t.id = ? AND t.use_flag = 1";
		List<JSONObject> list = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("name", rs.getString("name"));
				json.put("icon", rs.getString("icon"));
				json.put("introduction", rs.getString("introduction"));
				json.put("status", rs.getString("status"));
				json.put("specialids", SysConfigCache.getSpecialTitle(rs.getString("specialids")));
				json.put("type", rs.getString("type"));
				return json;
			}
		}, sql, params);
		
		if (CollectionUtils.isNotEmpty(list))
		{
			famous = list.get(0);
		}
		
		return famous;
	}
	
	/**
	 * 
	 * @param city
	 * @param startIdx
	 * @param endIdx
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray queryCuriosityShopList(double lat, double lon, String city, int startIdx, int endIdx)
	{
		JSONArray shopList = new JSONArray();
		List<Object> params = new ArrayList<Object>();
		params.add(city);
		params.add(startIdx);
		params.add(endIdx);
		String sql = "SELECT tcs.* FROM tb_curiosity_shop tcs WHERE tcs.city = ? AND tcs.use_flag = 1 LIMIT ?, ?";
		List<JSONObject> list = null;
		try {
			list = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = parseCuriosityShop(rs);
					return json;
				}
			}, sql, params);
		} catch (Exception e) {
			logger.error("queryCuriosityShopList city[" + city + "] error.", e);
		}
		
		if (CollectionUtils.isNotEmpty(list))
		{
			for(JSONObject obj :  list)
			{
				double distance =  CoordinatesUtil.getDistance(lat, lon, obj.getDouble("lat"), obj.getDouble("longitude"));
				obj.put("distance", (int)distance);
				shopList.add(obj);
			}
		}
		
		return shopList;
	}
	
	private static JSONObject parseCuriosityShop(ResultSet rs) throws SQLException {
		JSONObject json = new JSONObject();
		json.put("id", rs.getBigDecimal("id"));
		json.put("name", rs.getString("name"));
		json.put("address", rs.getString("address"));
		json.put("phone", rs.getString("phone"));
		json.put("introduction", rs.getString("introduction"));
		json.put("icon", rs.getString("icon"));
		json.put("city", rs.getString("city"));
		json.put("county", rs.getString("county"));
		json.put("images", rs.getString("images"));
		json.put("lat", rs.getDouble("lat"));
		json.put("longitude", rs.getDouble("longitude"));
		return json;
	}
	/**
	 *  declare @R float
		set @R = 6378.137;
		declare @lng float
		declare @lat float
		set @lng = 11.11111;
		set @lat = 22.22222;
		select * from gmap where @R*acos(cos(lng)*cos(@lng)*(lat-@lat)+sin(lng)*sin(@lng))*1000<=50
		--地球面2点间距离的公示
		R·arc cos[cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2] 
	 */
	
	/**
	 * 
	 * @param city
	 * @param startIdx
	 * @param endIdx
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray queryCuriosityShopList(double lat, double lon, int raidus, int startIdx, int endIdx)
	{
		// minLat,minLng,maxLat,maxLng 
		double[] latlons = CoordinatesUtil.getAround(lat, lon, raidus);
		
		
		JSONArray shopList = new JSONArray();
		List<Object> params = new ArrayList<Object>();
		params.add(latlons[0]);
		params.add(latlons[2]);
		params.add(latlons[1]);
		params.add(latlons[3]);
		params.add(startIdx);
		params.add(endIdx);
		
		String sql = "SELECT tcs.* FROM tb_curiosity_shop tcs WHERE tcs.lat >= ? AND tcs.lat <= ? AND tcs.longitude >= ? AND tcs.longitude <= ? AND tcs.use_flag = 1 LIMIT ?,?";
		List<JSONObject> list = null;
		try {
			list = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = parseCuriosityShop(rs);
					return json;
				}
			}, sql, params);
		} catch (Exception e) {
			logger.error("queryCuriosityShopList raidus[" + raidus + "] error.", e);
		}
		
		if (CollectionUtils.isNotEmpty(list))
		{
			for(JSONObject obj :  list)
			{
				double distance =  CoordinatesUtil.getDistance(lat, lon, obj.getDouble("lat"), obj.getDouble("longitude"));
				obj.put("distance", (int)distance);
				shopList.add(obj);
			}
		}
		
		return shopList;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject queryCuriosityShop(BigDecimal id)
	{
		JSONObject shop = new JSONObject();
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT tcs.* FROM tb_curiosity_shop tcs WHERE tcs.id = ? AND tcs.use_flag = 1";
		List<JSONObject> list = null;
		try {
			list = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = parseCuriosityShop(rs);
					String image = rs.getString("images");
					JSONArray imgs = new JSONArray();
					JSONObject img = null;
					if (StringUtils.isNotBlank(image))
					{
						String[] images = image.split(",");
						for(String str : images)
						{
							if (StringUtils.isNotBlank(str))
							{
								img = new JSONObject();
								img.put("imageUrl", str);
								imgs.add(img);
							}
						}
					}
					json.put("images", imgs);
					return json;
				}
			}, sql, params);
		} catch (Exception e) {
			logger.error("queryCuriosityShopList id[" + id + "] error.", e);
		}
		
		if (CollectionUtils.isNotEmpty(list))
		{
				shop = list.get(0);
		}
		return shop;
	}
	
	public static AuctionBean queryAuction(BigDecimal id)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			return (AuctionBean) BaseDao.getObjectByAnnotation(AuctionBean.class, "id = ?", params);
		} catch (Exception e) {
			logger.error("queryAuction id[" + id + "] error.", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAuctionCollectonsByCategory(BigDecimal categoryId, BigDecimal periodId, int startIdx, int endIdx) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		String condSql = "SELECT tc.*, ta.name FROM tb_collection tc LEFT JOIN tb_auction ta ON tc.auction_id = ta.id " +
				"WHERE tc.category_id = ? AND tc.collection_period_id = ? AND tc.status = 'collection_status_solded' " +
				"AND tc.use_flag = 1 ORDER BY tc.transaction_price_time DESC LIMIT ?,?";
		if (periodId == null)
		{
			condSql = condSql.replace("tc.collection_period_id = ? AND", "");
		}
		else
		{
			params.add(periodId);
		}
		params.add(startIdx);
		params.add(endIdx);
		List<JSONObject> collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToAuctionCollection(rs);
				return json;
			}
		}, condSql, params);
		return collectionList;
	}
	
	public static JSONObject parseDataToAuctionCollection(ResultSet rs) throws SQLException {
		JSONObject json = new JSONObject();
		json.put("id", rs.getBigDecimal("id"));
		json.put("category_id", rs.getBigDecimal("category_id"));
		json.put("collection_period_id", rs.getBigDecimal("collection_period_id"));
		json.put("title", rs.getString("title"));
		json.put("image_url", rs.getString("icon_img"));
		json.put("insert_date", DateUtil.DateFormat(rs.getTimestamp("insert_date")));
		json.put("lable", SysConfigCache.getCollectionLable(rs.getBigDecimal("label_id")));
		json.put("category", SysConfigCache.getCollectionCategory(rs.getBigDecimal("category_id")));
		json.put("collection_period", SysConfigCache.getCollectionPeriod(rs.getBigDecimal("collection_period_id")));
		if (rs.getDate("transaction_price_time") != null)
		{
			json.put("transaction_price_time", DateUtil.DateFormat(rs.getDate("transaction_price_time")));
		}
		if (rs.getString("transaction_price_unit") != null)
		{
			json.put("transaction_price_unit", SysConfigCache.getEnum(Constant.ENUM_MONEY_TYPE + rs.getString("transaction_price_unit")));
		}
		if (rs.getString("transaction_price") != null)
		{
			json.put("transaction_price", rs.getString("transaction_price"));
		}
		json.put("auction_name", rs.getString("name"));
		return json;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAuctionCollectonsByTitle(String title, int startIdx, int endIdx) throws Exception
	{
		
		String condSql = "SELECT tc.*, ta.name FROM tb_collection tc LEFT JOIN tb_auction ta ON tc.auction_id = ta.id " +
				"WHERE tc.title LIKE ? AND tc.status = 'collection_status_solded' " +
				"AND tc.use_flag = 1 ORDER BY tc.transaction_price_time DESC LIMIT ?,?";
		List<Object> params = new ArrayList<Object>();
		params.add("%" + title + "%");
		params.add(startIdx);
		params.add(endIdx);
		List<JSONObject> collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToAuctionCollection(rs);
				return json;
			}
		}, condSql, params);
		return collectionList;
	}
	
	public static void addApplyRecord(ApplyRecordBean applyRecordBean)
	{
		try {
			if (applyRecordBean != null)
			{
				if (applyRecordBean.getId() == null)
				{
					applyRecordBean.setId(IdCreaterTool.getApplyRecordId());
				}
				BaseDao.insert(applyRecordBean);
			}
			
		} catch (Exception e) {
			logger.error("addApplyRecord error.", e);
		}
	}
}
