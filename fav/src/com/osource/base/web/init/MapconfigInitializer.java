/**
 * @author luoj
 * @create 2009-6-14
 * @file MapconfigInitializer.java
 * @since v0.1
 * 
 */
package com.osource.base.web.init;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.core.PropertiesManager;
import com.osource.core.init.ServletInitializer;

/**
 * @author luoj
 *
 */
public class MapconfigInitializer implements ServletInitializer {
	private static final Log logger = LogFactory.getLog(MapconfigInitializer.class);
	/* (non-Javadoc)
	 * @see com.osource.base.web.init.ServletInitializer#initialize(javax.servlet.ServletContextEvent)
	 */
	public void initialize(ServletContextEvent sce) throws ServletException {
		String mapconfigFile = sce.getServletContext().getInitParameter("mapconfig");
		
		try {
			PropertiesManager.load(mapconfigFile);
			Properties props = PropertiesManager.getProperties(mapconfigFile);
			sce.getServletContext().setAttribute("LBS_MAP_SERVER", props.getProperty("LBS_MAP_SERVER", ""));
			sce.getServletContext().setAttribute("LBS_MAP_SRC", props.getProperty("LBS_MAP_SRC"));
            String centerx=props.getProperty("CENTER_LATITUDES");
            String centery=props.getProperty("CENTER_LONGITUDES");
            String scale=props.getProperty("SCALE");
            sce.getServletContext().setAttribute("CENTER_LATITUDES", centerx);
            sce.getServletContext().setAttribute("CENTER_LONGITUDES", centery);
            sce.getServletContext().setAttribute("SCALE", scale);
            sce.getServletContext().setAttribute("bordercenter", centerx+":"+centery+":"+scale);
            String userinfo=props.getProperty("USER_INFO");
            sce.getServletContext().setAttribute("userinfo", userinfo);
		} catch (IOException e) {
			logger.debug("加载属性文件 " + mapconfigFile + " 失败");
			e.printStackTrace();
		}
	}
}
