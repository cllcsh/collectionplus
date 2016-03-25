package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.CollectionImagesDao;
import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class CollectionImagesDaoImpl extends BaseDaoImpl<CollectionImagesInfo> implements CollectionImagesDao {
	@Override
	public String getEntityName() {
		return "fav_collectionImages";
	}
	
	public void deleteByCollectionId(int collectionId) throws IctException{
		delete(getEntityName() + "_deleteByCollectionId", collectionId);
	}
}