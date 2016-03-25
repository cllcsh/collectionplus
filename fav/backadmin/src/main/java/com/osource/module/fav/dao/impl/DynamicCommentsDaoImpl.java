package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DynamicCommentsInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicCommentsDao;

@Repository
public class DynamicCommentsDaoImpl extends BaseDaoImpl<DynamicCommentsInfo> implements DynamicCommentsDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicComments";
	}
	
}