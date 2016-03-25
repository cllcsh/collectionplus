package com.front.home.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.front.db.bean.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.express.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.front.cache.SysConfigCache;
import com.front.web.common.Constant;
import com.front.web.framework.database.BaseDao;
import com.front.web.framework.database.ResultSetInterface;
import com.front.web.util.IdCreaterTool;

public class HomeService {
	
	private final static Logger logger = LoggerFactory.getLogger(HomeService.class);
	
	/**
	 * 获取未读消息总数
	 * @param Receiver
	 * @return
	 */
	public static int queryUnreadMessagesTotal(BigDecimal Receiver)
	{
		int unreadMessagesTotal = 0;
		List<Object> params = new ArrayList<Object>();
		params.add(Receiver);
		try {
			unreadMessagesTotal = BaseDao.getCount("SELECT COUNT(*) FROM tb_messages WHERE receiver = ? AND is_read = 'N'", params);
		} catch (SQLException e) {
			logger.error("queryUnreadMessagesTotal error.", e);
		}
		return unreadMessagesTotal;
	}
	
	/**
	 * 获取未读消息
	 * @return
	 */
	private static List<MessagesBean> queryUnreadMessages(BigDecimal Receiver, int start, int end)
	{
	   	return null;
	}
	
	/**
	 * 获取所有消息
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserAllMessages(BigDecimal receiver, int start, int end) throws Exception
	{
		String sql = "SELECT tm.*, CASE WHEN um.unreadNum IS NULL THEN 0 ELSE um.unreadNum END AS unreadNum, tu.avatar, tu.user_name FROM `tb_messages` tm LEFT JOIN " +
				"(SELECT count(*) unreadNum, receiver, sender FROM `tb_messages` WHERE receiver = ? AND is_read = 'N' GROUP BY sender, receiver) as um " +
				"ON um.receiver = tm.receiver AND um.sender = tm.sender " +
				"LEFT JOIN tb_fav_user tu ON tu.id = tm.sender WHERE  tm.receiver = ? " +
				"GROUP BY tm.receiver, tm.sender ORDER BY send_time ASC LIMIT ?,?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(receiver);
		paraList.add(receiver);
		paraList.add(start);
		paraList.add(end);
		List<JSONObject> messageList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("sender", rs.getBigDecimal("sender"));
				json.put("receiver", rs.getBigDecimal("receiver"));
				json.put("send_time", DateUtil.DateTimeFormat(rs.getTimestamp("send_time")));
				json.put("content", rs.getString("content"));
				json.put("unreadNum", rs.getInt("unreadNum"));
				json.put("avatar", rs.getString("avatar"));
				json.put("user_name", rs.getString("user_name"));
				return json;
			}
		}, sql, paraList);
		return messageList;
	}

	/**
	 * 获取所有消息
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryMessages(BigDecimal receiver, BigDecimal sender) throws Exception
	{
		String sql = "SELECT * FROM  tb_messages m WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?) ORDER BY send_time DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(sender);
		paraList.add(receiver);
		paraList.add(receiver);
		paraList.add(sender);
		List<JSONObject> messageList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("sender", rs.getBigDecimal("sender"));
				json.put("receiver", rs.getBigDecimal("receiver"));
				json.put("send_time", DateUtil.DateTimeFormat(rs.getTimestamp("send_time")));
				json.put("content", rs.getString("content"));
				json.put("is_read", rs.getString("is_read"));
				return json;
			}
		}, sql, paraList);
		return messageList;
	}
	
	/**
	 * 更新消息的未读状态
	 * @return
	 * @throws Exception 
	 */
	public static boolean updateMessagesReadStatus(BigDecimal receiver, BigDecimal sender) throws Exception
	{
		String sql = "UPDATE tb_messages SET is_read = ?, update_id = ?, update_date = SYSDATE() WHERE receiver = ? AND sender = ? AND is_read != 'Y'";
		List<Object> params = new ArrayList<Object>();
		params.add("Y");
		params.add(receiver);
		params.add(receiver);
		params.add(sender);
		return BaseDao.update(sql, params);
	}
	
