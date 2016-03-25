package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.MessagesInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.MessagesDao;

@Repository
public class MessagesDaoImpl extends BaseDaoImpl<MessagesInfo> implements MessagesDao {
	@Override
	public String getEntityName() {
		return "fav_messages";
	}
	
}