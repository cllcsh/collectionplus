package com.osource.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

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
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		List<String> list1 = new ArrayList<String>();
		list1.add("2");
		list1.add("3");
		list1.add("4");
		Set<String> set = new HashSet<String>(list);
		Set<String> set1 = new HashSet<String>(list1);
		set.retainAll(list1);
		System.out.println(set);
		System.out.println(list);
		System.out.println(list1);
 		
	}
}
