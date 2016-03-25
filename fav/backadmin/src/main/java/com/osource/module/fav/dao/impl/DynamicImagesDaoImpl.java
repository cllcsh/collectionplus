package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.DynamicImagesInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.DynamicImagesDao;

@Repository
public class DynamicImagesDaoImpl extends BaseDaoImpl<DynamicImagesInfo> implements DynamicImagesDao {
	@Override
	public String getEntityName() {
		return "fav_dynamicImages";
	}
	
}