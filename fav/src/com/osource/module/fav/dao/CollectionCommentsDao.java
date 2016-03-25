package com.osource.module.fav.dao;

import java.util.Map;

import com.osource.orm.ibatis.BaseDao;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionCommentsInfo;

public interface CollectionCommentsDao extends BaseDao<CollectionCommentsInfo> {
	void updateLike(Map map) throws IctException;
	
	void updateTop(Map map) throws IctException;
}