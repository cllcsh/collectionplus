package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.EnumDao;
import com.osource.module.fav.model.EnumInfo;
import com.osource.module.fav.service.EnumService;

@Service
@Scope("prototype")
@Transactional
public class EnumServiceImpl extends BaseServiceImpl<EnumInfo> implements EnumService {

	/** setter and getter methods **/
	
	protected EnumDao getDao() {
		return (EnumDao)super.getDao();
	}

	@Autowired
	public void setDao(EnumDao enumDao) {
		super.setDao(enumDao);
	}
}
