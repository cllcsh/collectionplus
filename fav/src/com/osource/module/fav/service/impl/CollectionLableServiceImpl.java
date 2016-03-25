package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CollectionLableDao;
import com.osource.module.fav.model.CollectionLableInfo;
import com.osource.module.fav.service.CollectionLableService;

@Service
@Scope("prototype")
@Transactional
public class CollectionLableServiceImpl extends BaseServiceImpl<CollectionLableInfo> implements CollectionLableService {

	/** setter and getter methods **/
	
	protected CollectionLableDao getDao() {
		return (CollectionLableDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionLableDao collectionLableDao) {
		super.setDao(collectionLableDao);
	}
}
