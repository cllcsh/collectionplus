package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.SpecialDao;
import com.osource.module.fav.model.SpecialInfo;
import com.osource.module.fav.service.SpecialService;

@Service
@Scope("prototype")
@Transactional
public class SpecialServiceImpl extends BaseServiceImpl<SpecialInfo> implements SpecialService {

	/** setter and getter methods **/
	
	protected SpecialDao getDao() {
		return (SpecialDao)super.getDao();
	}

	@Autowired
	public void setDao(SpecialDao specialDao) {
		super.setDao(specialDao);
	}
}
