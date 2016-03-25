package com.osource.module.fav.dao.impl;

import org.springframework.stereotype.Repository;

import com.osource.module.fav.model.AuctionDynamicsTypeInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.module.fav.dao.AuctionDynamicsTypeDao;

@Repository
public class AuctionDynamicsTypeDaoImpl extends BaseDaoImpl<AuctionDynamicsTypeInfo> implements AuctionDynamicsTypeDao {
	@Override
	public String getEntityName() {
		return "fav_auctionDynamicsType";
	}
	
}