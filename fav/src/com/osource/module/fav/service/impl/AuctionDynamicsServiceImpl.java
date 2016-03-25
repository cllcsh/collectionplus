package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDynamicsDao;
import com.osource.module.fav.model.AuctionDynamicsInfo;
import com.osource.module.fav.service.AuctionDynamicsService;

@Service
@Scope("prototype")
@Transactional
public class AuctionDynamicsServiceImpl extends BaseServiceImpl<AuctionDynamicsInfo> implements AuctionDynamicsService {

	/** setter and getter methods **/
	
	protected AuctionDynamicsDao getDao() {
		return (AuctionDynamicsDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDynamicsDao auctionDynamicsDao) {
		super.setDao(auctionDynamicsDao);
	}
}
