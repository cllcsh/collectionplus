package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.FavUserSetDao;
import com.osource.module.fav.model.FavUserSetInfo;
import com.osource.module.fav.service.FavUserSetService;

@Service
@Scope("prototype")
@Transactional
public class FavUserSetServiceImpl extends BaseServiceImpl<FavUserSetInfo> implements FavUserSetService {

	/** setter and getter methods **/
	
	protected FavUserSetDao getDao() {
		return (FavUserSetDao)super.getDao();
	}

	@Autowired
	public void setDao(FavUserSetDao favUserSetDao) {
		super.setDao(favUserSetDao);
	}
}
