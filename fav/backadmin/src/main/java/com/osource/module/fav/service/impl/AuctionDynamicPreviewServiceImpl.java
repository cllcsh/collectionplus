package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDynamicPreviewDao;
import com.osource.module.fav.model.AuctionDynamicPreviewInfo;
import com.osource.module.fav.service.AuctionDynamicPreviewService;

@Service
@Scope("prototype")
@Transactional
public class AuctionDynamicPreviewServiceImpl extends BaseServiceImpl<AuctionDynamicPreviewInfo> implements AuctionDynamicPreviewService {

	/** setter and getter methods **/
	
	protected AuctionDynamicPreviewDao getDao() {
		return (AuctionDynamicPreviewDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDynamicPreviewDao auctionDynamicPreviewDao) {
		super.setDao(auctionDynamicPreviewDao);
	}
}
