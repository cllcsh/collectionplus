package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CountyInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CountyDao;

@Repository
public class CountyDaoImpl extends BaseDaoImpl<CountyInfo> implements CountyDao {
	@Override
	public String getEntityName() {
		return "fav_county";
	}
	
}