package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDao;
import com.osource.module.fav.model.AuctionInfo;
import com.osource.module.fav.service.AuctionService;

@Service
@Scope("prototype")
@Transactional
public class AuctionServiceImpl extends BaseServiceImpl<AuctionInfo> implements AuctionService {

	/** setter and getter methods **/
	
	protected AuctionDao getDao() {
		return (AuctionDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDao auctionDao) {
		super.setDao(auctionDao);
	}
}
