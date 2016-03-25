package org.express.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.express.common.convert.DateConvert;
import org.express.portal.ActionContext;


public abstract class ShareBaseAction 
{
	public HttpServletRequest getRequest() {
		return ActionContext.getActionContext().getHttpServletRequest();
	}

	public HttpServletResponse getResponse() {
		return ActionContext.getActionContext().getHttpServletResponse();
	}

	public ServletContext getContext() {
		return ActionContext.getActionContext().getServletContext();
	}

	public HttpSession getSession() {
		return ActionContext.getActionContext().getHttpSession();
	}
	
	/**
	 * 将参数绑定值对象
	 * @param request
	 * @param obj
	 */
	public void autoBeanParameter(HttpServletRequest request, Object obj) {
		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
		if ("GET".equals(request.getMethod())) {
			invodeParameterGetUTF8(request, obj);
		} else {
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement().toString().trim();
				String value = request.getParameter(name);
				try {
					if (null != value && !"".equals(value.trim())) {
						BeanUtils.setProperty(obj, name, value.trim());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Map<String, String> autoGenerateMap()
	{
		return autoGenerateMap(getRequest(), "utf-8");
	}
	
	public Map<String, String> autoGenerateMap(HttpServletRequest request)
	{
		return autoGenerateMap(request, "utf-8");
	}
	
	/**
	 * 将参数生成HashMap
	 * @param request
	 * @return
	 */
	public Map<String, String> autoGenerateMap(HttpServletRequest request , String code)
	{
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		
		if ("GET".equals(request.getMethod())) 
		{
			String queryString = request.getQueryString();
			if (null != queryString && !"".equals(queryString.trim())) {
				String[] result = queryString.trim().split("&");
				if (null != result && result.length > 0) {
					for (String str : result) {
						String[] arr = str.split("=");
						try 
						{
							if (null != arr && arr.length == 2) {
								map.put(arr[0].trim(), URLDecoder.decode(arr[1].trim(), code));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} 
		else 
		{
			while (paramNames.hasMoreElements()) {
				String name = paramNames.nextElement().toString().trim();
				String value = request.getParameter(name);
				map.put(name, value);
			}
		}
		return map;
	}
	
	public String generateParameterByMap(Map<String, String> map) throws UnsupportedEncodingException 
	{
		StringBuilder sb = new StringBuilder();
		String result = "";
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = iterator.next();
			sb.append(key).append("=").append(URLEncoder.encode(map.get(key),"utf-8")).append("&");
		}
		if(sb.length() > 0)
		{
			result = sb.substring(0, sb.length() - 1);
		}
		return result;
	}
	
	/**
	 * 得到Get方式提交的数据转换为UTF-8格式
	 * 
	 * @param request
	 * @param obj
	 */
	private void invodeParameterGetUTF8(HttpServletRequest request, Object obj) {
		String queryString = request.getQueryString().trim();
		if (null != queryString && !"".equals(queryString)) {
			String[] result = queryString.split("&");
			if (null != result && result.length > 0) {
				for (String str : result) {
					String[] arr = str.split("=");
					try {
						if (null != arr && arr.length == 2) {
							BeanUtils.setProperty(obj, arr[0].trim(), URLDecoder.decode(arr[1].trim(), "utf-8"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}