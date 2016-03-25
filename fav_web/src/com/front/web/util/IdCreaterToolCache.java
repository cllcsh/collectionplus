package com.front.web.util;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.front.db.bean.SequenceBean;
import com.front.web.framework.database.BaseDao;
import com.front.web.framework.database.ResultSetInterface;

/**
 * 
 * @author sung
 * id生成器
 */
public class IdCreaterToolCache {
	
	// 运行日志接口
    private final static Logger runLog = LoggerFactory.getLogger(IdCreaterTool.class);
    
	private static BigDecimal FAV_USER_ID = new BigDecimal(0);
	private static BigDecimal USER_INTERES_CATEGORY_ID = new BigDecimal(0);
	private static BigDecimal MESSEAGS_ID = new BigDecimal(0);
	private static BigDecimal FAVORITES_ID = new BigDecimal(0);
	private static BigDecimal COLLECTION_IMAGES_ID = new BigDecimal(0);
	private static BigDecimal COLLECTION_ID = new BigDecimal(0);
	private static BigDecimal DYNAMIC_ID = new BigDecimal(0);
	private static BigDecimal DYNAMIC_IMAGES_ID = new BigDecimal(0);
	private static BigDecimal USER_FANS_ID = new BigDecimal(0);
	private static BigDecimal COMMENT_ID = new BigDecimal(0);
	private static BigDecimal COMMENT_LIKE_ID = new BigDecimal(0);
	private static BigDecimal COMMENT_TOP_ID = new BigDecimal(0);
	private static BigDecimal DAILY_POLEMIC_VOTE_ID = new BigDecimal(0);
	private static BigDecimal DYNAMIC_LIKE_ID = new BigDecimal(0);
	private static BigDecimal USER_POINTS_RECORD_ID = new BigDecimal(0);
	private static BigDecimal USER_BLACKLIST_ID = new BigDecimal(0);
	private static BigDecimal FAV_USER_SET_ID = new BigDecimal(0);
	private static BigDecimal SMS_ID = new BigDecimal(0);
	
	static{
		//从数据库中取出当前最大的id
		try{
			FAV_USER_ID = getDBTableMaxId("tb_fav_user");
			USER_INTERES_CATEGORY_ID = getDBTableMaxId("tb_user_interes_category");
			MESSEAGS_ID = getDBTableMaxId("tb_messages");
			FAVORITES_ID = getDBTableMaxId("tb_favorites");
			COLLECTION_ID = getDBTableMaxId("tb_collection");
			COLLECTION_IMAGES_ID = getDBTableMaxId("tb_collection_images");
			DYNAMIC_ID = getDBTableMaxId("tb_dynamic");
			DYNAMIC_IMAGES_ID = getDBTableMaxId("tb_dynamic_images");
			USER_FANS_ID = getDBTableMaxId("tb_user_fans");
			COMMENT_ID = getDBTableMaxId("tb_comment");
			COMMENT_LIKE_ID = getDBTableMaxId("tb_comment_like");
			COMMENT_TOP_ID = getDBTableMaxId("tb_comment_top");
			DAILY_POLEMIC_VOTE_ID = getDBTableMaxId("tb_daily_polemic_vote");
			DYNAMIC_LIKE_ID = getDBTableMaxId("tb_dynamic_like");
			USER_POINTS_RECORD_ID = getDBTableMaxId("tb_user_points_record");
			USER_BLACKLIST_ID = getDBTableMaxId("tb_user_blacklist");
			FAV_USER_SET_ID = getDBTableMaxId("tb_fav_user_set");
			SMS_ID = getDBTableMaxId("tb_sms");
			
		}catch (Exception e) {
		}
	}
	
	private IdCreaterToolCache(){};
	
	private static BigDecimal getDBTableMaxId(String tableName)
	{
		try {
			BigDecimal maxId = (BigDecimal) BaseDao.getObjectBySql(new ResultSetInterface(){
				public BigDecimal getObject(ResultSet rs) throws SQLException {
					return rs.getBigDecimal("id");
				}
			}, "SELECT max(id) as id FROM " + tableName, null);
			
			if (maxId != null)
			{
				return maxId;
			}
			
		} catch (Exception e) {
		}
		return new BigDecimal(0);
	}
	
	public static BigDecimal getNextId(String tableName)
	{
		BigDecimal nextId = null;
		try {
			BigDecimal currentId = (BigDecimal) BaseDao.getObjectBySql(new ResultSetInterface(){
				public BigDecimal getObject(ResultSet rs) throws SQLException {
					return rs.getBigDecimal("id");
				}
			}, "SELECT current_value as id FROM ts_sequence WHERE name = '" + tableName + "'" , null);
			
			List<Object> params = new ArrayList<Object>();
			if (currentId != null)
			{
				 params.add(tableName);
				 params.add(currentId);
				 if (!BaseDao.update("UPDATE ts_sequence SET current_value = current_value + 1 WHERE name = ?  AND current_value = ?", params))
				 {
					 nextId = getNextId(tableName);
				 }
			}
			else
			{
				SequenceBean sb = new SequenceBean();
				nextId = new BigDecimal(1);
				sb.setCurrent_value(nextId);
				sb.setName(tableName);
				BaseDao.insert(sb);
			}
			
		} catch (Exception e) {
			runLog.error("getNextId error", e);
		}
		return nextId;
	}
	/**
	 * 生成用户表id
	 * @return
	 */
	public static synchronized BigDecimal getFavUserId(){
		FAV_USER_ID = FAV_USER_ID.add(new BigDecimal(1));
		return FAV_USER_ID;
	}
	
