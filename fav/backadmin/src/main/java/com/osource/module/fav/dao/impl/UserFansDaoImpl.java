package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.UserFansInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.UserFansDao;

@Repository
public class UserFansDaoImpl extends BaseDaoImpl<UserFansInfo> implements UserFansDao {
	@Override
	public String getEntityName() {
		return "fav_userFans";
	}
	
}