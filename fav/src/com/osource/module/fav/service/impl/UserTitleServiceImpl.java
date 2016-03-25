package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.UserTitleDao;
import com.osource.module.fav.model.UserTitleInfo;
import com.osource.module.fav.service.UserTitleService;

@Service
@Scope("prototype")
@Transactional
public class UserTitleServiceImpl extends BaseServiceImpl<UserTitleInfo> implements UserTitleService {

	/** setter and getter methods **/
	
	protected UserTitleDao getDao() {
		return (UserTitleDao)super.getDao();
	}

	@Autowired
	public void setDao(UserTitleDao userTitleDao) {
		super.setDao(userTitleDao);
	}
}
