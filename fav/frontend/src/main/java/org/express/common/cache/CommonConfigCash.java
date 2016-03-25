package org.express.common.cache;

import org.apache.commons.lang.StringUtils;
import org.express.portal.PathUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bruce on 2016/3/3.
 */
public class CommonConfigCash {

    private static Map<String,String> cash = new HashMap<String, String>();

    private static Properties pathProperties;

    static
    {
        try
        {
            pathProperties = new Properties();
            pathProperties.load(PathUtil.class.getResourceAsStream("/config/common.properties"));
            for (Object key : pathProperties.keySet())
            {
                String skey = (String) key;
                cash.put(skey,pathProperties.getProperty(skey));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static final String getValue(String key){
        if(StringUtils.isNotEmpty(key)){
            return cash.get(key);
        }
        return null;
    }

}
