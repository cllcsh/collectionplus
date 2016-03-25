package com.front.web.util;

import java.util.Collection;
import java.util.Map;

/**
 * 判断boolean
 * @author gaoxiang
 *
 */
public final class BooleanUtil {
	public static boolean isEmpty(Object obj){
		if (null == obj){
			return true;
		}
		if (obj instanceof String){
			if ("".equals(((String) obj).trim())){
				return true;
			}
		}
		else if (obj instanceof Collection){
			if (((Collection) obj).isEmpty()){
				return true;
			}
		} else if (obj instanceof Map){
			if (((Map) obj).isEmpty()){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isVoid(String isvoid){
		if ("Y".equalsIgnoreCase(isvoid)){
			return true;
		}
		return false;
	}
	
}
