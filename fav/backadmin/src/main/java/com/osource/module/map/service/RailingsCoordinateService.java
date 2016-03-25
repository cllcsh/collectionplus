/**
 * @author yangs
 * @create 2009-5-22
 * @file RailingsCoordinateService.java
 * @since v0.1
 */
package com.osource.module.map.service;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.RailingsCoordinateInfo;

/**
 * 电子围栏坐标点service
 * @author yangs
 * @create 2009-5-22
 * @file RailingsCoordinateService.java
 * @since v0.1
 */
public interface RailingsCoordinateService{
	
	/**
	 * 保存电子围栏坐标点
	 * @param rci
	 * @return RailingsCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo saveCoordinateInfo(RailingsCoordinateInfo rci) throws IctException ;
	
	/**
	 * 更新电子围栏坐标
	 * @param rci
	 * @return RailingsCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo updateCoordinateInfo(RailingsCoordinateInfo rci) throws IctException ;
	
	/**
	 * 根据id删除电子围栏坐标点
	 * @param id
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfoById(Integer id, Integer updateId) throws IctException ;
	
	/**
	 *  根据围栏id删除电子围栏坐标点
	 * @param railingsId
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfoByRailingsId(Integer railingsId, Integer updateId) throws IctException ;
	
	/**
	 * 根据围栏id查找电子围栏坐标点
	 * @param railingsId
	 * @return List<RailingsCoordinateInfo>
	 * @throws IctException
	 * 
	 */
	public List<RailingsCoordinateInfo> findCoordinateInfoByRailingsId(Integer railingsId) throws IctException ;
	
//	public static void main(String[] args) throws Exception {
//		RailingsCoordinateService us = new RailingsCoordinateService();
//		us.testfindCoordinateInfoByRailingsId();
//	}
	
	@SuppressWarnings("unchecked")
	public void testfindCoordinateInfoByRailingsId() throws IctException ;
	
}
