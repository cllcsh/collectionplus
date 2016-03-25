package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.TodayAppreciationDao;
import com.osource.module.fav.model.TodayAppreciationInfo;
import com.osource.module.fav.service.TodayAppreciationService;

@Service
@Scope("prototype")
@Transactional
public class TodayAppreciationServiceImpl extends BaseServiceImpl<TodayAppreciationInfo> implements TodayAppreciationService {

	/** setter and getter methods **/
	
	protected TodayAppreciationDao getDao() {
		return (TodayAppreciationDao)super.getDao();
	}

	@Autowired
	public void setDao(TodayAppreciationDao todayAppreciationDao) {
		super.setDao(todayAppreciationDao);
	}
}
