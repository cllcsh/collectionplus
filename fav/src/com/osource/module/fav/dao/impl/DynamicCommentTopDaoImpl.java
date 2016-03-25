package com.osource.module.fav.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentTopInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicCommentTopDao;

@Repository
public class DynamicCommentTopDaoImpl extends BaseDaoImpl<DynamicCommentTopInfo> implements DynamicCommentTopDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicCommentTop";
	}
	
	public void updateCommentTopSize(int size, int commentId) throws IctException{
		Map condition = new HashMap();
		condition.put("size", size);
		condition.put("commentId", commentId);
		update("fav_comment_update_top", condition);
	}
}