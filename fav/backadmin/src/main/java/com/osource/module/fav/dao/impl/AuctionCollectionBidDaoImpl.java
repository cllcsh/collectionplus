package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionCollectionBidInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionCollectionBidDao;

@Repository
public class AuctionCollectionBidDaoImpl extends BaseDaoImpl<AuctionCollectionBidInfo> implements AuctionCollectionBidDao {
	@Override
	public String getEntityName() {
		return "fav_auctionCollectionBid";
	}
	
}