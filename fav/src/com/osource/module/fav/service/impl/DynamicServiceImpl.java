package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DynamicDao;
import com.osource.module.fav.model.DynamicInfo;
import com.osource.module.fav.service.DynamicService;

@Service
@Scope("prototype")
@Transactional
public class DynamicServiceImpl extends BaseServiceImpl<DynamicInfo> implements DynamicService {

	/** setter and getter methods **/
	
	protected DynamicDao getDao() {
		return (DynamicDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicDao dynamicDao) {
		super.setDao(dynamicDao);
	}
}
