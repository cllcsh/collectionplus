package com.osource.module.system.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.osource.base.model.ConfigInfo;

@SuppressWarnings("unchecked")
public interface ConfigInfoDao{

	/**
	 * <通过配置键得到对应的配置>
	 * @param id
	 * @return
	 */
	public ConfigInfo select(String configKey) throws SQLException;
	
	/**
	 * 多条件取得所有配置数量
	 *
	 * @return long
	 * @throws SQLException 
	 */
	public long getAllConfigNumByCondition(Map condition) throws SQLException;
	
	/**
	 * <通过配置编号得到全部配置>
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ConfigInfo> findConfigInfosByCondition(Map condition, int start, int limit) throws SQLException;

	/**
	 * <通过配置编号得到全部配置>
	 * @param condition
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<ConfigInfo> findConfigInfosByCondition(Map condition) throws SQLException;
	
	/**
	 * 保存配置对象
	 *
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 */
	public ConfigInfo saveConfigInfo(ConfigInfo configInfo) throws SQLException;
	
	/**
	 * 更新配置对象
	 *
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 */
	public ConfigInfo updateConfigInfo(ConfigInfo configInfo) throws SQLException;
	
	/**
	 * 根据主键删除配置对象
	 *
	 * @param id
	 *            String
	 * @return UserInfo
	 */
	public void deleteConfigInfoById(String configCode) throws SQLException;
}
