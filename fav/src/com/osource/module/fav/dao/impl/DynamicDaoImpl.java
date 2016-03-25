package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DynamicInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicDao;

@Repository
public class DynamicDaoImpl extends BaseDaoImpl<DynamicInfo> implements DynamicDao {
	@Override
	public String getEntityName() {
		return "fav_dynamic";
	}
	
}