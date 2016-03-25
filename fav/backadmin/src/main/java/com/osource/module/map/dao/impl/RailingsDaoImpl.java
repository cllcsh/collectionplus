/**
 * @author yangs
 * @create 2009-5-12
 * @file RailingsDaoImpl.java
 * @since v0.1
 */
package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.map.dao.RailingsDao;
import com.osource.module.map.model.RailingsInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * 电子围栏dao实现
 * 
 * @author yangs
 * @create 2009-5-12
 * @file RailingsDaoImpl.java
 * @since v0.1
 */
@Repository
public class RailingsDaoImpl extends BaseDaoImpl<RailingsInfo> implements RailingsDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#deleteRailingsInfo(com.osource
	 * .ictmapmodule.map.model.RailingsInfo)
	 */
	public void deleteRailingsInfo(Integer id, Integer updateId)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateId", updateId);
		update("map_deleteRailingsInfo", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osource.module.map.dao.RailingsDao#findAllRailingsInfo()
	 */
	@SuppressWarnings("unchecked")
	public List<RailingsInfo> findAllRailingsInfo(Integer start, Integer limit)
			throws IctException {
		return queryForList("map_findAllRailingsInfo", start, limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#findRailingsInfoByDeptId(java
	 * .lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId, String node, 
			Integer start, Integer limit) throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("node", node);
		return queryForList("map_findAllRailingsInfoByDeptId", map, start,
				limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#findRailingsInfoById(java.
	 * lang.Integer)
	 */
	public RailingsInfo findRailingsInfoById(Integer id) throws IctException {
		return (RailingsInfo) queryForObject("map_findAllRailingsInfoById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#saveRailingsInfo(com.osource
	 * .ictmapmodule.map.model.RailingsInfo)
	 */
	public RailingsInfo saveRailingsInfo(RailingsInfo ri) throws IctException {
		insert("map_saveRailingsInfo", ri);
		return ri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#updateRailingsInfo(com.osource
	 * .ictmapmodule.map.model.RailingsInfo)
	 */
	public RailingsInfo updateRailingsInfo(RailingsInfo ri) throws IctException {
		update("map_updateRailingsInfo", ri);
		return ri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#deletesRailingsInfo(java.lang
	 * .String, java.lang.Integer)
	 */
	public void deletesRailingsInfo(String ids, Integer updateId)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ids);
		map.put("updateId", updateId);
		update("map_deletesRailingsInfo", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.osource.module.map.dao.RailingsDao#getRailingsInfoCount()
	 */
	public Integer getRailingsInfoCount() throws IctException {
		return (Integer) queryForObject("map_getRailingsInfoCount");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#getRailingsInfoCountByDeptId
	 * (java.lang.Integer)
	 */
	public Integer getRailingsInfoCountByDeptId(Integer deptId, String node)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("node", node);
		return (Integer) queryForObject("map_getRailingsInfoCountByDeptId", map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#findRailingsInfoByName(java
	 * .lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<RailingsInfo> findRailingsInfoByName(String name,
			Integer deptId, String node, Integer start, Integer limit) throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("deptId", deptId);
		map.put("node", node);
		return queryForList("map_findAllRailingsInfoByDeptId", map, start,
				limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.osource.module.map.dao.RailingsDao#getRailingsInfoCountByName
	 * (java.lang.String, java.lang.Integer)
	 */
	public Integer getRailingsInfoCountByName(String name, Integer deptId, String node)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("deptId", deptId);
		map.put("node", node);
		return (Integer) queryForObject("map_getRailingsInfoCountByDeptId", map);
	}

	@SuppressWarnings("unchecked")
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptId", deptId);
		return queryForList("map_findAllRailingsInfoByDeptId", map);
	}
}