package com.osource.module.fav.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.FavUserDao;
import com.osource.module.fav.model.FavUserInfo;
import com.osource.module.fav.service.FavUserService;

@Service
@Scope("prototype")
@Transactional
public class FavUserServiceImpl extends BaseServiceImpl<FavUserInfo> implements FavUserService {

	/** setter and getter methods **/
	
	protected FavUserDao getDao() {
		return (FavUserDao)super.getDao();
	}

	@Autowired
	public void setDao(FavUserDao favUserDao) {
		super.setDao(favUserDao);
	}
	
	public boolean isExist(String account) {
		Map map = new HashMap();
		map.put("account", account);
		long count = getDao().countByCondition("fav_favUser_countAllByCondition", map);
		return count > 0;
	}
}
