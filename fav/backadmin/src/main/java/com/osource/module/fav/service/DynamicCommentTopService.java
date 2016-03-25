package com.osource.module.fav.service;

import com.osource.orm.ibatis.BaseService;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentTopInfo;

public interface DynamicCommentTopService extends BaseService<DynamicCommentTopInfo> {
	void updateCommentTopSize(int size, int commentId) throws IctException;
}