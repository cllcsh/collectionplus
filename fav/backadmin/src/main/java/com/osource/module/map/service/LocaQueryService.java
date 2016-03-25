package com.osource.module.map.service;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.map.model.LocaQueryBean;
import com.osource.module.map.web.form.LocaQueryForm;
import com.osource.orm.ibatis.BaseService;

public interface LocaQueryService extends BaseService<LocaQueryBean> {	
	public List<LocaQueryBean> queryByCondition(String sqlMap, List condition);
	
	public StringBuffer buildTree(String showType, String contextPath, boolean modelFlag);
	
	public String generateTree(String contextPath, Map condition, boolean mutiSelected);
	
	PageList findLocationInfoInDate(String terminalId, String startDate, String endDate, Pages pages) throws IctException;
	
	PageList findLocationInfoInDateNew(String log,String lat,String startDate, String endDate, Pages pages) throws IctException;
	
	public LocaQueryBean findLocationInfoByCondition(String sqlMap, LocaQueryForm condition);
	
	public LocaQueryBean findLocationInfoById(Integer locationId);
}