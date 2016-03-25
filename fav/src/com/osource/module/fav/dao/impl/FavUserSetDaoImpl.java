package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.FavUserSetInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.FavUserSetDao;

@Repository
public class FavUserSetDaoImpl extends BaseDaoImpl<FavUserSetInfo> implements FavUserSetDao {
	@Override
	public String getEntityName() {
		return "fav_favUserSet";
	}
	
}