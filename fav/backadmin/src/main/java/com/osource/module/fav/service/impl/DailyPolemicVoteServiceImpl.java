package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DailyPolemicVoteDao;
import com.osource.module.fav.model.DailyPolemicVoteInfo;
import com.osource.module.fav.service.DailyPolemicVoteService;

@Service
@Scope("prototype")
@Transactional
public class DailyPolemicVoteServiceImpl extends BaseServiceImpl<DailyPolemicVoteInfo> implements DailyPolemicVoteService {

	/** setter and getter methods **/
	
	protected DailyPolemicVoteDao getDao() {
		return (DailyPolemicVoteDao)super.getDao();
	}

	@Autowired
	public void setDao(DailyPolemicVoteDao dailyPolemicVoteDao) {
		super.setDao(dailyPolemicVoteDao);
	}
}
