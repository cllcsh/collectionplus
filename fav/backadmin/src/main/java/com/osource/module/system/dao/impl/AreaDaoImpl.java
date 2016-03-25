package com.osource.module.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.base.util.Entry;
import com.osource.module.system.dao.AreaDao;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class AreaDaoImpl extends BaseDaoImpl implements AreaDao {

	public List<Entry<String, String>> getPrivonceSelectList(String code) {
		List<Entry<String, String>> result = getSqlMapClientTemplate().queryForList("system_area_getProvinceSelectList", code);
		return result;
	}
	public List<Entry<String, String>> getAllCity() {
		List<Entry<String, String>> result = getSqlMapClientTemplate().queryForList("system_area_getAllCity");
		return result;
	}
	public List<Entry<String, String>> getAllArea() {
		List<Entry<String, String>> result = getSqlMapClientTemplate().queryForList("system_area_getAllArea");
		return result;
	}
	public List<Entry<String, String>> getCitySelectList(String code) {
		List<Entry<String, String>> result = getSqlMapClientTemplate().queryForList("system_area_getCitySelectList", code);
		return result;
	}
	public List<Entry<String, String>> getAreaSelectList(String code) {
		List<Entry<String, String>> result = getSqlMapClientTemplate().queryForList("system_area_getAreaSelectList", code);
		return result;
	}

}
