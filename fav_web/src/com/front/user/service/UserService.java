package com.front.user.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.express.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.front.cache.SysConfigCache;
import com.front.db.bean.CollectionCategoryBean;
import com.front.db.bean.FavUserBean;
import com.front.db.bean.SmsBean;
import com.front.db.bean.UserInteresCategoryBean;
import com.front.db.bean.UserPointsRecordBean;
import com.front.user.dao.UserDao;
import com.front.web.framework.database.BaseDao;
import com.front.web.util.IdCreaterTool;

public class UserService {

	// 运行日志接口
    private final static Logger runLog = LoggerFactory.getLogger(UserService.class);
    
	public static FavUserBean queryUser(String account, String password) throws Exception
	{
		return UserDao.queryUser(account, password);
	}
	
	/**
	 * 根据帐号查询用户
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public static FavUserBean queryUserByAccount(String account) throws Exception
	{
		FavUserBean favUser = null;
		List<Object> params = new ArrayList<Object>();
		params.add(account);
		List<FavUserBean>  favUserList = BaseDao.getListByAnnotation(FavUserBean.class, "account = ? ", params);
		if (CollectionUtils.isNotEmpty(favUserList))
		{
			favUser = favUserList.get(0);
		}
		return favUser;
	}
	
	/**
	 * 更新用户的密码
	 * @param password
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public static boolean updateUserPassword(String password, String account) throws Exception
	{
		FavUserBean favUser = new FavUserBean();
		favUser.setPassword(password);
		List<Object> params = new ArrayList<Object>();
		params.add(account);
		return BaseDao.update(favUser, "account = ?", params);
	}
	
	/**
	 * 更新用户的密码
	 * @param password
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static boolean updateUserPassword(String password, BigDecimal userId) throws Exception
	{
		FavUserBean favUser = new FavUserBean();
		favUser.setPassword(password);
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		return BaseDao.update(favUser, "id = ?", params);
	}
	
	
	/**
	 * 根据帐号查询用户
	 * @param boolean
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean updateFavUser(FavUserBean favUserBean) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(favUserBean.getId());
		return BaseDao.update(favUserBean, "id = ?", params);
	}
	
	/**
	 * 新增用户
	 * @param favUserBean
	 * @param categoryIds
	 * @throws Exception
	 */
	public static boolean addFavUser(FavUserBean favUserBean, String categoryIds) throws Exception
	{
        boolean result = BaseDao.insert(favUserBean);	
        if (result)
        {
        	if (StringUtils.isNotBlank(categoryIds))
        	{
        		UserInteresCategoryBean ucbean = null;
            	String[] ids = categoryIds.split(",");
            	List<Object> insertObjList = new ArrayList<Object>();
            	BigDecimal userInteresCategoryId = null;
            	for(String categoryId : ids)
            	{
        			ucbean = new UserInteresCategoryBean();
        			userInteresCategoryId = IdCreaterTool.getUserInteresCategoryId();
        			ucbean.setId(userInteresCategoryId);
        			ucbean.setUser_id(favUserBean.getId());
        			ucbean.setCategory_id(new BigDecimal(categoryId.trim()));
        			ucbean.setInsert_id(favUserBean.getId());
        			ucbean.setUpdate_id(favUserBean.getId());
        			insertObjList.add(ucbean);				
            	}
            	if (CollectionUtils.isNotEmpty(insertObjList))
            	{
            		BaseDao.insertBatch(insertObjList);
            	}
        	}
        }
        return result;
	}
	
