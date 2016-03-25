package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.map.dao.LocaQueryDao;
import com.osource.module.map.model.LocaQueryBean;
import com.osource.module.map.web.form.LocaQueryForm;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class LocaQueryDaoImpl extends BaseDaoImpl<LocaQueryBean> implements LocaQueryDao {
	@Override
	public String getEntityName() {
		return "module_locaQuery";
	}
	
	public List<LocaQueryBean> queryByCondition(String sqlMap, List condition){
		return (List<LocaQueryBean>) getSqlMapClientTemplate().queryForList(sqlMap, condition);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.LocationDao#getLocationInfoCountInDate
	 * (java.lang.String, java.util.Date, java.util.Date)
	 */
	public Integer getLocationInfoCountInDate(String terminalId, String startDate,
			String endDate) throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("terminalId", terminalId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		int num = (Integer) queryForObject("historyTrace_location_getLocationInfoCountInDate", map);
		return num;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.LocationDao#findLocationInfoInDate(java
	 * .lang.String, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<LocaQueryBean> findLocationInfoInDate(String terminalId,
			String startDate, String endDate, Integer start, Integer limit)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("terminalId", terminalId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return queryForList("historyTrace_location_findLocationInfoInDate", map, start, limit);
	}
	
	/**
	 * @param 根据条件查询某个号码的定位信息
	 */
	public LocaQueryBean findLocationInfoByCondition(String sqlMap, LocaQueryForm condition){
		return (LocaQueryBean)queryForObject(sqlMap, condition);
	}

	public LocaQueryBean findLocationInfoById(Integer locationId) {
		return (LocaQueryBean)queryForObject("map_findLocationInfo", locationId);
	}

}