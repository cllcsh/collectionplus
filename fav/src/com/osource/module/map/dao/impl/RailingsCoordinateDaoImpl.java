/**
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingsCoordinateDaoImpl.java
 * @since v0.1
 */
package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.map.dao.RailingsCoordinateDao;
import com.osource.module.map.model.RailingsCoordinateInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * 电子围栏坐标点dao实现
 * @author yangs
 * @create 2009-5-22
 * @file RailingsCoordinateDaoImpl.java
 * @since v0.1
 */
@Repository
public class RailingsCoordinateDaoImpl extends BaseDaoImpl<RailingsCoordinateInfo> implements RailingsCoordinateDao {

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#deleteCoordinateInfo(java.lang.Integer, java.lang.Integer)
	 */
	public void deleteCoordinateInfo(Integer id, Integer updateId)
			throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateId", updateId);
		update("map_deleteCoordinateInfoById", map);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#deleteCoordinateInfoByRailingsId(java.lang.Integer, java.lang.Integer)
	 */
	public void deleteCoordinateInfoByRailingsId(Integer railingsId,
			Integer updateId) throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("railingsId", railingsId);
		map.put("updateId", updateId);
		update("map_deleteCoordinateInfoByRailingsId", map);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#findCoordinateInfoByRailingsId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<RailingsCoordinateInfo> findCoordinateInfoByRailingsId(
			Integer railingsId) throws IctException {
		return queryForList("map_findCoordinateInfoByRailingsId", railingsId);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#saveCoordinateInfo(com.osource.module.map.model.RailingsCoordinateInfo)
	 */
	public RailingsCoordinateInfo saveCoordinateInfo(RailingsCoordinateInfo rci)
			throws IctException {
		insert("map_saveCoordinateInfo", rci);
		return rci;
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#updateCoordinateInfo(com.osource.module.map.model.RailingsCoordinateInfo)
	 */
	public RailingsCoordinateInfo updateCoordinateInfo(
			RailingsCoordinateInfo rci) throws IctException {
		update("map_updateCoordinateInfo", rci);
		return rci;
	}

	/*
	 * (non-Javadoc)
	 * @see com.osource.module.map.dao.RailingsCoordinateDao#deletesCoordinateInfoByRailingsIds(java.lang.String, java.lang.Integer)
	 */
	public void deletesCoordinateInfoByRailingsIds(String railingsIds,
			Integer updateId) throws IctException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("railingsId", railingsIds);
		map.put("updateId", updateId);
		update("map_deletesCoordinateInfoByRailingsIds", map);
	}

}