	/**
	 * 根据Id查询用户详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static FavUserBean queryUserById(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		return (FavUserBean) BaseDao.getObjectByAnnotation(FavUserBean.class, "id = ?", params);
	}
	
	/**
	 * 用户粉丝数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryUserFansTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) total FROM tb_user_fans WHERE user_id = ?";
		return BaseDao.getCount(sql, params);
	}
	
	/**
	 * 用户关注数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static int queryUserAttentionTotal(BigDecimal id) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		String sql = "SELECT COUNT(*) total FROM tb_user_fans WHERE fan_id = ?";
		return BaseDao.getCount(sql, params);
	}
	
	/**
	 * 更新用户积分
	 * @param userId
	 * @param typeName
	 */
	public static void updateUserPoints(BigDecimal userId, String typeName)
	{
		int value = SysConfigCache.getPointsValue(typeName);
		if (value != 0)
		{
			List<Object> paraList = new ArrayList<Object>();
			paraList.add(value);
			paraList.add(userId);
			try {
				BaseDao.update("UPDATE tb_fav_user SET user_points = user_points + ? WHERE id = ?", paraList);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 更新用户积分
	 * @param userId
	 * @param typeName
	 */
	public static void addUserPointsRecord(BigDecimal userId, String typeName)
	{
		UserPointsRecordBean uprBean = new UserPointsRecordBean();
		uprBean.setId(IdCreaterTool.getUserPointsRecordId());
		uprBean.setUserid(userId);
		uprBean.setPoints(SysConfigCache.getPointsValue(typeName));
		uprBean.setTaskid(SysConfigCache.getPointTaskId(typeName));
		uprBean.setDay(DateUtil.format(new Date(), "yyyyMMdd"));
		uprBean.setInsert_id(userId);
		uprBean.setUpdate_id(userId);
		try {
			BaseDao.insert(uprBean);
		} catch (Exception e) {
		}
	}
	
	
	/**
	 * 是否存在用户的某个任务的积分记录
	 * @param userId
	 * @param typeName
	 * @return boolean  true:存在
	 */
	public static boolean isExistUserPointsRecord(BigDecimal userId, String typeName)
	{
	    boolean isExist = false;
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		params.add(SysConfigCache.getPointTaskId(typeName));
		String day = DateUtil.format(new Date(), "yyyyMMdd");
		params.add(day);
		String sql = "SELECT COUNT(*) FROM tb_user_points_record WHERE userid = ? AND taskid = ? AND `day` = ? ";
		try {
			int count = BaseDao.getCount(sql, params);
			isExist = count > 0 ? true : false;
		} catch (SQLException e) {
			runLog.error("isExistUserPointsRecord error", e);
		}
		return isExist;
	}
	
	/**
	 * 增加短信
	 * @param cotent
	 * @param phone
	 */
	public static void addSms(String cotent, String phone)
	{
		SmsBean smsBean = new SmsBean();
		smsBean.setId(IdCreaterTool.getSmsId());
		smsBean.setContent(cotent);
		smsBean.setPhone(phone);
		smsBean.setInsert_id(new BigDecimal(-1));
		smsBean.setUpdate_id(new BigDecimal(-1));
		try {
			BaseDao.insert(smsBean);
		} catch (Exception e) {
		}
	}
	
	public static String getRandomCode()
	{
		Random random = new Random();
		String sRand = "";
 		for (int i = 0; i < 6; i++) {
 			String rand = String.valueOf(random.nextInt(10));
 			sRand += rand;
 		}
 		return sRand;
	}
	
	public static SmsBean querySms(String phone)
	{
		SmsBean smsBean = null;
		try {
			smsBean = (SmsBean) BaseDao.getObjectByAnnotation(SmsBean.class, "phone = '"+ phone +"' ORDER BY insert_date DESC LIMIT 1", null);
		} catch (Exception e) {
			runLog.error("querySms error", e);
		}
		return smsBean;
	}
	
	public static void updateUserInfo(BigDecimal userId, String userName, String categoryIds) throws Exception
	{
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		if (StringUtils.isNotBlank(userName))
		{
			FavUserBean fuBean = new FavUserBean();
			fuBean.setUser_name(userName);
			fuBean.setUpdate_date(new Date());
			fuBean.setUpdate_id(userId);
			BaseDao.update(fuBean, "id =? ", params);
		}
		BaseDao.delete(UserInteresCategoryBean.class, "user_id = ?", params);
		//没有选择则表示默认所有的
		if (StringUtils.isBlank(categoryIds))
		{
			categoryIds = "";
			List<CollectionCategoryBean> ccbList = SysConfigCache.getCollectionCategorys();
			for(CollectionCategoryBean ccBean : ccbList)
			{
				categoryIds += ccBean.getId() + ",";
			}
		}
		String[] ids = categoryIds.split(",");
		UserInteresCategoryBean ucbean = null;
		List<Object> insertObjList = new ArrayList<Object>();
    	BigDecimal userInteresCategoryId = null;
    	for(String categoryId : ids)
    	{
			if(StringUtils.isNotBlank(categoryId))
			{
				ucbean = new UserInteresCategoryBean();
				userInteresCategoryId = IdCreaterTool.getUserInteresCategoryId();
				ucbean.setId(userInteresCategoryId);
				ucbean.setUser_id(userId);
				ucbean.setCategory_id(new BigDecimal(categoryId.trim()));
				ucbean.setInsert_id(userId);
				ucbean.setUpdate_id(userId);
				insertObjList.add(ucbean);								
			}
    	}
    	if (CollectionUtils.isNotEmpty(insertObjList))
    	{
    		BaseDao.insertBatch(insertObjList);
    	}
	}
}
