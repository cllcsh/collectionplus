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
public class IdCreaterTool {
	
	// 运行日志接口
    private final static Logger runLog = LoggerFactory.getLogger(IdCreaterTool.class);
    
	private static String FAV_USER_ID = "tb_fav_user";
	private static String USER_INTERES_CATEGORY_ID = "tb_user_interes_category";
	private static String MESSEAGS_ID = "tb_messages";
	private static String FAVORITES_ID = "tb_favorites";
	private static String COLLECTION_IMAGES_ID = "tb_collection_images";
	private static String COLLECTION_ID = "tb_collection";
	private static String DYNAMIC_ID = "tb_dynamic";
	private static String DYNAMIC_IMAGES_ID = "tb_dynamic_images";
	private static String USER_FANS_ID = "tb_user_fans";
	//private static String COMMENT_ID = "tb_comment";
	private static String COMMENT_ID = "tb_collection_comments";
	//private static String COMMENT_LIKE_ID = "tb_comment_like";
	private static String COMMENT_LIKE_ID = "tb_dynamic_comments_like";
	//private static String COMMENT_TOP_ID = "tb_comment_top";
	private static String COMMENT_TOP_ID = "tb_dynamic_comment_top";
	private static String DAILY_POLEMIC_VOTE_ID = "tb_daily_polemic_vote";
	private static String DYNAMIC_LIKE_ID = "tb_dynamic_like";
	private static String USER_POINTS_RECORD_ID = "tb_user_points_record";
	private static String USER_BLACKLIST_ID = "tb_user_blacklist";
	private static String FAV_USER_SET_ID = "tb_fav_user_set";
	private static String SMS_ID = "tb_sms";
	private static String APPLY_RECORD_ID = "tb_apply_record";
	private static int MAX_FREQUENCY = 3;
	private IdCreaterTool(){};
	
	public static BigDecimal getDBTableMaxId(String tableName)
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
	
	/**
	 * 取id
	 * @param tableName 表名
	 * @param frequency 失败次数
	 * @return
	 */
	public static BigDecimal getNextId(String tableName, int frequency)
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
				 if (BaseDao.update("UPDATE ts_sequence SET current_value = current_value + 1 WHERE name = ?  AND current_value = ?", params))
				 {
					 nextId = currentId.add(new BigDecimal(1));
				 }
				 else
				 {
					 frequency = frequency + 1;
					 if (frequency < MAX_FREQUENCY)
					 {
						 nextId = getNextId(tableName, frequency);						 
					 }
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
	public static BigDecimal getFavUserId(){
		return getNextId(FAV_USER_ID, 0);
	}
	
	/**
	 * 生成用户兴趣收藏类别表id
	 * @return
	 */
	public static BigDecimal getUserInteresCategoryId(){
		return getNextId(USER_INTERES_CATEGORY_ID, 0);
	}
	
	/**
	 * 生成消息表id
	 * @return
	 */
	public static BigDecimal getMessagesId(){
		return getNextId(MESSEAGS_ID, 0);
	}
	
	/**
	 * 生成收藏夹表id
	 * @return
	 */
	public static BigDecimal getFavoritesId(){
		return getNextId(FAVORITES_ID, 0);
	}
	
	/**
	 * 生成藏品表id
	 * @return
	 */
	public static BigDecimal getCollectionId(){
		return getNextId(COLLECTION_ID, 0);
	}
	
	/**
	 * 生成藏品图片集表id
	 * @return
	 */
	public static BigDecimal getCollectionImagesId(){
		return getNextId(COLLECTION_IMAGES_ID, 0);
	}
	
	/**
	 * 生成动态表id
	 * @return
	 */
	public static BigDecimal getDynamicId(){
		return getNextId(DYNAMIC_ID, 0);
	}
	
	/**
	 * 生成动态图片地址表id
	 * @return
	 */
	public static BigDecimal getDynamicimagesId(){
		return getNextId(DYNAMIC_IMAGES_ID, 0);
	}
	
	/**
	 * 生成粉丝表id
	 * @return
	 */
	public static BigDecimal getUserFansId(){
		return getNextId(USER_FANS_ID, 0);
	}
	
	/**
	 * 生成评论表id
	 * @return
	 */
	public static BigDecimal getCommentId(){
		return getNextId(COMMENT_ID, 0);
	}
	
	/**
	 * 生成评论赞同反对表id
	 * @return
	 */
	public static BigDecimal getCommentLikeId(){
		return getNextId(COMMENT_LIKE_ID, 0);
	}
	
	/**
	 * 生成评论顶表id
	 * @return
	 */
	public static BigDecimal getCommentTopId(){
		return getNextId(COMMENT_TOP_ID, 0);
	}
	
	/**
	 * 生成天天论战投票表id
	 * @return
	 */
	public static BigDecimal getPolemicVoteId(){
		return getNextId(DAILY_POLEMIC_VOTE_ID, 0);
	}
	
	/**
	 * 生成天天论战投票表id
	 * @return
	 */
	public static BigDecimal getDynamicLikeId(){
		return getNextId(DYNAMIC_LIKE_ID, 0);
	}
	
	/**
	 * 生成用户积分记录表id
	 * @return
	 */
	public static BigDecimal getUserPointsRecordId(){
		return getNextId(USER_POINTS_RECORD_ID, 0);
	}
	
	/**
	 * 生成用户黑名单表id
	 * @return
	 */
	public static BigDecimal getUserBlacklistId(){
		return getNextId(USER_BLACKLIST_ID, 0);
	}
	
	/**
	 * 生成用户设置表id
	 * @return
	 */
	public static BigDecimal getFavUserSetId(){
		return getNextId(FAV_USER_SET_ID, 0);
	}
	
	/**
	 * 生成短信表id
	 * @return
	 */
	public static BigDecimal getSmsId(){
		return getNextId(SMS_ID, 0);
	}
	
	/**
	 * 生成申请记录表id
	 * @return
	 */
	public static BigDecimal getApplyRecordId(){
		return getNextId(APPLY_RECORD_ID, 0);
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		//System.out.println(getNextId("tb_collection", 0).toString());
		System.out.println(getUserBlacklistId());
		System.out.println(System.currentTimeMillis() - start);
	}
}
