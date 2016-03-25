package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.SmsInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.SmsDao;

@Repository
public class SmsDaoImpl extends BaseDaoImpl<SmsInfo> implements SmsDao {
	@Override
	public String getEntityName() {
		return "fav_sms";
	}
	
}