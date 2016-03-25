package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionDynamicPreviewInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDynamicPreviewDao;

@Repository
public class AuctionDynamicPreviewDaoImpl extends BaseDaoImpl<AuctionDynamicPreviewInfo> implements AuctionDynamicPreviewDao {
	@Override
	public String getEntityName() {
		return "fav_auctionDynamicPreview";
	}
	
}