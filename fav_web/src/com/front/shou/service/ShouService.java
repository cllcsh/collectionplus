package com.front.shou.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.express.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.db.bean.CollectionBean;
import com.front.db.bean.CollectionImagesBean;
import com.front.db.bean.CommentBean;
import com.front.db.bean.CommentLikeBean;
import com.front.db.bean.CommentTopBean;
import com.front.db.bean.DynamicLikeBean;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.FavUserSetBean;
import com.front.db.bean.FavoritesBean;
import com.front.db.bean.UserBlacklistBean;
import com.front.db.bean.UserFansBean;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.framework.database.BaseDao;
import com.front.web.framework.database.ResultSetInterface;
import com.front.web.util.FavRuntimeException;
import com.front.web.util.IdCreaterTool;
import com.front.web.util.JsonUtil;

public class ShouService {

	private final static Logger logger = LoggerFactory.getLogger(ShouService.class);
	
	/**
	 * 查询今日最热的100个藏品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryDayHotCollections(BigDecimal id) throws Exception
	{
		String sql = "SELECT tc.*, COUNT(tcm.source_id) as comment_num FROM tb_collection tc " +
				"INNER JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = 0 " +
				"WHERE tc.use_flag = 1 AND tc.status='collection_status_show' " +
				"GROUP BY tcm.source_id ORDER BY tc.heat DESC LIMIT 0,100";
		List<Object> paraList = new ArrayList<Object>();
		List<JSONObject> collectionList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs, false);
				return json;
			}
		}, sql, paraList);
		
		//如果与用户兴趣有关的栏目没有相应今日最热的100个藏品藏品，则取今日最热的100个
		/*if (CollectionUtils.isEmpty(collectionList))
		{
			sql = "SELECT tc.*, SUM(tcm.point) as points, COUNT(tcm.source_id) as comment_num FROM tb_collection tc " +
					"INNER JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = 0 " +
					"WHERE tc.use_flag = 1 AND DATE_FORMAT(tcm.comment_time,'%Y-%m-%d') = CURDATE() " +
					"GROUP BY tcm.source_id ORDER BY points DESC LIMIT 0,100";
			collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = parseDataToCollection(rs);
					return json;
				}
			}, sql, null);
		}*/
		return collectionList;
	}
	/**查询全部藏品（所有类别）
	 * @param id
	 * @return
	 * throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryAllCollections() throws Exception
	{
		String sql = "SELECT tc.* FROM tb_collection tc " + 
						"WHERE tc.use_flag =1 AND tc.status='collection_status_show' " + 
						"ORDER BY tc.insert_date DESC LIMIT 0, 1000";
		List<Object> paraList = new ArrayList<Object>();
		List<JSONObject> collectionList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs, false);
				return json;
			}
		}, sql, paraList);
		return collectionList;
	}
	
	/**
	 * 查询最热的100个藏品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryHotCollections(BigDecimal id) throws Exception
	{
		String sql = "SELECT tc.* FROM tb_user_interes_category tic " +
				"INNER JOIN tb_collection tc ON tc.category_id = tic.category_id " +
				"WHERE tic.user_id = ? AND tc.use_flag = 1 AND tc.status='collection_status_show' ORDER BY tc.heat DESC LIMIT 0,100";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(id);
		List<JSONObject> collectionList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs, false);
				return json;
			}
		}, sql, paraList);
		
		//如果与用户兴趣有关的栏目没有相应的藏品，则取热度最高的100个
		/*if (CollectionUtils.isEmpty(collectionList))
		{
			sql = "SELECT tc.* FROM tb_collection tc WHERE tc.use_flag = 1 ORDER BY tc.heat DESC LIMIT 0,100";
			collectionList =  BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = parseDataToCollection(rs, false);
					return json;
				}
			}, sql, null);
		}*/
		return collectionList;
	}
	
	/**
	 * 根据标题查询藏品
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryCollectionsByTitle(String title)
			throws Exception {
		String sql = "SELECT tc.*, SUM(tcm.point) points, SUM(tcm.use_flag) comment_num FROM tb_collection tc " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = ? " +
				"WHERE tc.title like ? AND tc.use_flag = 1 GROUP BY tc.id ORDER BY tc.insert_date DESC LIMIT 0,1000";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(Constant.COMMENT_SOURCE_TYPE_COLLECTION);
		paraList.add("%" + title + "%");
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	public static JSONObject parseDataToCollection(ResultSet rs)
			throws SQLException {
		JSONObject json = parseDataToCollection(rs, true);
		return json;
	}
	
	public static JSONObject parseDataToCollection(ResultSet rs, boolean isCommentNum) throws SQLException {
		JSONObject json = new JSONObject();
		json.put("id", rs.getBigDecimal("id"));
		json.put("category_id", rs.getBigDecimal("category_id"));
		json.put("collection_period_id", rs.getBigDecimal("collection_period_id"));
		json.put("title", rs.getString("title"));
		json.put("label_id", rs.getBigDecimal("label_id"));
		json.put("image_url", rs.getString("icon_img"));
		json.put("insert_date", DateUtil.DateFormat(rs.getTimestamp("insert_date")));
		json.put("heat", rs.getInt("heat"));
		int heat = rs.getInt("heat");
		json.put("heat_star", heat/Constant.HEAT_LEVEL);
		json.put("max_star", Constant.MAX_STAR);
		if (isCommentNum)
		{
			json.put("comment_num", rs.getInt("comment_num"));			
		}
		else
		{
			json.put("comment_num", 0);	
		}
		json.put("lable", SysConfigCache.getCollectionLable(rs.getBigDecimal("label_id")));
		json.put("category", SysConfigCache.getCollectionCategory(rs.getBigDecimal("category_id")));
		json.put("collection_period", SysConfigCache.getCollectionPeriod(rs.getBigDecimal("collection_period_id")));
		return json;
	}
	
	/**
	 * 查询藏品详情，并关联此藏品评论等信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static JSONObject queryCollectionDetail(BigDecimal id) throws Exception
	{
		JSONObject collectionInfo = new JSONObject();
		CollectionBean collectionBean = queryCollection(id);
		if (collectionBean == null)
		{
			throw new FavRuntimeException("藏品 ["+ id +"]不存在");
		}
		JSONObject cbJson = JsonUtil.objToJSONObject(collectionBean);
		cbJson.put("category", SysConfigCache.getCollectionCategory(collectionBean.getCategory_id()));
		cbJson.put("period", SysConfigCache.getCollectionPeriod(collectionBean.getCollection_period_id()));
		cbJson.put("label", SysConfigCache.getCollectionLable(collectionBean.getLabel_id()));
		//cbJson.put("time", DateUtil.DateTimeFormat(collectionBean.getInsert_date()));
		cbJson.put("time", DateUtil.DateFormat(collectionBean.getInsert_date()));
		if (collectionBean.getTransaction_price_time() != null)
		{
			cbJson.put("transaction_price_time", DateUtil.DateFormat(collectionBean.getTransaction_price_time()));
		}
		
		if (collectionBean.getTransaction_price_unit() != null)
		{
			cbJson.put("transaction_price_unit", SysConfigCache.getEnum(Constant.ENUM_MONEY_TYPE + collectionBean.getTransaction_price_unit()));
		}
		FavUserBean fuBean = UserService.queryUserById(collectionBean.getInsert_id());
		if (fuBean != null)
		{
			cbJson.put("user_name", fuBean.getUser_name());
			cbJson.put("avatar", fuBean.getAvatar());
		}
		collectionInfo.put("detail", cbJson);
		List<CollectionImagesBean> imagesList = queryCollectionImages(id);
		JSONArray imagesJson = new JSONArray();
		for(CollectionImagesBean ciBean : imagesList)
		{
			imagesJson.add(ciBean.getImage_url());
		}
		collectionInfo.put("images", imagesJson);
		List<JSONObject> commentList = queryComments(id, 0, Constant.COLLECTION_DETAIL_COMMENTS_SHOW);
		JSONArray commentsJson = new JSONArray();
		for(JSONObject comment : commentList)
		{
			commentsJson.add(comment);
		}
		collectionInfo.put("comments", commentsJson);
		collectionInfo.put("commentsTotal", queryCommentsTotal(id));
		JSONArray interestJson = new JSONArray();
		List<JSONObject> interestCollections = queryInterestCollections(collectionBean.getCategory_id());
		if (CollectionUtils.isNotEmpty(interestCollections))
		{
			int count = interestCollections.size();
			int size = SysConfigCache.getHomeBean().getRecommend_collection_show_num();
			int begin = 0;
			if (count > size)
			{
				begin= new Random().nextInt(count - size);
			}
			for(int i =  begin; i < count && i < (begin+size); i++)
			{
				interestJson.add(interestCollections.get(i));
			}
		}
		collectionInfo.put("interests", interestJson);
		return collectionInfo;
	}
	
	/**
	 * 查询藏品详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static CollectionBean queryCollection(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return (CollectionBean) BaseDao.getObjectByAnnotation(CollectionBean.class, "id = ?", params);
	}
	
	/**
	 * 查询藏品的图片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<CollectionImagesBean> queryCollectionImages(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return BaseDao.getListByAnnotation(CollectionImagesBean.class, "collection_id = ? AND use_flag = 1 ORDER BY display_order ASC", params);
	}
	
	/**
	 * 查询某个藏品或者动态的评论数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryCommentsTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) FROM tb_comment WHERE source_id = ?";
		return BaseDao.getCount(sql, params);
	}
	
	/**
	 * 感兴趣的藏品，根据当前浏览的藏品对应分类中热度最高的前200条随机挑选8条
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryInterestCollections(BigDecimal categoryId) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(categoryId);
		String sql = "SELECT tc.* FROM tb_collection tc " +
				"WHERE tc.category_id = ? AND tc.use_flag = 1 ORDER BY tc.heat DESC LIMIT 0,200";
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("title", rs.getString("title"));
				json.put("image_url", rs.getString("icon_img"));
				return json;
			}
		}, sql, params);
	}
	
	/**
	 * 收藏藏品
	 * @param userId
	 * @param collectionId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean addFavorites(BigDecimal userId, BigDecimal collectionId) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(collectionId);
		params.add(userId);
		List<FavoritesBean> fBeanList = BaseDao.getListByAnnotation(FavoritesBean.class, "collection_id = ? AND user_id = ?", params);
		if (CollectionUtils.isNotEmpty(fBeanList))
		{
			throw new FavRuntimeException("此藏品已收藏");
		}
		FavoritesBean fBean = new FavoritesBean();
		fBean.setId(IdCreaterTool.getFavoritesId());
		fBean.setCollection_id(collectionId);
		fBean.setUser_id(userId);
		fBean.setFavorite_time(new Date());
		fBean.setInsert_id(userId);
		fBean.setUpdate_id(userId);
		return BaseDao.insert(fBean);
	}
	
	/**
	 * 查询某个栏目下的藏品
	 * @param categoryId
	 * @param periodId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryCollections(BigDecimal categoryId, BigDecimal periodId, int start, int end) throws Exception
	{
		String sql = "SELECT tc.*, SUM(tcm.point) AS points, COUNT(tcm.source_id) AS comment_num FROM tb_collection tc " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = ? " +
				"WHERE tc.category_id = ? AND tc.collection_period_id = ? AND tc.use_flag = 1 " +
				"GROUP BY tc.id ORDER BY points DESC, tc.insert_date DESC LIMIT ?, ?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(Constant.COMMENT_SOURCE_TYPE_COLLECTION);
		paraList.add(categoryId);
		paraList.add(periodId);
		paraList.add(start);
		paraList.add(end);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 查询某个栏目下的藏品
	 * @param categoryId
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryCollectionByCategory(BigDecimal categoryId, int start, int end) throws Exception
	{
		String sql = "SELECT tc.*, SUM(tcm.point) AS points, COUNT(tcm.source_id) AS comment_num FROM tb_collection tc " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = ? " +
				"WHERE tc.category_id = ? AND tc.use_flag = 1 " +
				"GROUP BY tc.id ORDER BY points DESC, tc.insert_date DESC LIMIT ?, ?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(Constant.COMMENT_SOURCE_TYPE_COLLECTION);
		paraList.add(categoryId);
		paraList.add(start);
		paraList.add(end);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryDynamics(int start, int end) throws Exception
	{
		String sql = "SELECT td.*, tfu.avatar, tfu.user_name FROM tb_dynamic td " +
				"LEFT JOIN tb_fav_user tfu ON tfu.id = td.release_by " +
				"WHERE td.use_flag = 1 AND td.is_shield = 'N' ORDER BY td.release_time DESC LIMIT ?,?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(start);
		paraList.add(end);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("release_by", rs.getBigDecimal("release_by"));
				json.put("dynamic_content", rs.getString("dynamic_content"));
				json.put("release_time", DateUtil.DateFormat(rs.getTimestamp("release_time")));
				json.put("comment_number", rs.getInt("comment_number"));
				json.put("like_number", rs.getInt("like_number"));
				json.put("avatar", rs.getString("avatar"));
				json.put("user_name", rs.getString("user_name"));
				return json;
			}
		}, sql, paraList);
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryFriendDynamics(BigDecimal userId, int start, int end) throws Exception
	{
		String sql = "SELECT DISTINCT(td.id), td.*, tfu.avatar, tfu.user_name FROM tb_dynamic td " +
				"INNER JOIN tb_user_fans tuf ON (tuf.fan_id = td.release_by OR tuf.user_id = td.release_by) " +
				"LEFT JOIN tb_fav_user tfu ON tfu.id = td.release_by " +
				"WHERE td.use_flag = 1 AND td.is_shield = 'N' AND (tuf.user_id = ? OR tuf.fan_id = ?) " +
				"ORDER BY td.release_time DESC LIMIT ?,?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(userId);
		paraList.add(start);
		paraList.add(end);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("release_by", rs.getBigDecimal("release_by"));
				json.put("dynamic_content", rs.getString("dynamic_content"));
				json.put("release_time", DateUtil.DateFormat(rs.getTimestamp("release_time")));
				json.put("comment_number", rs.getInt("comment_number"));
				json.put("like_number", rs.getInt("like_number"));
				json.put("avatar", rs.getString("avatar"));
				json.put("user_name", rs.getString("user_name"));
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 获取动态
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<JSONObject> queryDynamicsList(int start, int end)
			throws Exception {
		List<JSONObject> dynamicsList = queryDynamics(start, end);
		return handleDynamicsList(dynamicsList, null);
	}
	
	/**
	 * 获取用户的好友动态
	 * @param userId 用户Id
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<JSONObject> queryFriendDynamicsList(BigDecimal userId, int start, int end)
			throws Exception {
		List<JSONObject> dynamicsList = queryFriendDynamics(userId, start, end);
		return handleDynamicsList(dynamicsList, userId);
	}
	
	/**
	 * 获取用户的动态
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserDynamicsList(BigDecimal userId)
			throws Exception {
		String sql = "SELECT td.*, tfu.avatar, tfu.user_name FROM tb_dynamic td " +
				"LEFT JOIN tb_fav_user tfu ON tfu.id = td.release_by " +
				"WHERE td.use_flag = 1 AND td.is_shield = 'N'  AND td.release_by = ? ORDER BY td.release_time DESC ";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		List<JSONObject> dynamicsList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("release_by", rs.getBigDecimal("release_by"));
				json.put("dynamic_content", rs.getString("dynamic_content"));
				json.put("release_time", DateUtil.DateFormat(rs.getTimestamp("release_time")));
				json.put("comment_number", rs.getInt("comment_number"));
				json.put("like_number", rs.getInt("like_number"));
				json.put("avatar", rs.getString("avatar"));
				json.put("user_name", rs.getString("user_name"));
				return json;
			}
		}, sql, paraList);
		return handleDynamicsList(dynamicsList, userId);
	}

	/**
	 * 根据动态数据，获取动态的关联数据如评论回复、点赞数等
	 * @param dynamicsList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List<JSONObject> handleDynamicsList(List<JSONObject> dynamicsList, BigDecimal userId) throws Exception {
		if (CollectionUtils.isNotEmpty(dynamicsList)) {
			Map<BigDecimal, Integer> dynamicIdMap = new HashMap<BigDecimal, Integer>();
			String dynamicIds = "";
			for (int i = 0; i < dynamicsList.size(); i++)
			{
				dynamicIds += "," + dynamicsList.get(i).get("id");
				dynamicIdMap.put(dynamicsList.get(i).getBigDecimal("id"), i);
			}
			dynamicIds = dynamicIds.substring(1);
			
			String imagesSql = "SELECT * FROM tb_dynamic_images tdi WHERE tdi.dynamic_id in (" + dynamicIds + ") AND use_flag = 1 " +
					"ORDER BY tdi.dynamic_id, tdi.display_order ASC";
			List<JSONObject> imagesList = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = new JSONObject();
					json.put("id", rs.getBigDecimal("id"));
					json.put("dynamic_id", rs.getBigDecimal("dynamic_id"));
					json.put("image", rs.getString("image"));
					json.put("display_order", rs.getInt("display_order"));
					return json;
				}
			}, imagesSql, null);
			BigDecimal dynamicId = null;
			for(JSONObject imgJson : imagesList)
			{
				dynamicId = imgJson.getBigDecimal("dynamic_id");
				if (dynamicIdMap.containsKey(dynamicId))
				{
					int idx = dynamicIdMap.get(dynamicId);
					if (dynamicsList.get(idx).get("images") == null)
					{
						JSONArray imgs = new JSONArray();
						imgs.add(imgJson.getString("image"));
						dynamicsList.get(idx).put("images", imgs);
					}
					else
					{
						((JSONArray) dynamicsList.get(idx).get("images")).add(imgJson.getString("image"));
					}
				}
			}
			
			String likeSql = "SELECT tl.*, tu.user_name, tu.avatar FROM tb_dynamic_like tl " +
					"LEFT JOIN tb_fav_user tu ON tu.id = tl.friend_id " +
					"WHERE tl.dynamic_id IN (" + dynamicIds + ") AND tl.use_flag = 1 AND tl.type = '0' ORDER BY tl.dynamic_id ASC;";
			List<JSONObject> likeList = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = new JSONObject();
					json.put("id", rs.getBigDecimal("id"));
					json.put("dynamic_id", rs.getBigDecimal("dynamic_id"));
					json.put("friend_id", rs.getBigDecimal("friend_id"));
					json.put("type", rs.getInt("type"));
					json.put("user_name", rs.getString("user_name"));
					json.put("avatar", rs.getString("avatar"));
					return json;
				}
			}, likeSql, null);
			dynamicId = null;
			for(JSONObject likeJson : likeList)
			{
				dynamicId = likeJson.getBigDecimal("dynamic_id");
				if (dynamicIdMap.containsKey(dynamicId))
				{
					int idx = dynamicIdMap.get(dynamicId);
					if (dynamicsList.get(idx).get("likes") == null)
					{
						JSONArray comments = new JSONArray();
						comments.add(likeJson);
						dynamicsList.get(idx).put("likes", comments);
					}
					else
					{
						((JSONArray)dynamicsList.get(idx).get("likes")).add(likeJson);
					}
				}
			}
			String block_dynamic = "";
			if (null != userId)
			{
				block_dynamic = "AND tc.insert_id NOT IN " +
				"(SELECT tfus.friend_id from tb_fav_user_set tfus WHERE tfus.user_id = " + userId + 
				" AND tfus.block_reply = '1' AND tfus.use_flag = 1) " ;
			}
			String commentSql = "SELECT tc.*, tu.user_name, tu.avatar, tfu1.user_name friend_name, tfu1.avatar friend_avatar FROM tb_comment tc " +
					"LEFT JOIN tb_fav_user tu ON tu.id = tc.insert_id " +
					"LEFT JOIN tb_fav_user tfu1 ON tfu1.id = tc.friend_id " + 
					"WHERE tc.source_id IN (" + dynamicIds + ") AND source_type = '1' AND tc.use_flag = 1 " +
					block_dynamic + 
					"ORDER BY tc.source_id, tc.comment_time ASC;";
			List<JSONObject> commentList = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = new JSONObject();
					json.put("id", rs.getBigDecimal("id"));
					json.put("dynamic_id", rs.getBigDecimal("source_id"));
					json.put("comment_content", rs.getString("comment_content"));
					json.put("comment_time", DateUtil.DateTimeFormat(rs.getTimestamp("comment_time")));
					json.put("type", rs.getInt("type"));
					json.put("insert_id", rs.getBigDecimal("insert_id"));
					json.put("user_name", rs.getString("user_name"));
					json.put("avatar", rs.getString("avatar"));
					json.put("friend_id", rs.getBigDecimal("friend_id"));
					json.put("friend_name", rs.getString("friend_name"));
					json.put("friend_avatar", rs.getString("friend_avatar"));
					json.put("reply_id", rs.getBigDecimal("reply_id"));
					return json;
				}
			}, commentSql, null);
			dynamicId = null;
			for(JSONObject commentJson : commentList)
			{
				dynamicId = commentJson.getBigDecimal("dynamic_id");
				if (dynamicIdMap.containsKey(dynamicId))
				{
					int idx = dynamicIdMap.get(dynamicId);
					if (dynamicsList.get(idx).get("comments") == null)
					{
						JSONArray comments = new JSONArray();
						comments.add(commentJson);
						dynamicsList.get(idx).put("comments", comments);
					}
					else
					{
						((JSONArray)dynamicsList.get(idx).get("comments")).add(commentJson);
					}
				}
			}
		}
		return dynamicsList;
	}
	
	/**
	 * 用户发布藏品数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryUserCollectionTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) total FROM tb_collection WHERE insert_id = ? AND use_flag = 1";
		return BaseDao.getCount(sql, params);
	}
	
	/**
	 * 用户发布个人动态数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryUserDynamicTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) total FROM tb_dynamic WHERE insert_id = ? AND use_flag = 1";
		return BaseDao.getCount(sql, params);
	}

	/**
	 * 用户评论回复态数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryUserCommentTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) total FROM tb_comment WHERE insert_id = ?";
		return BaseDao.getCount(sql, params);
	}

	/**
	 * 查询用户粉丝
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FavUserBean> queryUserFans(BigDecimal id) throws Exception
	{
		String sql = "SELECT tu.* FROM tb_user_fans tf INNER JOIN tb_fav_user tu ON tu.id = tf.fan_id WHERE tf.user_id = ? ORDER BY tf.concern_time ASC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(id);
		return BaseDao.getListBySqlAnnotation(FavUserBean.class, sql, paraList);
	}
	
	/**
	 * 查询用户粉丝
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FavUserBean> queryUserAttentions(BigDecimal id) throws Exception
	{
		String sql = "SELECT tu.* FROM tb_user_fans tf INNER JOIN tb_fav_user tu ON tu.id = tf.user_id WHERE tf.fan_id = ? ORDER BY tf.concern_time ASC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(id);
		return BaseDao.getListBySqlAnnotation(FavUserBean.class, sql, paraList);
	}
	
	/**
	 * 查询用户粉丝
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<FavUserBean> queryUserAttentions(BigDecimal id, String userName) throws Exception
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(id);
		StringBuffer sql = new StringBuffer();
		//String sql = "SELECT tu.* FROM tb_user_fans tf INNER JOIN tb_fav_user tu ON tu.id = tf.user_id WHERE tf.fan_id = ? ORDER BY tf.concern_time ASC";
		sql.append("SELECT tu.* FROM tb_user_fans tf INNER JOIN tb_fav_user tu ON tu.id = tf.user_id WHERE tf.fan_id = ? ");
		if (StringUtils.isNotBlank(userName))
		{
			sql.append("AND tu.user_name LIKE ? ");
			paraList.add("%"+userName+"%");
		}
		sql.append("ORDER BY tf.concern_time ASC");
		
		return BaseDao.getListBySqlAnnotation(FavUserBean.class, sql.toString(), paraList);
	}
	
	/**
	 * 查询用户藏品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserCollections(BigDecimal id)
			throws Exception {
		String sql = "SELECT tc.*, SUM(tcm.point) points, SUM(tcm.use_flag) comment_num FROM tb_collection tc " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id AND tcm.source_type = ? " +
				"WHERE tc.insert_id = ? AND tc.use_flag = 1 GROUP BY tc.id ORDER BY tc.insert_date DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(Constant.COMMENT_SOURCE_TYPE_COLLECTION);
		paraList.add(id);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToCollection(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 查询用户某个粉丝
	 * @param userId
	 * @param fanId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<UserFansBean> queryUserFansBean(BigDecimal userId, BigDecimal fanId) throws Exception
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(fanId);
		return BaseDao.getListByAnnotation(UserFansBean.class, "user_id = ? AND fan_id = ?", paraList);
	}
	
	/**
	 * 是否为用户某个粉丝
	 * @param userId
	 * @param fanId
	 * @return boolean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean isUserFans(BigDecimal userId, BigDecimal fanId) throws Exception
	{
		boolean isFans = true;
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(fanId);
		List<UserFansBean> list = BaseDao.getListByAnnotation(UserFansBean.class, "user_id = ? AND fan_id = ?", paraList);
		if (CollectionUtils.isEmpty(list))
		{
			isFans = false;
		}
		return isFans;
	}
	
	
	/**
	 * 删除用户某个粉丝
	 * @param userId
	 * @param fanId
	 * @return
	 * @throws Exception
	 */
	public static boolean deleteUserFansBean(BigDecimal userId, BigDecimal fanId) throws Exception
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(fanId);
		boolean result = BaseDao.delete(UserFansBean.class, "user_id = ? AND fan_id = ?", paraList);
		if (result)
		{
			updateUserHeat(userId, Constant.HEAT_USER_FANS, Constant.HEAT_NEGATIVE);
		}
		return result;
	}
	
	/**
	 * 新增粉丝
	 * @param userFansBean
	 * @return
	 * @throws Exception
	 */
	public static boolean addUserFan(UserFansBean userFansBean) throws Exception
	{
		boolean result = BaseDao.insert(userFansBean);
		if (result)
		{
			updateUserHeat(userFansBean.getUser_id(), Constant.HEAT_USER_FANS, Constant.HEAT_POSITIVE);
		}
		return result;
	}
	
	/**
	 * 查询某个藏品的所有评论
	 * @param collectionId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryComments(BigDecimal collectionId)
			throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.friend_id " +
				"WHERE tcc.source_id = ? AND tcc.use_flag = 1 ORDER BY tcc.like_size DESC, tcc.comment_time DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(collectionId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 某个用户查询某个藏品的所有评论，并过滤此用户设置屏蔽某些人的评论
	 * @param collectionId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryComments(BigDecimal collectionId, BigDecimal userId)
			throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.friend_id " +
				"WHERE tcc.source_id = ? AND tcc.use_flag = 1 AND tcc.insert_id NOT IN " +
				"(SELECT tfus.friend_id from tb_fav_user_set tfus WHERE tfus.user_id = ? AND tfus.block_comment = '1' AND tfus.use_flag = 1) " +
				"ORDER BY tcc.like_size DESC, tcc.comment_time DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(collectionId);
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 查询某个评论的的所有回复评论
	 * @param replyId 回复对应的评论id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryCommentReplys(BigDecimal replyId)
			throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.friend_id " +
				"WHERE tcc.reply_id = ? AND tcc.use_flag = 1 ORDER BY tcc.like_size DESC, tcc.comment_time DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(replyId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	private static JSONObject parseDataToComment(ResultSet rs)
			throws SQLException {
		JSONObject json = new JSONObject();
		json.put("id", rs.getBigDecimal("id"));
		json.put("source_id", rs.getBigDecimal("source_id"));
		json.put("source_type", rs.getString("source_type"));
		json.put("user_id", rs.getBigDecimal("friend_id"));
		json.put("insert_id", rs.getBigDecimal("insert_id"));
		json.put("content", rs.getString("comment_content"));
		json.put("point", rs.getInt("point"));
		json.put("comment_time", DateUtil.DateTimeFormat(rs.getTimestamp("comment_time")));
		json.put("top_size", rs.getInt("top_size"));
		json.put("like_size", rs.getInt("like_size"));
		json.put("oppose_size", rs.getInt("oppose_size"));
		json.put("type", rs.getString("type"));
		json.put("avatar", rs.getString("avatar"));
		json.put("user_name", rs.getString("user_name"));
		return json;
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryComments(BigDecimal id, int start, int end) throws Exception
	{
		
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.friend_id " +
				"WHERE tcc.source_id = ? AND tcc.use_flag = 1 ORDER BY like_size DESC, comment_time DESC LIMIT ? , ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		params.add(start);
		params.add(end);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, params);
	}
	
	/**
	 * 新增评论
	 * @param commentsBean
	 * @return
	 * @throws Exception
	 */
	public static boolean addComment(CommentBean commentsBean) throws Exception
	{
		return BaseDao.insert(commentsBean);
	}
	
    /**
     * 查询用户的对藏品的所有评论
     * @param userId
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserCollectionReply(BigDecimal userId) throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name, tc.title FROM tb_comment tcc "
				+ "LEFT JOIN tb_fav_user tu ON tu.id = tcc.insert_id "
				+ "LEFT JOIN tb_collection tc ON tc.id = tcc.source_id "
				+ "WHERE tcc.insert_id  = ? AND tcc.use_flag = 1 AND tcc.source_type = 0 ORDER BY tcc.comment_time ASC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(
				new ResultSetInterface() {
					public JSONObject getObject(ResultSet rs)
							throws SQLException {
						JSONObject json = new JSONObject();
						json.put("id", rs.getBigDecimal("id"));
						json.put("source_id", rs.getBigDecimal("source_id"));
						json.put("source_type", rs.getString("source_type"));
						json.put("user_id", rs.getBigDecimal("friend_id"));
						json.put("insert_id", rs.getBigDecimal("insert_id"));
						json.put("content", rs.getString("comment_content"));
						json.put("point", rs.getInt("point"));
						json.put("comment_time", DateUtil.DateTimeFormat(rs
								.getTimestamp("comment_time")));
						json.put("top_size", rs.getInt("top_size"));
						json.put("like_size", rs.getInt("like_size"));
						json.put("oppose_size", rs.getInt("oppose_size"));
						json.put("type", rs.getString("type"));
						json.put("avatar", rs.getString("avatar"));
						json.put("user_name", rs.getString("user_name"));
						json.put("title", rs.getString("title"));
						return json;
					}
				}, sql, paraList);
	}
	
	/**
	 * 查询用户对动态所有评论
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserDynamicReply(BigDecimal userId) throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name, td.dynamic_content title FROM tb_comment tcc " +
		"LEFT JOIN tb_fav_user tu ON tu.id = tcc.insert_id " +
		"LEFT JOIN tb_dynamic td ON td.id = tcc.source_id " +
		"WHERE tcc.insert_id  = ? AND tcc.use_flag = 1 AND tcc.source_type = 1 ORDER BY tcc.comment_time ASC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("source_id", rs.getBigDecimal("source_id"));
				json.put("source_type", rs.getString("source_type"));
				json.put("user_id", rs.getBigDecimal("friend_id"));
				json.put("insert_id", rs.getBigDecimal("insert_id"));
				json.put("content", rs.getString("comment_content"));
				json.put("point", rs.getInt("point"));
				json.put("comment_time", DateUtil.DateTimeFormat(rs.getTimestamp("comment_time")));
				json.put("top_size", rs.getInt("top_size"));
				json.put("like_size", rs.getInt("like_size"));
				json.put("oppose_size", rs.getInt("oppose_size"));
				json.put("type", rs.getString("type"));
				json.put("avatar", rs.getString("avatar"));
				json.put("user_name", rs.getString("user_name"));
				json.put("title", rs.getString("title"));
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 查询用户的所有评论
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static List<JSONObject> queryUserReply(BigDecimal userId)
			throws Exception {
		List<JSONObject> collectionReply = queryUserCollectionReply(userId);
		if (CollectionUtils.isEmpty(collectionReply)) {
			collectionReply = new ArrayList<JSONObject>();
		}

		List<JSONObject> dynamicReply = queryUserDynamicReply(userId);

		if (CollectionUtils.isNotEmpty(dynamicReply)) {
			collectionReply.addAll(dynamicReply);
		}
		if (CollectionUtils.isNotEmpty(collectionReply)) {
			Collections.sort(collectionReply, new Comparator<JSONObject>() {
				public int compare(JSONObject obj1, JSONObject obj2) {
					if (obj1.getIntValue("like_size") == obj2.getIntValue("like_size"))
					{
						return obj2.getString("comment_time").compareTo(
								obj1.getString("comment_time"));
					}
					else
					{
						return obj1.getIntValue("like_size") <  obj2.getIntValue("like_size") ? 1 : -1;
					}
				}
			});
		}

		return collectionReply;
	}
	
	/**
	 * 查询用户的评论
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserComments(BigDecimal userId)
			throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.insert_id " +
				"WHERE tcc.insert_id  = ? AND tcc.use_flag = 1 ORDER BY tcc.comment_time ASC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 查询某个评论详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static JSONObject queryCommentDetail(BigDecimal id)
			throws Exception {
		String sql = "SELECT tcc.*, tu.avatar, tu.user_name FROM tb_comment tcc " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tcc.insert_id " +
				"WHERE tcc.id  = ? AND tcc.use_flag = 1 ";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(id);
		return (JSONObject) BaseDao.getObjectBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = parseDataToComment(rs);
				return json;
			}
		}, sql, paraList);
	}
	
	public static JSONObject queryCommentLike(BigDecimal commentId, BigDecimal userId) throws Exception {
		String sql = "SELECT * FROM tb_comment_like WHERE comment_id = ? AND friend_id = ?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(commentId);
		paraList.add(userId);
		return (JSONObject) BaseDao.getObjectBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("source_id", rs.getBigDecimal("source_id"));
				json.put("comment_id", rs.getBigDecimal("comment_id"));
				json.put("friend_id", rs.getBigDecimal("friend_id"));
				json.put("source_type", rs.getString("source_type"));
				json.put("type", rs.getString("type"));
				return json;
			}
		}, sql, paraList);
	}
	
	/**
	 * 用户热度：数值属性，计算公式为用户热度=该用户的粉丝数量*系A+该用户获得的赞同值*系数B（A、B默认为1，可调整）
	 * @param userId
	 * @param typeName
	 * @param symbol 加/减的标记
	 */
	public static void updateUserHeat(BigDecimal userId, String typeName, int symbol)
	{
		int value = SysConfigCache.getHeatValue(typeName);
		if (value != 0)
		{
			List<Object> paraList = new ArrayList<Object>();
			paraList.add(value * symbol);
			paraList.add(userId);
			try {
				BaseDao.update("UPDATE tb_fav_user SET heat = heat + ? WHERE id = ?", paraList);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 藏品热度：藏品话题的属性，体现该条话题的热度，计算公式为该条藏品话题下的赞同值*系数X+反对值*系数Y。（X、Y默认为1，可调整）
	 * @param collectionId
	 * @param typeName
	 * @param symbol 加/减的标记
	 */
	public static void updateCollectionHeat(BigDecimal collectionId, String typeName, int symbol)
	{
		int value = SysConfigCache.getHeatValue(typeName);
		if (value != 0)
		{
			List<Object> paraList = new ArrayList<Object>();
			paraList.add(value * symbol);
			paraList.add(collectionId);
			try {
				BaseDao.update("UPDATE tb_collection SET heat = heat + ? WHERE id = ?", paraList);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 更新评论的赞成反对数
	 * @param commentId
	 * @param like_size
	 * @param oppose_size
	 */
	public static void updateCommentLikeOrOppose(BigDecimal commentId, int like_size, int oppose_size)
	{
			List<Object> paraList = new ArrayList<Object>();
			paraList.add(like_size);
			paraList.add(oppose_size);
			paraList.add(commentId);
			try {
				BaseDao.update("UPDATE tb_comment SET like_size = like_size + ?,  oppose_size = oppose_size + ? WHERE id = ?", paraList);
			} catch (Exception e) {
			}
	}
	
	public static boolean addCommentLike(CommentLikeBean commentLikeBean) throws Exception
	{
		boolean result = false;
		JSONObject comment = queryCommentDetail(commentLikeBean.getComment_id());
		if (comment != null)
		{
			commentLikeBean.setSource_id(comment.getBigDecimal("source_id"));
			result = BaseDao.insert(commentLikeBean);
			if (result)
			{
				if (Constant.COMMENT_SOURCE_TYPE_COLLECTION.equals(commentLikeBean.getSource_type()))
				{
					updateCollectionHeat(comment.getBigDecimal("source_id"), Constant.HEAT_COLLECTION_LIKE, Constant.HEAT_POSITIVE);
				}
				
				if (Constant.COMMENT_LIKE.equals(commentLikeBean.getType()))
				{
					updateUserHeat(comment.getBigDecimal("insert_id"), Constant.HEAT_USER_COMMENT_LIKE, Constant.HEAT_POSITIVE);
					updateCommentLikeOrOppose(commentLikeBean.getComment_id(), 1, 0);
					UserService.updateUserPoints(comment.getBigDecimal("insert_id"), Constant.TASK_TYPE_LIKE);
					UserService.addUserPointsRecord(comment.getBigDecimal("insert_id"), Constant.TASK_TYPE_LIKE);
				}
				else
				{
					updateCommentLikeOrOppose(commentLikeBean.getComment_id(), 0, 1);
				}
			}
		}
		return result;
	}
	
	public static boolean addDynamicLike(BigDecimal dynamicId, BigDecimal friendId) throws Exception
	{
		boolean result = false;
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(dynamicId);
		paraList.add(friendId);
		DynamicLikeBean dynamicLikeBean = (DynamicLikeBean) BaseDao.getObjectByAnnotation(DynamicLikeBean.class, "dynamic_id = ? AND friend_id = ?", paraList);
		if (dynamicLikeBean == null)
		{
			dynamicLikeBean = new DynamicLikeBean();
			dynamicLikeBean.setId(IdCreaterTool.getDynamicLikeId());
			dynamicLikeBean.setDynamic_id(dynamicId);
			dynamicLikeBean.setFriend_id(friendId);
			dynamicLikeBean.setInsert_id(friendId);
			dynamicLikeBean.setUpdate_id(friendId);
			dynamicLikeBean.setType("0");
			dynamicLikeBean.setLike_time(new Date());
			result = BaseDao.insert(dynamicLikeBean);
		}
		return result;
	}
	
	/**
	 * 新增黑名单 
	 * @param userId
	 * @param friendId
	 * @return
	 * @throws Exception
	 */
	public static boolean addBlacklist(BigDecimal userId, BigDecimal friendId) throws Exception
	{
		boolean result = false;
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(friendId);
		UserBlacklistBean userBlacklistBean = (UserBlacklistBean) BaseDao.getObjectByAnnotation(UserBlacklistBean.class, "user_id = ? AND blacklist_user_id = ?", paraList);
		if (userBlacklistBean != null)
		{
			throw new FavRuntimeException("此用户已添加到黑名单");
		}
		
		userBlacklistBean = new UserBlacklistBean();
		userBlacklistBean.setId(IdCreaterTool.getUserBlacklistId());
		userBlacklistBean.setUser_id(userId);
		userBlacklistBean.setBlacklist_user_id(friendId);
		userBlacklistBean.setInsert_id(userId);
		userBlacklistBean.setUpdate_id(userId);
		result = BaseDao.insert(userBlacklistBean);
		return result;
	}
	
	/**
	 * 新增用户设置
	 * @param favUserSetBean
	 * @return
	 * @throws Exception
	 */
	public static boolean addFavUserSetBean(FavUserSetBean favUserSetBean) throws Exception 
	{
		return BaseDao.insert(favUserSetBean);
	}
	
	/**
	 * 更新用户设置
	 * @param favUserSetBean
	 * @return
	 * @throws Exception
	 */
	public static boolean updateFavUserSetBean(FavUserSetBean favUserSetBean) throws Exception 
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(favUserSetBean.getUser_id());
		paraList.add(favUserSetBean.getFriend_id());
		return BaseDao.update(favUserSetBean, "user_id = ? AND friend_id = ?", paraList);
	}
	
	/**
	 * 查询用户设置
	 * @param userId
	 * @param friendId
	 * @return
	 * @throws Exception
	 */
	public static FavUserSetBean queryFavUserSetBean(BigDecimal userId, BigDecimal friendId) throws Exception 
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(friendId);
		return (FavUserSetBean) BaseDao.getObjectByAnnotation(FavUserSetBean.class, "user_id = ? AND friend_id = ?", paraList);
	}
	
	/**
	 * 查询某个人收藏某个藏品
	 * @param userId
	 * @param collectionId
	 * @return
	 */
	public static FavoritesBean queryFavoritesBean(BigDecimal userId, BigDecimal collectionId)
	{
		FavoritesBean favoritesBean = null;
		List<Object> params = new ArrayList<Object>();
		params.add(collectionId);
		params.add(userId);
		try {
			favoritesBean = (FavoritesBean)BaseDao.getObjectByAnnotation(FavoritesBean.class, "collection_id = ? AND user_id = ?", params);
		} catch (Exception e) {
			logger.error("queryFavoritesBean", e);
		}
		return favoritesBean;
	}
	
	/**
	 * 删除收藏夹某个藏品
	 * @param id
	 * @return
	 */
	public static boolean deleteFavoritesBean(BigDecimal id)
	{
		boolean optResult = false;
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		try {
			optResult = BaseDao.delete(FavoritesBean.class, "id = ? ", params);
		} catch (Exception e) {
			logger.error("deleteFavoritesBean", e);
		}
		return optResult;
	}
	
	/**
	 * 删除收藏夹某个藏品
	 * @param id
	 * @return
	 */
	public static boolean deleteFavoritesBean(BigDecimal userId, BigDecimal collectionId)
	{
		boolean optResult = false;
		List<Object> params = new ArrayList<Object>();
		params.add(collectionId);
		params.add(userId);
		try {
			optResult = BaseDao.delete(FavoritesBean.class, "collection_id = ? AND user_id = ?", params);
		} catch (Exception e) {
			logger.error("deleteFavoritesBean", e);
		}
		return optResult;
	}
	
	/**
	 * 某个评论回复总数
	 * @param id 回复对应的评论id
	 * @return
	 * @throws Exception
	 */
	public static int queryCommentReplyTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT count(*) FROM tb_comment where reply_id = ?";
		return BaseDao.getCount(sql, params);
	}
	
	/**
	 * 新增顶的记录
	 * @param commentTopBean
	 * @return
	 * @throws Exception
	 */
	public static boolean addCommentTopBean(CommentTopBean commentTopBean) throws Exception
	{
		boolean result = BaseDao.insert(commentTopBean);
		if (result)
		{
			updateCommentTop(commentTopBean.getComment_id());
		}
		return result;
	}
	
	public static void updateCommentTop(BigDecimal commentId)
	{
		String sql= "UPDATE tb_comment SET top_size = top_size + 1 WHERE id = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(commentId);
		try {
			BaseDao.update(sql, params);
		} catch (Exception e) {
			logger.error("updateCommentTop error", e);
		}
	}
	
}
