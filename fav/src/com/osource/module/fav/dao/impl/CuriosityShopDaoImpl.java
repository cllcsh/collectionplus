package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.CuriosityShopInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.CuriosityShopDao;

@Repository
public class CuriosityShopDaoImpl extends BaseDaoImpl<CuriosityShopInfo> implements CuriosityShopDao {
	@Override
	public String getEntityName() {
		return "fav_curiosityShop";
	}
	
}