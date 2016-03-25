package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.UserPointsRecordInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.UserPointsRecordDao;

@Repository
public class UserPointsRecordDaoImpl extends BaseDaoImpl<UserPointsRecordInfo> implements UserPointsRecordDao {
	@Override
	public String getEntityName() {
		return "fav_userPointsRecord";
	}
	
}