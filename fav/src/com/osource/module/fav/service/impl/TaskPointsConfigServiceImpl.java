package com.osource.module.fav.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.fav.dao.CollectionDao;
import com.osource.module.fav.dao.TaskPointsConfigDao;
import com.osource.module.fav.model.TaskPointsConfigInfo;
import com.osource.module.fav.service.TaskPointsConfigService;
import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.util.StringUtil;

@Service
@Scope("prototype")
@Transactional
public class TaskPointsConfigServiceImpl extends BaseServiceImpl<TaskPointsConfigInfo> implements TaskPointsConfigService {
	
	protected TaskPointsConfigDao getDao() {
		return (TaskPointsConfigDao)super.getDao();
	}

	@Autowired
	public void setDao(TaskPointsConfigDao taskPointsConfigDao) {
		super.setDao(taskPointsConfigDao);
	}
}
