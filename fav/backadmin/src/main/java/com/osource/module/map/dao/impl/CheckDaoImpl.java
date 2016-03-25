package com.osource.module.map.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.base.util.Entry;
import com.osource.module.map.dao.CheckDao;
import com.osource.module.map.model.CheckBean;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class CheckDaoImpl extends BaseDaoImpl<CheckBean> implements CheckDao {
	public String getEntityName() {
		return "module_check";
	}

	@SuppressWarnings("unchecked")
	public List<Entry<String, String>> getCriminalSelectList(int userId) {
		return getSqlMapClientTemplate().queryForList("select_Criminal",userId);
	}

	public String findPhone(String criminalId) {
		return (String)getSqlMapClientTemplate().queryForObject("select_findPhone",criminalId);
	}
	
}