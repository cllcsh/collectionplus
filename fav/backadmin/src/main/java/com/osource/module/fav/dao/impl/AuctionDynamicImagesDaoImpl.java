package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionDynamicImagesInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDynamicImagesDao;

@Repository
public class AuctionDynamicImagesDaoImpl extends BaseDaoImpl<AuctionDynamicImagesInfo> implements AuctionDynamicImagesDao {
	@Override
	public String getEntityName() {
		return "fav_auctionDynamicImages";
	}
	
}