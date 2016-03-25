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

public class Init implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()); 
		final EnumService enumService = (EnumService)appctx.getBean("enumServiceImpl"); 
		final CollectionPeriodService collectionPeriodService = (CollectionPeriodService)appctx.getBean("collectionPeriodServiceImpl"); 
		final CollectionLableService collectionLableService = (CollectionLableService)appctx.getBean("collectionLableServiceImpl"); 
		final CollectionCategoryService collectionCategoryService = (CollectionCategoryService)appctx.getBean("collectionCategoryServiceImpl"); 
		final AuctionService auctionService = (AuctionService)appctx.getBean("auctionServiceImpl"); 
		final SpecialService specialService = (SpecialService)appctx.getBean("specialServiceImpl"); 
		final UserTitleService userTitleService = (UserTitleService)appctx.getBean("userTitleServiceImpl");
		final AuctionDynamicsTypeService auctionDynamicsTypeService = (AuctionDynamicsTypeService)appctx.getBean("auctionDynamicsTypeServiceImpl");
		CommonCache.reflashCache(enumService, collectionPeriodService, collectionLableService, 
			collectionCategoryService, auctionService, specialService, userTitleService, auctionDynamicsTypeService);
		new Thread(){
			public void run() {
				while (true) {
					try {
						CommonCache.reflashCache(enumService, collectionPeriodService, collectionLableService, 
								collectionCategoryService, auctionService, specialService, userTitleService, auctionDynamicsTypeService);
						sleep(600000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
}
