package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDao;

@Repository
public class AuctionDaoImpl extends BaseDaoImpl<AuctionInfo> implements AuctionDao {
	@Override
	public String getEntityName() {
		return "fav_auction";
	}
	
}