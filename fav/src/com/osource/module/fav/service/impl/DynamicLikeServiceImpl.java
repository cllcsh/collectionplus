package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.DynamicLikeDao;
import com.osource.module.fav.model.DynamicLikeInfo;
import com.osource.module.fav.service.DynamicLikeService;

@Service
@Scope("prototype")
@Transactional
public class DynamicLikeServiceImpl extends BaseServiceImpl<DynamicLikeInfo> implements DynamicLikeService {

	/** setter and getter methods **/
	
	protected DynamicLikeDao getDao() {
		return (DynamicLikeDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicLikeDao dynamicLikeDao) {
		super.setDao(dynamicLikeDao);
	}
}
