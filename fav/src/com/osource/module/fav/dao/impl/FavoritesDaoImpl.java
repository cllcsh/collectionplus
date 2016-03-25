package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.FavoritesInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.FavoritesDao;

@Repository
public class FavoritesDaoImpl extends BaseDaoImpl<FavoritesInfo> implements FavoritesDao {
	@Override
	public String getEntityName() {
		return "fav_favorites";
	}
	
}