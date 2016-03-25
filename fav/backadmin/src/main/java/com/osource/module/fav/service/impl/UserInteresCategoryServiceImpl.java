package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.UserInteresCategoryDao;
import com.osource.module.fav.model.UserInteresCategoryInfo;
import com.osource.module.fav.service.UserInteresCategoryService;

@Service
@Scope("prototype")
@Transactional
public class UserInteresCategoryServiceImpl extends BaseServiceImpl<UserInteresCategoryInfo> implements UserInteresCategoryService {

	/** setter and getter methods **/
	
	protected UserInteresCategoryDao getDao() {
		return (UserInteresCategoryDao)super.getDao();
	}

	@Autowired
	public void setDao(UserInteresCategoryDao userInteresCategoryDao) {
		super.setDao(userInteresCategoryDao);
	}
}
