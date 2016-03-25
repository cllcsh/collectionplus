package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.HeatInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.HeatDao;

@Repository
public class HeatDaoImpl extends BaseDaoImpl<HeatInfo> implements HeatDao {
	@Override
	public String getEntityName() {
		return "fav_heat";
	}
	
}