package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.SpecialInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.SpecialDao;

@Repository
public class SpecialDaoImpl extends BaseDaoImpl<SpecialInfo> implements SpecialDao {
	@Override
	public String getEntityName() {
		return "fav_special";
	}
	
}