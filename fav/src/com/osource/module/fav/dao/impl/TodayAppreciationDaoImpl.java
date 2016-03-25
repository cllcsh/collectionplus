package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.TodayAppreciationInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.TodayAppreciationDao;

@Repository
public class TodayAppreciationDaoImpl extends BaseDaoImpl<TodayAppreciationInfo> implements TodayAppreciationDao {
	@Override
	public String getEntityName() {
		return "fav_todayAppreciation";
	}
	
}