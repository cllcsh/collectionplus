package com.osource.module.system.service;

import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.SpecialistInfo;

@SuppressWarnings("unchecked")
public interface SpecialistService{

	public PageList findSpecialistListByCondition(Map condition,Pages pages);
	
	
	public SpecialistInfo findSpecialistInfoById(Integer specialistId);
	
	public SpecialistInfo saveSpecialistInfo(SpecialistInfo specialistInfo) throws IctException;

	
	public SpecialistInfo updateSpecialistInfo(SpecialistInfo specialistInfo) throws IctException;
	
	public void deleteSpecialistInfoById(String specialistId) throws IctException;

}
