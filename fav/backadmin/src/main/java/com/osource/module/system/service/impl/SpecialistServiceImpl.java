package com.osource.module.system.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.SpecialistDao;
import com.osource.module.system.model.SpecialistInfo;
import com.osource.module.system.service.SpecialistService;
import com.osource.orm.ibatis.BaseServiceImpl;

@SuppressWarnings("unchecked")
@Service
@Scope("prototype")
@Transactional
public class SpecialistServiceImpl extends BaseServiceImpl implements SpecialistService{

	@Autowired
	private SpecialistDao specialistDao;
	
	
	public PageList findSpecialistListByCondition(Map condition,Pages pages){
		PageList result = new PageList();
		pages.setTotal(specialistDao.getAllSpecialistNumByCondition(condition));
		pages.executeCount();
		result.setPages(pages);
	    result.addAll(specialistDao.findSpecialistInfosByCondition(condition, pages.getStart(), pages.getLimit()));
		return result;
	}
	
	
	public SpecialistInfo findSpecialistInfoById(Integer specialistId) {
		return specialistDao.findSpecialistInfoById(specialistId);
	}
	
	
	public SpecialistInfo saveSpecialistInfo(SpecialistInfo specialistInfo) throws IctException {
		
		try {
			specialistDao.saveSpecialistInfo(specialistInfo);
	
		} catch (Exception e) {
			logger.error(e);
			throw new IctException(e);
		} 
		
		return specialistInfo;
	}

	
	public SpecialistInfo updateSpecialistInfo(SpecialistInfo specialistInfo) throws IctException {
		 try {
				
			specialistDao.updateSpecialistInfo(specialistInfo);
			
		} catch (Exception e) {
			logger.error(e);
			throw new IctException(e);
		} 
		
		return specialistInfo;
	}
	
	public void deleteSpecialistInfoById(String specialistId) throws IctException {
		try {
				
			specialistDao.deleteSpecialistInfoById(specialistId);
			
		} catch (Exception e) {
			logger.error(e);
			throw new IctException(e);
		} 
			
	}

	public SpecialistDao getSpecialistDao() {
		return specialistDao;
	}

	public void setSpecialistDao(SpecialistDao specialistDao) {
		this.specialistDao = specialistDao;
	}

	
}
