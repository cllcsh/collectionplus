package com.osource.module.fav.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.fav.model.CollectionCommentsInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CollectionCommentsDao;

@Repository
public class CollectionCommentsDaoImpl extends BaseDaoImpl<CollectionCommentsInfo> implements CollectionCommentsDao {
	@Override
	public String getEntityName() {
		return "fav_collectionComments";
	}
	
	public void updateLike(Map map) throws IctException{
		update("fav_collectionComments_update_like", map);
	}
	
	public void updateTop(Map map) throws IctException{
		update("fav_collectionComments_update_top", map);
	}
}