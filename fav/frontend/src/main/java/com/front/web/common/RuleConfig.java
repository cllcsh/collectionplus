package com.front.web.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.express.portal.PathUtil;
import org.express.util.Convert;

public class RuleConfig {
	
	private static Properties roleProperties;
	
	private static Map<String, String> notCheckUrlMap = new HashMap<String, String>();
	
	private static RuleConfig instance = new RuleConfig();
	
	public static RuleConfig getInstance() {
		return instance;
	}
	
	public static Properties getPathProperties() {
		return roleProperties;
	}

	public static void setPathProperties(Properties pathProperties) {
		RuleConfig.roleProperties = pathProperties;
	}
	
	public String getRule(String key)
	{
		return getRule(key, null);
	}
	
	public String getRule(String key , String defaultValue)
	{
		return Convert.toString(roleProperties.getProperty(key,defaultValue));
	}

	public int getPageSize(String key)
	{
	    int pageSize = 10;
	    String value = getRule(key, null);
	    if (StringUtils.isNotBlank(value))
	    {
	       try
	       {
	           pageSize = Integer.valueOf(value);	           
	       }
	       catch (NumberFormatException e) {
           }
	    }
	    
	    return pageSize;
	}
	
	public static void setNotCheckUrlMap()
	{
		String notCheckUrl = Convert.toString(roleProperties.getProperty(Constant.NOT_CHECK_URL, null));
		if (StringUtils.isNotBlank(notCheckUrl))
		{
			String[] notCheckUrls =  notCheckUrl.split(",");
			for(String url : notCheckUrls)
			{
				notCheckUrlMap.put(url, null);
			}			
		}
	}
	
	public Map<String, String> getNotCheckUrlMap()
	{
		return notCheckUrlMap;
	}
	
	
	static
	{
		try
		{
			roleProperties = new Properties();
			roleProperties.load(PathUtil.class.getResourceAsStream("/config/rule.properties"));
			setNotCheckUrlMap();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
