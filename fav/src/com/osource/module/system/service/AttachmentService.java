package com.osource.module.system.service;

import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.AttachmentBean;
import com.osource.orm.ibatis.BaseService;

public interface AttachmentService extends BaseService<AttachmentBean> {
	public void addAtt(Map condition) throws IctException;
}