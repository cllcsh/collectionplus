package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CollectionCategoryInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CollectionCategoryDao;

@Repository
public class CollectionCategoryDaoImpl extends BaseDaoImpl<CollectionCategoryInfo> implements CollectionCategoryDao {
	@Override
	public String getEntityName() {
		return "fav_collectionCategory";
	}
	
}