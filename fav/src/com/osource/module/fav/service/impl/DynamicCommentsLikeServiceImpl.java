package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.DynamicCommentsLikeDao;
import com.osource.module.fav.model.DynamicCommentsLikeInfo;
import com.osource.module.fav.service.DynamicCommentsLikeService;

@Service
@Scope("prototype")
@Transactional
public class DynamicCommentsLikeServiceImpl extends BaseServiceImpl<DynamicCommentsLikeInfo> implements DynamicCommentsLikeService {

	/** setter and getter methods **/
	
	protected DynamicCommentsLikeDao getDao() {
		return (DynamicCommentsLikeDao)super.getDao();
	}

	@Autowired
	public void setDao(DynamicCommentsLikeDao dynamicCommentsLikeDao) {
		super.setDao(dynamicCommentsLikeDao);
	}
	
	public void updateCommentLikeAndOpposeSize(int commentId, int likeSize, int opposeSize) throws IctException{
		getDao().updateCommentLikeAndOpposeSize(commentId, likeSize, opposeSize);
	}
}
