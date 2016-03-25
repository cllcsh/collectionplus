package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DynamicCommentsDao;
import com.osource.module.fav.model.DynamicCommentsInfo;
import com.osource.module.fav.service.DynamicCommentsService;

@Service
@Scope("prototype")
@Transactional
public class DynamicCommentsServiceImpl extends BaseServiceImpl<DynamicCommentsInfo> implements DynamicCommentsService {

	/** setter and getter methods **/
	
	protected DynamicCommentsDao getDao() {
		return (DynamicCommentsDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicCommentsDao dynamicCommentsDao) {
		super.setDao(dynamicCommentsDao);
	}
}
