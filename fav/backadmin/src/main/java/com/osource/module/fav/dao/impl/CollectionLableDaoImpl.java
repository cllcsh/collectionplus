package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CollectionLableInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CollectionLableDao;

@Repository
public class CollectionLableDaoImpl extends BaseDaoImpl<CollectionLableInfo> implements CollectionLableDao {
	@Override
	public String getEntityName() {
		return "fav_collectionLable";
	}
	
}