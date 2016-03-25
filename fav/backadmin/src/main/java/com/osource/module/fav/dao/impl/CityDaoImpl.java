package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CityInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CityDao;

@Repository
public class CityDaoImpl extends BaseDaoImpl<CityInfo> implements CityDao {
	@Override
	public String getEntityName() {
		return "fav_city";
	}
	
}