	/**
	 * 生成用户兴趣收藏类别表id
	 * @return
	 */
	public static synchronized BigDecimal getUserInteresCategoryId(){
		USER_INTERES_CATEGORY_ID = USER_INTERES_CATEGORY_ID.add(new BigDecimal(1));
		return USER_INTERES_CATEGORY_ID;
	}
	
	/**
	 * 生成消息表id
	 * @return
	 */
	public static synchronized BigDecimal getMessagesId(){
		MESSEAGS_ID = MESSEAGS_ID.add(new BigDecimal(1));
		return MESSEAGS_ID;
	}
	
	/**
	 * 生成收藏夹表id
	 * @return
	 */
	public static synchronized BigDecimal getFavoritesId(){
		FAVORITES_ID = FAVORITES_ID.add(new BigDecimal(1));
		return FAVORITES_ID;
	}
	
	/**
	 * 生成藏品表id
	 * @return
	 */
	public static synchronized BigDecimal getCollectionId(){
		COLLECTION_ID = COLLECTION_ID.add(new BigDecimal(1));
		return COLLECTION_ID;
	}
	
	/**
	 * 生成藏品图片集表id
	 * @return
	 */
	public static synchronized BigDecimal getCollectionImagesId(){
		COLLECTION_IMAGES_ID = COLLECTION_IMAGES_ID.add(new BigDecimal(1));
		return COLLECTION_IMAGES_ID;
	}
	
	/**
	 * 生成动态表id
	 * @return
	 */
	public static synchronized BigDecimal getDynamicId(){
		DYNAMIC_ID = DYNAMIC_ID.add(new BigDecimal(1));
		return DYNAMIC_ID;
	}
	
	/**
	 * 生成动态图片地址表id
	 * @return
	 */
	public static synchronized BigDecimal getDynamicimagesId(){
		DYNAMIC_IMAGES_ID = DYNAMIC_IMAGES_ID.add(new BigDecimal(1));
		return DYNAMIC_IMAGES_ID;
	}
	
	/**
	 * 生成粉丝表id
	 * @return
	 */
	public static synchronized BigDecimal getUserFansId(){
		USER_FANS_ID = USER_FANS_ID.add(new BigDecimal(1));
		return USER_FANS_ID;
	}
	
	/**
	 * 生成评论表id
	 * @return
	 */
	public static synchronized BigDecimal getCommentId(){
		COMMENT_ID = COMMENT_ID.add(new BigDecimal(1));
		return COMMENT_ID;
	}
	
	/**
	 * 生成评论赞同反对表id
	 * @return
	 */
	public static synchronized BigDecimal getCommentLikeId(){
		COMMENT_LIKE_ID = COMMENT_LIKE_ID.add(new BigDecimal(1));
		return COMMENT_LIKE_ID;
	}
	
	/**
	 * 生成评论顶表id
	 * @return
	 */
	public static synchronized BigDecimal getCommentTopId(){
		COMMENT_TOP_ID = COMMENT_TOP_ID.add(new BigDecimal(1));
		return COMMENT_TOP_ID;
	}
	
	/**
	 * 生成天天论战投票表id
	 * @return
	 */
	public static synchronized BigDecimal getPolemicVoteId(){
		DAILY_POLEMIC_VOTE_ID = DAILY_POLEMIC_VOTE_ID.add(new BigDecimal(1));
		return DAILY_POLEMIC_VOTE_ID;
	}
	
	/**
	 * 生成天天论战投票表id
	 * @return
	 */
	public static synchronized BigDecimal getDynamicLikeId(){
		DYNAMIC_LIKE_ID = DYNAMIC_LIKE_ID.add(new BigDecimal(1));
		return DYNAMIC_LIKE_ID;
	}
	
	/**
	 * 生成用户积分记录表id
	 * @return
	 */
	public static synchronized BigDecimal getUserPointsRecordId(){
		USER_POINTS_RECORD_ID = USER_POINTS_RECORD_ID.add(new BigDecimal(1));
		return USER_POINTS_RECORD_ID;
	}
	
	/**
	 * 生成用户黑名单表id
	 * @return
	 */
	public static synchronized BigDecimal getUserBlacklistId(){
		USER_BLACKLIST_ID = USER_BLACKLIST_ID.add(new BigDecimal(1));
		return USER_BLACKLIST_ID;
	}
	
	/**
	 * 生成用户设置表id
	 * @return
	 */
	public static synchronized BigDecimal getFavUserSetId(){
		FAV_USER_SET_ID = FAV_USER_SET_ID.add(new BigDecimal(1));
		return FAV_USER_SET_ID;
	}
	
	/**
	 * 生成短信表id
	 * @return
	 */
	public static synchronized BigDecimal getSmsId(){
		SMS_ID = SMS_ID.add(new BigDecimal(1));
		return SMS_ID;
	}
	
	public static void main(String[] args) {
		System.out.println(getNextId("tb_collection").toString());
	}
}
