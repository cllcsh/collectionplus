package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DailyPolemicInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DailyPolemicDao;

@Repository
public class DailyPolemicDaoImpl extends BaseDaoImpl<DailyPolemicInfo> implements DailyPolemicDao {
	@Override
	public String getEntityName() {
		return "fav_dailyPolemic";
	}
	
}