package com.osource.base.dao;

import java.util.List;
import java.util.Map;

import com.osource.util.Entry;

/**
 * @author Fun
 * 该Action用来处理下拉列表的生成
 */
public interface SelectUIDao {
	
	/**
	 * getSelectList
	 * <p>获取下拉数据列表
	 * <p>@param  
	 * <p>@param 
	 * <p>@return  
	 * <p>@Exception
	 */
	public List<Entry> getSelectList(Map condition);
	
}
