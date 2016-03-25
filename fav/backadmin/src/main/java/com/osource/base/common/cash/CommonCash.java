package com.osource.base.common.cash;

import com.osource.base.util.StringUtil;
import com.osource.core.PropertiesManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bruce on 2016/3/9.
 */
public class CommonCash {

    private static final Logger LOGGER = Logger.getLogger(CommonCash.class);

    private static Map<String, Object> common_cash = new HashMap<String, Object>();

    static {
        try {
            Properties pro = PropertiesManager.getProperties("common.properties");
            if (pro != null) {
                for (Map.Entry<Object, Object> entry : pro.entrySet()) {
                    common_cash.put(String.valueOf(entry.getKey()), entry.getValue());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static final String getValue(String key) {
        if (StringUtil.isNotEmpty(key)) {
            Object obj = common_cash.get(key);
            if (obj != null) {
                return String.valueOf(obj);
            }
        }
        return null;
    }

}
