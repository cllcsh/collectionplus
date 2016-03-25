/**
 * @author luoj
 * @create 2009-3-23
 * @file IctmapUtil.java
 * @since v0.1
 * 
 */
package com.osource.base.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.osource.base.Constants;

/**
 *
 */
public class IctmapUtil {

	/**
	 * 获取web请求的实际根路径
	 * @param request
	 * @return
	 */
	public static String getWebRealPath(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("http://");
		sb.append(request.getServerName());
		if (request.getServerPort() != 80) {
			sb.append(":");
			sb.append(request.getServerPort());
		}
		return sb.toString();
	}

	/**
	 * 获取action请求的路径和参数
	 * @param action
	 * @return
	 */
	public static String getActionMappingURLWithoutPrefix(String action) {

		StringBuffer value = new StringBuffer();

		// Use our servlet mapping, if one is specified
		String servletMapping = Constants.SERVLET_MAPPING;
		if (servletMapping != null) {

			String queryString = null;
			int question = action.indexOf("?");
			if (question >= 0) {
				queryString = action.substring(question);
			}
			String actionMapping = getActionMappingNameWithoutPrefix(action);
			if (servletMapping.startsWith("*.")) {
				value.append(actionMapping);
				value.append(servletMapping.substring(1));
			} else if (servletMapping.endsWith("/*")) {
				value.append(servletMapping.substring(0, servletMapping.length() - 2));
				value.append(actionMapping);
			} else if (servletMapping.equals("/")) {
				value.append(actionMapping);
			}
			if (queryString != null) {
				value.append(queryString);
			}
		}
		return (value.toString());
	}
	
	/**
	 * 获取action请求的action名
	 * @param action
	 * @return
	 */
	public static String getActionMappingNameWithoutPrefix(String action) {
		String value = action;
		int question = action.indexOf("?");
		if (question >= 0) {
			value = value.substring(0, question);
		}
		int slash = value.lastIndexOf("/");
		int period = value.lastIndexOf(".");
		if ((period >= 0) && (period > slash)) {
			value = value.substring(0, period);
		}
		return (value);
	}
	
	/**
	 * 根据查询字符串，得到参数Map.
	 * added by luoj,modified by lifa.2010-3-25
	 * @param queryString
	 * @return
	 */
	public static Map buildQueryStringToMap(String queryString){
		if(queryString.indexOf("?")>=0)
			 queryString = queryString.substring(queryString.indexOf("?")+1);
		
		Map queryParams = new LinkedHashMap();
        if (queryString != null) {
            String[] params = queryString.split("&");
            for (int a=0; a< params.length; a++) {
                if (params[a].trim().length() > 0) {
                    String[] tmpParams = params[a].split("=");
                    String paramName = null;
                    String paramValue = "";
                    if (tmpParams.length > 0) {
                        paramName = tmpParams[0];
                    }
                    if (tmpParams.length > 1) {
                        paramValue = tmpParams[1];
                    }
                    if (paramName != null) {
                        String translatedParamValue = paramValue;

                        if(queryParams.containsKey(paramName)) {
                            // WW-1619 append new param value to existing value(s)
                            Object currentParam = queryParams.get(paramName);
                            if(currentParam instanceof String) {
                                queryParams.put(paramName, new String[] {
                                        (String) currentParam, translatedParamValue});
                            } else {
                                String currentParamValues[] = (String[]) currentParam;
                                if (currentParamValues != null) {
                                    List paramList = new ArrayList(Arrays
                                        .asList(currentParamValues));
                                    paramList.add(translatedParamValue);
                                    String newParamValues[] = new String[paramList
                                        .size()];
                                    queryParams.put(paramName, paramList
                                        .toArray(newParamValues));
                                } else {
                                    queryParams.put(paramName, new String[] {translatedParamValue});
                                }
                            }
                        } else {
                            queryParams.put(paramName, translatedParamValue);
                        }
                    }
                }
            }
        }
        return queryParams;
	}
}
