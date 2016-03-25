package com.osource.base.web.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.osource.base.common.location.LocationQueue;
import com.osource.core.init.ServletInitializer;

/**
 * @author : FengJingzhun
 * @version : 1.0
 * @date : 2009-6-18 11:24:18
 */
public class LocationQueueInitializer implements ServletInitializer
{
    private static final Log logger = LogFactory.getLog(LocationQueueInitializer.class);

    public void initialize(ServletContextEvent sce) throws ServletException
    {
        try
        {
            logger.info("####### 启动定位引擎....");
            new LocationQueue().start();
            logger.info("####### 启动定位引擎 成功");
        }
        catch (Exception e)
        {
            logger.info("####### 启动定位引擎 失败");
            e.printStackTrace();
        }
    }
}
