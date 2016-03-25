package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDynamicImagesDao;
import com.osource.module.fav.model.AuctionDynamicImagesInfo;
import com.osource.module.fav.service.AuctionDynamicImagesService;

@Service
@Scope("prototype")
@Transactional
public class AuctionDynamicImagesServiceImpl extends BaseServiceImpl<AuctionDynamicImagesInfo> implements AuctionDynamicImagesService {

	/** setter and getter methods **/
	
	protected AuctionDynamicImagesDao getDao() {
		return (AuctionDynamicImagesDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDynamicImagesDao auctionDynamicImagesDao) {
		super.setDao(auctionDynamicImagesDao);
	}
}
