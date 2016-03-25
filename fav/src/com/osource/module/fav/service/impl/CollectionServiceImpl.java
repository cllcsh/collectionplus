package com.osource.module.fav.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.util.StringUtil;
import com.osource.core.exception.IctException;
import com.osource.module.fav.dao.CollectionDao;
import com.osource.module.fav.model.CollectionImagesInfo;
import com.osource.module.fav.model.CollectionInfo;
import com.osource.module.fav.service.CollectionImagesService;
import com.osource.module.fav.service.CollectionService;
import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.util.Cons;

@Service
@Scope("prototype")
@Transactional
public class CollectionServiceImpl extends BaseServiceImpl<CollectionInfo> implements CollectionService {

	@Autowired
	private CollectionImagesService collectionImagesService;
	
	/** setter and getter methods **/
	
	protected CollectionDao getDao() {
		return (CollectionDao)super.getDao();
	}

	@Autowired
	public void setDao(CollectionDao collectionDao) {
		super.setDao(collectionDao);
	}
	
	public void save(CollectionInfo collectionInfo, List<String> imgs) throws IctException{
		getDao().save(collectionInfo);
		int size = imgs.size();
		if (null != imgs && size != 0){
			int index = 1;
			for (String img : imgs) {
				if (StringUtil.isEmptyOrNull(img)){
					continue;
				}
				CollectionImagesInfo info = new CollectionImagesInfo();
				info.setInsertId(collectionInfo.getInsertId());
				info.setCollectionId(collectionInfo.getId());
				info.setImageUrl(img);
				info.setDisplayOrder(index);
				collectionImagesService.save(info);
				index ++;
			}
		}

	}
	
	public void update(CollectionInfo collectionInfo, List<String> imgs) throws IctException{
		update(collectionInfo);
		collectionImagesService.deleteByCollectionId(collectionInfo.getId());
		if (null != imgs && imgs.size() != 0){
			int index = 1;
			for (String img : imgs) {
				if (StringUtil.isEmptyOrNull(img)){
					continue;
				}
				CollectionImagesInfo info = new CollectionImagesInfo();
				info.setInsertId(collectionInfo.getInsertId());
				info.setCollectionId(collectionInfo.getId());
				info.setImageUrl(img);
				info.setDisplayOrder(index);
				collectionImagesService.save(info);
				index ++;
			}
		}
	}
	
	public void delete(String ids)throws IctException{
		for (String id : ids.split(",")) {
			collectionImagesService.deleteByCollectionId(Integer.valueOf(id));
		}
		deleteById(ids);
	}
}
