package com.front.my.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.express.util.DateUtil;

import com.alibaba.fastjson.JSONObject;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.UserBlacklistBean;
import com.front.shou.service.ShouService;
import com.front.user.dao.UserDao;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.framework.database.BaseDao;
import com.front.web.framework.database.ResultSetInterface;
import com.front.web.util.MD5;

public class MyService {

	public static FavUserBean queryUserByPwd(String password, BigDecimal userId) throws Exception
	{
		return UserDao.queryUser(userId, MD5.GetMD5Code(password));
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserBlacklist(BigDecimal userId) throws Exception
	{
		String sql = "SELECT tu.*, tb.id blacklistId FROM tb_user_blacklist tb " +
				"INNER JOIN tb_fav_user tu ON tu.id = tb.blacklist_user_id WHERE tb.user_id = ? ";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("blacklistId", rs.getBigDecimal("blacklistId"));
				json.put("user_name", rs.getString("user_name"));
				json.put("signature", rs.getString("signature"));
				json.put("avatar", rs.getString("avatar"));
				return json;
			}
		}, sql, paraList);
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserDayTask(BigDecimal userId) throws Exception
	{
		String sql = "SELECT tr.*, tc.task_name FROM tb_user_points_record tr " +
				"LEFT JOIN tb_task_points_config tc ON tr.taskid = tc.id " +
				"WHERE tr.userid = ? AND tr.use_flag = 1 AND tr.`day` = ? GROUP BY tr.taskid";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		paraList.add(DateUtil.format(new Date(), "yyyyMMdd"));
	    return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("userid", rs.getBigDecimal("userid"));
				json.put("points", rs.getInt("points"));
				json.put("taskid", rs.getBigDecimal("taskid"));
				json.put("day", rs.getString("day"));
				json.put("task_name", rs.getString("task_name"));
				return json;
			}
		}, sql, paraList);
	}
	
	public static boolean updateUserMsgSet(BigDecimal userId, String msgSet) throws Exception
	{
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(msgSet);
		paraList.add(new Date());
		paraList.add(userId);
		paraList.add(userId);
		return BaseDao.update("UPDATE tb_fav_user SET personal_msg_set = ?, update_date = ?, update_id = ? WHERE id = ?", paraList);
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryCollectionsByUserId(BigDecimal userId) throws Exception
	{
		String sql = "SELECT tc.*, SUM(tcm.point) as points, COUNT(tcm.source_id) as comment_num FROM tb_collection tc " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id " +
				"WHERE tc.insert_id = ? AND tc.use_flag = 1 GROUP BY tc.id ORDER BY tc.insert_date DESC";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = ShouService.parseDataToCollection(rs);
				return json;
			}}, sql, paraList);
	}
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserFavorites(BigDecimal userId) throws Exception
	{
		String sql = "SELECT tc.*, SUM(tcm.point) as points, COUNT(tcm.source_id) as comment_num FROM tb_favorites tf " +
				"INNER JOIN tb_collection tc ON tf.collection_id = tc.id " +
				"LEFT JOIN tb_comment tcm ON tcm.source_id = tc.id " +
				"WHERE tf.user_id = ? AND tf.use_flag = 1 AND tc.use_flag = 1 GROUP BY tc.id ORDER BY tf.favorite_time DESC;";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = ShouService.parseDataToCollection(rs);
				return json;
			}}, sql, paraList);
	}
	
	/*@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserApplyRecord(BigDecimal userId) throws Exception
	{
		String sql = "SELECT * FROM tb_apply_record WHERE insert_id = ?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("collection_id", rs.getBigDecimal("collection_id"));
				json.put("apply_type", rs.getString("apply_type"));
				json.put("apply_time", DateUtil.DateFormat(rs.getTimestamp("apply_time")));
				json.put("status", Constant.APPLY_STATUS.get(rs.getString("status")));
				return json;
			}}, sql, paraList);
	}*/
	
	@SuppressWarnings("unchecked")
	public static List<JSONObject> queryUserApplyRecord(BigDecimal userId) throws Exception
	{
		String sql = "SELECT tar.*, ta.name, tad.title, tad.auction_id, tc.icon_img FROM tb_apply_record tar " +
				"LEFT JOIN tb_auction_dynamics tad ON tad.id = tar.apply_type " +
				"LEFT JOIN tb_auction ta ON ta.id = tad.auction_id " +
				"LEFT JOIN tb_collection tc ON tc.id = tar.collection_id WHERE tar.applier_id = ?";
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.getListBySql(new ResultSetInterface() {
			public JSONObject getObject(ResultSet rs) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id", rs.getBigDecimal("id"));
				json.put("collection_id", rs.getBigDecimal("collection_id"));
				json.put("auction_id", rs.getBigDecimal("auction_id"));
				json.put("apply_type", rs.getString("apply_type"));
				json.put("apply_time", DateUtil.DateFormat(rs.getTimestamp("apply_time")));
				json.put("name", rs.getString("name"));
				json.put("title", rs.getString("title"));
				json.put("status", Constant.APPLY_STATUS.get(rs.getString("status")));
				json.put("icon_img", rs.getString("icon_img"));
				return json;
			}}, sql, paraList);
	}
	
	public static boolean deleteBlacklist(BigDecimal id) throws Exception
	{
		return BaseDao.delete(UserBlacklistBean.class, "id = " + id, null);
	}
	
	/**
	 * 修改个人签名
	 * @param userId
	 * @param signature
	 * @return
	 * @throws Exception 
	 */
	public static boolean modifySignature(BigDecimal userId, String signature) throws Exception
	{
		FavUserBean fuBean = new FavUserBean();
		fuBean.setSignature(signature);
		List<Object> paraList = new ArrayList<Object>();
		paraList.add(userId);
		return BaseDao.update(fuBean, "id = ? ", paraList);
	}
}
