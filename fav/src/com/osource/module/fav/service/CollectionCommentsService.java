package com.osource.module.fav.service;

import com.osource.orm.ibatis.BaseService;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionCommentsInfo;

public interface CollectionCommentsService extends BaseService<CollectionCommentsInfo> {
	long countAll(String sql, Object condition);
	
	void updateInfo(CollectionCommentsInfo info) throws IctException;
}