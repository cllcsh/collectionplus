package com.osource.module.fav.dao;

import com.osource.orm.ibatis.BaseDao;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionImagesInfo;

public interface CollectionImagesDao extends BaseDao<CollectionImagesInfo> {
	void deleteByCollectionId(int collectionId) throws IctException;
}