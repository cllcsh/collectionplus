package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.ProvinceDao;
import com.osource.module.fav.model.ProvinceInfo;
import com.osource.module.fav.service.ProvinceService;

@Service
@Scope("prototype")
@Transactional
public class ProvinceServiceImpl extends BaseServiceImpl<ProvinceInfo> implements ProvinceService {

	/** setter and getter methods **/
	
	protected ProvinceDao getDao() {
		return (ProvinceDao)super.getDao();
	}

	@Autowired
	public void setDao(ProvinceDao provinceDao) {
		super.setDao(provinceDao);
	}
}
