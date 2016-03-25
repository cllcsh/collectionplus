/**
 * @author zhouhao	
 * @create 2009-11-5
 * @file RailingDao.java
 * @since v0.1
 */
package com.osource.module.map.dao;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.RailingsInfo;

/**
 * 电子围栏dao接口
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingDao.java
 * @since v0.1
 */
public interface RailingsDao {

	/**
	 * 保存电子围栏
	 * @param ri
	 * @return RailingInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo saveRailingsInfo(RailingsInfo ri) throws IctException;
	
	/**
	 * 更新电子围栏
	 * @param ri
	 * @return RailingInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo updateRailingsInfo(RailingsInfo ri) throws IctException;
	
	/**
	 * 删除电子围栏
	 * @param ri
	 * @throws IctException
	 * 
	 */
	public void deleteRailingsInfo(Integer id, Integer updateId) throws IctException;
	
	/**
	 * 删除多个电子围栏
	 * @param ids
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deletesRailingsInfo(String ids, Integer updateId) throws IctException;
	
	/**
	 * 查找全部电子围栏
	 * @return List<RailingInfo>
	 * @throws IctException
	 * 
	 */
	public List<RailingsInfo> findAllRailingsInfo(Integer start, Integer limit) throws IctException;
	
	/**
	 * 获取全部电子围栏数量
	 * @return Integer
	 * @throws IctException
	 * 
	 */
	public Integer getRailingsInfoCount() throws IctException;
	
	/**
	 * 根据机构查找电子围栏
	 * @param deptId
	 * @return List<RailingsInfo>
	 * @throws IctException
	 * 
	 */
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId, String node, Integer start, Integer limit) throws IctException;
	
	/**
	 * 根据机构查找电子围栏
	 * @param deptId
	 * @return List<RailingsInfo>
	 * 
	 */
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId);
	
	/**
	 * 根据围栏名称查找电子围栏
	 * @param name
	 * @param deptId
	 * @param start
	 * @param limit
	 * @return List<RailingsInfo>
	 * @throws IctException
	 * 
	 */
	public List<RailingsInfo> findRailingsInfoByName(String name, Integer deptId, String node, Integer start, Integer limit) throws IctException;
	
	/**
	 * 根据围栏名称查找电子围栏数量
	 * @param name
	 * @param deptId
	 * @return Integer
	 * @throws IctException
	 * 
	 */
	public Integer getRailingsInfoCountByName(String name, Integer deptId, String node) throws IctException;
	
	/**
	 * 获取机构的电子围栏数量
	 * @param deptId
	 * @return Integer
	 * @throws IctException
	 * 
	 */
	public Integer getRailingsInfoCountByDeptId(Integer deptId, String node) throws IctException;
	
	/**
	 * 根据编号查找电子围栏
	 * @param id
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo findRailingsInfoById(Integer id) throws IctException;

}
