package com.osource.module.fav.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.fav.model.DynamicCommentsLikeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicCommentsLikeDao;

@Repository
public class DynamicCommentsLikeDaoImpl extends BaseDaoImpl<DynamicCommentsLikeInfo> implements DynamicCommentsLikeDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicCommentsLike";
	}
	
	public void updateCommentLikeAndOpposeSize(int commentId, int likeSize, int opposeSize) throws IctException{
		Map map = new HashMap();
		map.put("commentId", commentId);
		map.put("likeSize", likeSize);
		map.put("opposeSize", opposeSize);
		update("fav_comment_update_like_opppse", map);
	}
}