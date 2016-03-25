package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DynamicDealInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicDealDao;

@Repository
public class DynamicDealDaoImpl extends BaseDaoImpl<DynamicDealInfo> implements DynamicDealDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicDeal";
	}
	
}