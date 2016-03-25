package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.SmsDao;
import com.osource.module.fav.model.SmsInfo;
import com.osource.module.fav.service.SmsService;

@Service
@Scope("prototype")
@Transactional
public class SmsServiceImpl extends BaseServiceImpl<SmsInfo> implements SmsService {

	/** setter and getter methods **/
	
	protected SmsDao getDao() {
		return (SmsDao)super.getDao();
	}

	@Autowired
	public void setDao(SmsDao smsDao) {
		super.setDao(smsDao);
	}
}
