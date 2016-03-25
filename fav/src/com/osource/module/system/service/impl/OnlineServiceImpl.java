package com.osource.module.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.module.system.dao.OnlineDao;
import com.osource.module.system.model.OnlineUserInfo;
import com.osource.module.system.service.OnlineService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class OnlineServiceImpl extends BaseServiceImpl<OnlineUserInfo> implements OnlineService {

	/** setter and getter methods **/
	
	protected OnlineDao getDao() {
		return (OnlineDao)super.getDao();
	}

	@Autowired
	public void setDao(OnlineDao onlineDao) {
		super.setDao(onlineDao);
	}
}
