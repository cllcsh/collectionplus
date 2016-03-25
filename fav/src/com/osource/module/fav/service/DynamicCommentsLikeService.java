package com.osource.module.fav.service;

import com.osource.orm.ibatis.BaseService;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentsLikeInfo;

public interface DynamicCommentsLikeService extends BaseService<DynamicCommentsLikeInfo> {
	void updateCommentLikeAndOpposeSize(int commentId, int likeSize, int opposeSize) throws IctException;
}