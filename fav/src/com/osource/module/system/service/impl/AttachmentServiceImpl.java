package com.osource.module.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.AttachmentDao;
import com.osource.module.system.model.AttachmentBean;
import com.osource.module.system.service.AttachmentService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class AttachmentServiceImpl extends BaseServiceImpl<AttachmentBean> implements AttachmentService {

	/** setter and getter methods **/
	
	protected AttachmentDao getDao() {
		return (AttachmentDao)super.getDao();
	}

	@Autowired
	public void setDao(AttachmentDao attachmentDao) {
		super.setDao(attachmentDao);
	}

	public void addAtt(Map condition) throws IctException {
		// TODO Auto-generated method stub
		getDao().addAtt(condition);
	}
}
