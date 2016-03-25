package com.osource.module.fav.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.CollectionCommentsDao;
import com.osource.module.fav.model.CollectionCommentsInfo;
import com.osource.module.fav.service.CollectionCommentsService;

@Service
@Scope("prototype")
@Transactional
public class CollectionCommentsServiceImpl extends BaseServiceImpl<CollectionCommentsInfo> implements CollectionCommentsService {

	/** setter and getter methods **/
	
	protected CollectionCommentsDao getDao() {
		return (CollectionCommentsDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionCommentsDao collectionCommentsDao) {
		super.setDao(collectionCommentsDao);
	}
	
	public long countAll(String sql, Object condition){
		return getDao().countByCondition(sql, condition);
	}
	
	public void updateInfo(CollectionCommentsInfo info) throws IctException{
		CollectionCommentsInfo qInfo = findById(info.getId());
		update(info);
		// 如果修改sourceId或者sourceType则需要更新对应的评论的top和like的评论来源id和评论来源类型
		if (info.getSourceId() != qInfo.getSourceId() || !info.getSourceType().equals(qInfo.getSourceType())){
			Map map = new HashMap();
			map.put("sourceId", info.getSourceId());
			map.put("sourceType", info.getSourceType());
			map.put("commentId", info.getId());
			getDao().updateLike(map);
			getDao().updateTop(map);
		}

	}
}
