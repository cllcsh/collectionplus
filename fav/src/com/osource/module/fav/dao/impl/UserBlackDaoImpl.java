package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.UserBlackInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.UserBlackDao;

@Repository
public class UserBlackDaoImpl extends BaseDaoImpl<UserBlackInfo> implements UserBlackDao {
	@Override
	public String getEntityName() {
		return "fav_userBlack";
	}
	
}