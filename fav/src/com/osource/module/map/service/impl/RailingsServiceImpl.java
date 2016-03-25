/**
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingsService.java
 * @since v0.1
 */
package com.osource.module.map.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.IDgenerator;
import com.osource.core.PropertiesManager;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.dao.RailingsCoordinateDao;
import com.osource.module.map.dao.RailingsDao;
import com.osource.module.map.model.AreaCodeBean;
import com.osource.module.map.model.RailingsCoordinateInfo;
import com.osource.module.map.model.RailingsInfo;
import com.osource.module.map.service.AreaCode;
import com.osource.module.map.service.RailingsService;
import com.osource.orm.ibatis.BaseServiceImpl;
/**
 * 电子围栏service
 * @author zhouhao	
 * @create 2009-11-5
 * @file RailingsService.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class RailingsServiceImpl extends BaseServiceImpl implements RailingsService{
	@Autowired
	private RailingsDao railingsDao;
	@Autowired
	private RailingsCoordinateDao coordinateDao;
	@Autowired
	private AreaCode ac;
	// 行政区域
	private static List<AreaCodeBean> areaCodeList = new ArrayList<AreaCodeBean>();
	private static final String AREACODE_PARENTCODE = PropertiesManager.getProperty("common.properties", "AREACODE_PARENTCODE").toString();
	
	public List<AreaCodeBean> getAreaCodeList() {
		return areaCodeList;
	}

	/**
	 * 增加电子围栏
	 * @param ri
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo saveRailingsInfo(RailingsInfo ri) throws IctException {
		try {
			ri.setId(IDgenerator.gettNextID("tb_railings"));
			railingsDao.saveRailingsInfo(ri);
			List<RailingsCoordinateInfo> list = ri.getCoordinateList();
			if((list != null) && (list.size() > 0)) {
				for(RailingsCoordinateInfo rci : list) {
					rci.setId(IDgenerator.gettNextID("tb_coordinate_set"));
					rci.setRailingsId(ri.getId());
					coordinateDao.saveCoordinateInfo(rci);
				}
				ri.setCoordinateList(list);
			}
			return ri;
		} catch(IctException e) {
			logger.error("保存电子围栏失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 更新电子围栏
	 * @param ri
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	public RailingsInfo updateRailingsInfo(RailingsInfo ri) throws IctException {
		try {
			railingsDao.updateRailingsInfo(ri);
			return ri;
		} catch(IctException e) {
			logger.error("更新电子围栏失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 删除电子围栏
	 * @param id
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deleteRailingsInfo(Integer id, Integer updateId) throws IctException {
		try {
			railingsDao.deleteRailingsInfo(id, updateId);
			coordinateDao.deleteCoordinateInfoByRailingsId(id, updateId);
		} 
		catch(IctException e) {
			logger.error("删除电子围栏失败！");
			logger.error(e);
			throw e;
		} 
	}
	
	/**
	 * 删除多个电子围栏
	 * @param ids
	 * @param updateId
	 * @throws IctException
	 * 
	 */
	public void deletesRailingsInfo(String ids, Integer updateId) throws IctException {
		try {
			railingsDao.deletesRailingsInfo(ids, updateId);
			coordinateDao.deletesCoordinateInfoByRailingsIds(ids, updateId);
		} 
		catch(IctException e) {
			logger.error("删除电子围栏失败！");
			logger.error(e);
			throw e;
		} 
	}
	
	/**
	 * 根据围栏id查找电子围栏
	 * @param id
	 * @return RailingsInfo
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public RailingsInfo findRailingsInfoById(Integer id) throws IctException {
		try {
			return railingsDao.findRailingsInfoById(id);
		} catch(IctException e) {
			logger.error("查找电子围栏失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 查找全部电子围栏
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public PageList findAllRilingsInfo(Pages pages) throws IctException {
		try {
			PageList result = new PageList();
			pages.setTotal(railingsDao.getRailingsInfoCount());
			pages.executeCount();
			result.setPages(pages);
			result.addAll(railingsDao.findAllRailingsInfo(pages.getStart(), pages.getLimit()));
			return result;
		} catch(IctException e) {
			logger.error("查找电子围栏失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 根据机构id查找电子围栏
	 * @param deptId
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public PageList findRailingsInfoByDeptId(Integer deptId,String node, Pages pages) throws IctException {
		try {
			PageList result = new PageList();
			pages.setTotal(railingsDao.getRailingsInfoCountByDeptId(deptId, node));
			pages.executeCount();
			result.setPages(pages);
			result.addAll(railingsDao.findRailingsInfoByDeptId(deptId, node, pages.getStart(), pages.getLimit()));
			return result;
		} catch(IctException e) {
			logger.error("查找电子围栏失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 根据机构id查找电子围栏
	 * @param deptId
	 * @return List<RailingsInfo>
	 * 
	 */
	@Transactional(readOnly = true)
	public List<RailingsInfo> findRailingsInfoByDeptId(Integer deptId) {
		return railingsDao.findRailingsInfoByDeptId(deptId);
	}
	
	/**
	 * 根据围栏名称查找电子围栏
	 * @param name
	 * @param deptId
	 * @param pages
	 * @return PageList
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public PageList findRailingsInfoByName(String name, Integer deptId,String node, Pages pages) throws IctException {
		PageList result = new PageList();
		pages.setTotal(railingsDao.getRailingsInfoCountByName(name, deptId, node));
		pages.executeCount();
		result.setPages(pages);
		result.addAll(railingsDao.findRailingsInfoByName(name, deptId,node, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	//获取所有行政区域
	@Transactional(readOnly = true)
	public List<AreaCodeBean> getAreaCode() throws Exception {
		areaCodeList = ac.getAreaCode();
		return areaCodeList;
	}
	
	// 获取默认行政区域
	@Transactional(readOnly = true)
	public List<AreaCodeBean> getDefaultAreaCode() throws Exception {
		List<AreaCodeBean>  acList = new ArrayList<AreaCodeBean>();
		for(int i = 0; i < areaCodeList.size(); i++) {
			if(AREACODE_PARENTCODE.equals(areaCodeList.get(i).getParentCode())) {
				acList.add(areaCodeList.get(i));
			}
		}
		return acList;
	}
	
	// 根据父code获取行政区域
	@Transactional(readOnly = true)
	public List<AreaCodeBean> getAreaCodeByParent(String parentCode) throws Exception {
		List<AreaCodeBean>  acList = new ArrayList<AreaCodeBean>();
		for(int i = 0; i < areaCodeList.size(); i++) {
			if(parentCode.equals(areaCodeList.get(i).getParentCode())) {
				acList.add(areaCodeList.get(i));
			}
		}
		return acList;
	}
	
	// 根据行政区域反查父行政区域
	@Transactional(readOnly = true)
	public List<AreaCodeBean> getParentAreaCode(String Code, List<AreaCodeBean> acList) throws Exception {
		AreaCodeBean acb = getAreaBean(Code);
		String parentCode = acb.getParentCode();
		for(int i = 0; i < areaCodeList.size(); i++) {
			if(parentCode.equals(areaCodeList.get(i).getAreaCode())) {
				acList.add(0, areaCodeList.get(i));
				if(AREACODE_PARENTCODE.equals(areaCodeList.get(i).getAreaCode()))
					break;
				acList = getParentAreaCode(areaCodeList.get(i).getAreaCode(), acList);
			}
			
		}
		return acList;
	}
	
	// 根据areacode获取行政区域
	@Transactional(readOnly = true)
	public AreaCodeBean getAreaBean(String areaCode) throws Exception {
		AreaCodeBean ac = null;
		for(int i = 0; i < areaCodeList.size(); i++) {
			if(areaCode.equals(areaCodeList.get(i).getAreaCode())) {
				ac = areaCodeList.get(i);
				break;
			}
		}
		return ac;
	}
	public RailingsDao getRailingsDao() {
		return railingsDao;
	}

	public void setRailingsDao(RailingsDao railingsDao) {
		this.railingsDao = railingsDao;
	}

	public RailingsCoordinateDao getCoordinateDao() {
		return coordinateDao;
	}

	public void setCoordinateDao(RailingsCoordinateDao coordinateDao) {
		this.coordinateDao = coordinateDao;
	}

	public AreaCode getAc() {
		return ac;
	}

	public void setAc(AreaCode ac) {
		this.ac = ac;
	}
}
