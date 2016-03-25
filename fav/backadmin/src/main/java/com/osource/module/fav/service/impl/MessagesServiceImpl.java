package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.MessagesDao;
import com.osource.module.fav.model.MessagesInfo;
import com.osource.module.fav.service.MessagesService;

@Service
@Scope("prototype")
@Transactional
public class MessagesServiceImpl extends BaseServiceImpl<MessagesInfo> implements MessagesService {

	/** setter and getter methods **/
	
	protected MessagesDao getDao() {
		return (MessagesDao)super.getDao();
	}

	@Autowired
	public void setDao(MessagesDao messagesDao) {
		super.setDao(messagesDao);
	}
}
