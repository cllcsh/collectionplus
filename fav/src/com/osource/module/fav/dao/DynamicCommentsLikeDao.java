package com.osource.module.fav.dao;

import com.osource.orm.ibatis.BaseDao;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentsLikeInfo;

public interface DynamicCommentsLikeDao extends BaseDao<DynamicCommentsLikeInfo> {
	void updateCommentLikeAndOpposeSize(int commentId, int likeSize, int opposeSize) throws IctException;
}