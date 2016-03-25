package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.CollectionImagesDao;
import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.CollectionImagesService;

@Service
@Scope("prototype")
@Transactional
public class CollectionImagesServiceImpl extends BaseServiceImpl<CollectionImagesInfo> implements CollectionImagesService {

	/** setter and getter methods **/
	
	protected CollectionImagesDao getDao() {
		return (CollectionImagesDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionImagesDao collectionImagesDao) {
		super.setDao(collectionImagesDao);
	}
	
	public void deleteByCollectionId(int collectionId) throws IctException{
		getDao().deleteByCollectionId(collectionId);
	}
}
