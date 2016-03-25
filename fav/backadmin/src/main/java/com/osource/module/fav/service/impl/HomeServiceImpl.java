package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.HomeDao;
import com.osource.module.fav.model.HomeInfo;
import com.osource.module.fav.service.HomeService;

@Service
@Scope("prototype")
@Transactional
public class HomeServiceImpl extends BaseServiceImpl<HomeInfo> implements HomeService {

	/** setter and getter methods **/
	
	protected HomeDao getDao() {
		return (HomeDao)super.getDao();
	}

	@Autowired
	public void setDao(HomeDao homeDao) {
		super.setDao(homeDao);
	}
}
