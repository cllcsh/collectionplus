/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointDaoImpl.java
 * @since v0.1
 */
package com.osource.module.map.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.map.dao.CenterPointDao;
import com.osource.module.map.model.CenterPointBean;
import com.osource.orm.ibatis.BaseDaoImpl;

/**
 * 中心点Dao实现
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointDaoImpl.java
 * @since v0.1
 */
@Repository
public class CenterPointDaoImpl extends BaseDaoImpl<CenterPointBean> implements CenterPointDao {
	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.CenterPointDao#deleteCenterPointInfo(int)
	 */
	public void deleteCenterPointInfo(Integer id, Integer updateId) throws IctException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("updateId", updateId);
		update("map_deleteCenterPointInfoById", map);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.CenterPointDao#findCenterPointById(int)
	 */
	public CenterPointBean findCenterPointById(Integer id) {
		return (CenterPointBean) queryForObject("map_findCenterPointInfoById", id);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.CenterPointDao#findCenterPointByUserId(int)
	 */
	public CenterPointBean findCenterPointByUserId(Integer userId) {
		return (CenterPointBean) queryForObject("map_findCenterPointInfoByUserId", userId);
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.CenterPointDao#saveCenterPointInfo(com.osource.module.map.model.CenterPointInfo)
	 */
	public CenterPointBean saveCenterPointInfo(CenterPointBean cpi)
			throws IctException {
		insert("map_saveCenterPointInfo", cpi);
		return cpi;
	}

	/* (non-Javadoc)
	 * @see com.osource.module.map.dao.CenterPointDao#updateCenterPointInfo(com.osource.module.map.model.CenterPointInfo)
	 */
	public CenterPointBean updateCenterPointInfo(CenterPointBean cpi)
			throws IctException {
		update("map_updateCenterPointInfo", cpi);
		return cpi;
	}
}
