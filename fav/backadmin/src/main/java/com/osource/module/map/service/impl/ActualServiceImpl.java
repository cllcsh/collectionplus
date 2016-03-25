package com.osource.module.map.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.module.map.dao.ActualDao;
import com.osource.module.map.model.ActualBean;
import com.osource.module.map.service.ActualService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class ActualServiceImpl extends BaseServiceImpl<ActualBean> implements ActualService {

	/** setter and getter methods **/
	
	protected ActualDao getDao() {
		return (ActualDao)super.getDao();
	}

	@Autowired
	public void setDao(ActualDao actualDao) {
		super.setDao(actualDao);
	}
}
