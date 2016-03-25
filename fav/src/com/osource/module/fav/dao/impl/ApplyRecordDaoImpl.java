package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.ApplyRecordInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.ApplyRecordDao;

@Repository
public class ApplyRecordDaoImpl extends BaseDaoImpl<ApplyRecordInfo> implements ApplyRecordDao {
	@Override
	public String getEntityName() {
		return "fav_applyRecord";
	}
	
}