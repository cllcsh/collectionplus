package com.osource.module.map.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.map.dao.AreaTimeDao;
import com.osource.module.map.model.AreaTimeBean;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class AreaTimeDaoImpl extends BaseDaoImpl<AreaTimeBean> implements AreaTimeDao {
	@Override
	public String getEntityName() {
		return "module_areaTime";
	}
	
}