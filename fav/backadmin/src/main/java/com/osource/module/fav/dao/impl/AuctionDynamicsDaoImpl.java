package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDynamicsDao;

@Repository
public class AuctionDynamicsDaoImpl extends BaseDaoImpl<AuctionDynamicsInfo> implements AuctionDynamicsDao {
	@Override
	public String getEntityName() {
		return "fav_auctionDynamics";
	}
	
}