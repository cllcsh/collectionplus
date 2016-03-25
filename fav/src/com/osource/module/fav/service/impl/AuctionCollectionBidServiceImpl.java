package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionCollectionBidDao;
import com.osource.module.fav.model.AuctionCollectionBidInfo;
import com.osource.module.fav.service.AuctionCollectionBidService;

@Service
@Scope("prototype")
@Transactional
public class AuctionCollectionBidServiceImpl extends BaseServiceImpl<AuctionCollectionBidInfo> implements AuctionCollectionBidService {

	/** setter and getter methods **/
	
	protected AuctionCollectionBidDao getDao() {
		return (AuctionCollectionBidDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionCollectionBidDao auctionCollectionBidDao) {
		super.setDao(auctionCollectionBidDao);
	}
}
