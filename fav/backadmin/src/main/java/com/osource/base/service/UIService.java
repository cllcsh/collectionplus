/**
 * 
 */
package com.osource.base.service;

import java.util.List;
import java.util.Map;

import com.osource.util.Entry;

/**
 * 该Service用来访问ui中的一些公共接口，如关联下拉框列表
 * @author Fun,2010-3-10
 */
public interface UIService {
	public List<Entry> getSelectList(String beanId, Map condition);
}
