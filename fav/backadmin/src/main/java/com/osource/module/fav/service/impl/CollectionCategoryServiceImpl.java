package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CollectionCategoryDao;
import com.osource.module.fav.model.CollectionCategoryInfo;
import com.osource.module.fav.service.CollectionCategoryService;

@Service
@Scope("prototype")
@Transactional
public class CollectionCategoryServiceImpl extends BaseServiceImpl<CollectionCategoryInfo> implements CollectionCategoryService {

	/** setter and getter methods **/
	
	protected CollectionCategoryDao getDao() {
		return (CollectionCategoryDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionCategoryDao collectionCategoryDao) {
		super.setDao(collectionCategoryDao);
	}
}
