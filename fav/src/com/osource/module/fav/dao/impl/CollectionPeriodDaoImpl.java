package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CollectionPeriodInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CollectionPeriodDao;

@Repository
public class CollectionPeriodDaoImpl extends BaseDaoImpl<CollectionPeriodInfo> implements CollectionPeriodDao {
	@Override
	public String getEntityName() {
		return "fav_collectionPeriod";
	}
	
}