/**
 * @author luoj
 * @create 2009-6-14
 * @file ConstantsInitializer.java
 * @since v0.1
 * 
 */
package com.osource.base.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.Constants;
import com.osource.core.init.ServletInitializer;

/**
 * @author luoj
 *
 */
public class ConstantsInitializer implements ServletInitializer {
	private static final Log logger = LogFactory.getLog(ConstantsInitializer.class);
	/* (non-Javadoc)
	 * @see com.osource.base.web.init.ServletInitializer#initialize(javax.servlet.ServletConfig)
	 */
	public void initialize(ServletContextEvent sce) throws ServletException {
		String rootpath  = sce.getServletContext().getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		Constants.ROOTPATH = rootpath;
		logger.info("Application Run Path:" + rootpath);
	}

}
