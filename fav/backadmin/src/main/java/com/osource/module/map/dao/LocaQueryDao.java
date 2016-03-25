package com.osource.module.map.dao;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.map.model.LocaQueryBean;
import com.osource.module.map.web.form.LocaQueryForm;
import com.osource.orm.ibatis.BaseDao;

public interface LocaQueryDao extends BaseDao<LocaQueryBean> {

	public List<LocaQueryBean> queryByCondition(String sqlMap, List condition);
	
	/**
	 * 查找某个时间内的某个定位号码的定位信息
	 * @param locNum
	 * @param startDate
	 * @param endDate
	 * @return List<LocationInfo>
	 * @throws IctException
	 * 
	 */
	public List<LocaQueryBean> findLocationInfoInDate(String terminalId, String startDate, String endDate, Integer start, Integer limit) throws IctException;
	
	/**
	 * 查找某个时间内的某个定位号码的定位信息
	 * @param locNum
	 * @param startDate
	 * @param endDate
	 * @return Integer
	 * @throws IctException
	 * 
	 */
	public Integer getLocationInfoCountInDate(String terminalId, String startDate,String endDate) throws IctException ;
	
	/**
	 * @param 根据条件查询某个号码的定位信息
	 */
	public LocaQueryBean findLocationInfoByCondition(String sqlMap, LocaQueryForm condition);
	
	public LocaQueryBean findLocationInfoById(Integer locationId);
}