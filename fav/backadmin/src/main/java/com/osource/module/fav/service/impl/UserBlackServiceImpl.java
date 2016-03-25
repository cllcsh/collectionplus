package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.UserBlackDao;
import com.osource.module.fav.model.UserBlackInfo;
import com.osource.module.fav.service.UserBlackService;

@Service
@Scope("prototype")
@Transactional
public class UserBlackServiceImpl extends BaseServiceImpl<UserBlackInfo> implements UserBlackService {

	/** setter and getter methods **/
	
	protected UserBlackDao getDao() {
		return (UserBlackDao)super.getDao();
	}

	@Autowired
	public void setDao(UserBlackDao userBlackDao) {
		super.setDao(userBlackDao);
	}
}
