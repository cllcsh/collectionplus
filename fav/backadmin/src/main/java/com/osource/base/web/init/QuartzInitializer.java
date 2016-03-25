package com.osource.base.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.core.init.ServletInitializer;

/**
 * @author lrb
 *
 */
public class QuartzInitializer implements ServletInitializer {
	private static final Log logger = LogFactory.getLog(QuartzInitializer.class);

	/* (non-Javadoc)
	 * @see com.osource.base.web.init.ServletInitializer#initialize(javax.servlet.ServletContextEvent)
	 */
	public void initialize(ServletContextEvent sce) throws ServletException {
		logger.info("启动矫正对象月统计定时任务...");
	}
	
}