	/**
	 * 
	 * @param receiver 接收人
	 * @param sender 发送人
	 * @param content 内容
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean addMessage(BigDecimal receiver, BigDecimal sender, String content) throws Exception
	{
		MessagesBean mBean = new MessagesBean();
		BigDecimal id = IdCreaterTool.getMessagesId();
		mBean.setId(id);
		mBean.setReceiver(receiver);
		mBean.setSender(sender);
		mBean.setSend_time(new Date());
		mBean.setContent(content);
		mBean.setIs_read("N");
		mBean.setInsert_id(sender);
		mBean.setUpdate_id(sender);
		return BaseDao.insert(mBean);
	}

	/**
	 * 获取推荐藏品
	 * 从一定范围内的藏品话题中随机挑选四条显示，筛选范围规则如下：
	 * 先根据用户当前兴趣选出对应类别的藏品话题
	 * 在以上藏品话题中选出历史藏品热度排行前100条话题
	 * 在以上100条话题中随机挑选四条话题
	 * 点击“换一批”按钮后从以上100条话题中重新随机选择四条话题
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryRecommendCollections(BigDecimal receiver) throws Exception
	{
		List<JSONObject> collectionsList = new ArrayList<JSONObject>();
		String sql = "SELECT tc.* FROM tb_user_interes_category tic " +
				"INNER JOIN tb_collection tc ON tc.category_id = tic.category_id " +
				"WHERE tic.user_id = ? AND tc.use_flag = 1 ORDER BY tc.heat DESC LIMIT 0, 100";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(receiver);
		List<JSONObject> cbList = BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("category_id", rs.getBigDecimal("category_id"));
				json.put("collection_period_id", rs.getBigDecimal("collection_period_id"));
				json.put("title", rs.getString("title"));
				json.put("label_id", rs.getBigDecimal("label_id"));
				json.put("image_url", rs.getString("icon_img"));
				json.put("insert_date", DateUtil.DateFormat(rs.getTimestamp("insert_date")));
				json.put("heat", rs.getInt("heat"));
				return json;
			}
		}, sql, paraList);
		
		if (CollectionUtils.isEmpty(cbList))
		{
			sql = "SELECT tc.* FROM tb_collection tc WHERE tc.use_flag = 1 ORDER BY tc.heat DESC LIMIT 0, 100";
			cbList = BaseDao.getListBySql(new ResultSetInterface() {
				public JSONObject getObject(ResultSet rs) throws SQLException {
					JSONObject json = new JSONObject();
					json.put("id", rs.getBigDecimal("id"));
					json.put("category_id", rs.getBigDecimal("category_id"));
					json.put("collection_period_id", rs.getBigDecimal("collection_period_id"));
					json.put("title", rs.getString("title"));
					json.put("label_id", rs.getBigDecimal("label_id"));
					json.put("image_url", rs.getString("icon_img"));
					json.put("insert_date", DateUtil.DateFormat(rs.getTimestamp("insert_date")));
					json.put("heat", rs.getInt("heat"));
					return json;
				}
			}, sql, null);
		}
		
		if (CollectionUtils.isNotEmpty(cbList))
		{
			int count = cbList.size();
			int size = SysConfigCache.getHomeBean().getRecommend_collection_show_num();
			if (count <= size)
			{
				collectionsList = cbList;
			}
			else
			{
				int begin = new Random().nextInt(count - size);
				for (int i = 0; i < size; i++)
				{
					collectionsList.add(cbList.get(i + begin));
				}
			}
		}
		
		return collectionsList;
	}
	
	/**
	 * 获取热门藏家
	 * 从一定范围内随机挑选四位用户，筛选范围如下：
     * 用户热度排明前100位
     * 点击“换一批”按钮后从以上100位用户中重新随机选择四位用户
	 * @throws Exception 
	 */
	public static List<FavUserBean> queryTopCollectors(BigDecimal receiver) throws Exception
	{
		List<FavUserBean> favUserList = new ArrayList<FavUserBean>();
		String sql = "SELECT * FROM tb_fav_user t where t.id != ? AND t.use_flag = 1 ORDER BY t.heat DESC LIMIT 0,100";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(receiver);
		List<FavUserBean> fuList = BaseDao.getListBySqlAnnotation(FavUserBean.class, sql, paraList);
		if (CollectionUtils.isNotEmpty(fuList))
		{
			int count = fuList.size();
			int size = SysConfigCache.getHomeBean().getTop_collectors_show_num();
			if (count <= size)
			{
				favUserList = fuList;
			}
			else
			{
				int begin = new Random().nextInt(count - size);
				for (int i = 0; i < size; i++)
				{
					favUserList.add(fuList.get(i + begin));
				}
			}
		}
		
		return favUserList;
	}
	
	/**
	 * 获取今日鉴赏
	 * @return TodayAppreciationBean
	 * @throws Exception
	 */
	public static List<TodayAppreciationBean> queryTodayAppreciation() throws Exception
	{
		return BaseDao.getListByAnnotation(TodayAppreciationBean.class, "use_flag = 1 AND is_show = 'Y' ORDER BY display_order ASC", null);
	}
	
	/**
	 * 获取天天论战
	 * @return DailyPolemicBean
	 * @throws Exception
	 */
	public static DailyPolemicBean queryTodayDailyPolemic() throws Exception
	{
		return (DailyPolemicBean) BaseDao.getObjectByAnnotation(DailyPolemicBean.class, "use_flag = 1 ORDER BY insert_date DESC LIMIT 0,1", null);
	}
	
	public static List<String> setImageList(Class cls, Object obj, String name)
    {    	
		List<String> imagesList = new ArrayList<String>();
		if (obj != null) {
			Method[] methods = cls.getMethods();
			for (Method method : methods) {
				if (method.getName().startsWith(name)) {
					try {
						String adImage = (String) method.invoke(obj, null);
						if (StringUtils.isNotBlank(adImage)) {
							imagesList.add(adImage);
						}
					} catch (IllegalArgumentException e) {
					} catch (IllegalAccessException e) {
					} catch (InvocationTargetException e) {
					}
				}
			}
		}
		return imagesList;
    }
	
