/**
 * 
 */
package com.osource.module.map.dao;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.CenterPointBean;

/**
 * 中心点Dao接口
 * @author zhouhao
 * @create 2009-11-5
 * @file CenterPointDao.java
 * @since v0.1
 */
public interface CenterPointDao {

	/**
	 * 保存中心点
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 */
	public CenterPointBean saveCenterPointInfo(CenterPointBean cpi) throws IctException;
	
	/**
	 * 更新中心点
	 * 
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean updateCenterPointInfo(CenterPointBean cpi) throws IctException;
	
	/**
	 * 删除中心点
	 * 
	 * @param id 
	 * @throws IctException
	 */
	public void deleteCenterPointInfo(Integer id, Integer updateId) throws IctException;
	
	/**
	 * 根据id查找中心点
	 * @param id
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean findCenterPointById(Integer id);
	
	/**
	 * 根据用户id查找中心点
	 * @param userId
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean findCenterPointByUserId(Integer userId);
}
