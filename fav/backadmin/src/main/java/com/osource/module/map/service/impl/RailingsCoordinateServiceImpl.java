/**
 * @author yangs
 * @create 2009-5-22
 * @file RailingsCoordinateService.java
 * @since v0.1
 */
package com.osource.module.map.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.module.map.dao.RailingsCoordinateDao;
import com.osource.module.map.model.RailingsCoordinateInfo;
import com.osource.module.map.service.RailingsCoordinateService;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * 电子围栏坐标点service
 * @author yangs
 * @create 2009-5-22
 * @file RailingsCoordinateService.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class RailingsCoordinateServiceImpl extends BaseServiceImpl implements RailingsCoordinateService{
	@Autowired
	private RailingsCoordinateDao coordinateDao;
	
	public RailingsCoordinateDao getCoordinateDao() {
		return coordinateDao;
	}

	public void setCoordinateDao(RailingsCoordinateDao coordinateDao) {
		this.coordinateDao = coordinateDao;
	}

	public RailingsCoordinateServiceImpl() {
		super();
	}
	
	/**
	 * 保存电子围栏坐标点
	 * @param rci
	 * @return RailingsCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo saveCoordinateInfo(RailingsCoordinateInfo rci) throws IctException {
//		try {
			rci.setId(IDgenerator.gettNextID("tb_coordinate_set"));
			return coordinateDao.saveCoordinateInfo(rci);
//		} catch(IctException e) {
//			logger.error("保存电子围栏坐标点失败！");
//			logger.error(e);
//			throw e;
//		}
	}
	
	/**
	 * 更新电子围栏坐标
	 * @param rci
	 * @return RailingsCoordinateInfo
	 * @throws IctException
	 * 
	 */
	public RailingsCoordinateInfo updateCoordinateInfo(RailingsCoordinateInfo rci) throws IctException {
		return coordinateDao.updateCoordinateInfo(rci);
	}
	
	/**
	 * 根据id删除电子围栏坐标点
	 * @param id
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfoById(Integer id, Integer updateId) throws IctException {
		coordinateDao.deleteCoordinateInfo(id, updateId);
	}
	
	/**
	 *  根据围栏id删除电子围栏坐标点
	 * @param railingsId
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteCoordinateInfoByRailingsId(Integer railingsId, Integer updateId) throws IctException {
		coordinateDao.deleteCoordinateInfoByRailingsId(railingsId, updateId);
	}
	
	/**
	 * 根据围栏id查找电子围栏坐标点
	 * @param railingsId
	 * @return List<RailingsCoordinateInfo>
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public List<RailingsCoordinateInfo> findCoordinateInfoByRailingsId(Integer railingsId) throws IctException {
		return coordinateDao.findCoordinateInfoByRailingsId(railingsId);
	}
	
	@SuppressWarnings("unchecked")
	public void testfindCoordinateInfoByRailingsId() throws IctException{
		List num = findCoordinateInfoByRailingsId(25);
		if(num != null){
			System.out.println("size:"+num.size());
			System.out.println("testGetMarkets 测试通过");
		}
		else 
			System.err.println("testGetMarkets 测试失败");
	}
}
