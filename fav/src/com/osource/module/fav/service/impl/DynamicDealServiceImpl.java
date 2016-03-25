package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DynamicDealDao;
import com.osource.module.fav.model.DynamicDealInfo;
import com.osource.module.fav.service.DynamicDealService;

@Service
@Scope("prototype")
@Transactional
public class DynamicDealServiceImpl extends BaseServiceImpl<DynamicDealInfo> implements DynamicDealService {

	/** setter and getter methods **/
	
	protected DynamicDealDao getDao() {
		return (DynamicDealDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicDealDao dynamicDealDao) {
		super.setDao(dynamicDealDao);
	}
}
