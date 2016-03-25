package com.osource.module.system.service;

import java.util.List;
import java.util.Map;

import com.osource.base.model.ConfigInfo;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.ConfigInfoDao;

public interface ConfigInfoService{
	public ConfigInfo select(String configKey) throws IctException;
	
	@SuppressWarnings("unchecked")
	public PageList findConfigInfoListByCondition(Map condition,Pages pages);
	
	/**
	 * <通过条件查询信息列表>
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigInfo> findConfigInfosByCondition(Map condition) throws IctException;
	
	/**
	 * 保存ConfigInfo对象
	 * 
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 * @throws IctException
	 */
	public ConfigInfo saveConfigInfo(ConfigInfo configInfo) throws IctException;
	
	/**
	 * 更新ConfigInfo对象
	 * 
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 * @throws IctException
	 */
	public ConfigInfo updateConfigInfo(ConfigInfo configInfo) throws IctException;
	/**
	   * 根据用户名删除配置对象
	   * @param configCode String
	   */
	public void deleteConfigInfoById(String configCode) throws IctException;
	
	public ConfigInfoDao getConfigInfoDao();
	
	public void setConfigInfoDao(ConfigInfoDao configInfoDao);
}
