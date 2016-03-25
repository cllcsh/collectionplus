package com.osource.module.system.dao;

import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.AttachmentBean;
import com.osource.orm.ibatis.BaseDao;

public interface AttachmentDao extends BaseDao<AttachmentBean> {
	public void addAtt(Map condition) throws IctException;
}