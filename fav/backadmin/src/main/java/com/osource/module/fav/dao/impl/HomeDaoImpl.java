package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.HomeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.HomeDao;

@Repository
public class HomeDaoImpl extends BaseDaoImpl<HomeInfo> implements HomeDao {
	@Override
	public String getEntityName() {
		return "fav_home";
	}
	
}