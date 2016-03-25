package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.FamousHomeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.FamousHomeDao;

@Repository
public class FamousHomeDaoImpl extends BaseDaoImpl<FamousHomeInfo> implements FamousHomeDao {
	@Override
	public String getEntityName() {
		return "fav_famousHome";
	}
	
}