package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CollectionPeriodDao;
import com.osource.module.fav.model.CollectionPeriodInfo;
import com.osource.module.fav.service.CollectionPeriodService;

@Service
@Scope("prototype")
@Transactional
public class CollectionPeriodServiceImpl extends BaseServiceImpl<CollectionPeriodInfo> implements CollectionPeriodService {

	/** setter and getter methods **/
	
	protected CollectionPeriodDao getDao() {
		return (CollectionPeriodDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionPeriodDao collectionPeriodDao) {
		super.setDao(collectionPeriodDao);
	}
}
