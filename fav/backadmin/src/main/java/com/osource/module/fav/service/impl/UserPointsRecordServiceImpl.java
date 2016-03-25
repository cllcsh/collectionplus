package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.UserPointsRecordDao;
import com.osource.module.fav.model.UserPointsRecordInfo;
import com.osource.module.fav.service.UserPointsRecordService;

@Service
@Scope("prototype")
@Transactional
public class UserPointsRecordServiceImpl extends BaseServiceImpl<UserPointsRecordInfo> implements UserPointsRecordService {

	/** setter and getter methods **/
	
	protected UserPointsRecordDao getDao() {
		return (UserPointsRecordDao)super.getDao();
	}

	@Autowired
	public void setDao(UserPointsRecordDao userPointsRecordDao) {
		super.setDao(userPointsRecordDao);
	}
}
