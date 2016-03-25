package com.front.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.front.db.bean.FavUserBean;
import com.front.web.framework.database.BaseDao;

public class UserDao {

	public static FavUserBean queryUser(String account, String password) throws Exception
	{
		String conditionSql = "account = ? AND password = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(account);
		params.add(password);
		return (FavUserBean) BaseDao.getObjectByAnnotation(FavUserBean.class, conditionSql, params);
	}
	
	
	public static FavUserBean queryUser(BigDecimal id, String password) throws Exception
	{
		String conditionSql = "id = ? AND password = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		params.add(password);
		return (FavUserBean) BaseDao.getObjectByAnnotation(FavUserBean.class, conditionSql, params);
	}
}
