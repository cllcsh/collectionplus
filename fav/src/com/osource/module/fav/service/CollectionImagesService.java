package com.osource.module.fav.service;

import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.orm.ibatis.BaseService;

public interface CollectionImagesService extends BaseService<CollectionImagesInfo> {
	public void deleteByCollectionId(int collectionId) throws IctException;
}