	public static DailyPolemicVoteBean queryDailyPolemicVote(BigDecimal userId, BigDecimal dailyPolemicId, String day)
	{
		List<Object> params = new ArrayList<Object>();
		params.add(dailyPolemicId);
		params.add(userId);
		params.add(day);
		DailyPolemicVoteBean dpvBean = null;
		try {
			dpvBean = (DailyPolemicVoteBean) BaseDao.getObject(DailyPolemicVoteBean.class, "daily_polemic_id = ? AND user_id = ? AND day = ?", params);
		} catch (Exception e) {
			logger.error("queryDailyPolemicVote error.", e);
		}
		return dpvBean;
	}
	
	public static boolean addDailyPolemicVote(DailyPolemicVoteBean dailyPolemicVoteBean) throws Exception
	{
		boolean result = BaseDao.insert(dailyPolemicVoteBean);
		if (result)
		{
			String sql = "UPDATE tb_daily_polemic SET support_a_viewpoint = support_a_viewpoint + ?, support_b_viewpoint = support_b_viewpoint + ? " +
			"WHERE id = ?";
			List<Object> params = new ArrayList<Object>();
			if (Constant.DAILY_POLEMIC_VOTE_LIKE.equals(dailyPolemicVoteBean.getType()))
			{
				params.add(1);
				params.add(0);
			}
			else
			{
				params.add(0);
				params.add(1);
			}
			params.add(dailyPolemicVoteBean.getDaily_polemic_id());
			BaseDao.update(sql, params);
		}
		return result;
	}
	
	/**
	 * 刷新缓存，如首页有变化则更新缓存数据
	 */
    @SuppressWarnings("unchecked")
	public static void refreshHomeBean()
    {
    	try {
    		//首页参数
			List<HomeBean> haList = BaseDao.getListByAnnotation(HomeBean.class, "use_flag = 1 ORDER BY insert_date DESC", null);
			if (CollectionUtils.isNotEmpty(haList))
			{
				HomeBean homeBean = haList.get(0);
				boolean timeCompare = true;
				if (homeBean.getUpdate_date() == null)
				{
					if (SysConfigCache.getHomeBean().getUpdate_date() == null)
					{
						timeCompare = false;
					}
				}
				else
				{
					if (SysConfigCache.getHomeBean().getUpdate_date() == null)
					{
						timeCompare = false;
					}
					else
					{
						if (homeBean.getUpdate_date().compareTo(SysConfigCache.getHomeBean().getUpdate_date()) == 0)
						{
							timeCompare = false;
						}
					}
				}
				
				if (homeBean.getId().compareTo(SysConfigCache.getHomeBean().getId()) != 0 || timeCompare)
				{
					List<JSONObject> adImagesList = new ArrayList<JSONObject>();
					SysConfigCache.setHomeBean(homeBean);
					if (homeBean != null) {
						Method method = null;
						JSONObject adJson = null;
						for(int i= 1; i < 10; i++)
						{
							try {
								adJson = new JSONObject();
								method = HomeBean.class.getMethod("getAd_images" + i, null);
								String adImage = (String) method.invoke(homeBean, null);
								method = HomeBean.class.getMethod("getAd_path" + i, null);
								String adPath = (String) method.invoke(homeBean, null);
								if (StringUtils.isNotBlank(adImage)) {
									adJson.put("ad_image", adImage);
									if (StringUtils.isBlank(adPath))
									{
										adPath = "";
									}
									adJson.put("ad_path", adPath);
									adImagesList.add(adJson);
								}
							} catch (SecurityException e) {
								logger.error("refreshHomeBean error.", e);
							} catch (NoSuchMethodException e) {
								logger.error("refreshHomeBean error.", e);
							} catch (IllegalArgumentException e) {
								logger.error("refreshHomeBean error.", e);
							} catch (IllegalAccessException e) {
								logger.error("refreshHomeBean error.", e);
							} catch (InvocationTargetException e) {
								logger.error("refreshHomeBean error.", e);
							}
						}
						/* Method[] methods = HomeBean.class.getMethods();
						    for (Method method : methods) {
							if (method.getName().startsWith("getAd_images")) {
								try {
									String adImage = (String) method.invoke(homeBean, null);
									if (StringUtils.isNotBlank(adImage)) {
										adImagesList.add(adImage);
									}
								} catch (IllegalArgumentException e) {
									logger.error("setHomeAdImages error.", e);
								} catch (IllegalAccessException e) {
									logger.error("setHomeAdImages error.", e);
								} catch (InvocationTargetException e) {
									logger.error("setHomeAdImages error.", e);
								}
							}
						}*/
					}
					if (CollectionUtils.isNotEmpty(adImagesList))
					{
						SysConfigCache.setHomeAdImageList(adImagesList);						
					}
				}
			}
		} catch (Exception e) {
			logger.error("queryHomeBean error.", e);
		}
    }
    
}
