package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.HeatDao;
import com.osource.module.fav.model.HeatInfo;
import com.osource.module.fav.service.HeatService;

@Service
@Scope("prototype")
@Transactional
public class HeatServiceImpl extends BaseServiceImpl<HeatInfo> implements HeatService {

	/** setter and getter methods **/
	
	protected HeatDao getDao() {
		return (HeatDao)super.getDao();
	}

	@Autowired
	public void setDao(HeatDao heatDao) {
		super.setDao(heatDao);
	}
}
