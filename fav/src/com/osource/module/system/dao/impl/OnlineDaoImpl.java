package com.osource.module.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.system.dao.OnlineDao;
import com.osource.module.system.model.OnlineUserInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class OnlineDaoImpl extends BaseDaoImpl<OnlineUserInfo> implements OnlineDao {
	public String getEntityName() {
		return "system_online";
	}
	
}