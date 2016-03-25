package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.FamousHomeDao;
import com.osource.module.fav.model.FamousHomeInfo;
import com.osource.module.fav.service.FamousHomeService;

@Service
@Scope("prototype")
@Transactional
public class FamousHomeServiceImpl extends BaseServiceImpl<FamousHomeInfo> implements FamousHomeService {

	/** setter and getter methods **/
	
	protected FamousHomeDao getDao() {
		return (FamousHomeDao)super.getDao();
	}

	@Autowired
	public void setDao(FamousHomeDao famousHomeDao) {
		super.setDao(famousHomeDao);
	}
}
