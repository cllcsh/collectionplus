package com.osource.module.map.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.map.dao.ActualDao;
import com.osource.module.map.model.ActualBean;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class ActualDaoImpl extends BaseDaoImpl<ActualBean> implements ActualDao {
	@Override
	public String getEntityName() {
		return "map_actual";
	}
	
}