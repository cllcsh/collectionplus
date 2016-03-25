package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CountyDao;
import com.osource.module.fav.model.CountyInfo;
import com.osource.module.fav.service.CountyService;

@Service
@Scope("prototype")
@Transactional
public class CountyServiceImpl extends BaseServiceImpl<CountyInfo> implements CountyService {

	/** setter and getter methods **/
	
	protected CountyDao getDao() {
		return (CountyDao)super.getDao();
	}

	@Autowired
	public void setDao(CountyDao countyDao) {
		super.setDao(countyDao);
	}
}
