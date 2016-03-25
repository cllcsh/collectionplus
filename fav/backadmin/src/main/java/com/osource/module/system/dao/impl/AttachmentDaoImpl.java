package com.osource.module.system.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.AttachmentDao;
import com.osource.module.system.model.AttachmentBean;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class AttachmentDaoImpl extends BaseDaoImpl<AttachmentBean> implements AttachmentDao {
	@Override
	public String getEntityName() {
		return "system_attachment";
	}

	public void addAtt(Map condition) throws IctException {
		// TODO Auto-generated method stub
		update("system_attachment_add", condition);
	}
	
}