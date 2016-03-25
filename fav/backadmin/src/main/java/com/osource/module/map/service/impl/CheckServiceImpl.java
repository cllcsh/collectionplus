package com.osource.module.map.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.module.map.dao.CheckDao;
import com.osource.module.map.model.CheckBean;
import com.osource.module.map.service.CheckService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class CheckServiceImpl extends BaseServiceImpl<CheckBean> implements CheckService {

	/** setter and getter methods **/
	
	protected CheckDao getDao() {
		return (CheckDao)super.getDao();
	}

	@Autowired
	public void setDao(CheckDao checkDao) {
		super.setDao(checkDao);
	}

	@Transactional(readOnly = true)
	public String findPhone(String criminalId) {
		return getDao().findPhone(criminalId);
	}
}
