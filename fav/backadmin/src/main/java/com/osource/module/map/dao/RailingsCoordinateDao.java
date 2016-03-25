/**
 * @author yangs
 * @create 2009-5-11
 * @file RailingCoordinateDao.java
 * @since v0.1
 */
package com.osource.module.map.dao;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.RailingsCoordinateInfo;

/**
 * 电子围栏坐标dao接口
 * @author yangs
 * @create 2009-5-11
 * @file RailingCoordinateDao.java
 * @since v0.1
 */
public interface RailingsCoordinateDao {

	/**
	 * 保存电子围栏坐标
	 * @param rci
	 * @return RailingCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo saveCoordinateInfo(RailingsCoordinateInfo rci) throws IctException;
	
	/**
	 * 更新电子围栏坐标
	 * @param rci
	 * @return RailingCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo updateCoordinateInfo(RailingsCoordinateInfo rci) throws IctException;
	
	/**
	 * 根据编号删除电子围栏坐标
	 * @param id
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfo(Integer id, Integer updateId) throws IctException;
	
	/**
	 * 根据围栏编号删除电子围栏坐标
	 * @param railingsId
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfoByRailingsId(Integer railingsId, Integer updateId) throws IctException;
	
	/**
	 *  根据多个围栏编号删除电子围栏坐标
	 * @param railingsIds
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deletesCoordinateInfoByRailingsIds(String railingsIds, Integer updateId) throws IctException;
	
	/**
	 * 根据电子围栏id查找电子围栏坐标
	 * @param railingId
	 * @return List<RailingCoordinateInfo>
	 * @throws IctException
	 * 
	 */
	public List<RailingsCoordinateInfo> findCoordinateInfoByRailingsId(Integer railingsId) throws IctException;
}
