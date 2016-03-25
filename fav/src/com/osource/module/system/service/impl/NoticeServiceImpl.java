package com.osource.module.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.system.dao.NoticeDao;
import com.osource.module.system.model.NoticeInfo;
import com.osource.module.system.service.NoticeService;

@Service
@Scope("prototype")
@Transactional
public class NoticeServiceImpl extends BaseServiceImpl<NoticeInfo> implements NoticeService {

	/** setter and getter methods **/
	
	protected NoticeDao getDao() {
		return (NoticeDao)super.getDao();
	}

	@Autowired
	public void setDao(NoticeDao noticeDao) {
		super.setDao(noticeDao);
	}
}
