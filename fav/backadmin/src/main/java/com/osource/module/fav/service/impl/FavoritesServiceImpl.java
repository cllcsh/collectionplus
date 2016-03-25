package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.FavoritesDao;
import com.osource.module.fav.model.FavoritesInfo;
import com.osource.module.fav.service.FavoritesService;

@Service
@Scope("prototype")
@Transactional
public class FavoritesServiceImpl extends BaseServiceImpl<FavoritesInfo> implements FavoritesService {

	/** setter and getter methods **/
	
	protected FavoritesDao getDao() {
		return (FavoritesDao)super.getDao();
	}

	@Autowired
	public void setDao(FavoritesDao favoritesDao) {
		super.setDao(favoritesDao);
	}
}
