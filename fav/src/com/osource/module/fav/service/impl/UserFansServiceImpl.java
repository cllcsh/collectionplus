package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.UserFansDao;
import com.osource.module.fav.model.UserFansInfo;
import com.osource.module.fav.service.UserFansService;

@Service
@Scope("prototype")
@Transactional
public class UserFansServiceImpl extends BaseServiceImpl<UserFansInfo> implements UserFansService {

	/** setter and getter methods **/
	
	protected UserFansDao getDao() {
		return (UserFansDao)super.getDao();
	}

	@Autowired
	public void setDao(UserFansDao userFansDao) {
		super.setDao(userFansDao);
	}
}
