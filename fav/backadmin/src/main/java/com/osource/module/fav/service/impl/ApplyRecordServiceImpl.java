package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.ApplyRecordDao;
import com.osource.module.fav.model.ApplyRecordInfo;
import com.osource.module.fav.service.ApplyRecordService;

@Service
@Scope("prototype")
@Transactional
public class ApplyRecordServiceImpl extends BaseServiceImpl<ApplyRecordInfo> implements ApplyRecordService {

	/** setter and getter methods **/
	
	protected ApplyRecordDao getDao() {
		return (ApplyRecordDao)super.getDao();
	}

	@Autowired
	public void setDao(ApplyRecordDao applyRecordDao) {
		super.setDao(applyRecordDao);
	}
}
