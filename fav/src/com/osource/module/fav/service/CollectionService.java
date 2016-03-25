package com.osource.module.fav.service;

import java.util.List;

import com.osource.orm.ibatis.BaseService;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionInfo;

public interface CollectionService extends BaseService<CollectionInfo> {
	void save(CollectionInfo collectionInfo, List<String> imgs)throws IctException;
	void update(CollectionInfo collectionInfo, List<String> imgs)throws IctException;
	void delete(String ids)throws IctException;
}