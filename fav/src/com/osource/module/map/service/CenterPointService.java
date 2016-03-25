/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointService.java
 * @since v0.1
 */
package com.osource.module.map.service;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.CenterPointBean;

/**
 * 中心点service
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointService.java
 * @since v0.1
 */
public interface CenterPointService {
	
	/**
	 * 保存中心点
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean saveCenterPointInfo(CenterPointBean cpi) throws IctException ;
	
	/**
	 * 更新中心点
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean updateCenterPointInfo(CenterPointBean cpi) throws IctException ;
	
	/**
	 * 删除中心点
	 * @param id
	 * @throws IctException
	 * 
	 */
	public void deleteCenterPointInfo(Integer id, Integer updateId) throws IctException ;
	
	/**
	 * 根据id查找中心点
	 * @param id
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean findCenterPointInfoById(Integer id) throws IctException ;
	
	/**
	 * 根据userId查找中心点
	 * @param userId
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean findCenterPointInfoByUserId(Integer userId) throws IctException ;

}
