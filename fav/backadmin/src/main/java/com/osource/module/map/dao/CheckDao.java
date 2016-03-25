package com.osource.module.map.dao;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.module.map.model.CheckBean;
import com.osource.orm.ibatis.BaseDao;

public interface CheckDao extends BaseDao<CheckBean> {
	public List<Entry<String, String>> getCriminalSelectList(int userId);
	
	public String findPhone(String criminalId);
}