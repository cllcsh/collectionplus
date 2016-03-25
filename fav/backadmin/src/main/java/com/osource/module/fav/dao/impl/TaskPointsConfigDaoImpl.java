package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.dao.TaskPointsConfigDao;
import com.osource.module.fav.model.TaskPointsConfigInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class TaskPointsConfigDaoImpl extends BaseDaoImpl<TaskPointsConfigInfo> implements TaskPointsConfigDao {
	@Override
	public String getEntityName() {
		return "fav_taskPointsConfig";
	}
}