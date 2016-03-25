package com.front.fa.service;

import java.util.List;

import com.front.db.bean.CollectionBean;
import com.front.db.bean.DynamicBean;
import com.front.user.service.UserService;
import com.front.web.common.Constant;
import com.front.web.framework.database.BaseDao;

public class FaService {

	public static boolean saveCollection(CollectionBean collectionBean, List<Object> imagesList) throws Exception
	{
		boolean result = BaseDao.insert(collectionBean);
		if (result)
		{
			result = BaseDao.insertBatch(imagesList);
			if (result)
			{
				UserService.updateUserPoints(collectionBean.getInsert_id(), Constant.TASK_TYPE_COLLECTION);
				UserService.addUserPointsRecord(collectionBean.getInsert_id(), Constant.TASK_TYPE_COLLECTION);
			}
		}
		return result;
	}
	
	public static boolean saveDynamic(DynamicBean dynamicBean, List<Object> imagesList) throws Exception
	{
		boolean result = BaseDao.insert(dynamicBean);
		if (result)
		{
			result = BaseDao.insertBatch(imagesList);
			if (result)
			{
				UserService.updateUserPoints(dynamicBean.getInsert_id(), Constant.TASK_TYPE_DYNAMIC);
				UserService.addUserPointsRecord(dynamicBean.getInsert_id(), Constant.TASK_TYPE_DYNAMIC);
			}
		}
		return result;
	}
	
}
