package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.FavUserInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.FavUserDao;

@Repository
public class FavUserDaoImpl extends BaseDaoImpl<FavUserInfo> implements FavUserDao {
	@Override
	public String getEntityName() {
		return "fav_favUser";
	}
	
}