package com.osource.module.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.system.model.NoticeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.system.dao.NoticeDao;

@Repository
public class NoticeDaoImpl extends BaseDaoImpl<NoticeInfo> implements NoticeDao {
	@Override
	public String getEntityName() {
		return "system_notice";
	}
	
}