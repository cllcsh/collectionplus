package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.CuriosityShopDao;
import com.osource.module.fav.model.CuriosityShopInfo;
import com.osource.module.fav.service.CuriosityShopService;

@Service
@Scope("prototype")
@Transactional
public class CuriosityShopServiceImpl extends BaseServiceImpl<CuriosityShopInfo> implements CuriosityShopService {

	/** setter and getter methods **/
	
	protected CuriosityShopDao getDao() {
		return (CuriosityShopDao)super.getDao();
	}

	@Autowired
	public void setDao(CuriosityShopDao curiosityShopDao) {
		super.setDao(curiosityShopDao);
	}
}
