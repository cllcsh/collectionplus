package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DynamicLikeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicLikeDao;

@Repository
public class DynamicLikeDaoImpl extends BaseDaoImpl<DynamicLikeInfo> implements DynamicLikeDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicLike";
	}
	
}