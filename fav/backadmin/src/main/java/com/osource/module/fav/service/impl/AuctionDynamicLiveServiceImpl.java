package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDynamicLiveDao;
import com.osource.module.fav.model.AuctionDynamicLiveInfo;
import com.osource.module.fav.service.AuctionDynamicLiveService;

@Service
@Scope("prototype")
@Transactional
public class AuctionDynamicLiveServiceImpl extends BaseServiceImpl<AuctionDynamicLiveInfo> implements AuctionDynamicLiveService {

	/** setter and getter methods **/
	
	protected AuctionDynamicLiveDao getDao() {
		return (AuctionDynamicLiveDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDynamicLiveDao auctionDynamicLiveDao) {
		super.setDao(auctionDynamicLiveDao);
	}
}
