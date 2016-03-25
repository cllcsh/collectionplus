package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CollectionInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CollectionDao;

@Repository
public class CollectionDaoImpl extends BaseDaoImpl<CollectionInfo> implements CollectionDao {
	@Override
	public String getEntityName() {
		return "fav_collection";
	}
	
}