package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.UserTitleInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.UserTitleDao;

@Repository
public class UserTitleDaoImpl extends BaseDaoImpl<UserTitleInfo> implements UserTitleDao {
	@Override
	public String getEntityName() {
		return "fav_userTitle";
	}
	
}