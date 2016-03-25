package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionDynamicLiveInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDynamicLiveDao;

@Repository
public class AuctionDynamicLiveDaoImpl extends BaseDaoImpl<AuctionDynamicLiveInfo> implements AuctionDynamicLiveDao {
	@Override
	public String getEntityName() {
		return "fav_auctionDynamicLive";
	}
	
}