package com.osource.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.osource.cache.CommonCache;
import com.osource.module.fav.service.AuctionDynamicsTypeService;
import com.osource.module.fav.service.AuctionService;
import com.osource.module.fav.service.CollectionCategoryService;
import com.osource.module.fav.service.CollectionLableService;
import com.osource.module.fav.service.CollectionPeriodService;
import com.osource.module.fav.service.EnumService;
import com.osource.module.fav.service.SpecialService;
import com.osource.module.fav.service.UserTitleService;

public class CopyOfInit implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()); 
		EnumService enumService = (EnumService)appctx.getBean("enumServiceImpl"); 
		CollectionPeriodService collectionPeriodService = (CollectionPeriodService)appctx.getBean("collectionPeriodServiceImpl"); 
		CollectionLableService collectionLableService = (CollectionLableService)appctx.getBean("collectionLableServiceImpl"); 
		CollectionCategoryService collectionCategoryService = (CollectionCategoryService)appctx.getBean("collectionCategoryServiceImpl"); 
		AuctionService auctionService = (AuctionService)appctx.getBean("auctionServiceImpl"); 
		SpecialService specialService = (SpecialService)appctx.getBean("specialServiceImpl"); 
		UserTitleService userTitleService = (UserTitleService)appctx.getBean("userTitleServiceImpl");
		AuctionDynamicsTypeService auctionDynamicsTypeService = (AuctionDynamicsTypeService)appctx.getBean("auctionDynamicsTypeServiceImpl");
		CommonCache.reflashCache(enumService, collectionPeriodService, collectionLableService, 
			collectionCategoryService, auctionService, specialService, userTitleService, auctionDynamicsTypeService);
	}
}
