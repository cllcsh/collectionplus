package com.osource.module.fav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.module.fav.dao.AuctionDynamicsTypeDao;
import com.osource.module.fav.model.AuctionDynamicsTypeInfo;
import com.osource.module.fav.service.AuctionDynamicsTypeService;

@Service
@Scope("prototype")
@Transactional
public class AuctionDynamicsTypeServiceImpl extends BaseServiceImpl<AuctionDynamicsTypeInfo> implements AuctionDynamicsTypeService {

	/** setter and getter methods **/
	
	protected AuctionDynamicsTypeDao getDao() {
		return (AuctionDynamicsTypeDao)super.getDao();
	}

	@Autowired
	public void setDao(AuctionDynamicsTypeDao auctionDynamicsTypeDao) {
		super.setDao(auctionDynamicsTypeDao);
	}
}
