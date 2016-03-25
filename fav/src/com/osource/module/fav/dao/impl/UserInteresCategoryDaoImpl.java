package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.UserInteresCategoryInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.UserInteresCategoryDao;

@Repository
public class UserInteresCategoryDaoImpl extends BaseDaoImpl<UserInteresCategoryInfo> implements UserInteresCategoryDao {
	@Override
	public String getEntityName() {
		return "fav_userInteresCategory";
	}
	
}