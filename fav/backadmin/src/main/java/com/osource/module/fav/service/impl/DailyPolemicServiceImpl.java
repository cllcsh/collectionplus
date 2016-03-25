package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DailyPolemicDao;
import com.osource.module.fav.model.DailyPolemicInfo;
import com.osource.module.fav.service.DailyPolemicService;

@Service
@Scope("prototype")
@Transactional
public class DailyPolemicServiceImpl extends BaseServiceImpl<DailyPolemicInfo> implements DailyPolemicService {

	/** setter and getter methods **/
	
	protected DailyPolemicDao getDao() {
		return (DailyPolemicDao)super.getDao();
	}

	@Autowired
	public void setDao(DailyPolemicDao dailyPolemicDao) {
		super.setDao(dailyPolemicDao);
	}
}
