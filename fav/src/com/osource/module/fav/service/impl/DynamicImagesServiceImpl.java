package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DynamicImagesDao;
import com.osource.module.fav.model.DynamicImagesInfo;
import com.osource.module.fav.service.DynamicImagesService;

@Service
@Scope("prototype")
@Transactional
public class DynamicImagesServiceImpl extends BaseServiceImpl<DynamicImagesInfo> implements DynamicImagesService {

	/** setter and getter methods **/
	
	protected DynamicImagesDao getDao() {
		return (DynamicImagesDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicImagesDao dynamicImagesDao) {
		super.setDao(dynamicImagesDao);
	}
}
