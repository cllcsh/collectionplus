package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CityDao;
import com.osource.module.fav.model.CityInfo;
import com.osource.module.fav.service.CityService;

@Service
@Scope("prototype")
@Transactional
public class CityServiceImpl extends BaseServiceImpl<CityInfo> implements CityService {

	/** setter and getter methods **/
	
	protected CityDao getDao() {
		return (CityDao)super.getDao();
	}

	@Autowired
	public void setDao(CityDao cityDao) {
		super.setDao(cityDao);
	}
}
