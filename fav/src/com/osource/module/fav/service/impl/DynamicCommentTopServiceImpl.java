package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.DynamicCommentTopDao;
import com.osource.module.fav.model.DynamicCommentTopInfo;
import com.osource.module.fav.service.DynamicCommentTopService;

@Service
@Scope("prototype")
@Transactional
public class DynamicCommentTopServiceImpl extends BaseServiceImpl<DynamicCommentTopInfo> implements DynamicCommentTopService {

	/** setter and getter methods **/
	
	protected DynamicCommentTopDao getDao() {
		return (DynamicCommentTopDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicCommentTopDao dynamicCommentTopDao) {
		super.setDao(dynamicCommentTopDao);
	}
	
	public void updateCommentTopSize(int size, int commentId) throws IctException{
		getDao().updateCommentTopSize(size, commentId);
	}
}
