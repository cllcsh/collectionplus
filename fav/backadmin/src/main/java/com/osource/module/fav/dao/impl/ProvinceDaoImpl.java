package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.ProvinceInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.ProvinceDao;

@Repository
public class ProvinceDaoImpl extends BaseDaoImpl<ProvinceInfo> implements ProvinceDao {
	@Override
	public String getEntityName() {
		return "fav_province";
	}
	
}