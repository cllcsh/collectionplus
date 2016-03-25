package com.osource.module.fav.dao;

import com.osource.orm.ibatis.BaseDao;
import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentTopInfo;

public interface DynamicCommentTopDao extends BaseDao<DynamicCommentTopInfo> {
	void updateCommentTopSize(int size, int commentId) throws IctException;
}