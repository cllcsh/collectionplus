/**
 * @author luoj
 * @create 2009-6-14
 * @file SMSQueueInitializer.java
 * @since v0.1
 * 
 */
package com.osource.base.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.core.init.ServletInitializer;
import com.osource.sms.SMSQueue;

/**
 * @author luoj
 *
 */
public class SMSQueueInitializer implements ServletInitializer {
	private static final Log logger = LogFactory.getLog(SMSQueueInitializer.class);
	/* (non-Javadoc)
	 * @see com.osource.base.web.init.ServletInitializer#initialize(javax.servlet.ServletConfig)
	 */
	public void initialize(ServletContextEvent sce) throws ServletException {
		try {
            logger.info("####### 启动短息引擎....");
            new SMSQueue().start();
            logger.info("####### 启动短息引擎 成功");
        } catch (Exception e) {
            logger.info("####### 启动短息引擎 失败");
            e.printStackTrace();
        }
	}

}
