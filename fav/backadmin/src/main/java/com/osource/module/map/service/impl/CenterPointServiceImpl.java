/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointService.java
 * @since v0.1
 */
package com.osource.module.map.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.module.map.dao.CenterPointDao;
import com.osource.module.map.model.CenterPointBean;
import com.osource.module.map.service.CenterPointService;
import com.osource.orm.ibatis.BaseServiceImpl;

/**
 * 中心点service
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointService.java
 * @since v0.1
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class CenterPointServiceImpl extends BaseServiceImpl implements CenterPointService{
	@Autowired
	private CenterPointDao centerPointDao;

	public CenterPointDao getCenterPointDao() {
		return centerPointDao;
	}

	public void setCenterPointDao(CenterPointDao centerPointDao) {
		this.centerPointDao = centerPointDao;
	}

	public CenterPointServiceImpl() {
		super();
	}
	
	/**
	 * 保存中心点
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean saveCenterPointInfo(CenterPointBean cpi) throws IctException {
		try {
			cpi.setId(IDgenerator.gettNextID("tb_center_point"));
			cpi = centerPointDao.saveCenterPointInfo(cpi);
		} catch(IctException e) {
			logger.error("保存中心点失败！");
			logger.error(e);
			throw e;
		}
		return cpi;
	}
	
	/**
	 * 更新中心点
	 * @param cpi
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	public CenterPointBean updateCenterPointInfo(CenterPointBean cpi) throws IctException {
		try {
			cpi = centerPointDao.updateCenterPointInfo(cpi);
		} catch(IctException e) {
			logger.error("更新中心点失败！");
			logger.error(e);
			throw e;
		}
		return cpi;
	}
	
	/**
	 * 删除中心点
	 * @param id
	 * @throws IctException
	 * 
	 */
	public void deleteCenterPointInfo(Integer id, Integer updateId) throws IctException {
		try {
			centerPointDao.deleteCenterPointInfo(id, updateId);
		} catch(IctException e) {
			logger.error("删除中心点失败！");
			logger.error(e);
			throw e;
		}
	}
	
	/**
	 * 根据id查找中心点
	 * @param id
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public CenterPointBean findCenterPointInfoById(Integer id) {
		return centerPointDao.findCenterPointById(id);
	}
	
	/**
	 * 根据userId查找中心点
	 * @param userId
	 * @return CenterPointInfo
	 * @throws IctException
	 * 
	 */
	@Transactional(readOnly = true)
	public CenterPointBean findCenterPointInfoByUserId(Integer userId) {
		return centerPointDao.findCenterPointByUserId(userId);
	}
}
