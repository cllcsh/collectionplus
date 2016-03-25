package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.EnumInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.EnumDao;

@Repository
public class EnumDaoImpl extends BaseDaoImpl<EnumInfo> implements EnumDao {
	@Override
	public String getEntityName() {
		return "fav_enum";
	}
	
}