package com.osource.module.map.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.module.map.dao.AreaTimeDao;
import com.osource.module.map.model.AreaTimeBean;
import com.osource.module.map.service.AreaTimeService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class AreaTimeServiceImpl extends BaseServiceImpl<AreaTimeBean> implements AreaTimeService {

	/** setter and getter methods **/
	
	protected AreaTimeDao getDao() {
		return (AreaTimeDao)super.getDao();
	}

	@Autowired
	public void setDao(AreaTimeDao areaTimeDao) {
		super.setDao(areaTimeDao);
	}
}
