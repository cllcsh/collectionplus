/**
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingsService.java
 * @since v0.1
 */
package com.osource.module.map.service;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.model.AreaCodeBean;
import com.osource.module.map.model.RailingsInfo;
/**
 * 电子围栏service
 * @author zhouhao	
 * @create 2009-11-5
 * @file RailingsService.java
 * @since v0.1
 */
public interface RailingsService {

	public List<AreaCodeBean> getAreaCodeList();

//	public void setAreaCodeList(List<AreaCodeBean> areaCodeList);
	
	/**
	 * 增加电子围栏
	 * @param ri
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo saveRailingsInfo(RailingsInfo ri) throws IctException ;
	
	/**
	 * 更新电子围栏
	 * @param ri
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo updateRailingsInfo(RailingsInfo ri) throws IctException ;
	
	/**
	 * 删除电子围栏
	 * @param id
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteRailingsInfo(Integer id, Integer updateId) throws IctException ;
	
	/**
	 * 删除多个电子围栏
	 * @param ids
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deletesRailingsInfo(String ids, Integer updateId) throws IctException ;
	
	/**
	 * 根据围栏id查找电子围栏
	 * @param id
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo findRailingsInfoById(Integer id) throws IctException ;
	
	/**
	 * 查找全部电子围栏
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	public PageList findAllRilingsInfo(Pages pages) throws IctException ;
	
	/**
	 * 根据机构id查找电子围栏
	 * @param deptId
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	public PageList findRailingsInfoByDeptId(Integer deptId,String node, Pages pages) throws IctException ;
	
	/**
	 * 根据机构id查找电子围栏
	 * @param deptId
	 * @return List<RailingsInfo>
	 * 
	 */
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId) ;
	
	/**
	 * 根据围栏名称查找电子围栏
	 * @param name
	 * @param deptId
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	public PageList findRailingsInfoByName(String name, Integer deptId,String node, Pages pages) throws IctException ;
	
	//获取所有行政区域
	public List<AreaCodeBean> getAreaCode() throws Exception ;
	
	// 获取默认行政区域
	public List<AreaCodeBean> getDefaultAreaCode() throws Exception ;
	
	// 根据父code获取行政区域
	public List<AreaCodeBean> getAreaCodeByParent(String parentCode) throws Exception ;
	
	// 根据行政区域反查父行政区域
	public List<AreaCodeBean> getParentAreaCode(String Code, List<AreaCodeBean> acList) throws Exception ;
	
	// 根据areacode获取行政区域
	public AreaCodeBean getAreaBean(String areaCode) throws Exception ;
	
	
//	public static void main(String[] args) throws Exception {
//		RailingsService rs = new RailingsService();
//		RailingsInfo ri = rs.findRailingsInfoById(31);
//		System.out.println(ri);
////		rs.setRailingsToGroup("10,11,12,15,146", 1, 1);
//	}
}
