package com.osource.module.system.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.osource.base.model.ConfigInfo;
import com.osource.core.exception.IctException;
import com.osource.module.system.dao.ConfigInfoDao;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class ConfigInfoDaoImpl extends BaseDaoImpl implements ConfigInfoDao{
	private static final Logger logger = Logger.getLogger(ConfigInfoDaoImpl.class);
	
	public ConfigInfo select(String configKey) throws SQLException{
		// TODO Auto-generated method stub
		return (ConfigInfo) queryForObject("Config_select",configKey);
	}
	
	public long getAllConfigNumByCondition(Map condition) throws SQLException {
		int configNum = 0;
		configNum = (Integer) queryForObject("getAllConfigNumByCondition", condition);
		return configNum;
	}
	
	public List<ConfigInfo> findConfigInfosByCondition(Map condition, int start, int limit)  throws SQLException{
		return (List<ConfigInfo>) queryForList("findConfigInfosByCondition", condition, start, limit);
	}
	
	public List<ConfigInfo> findConfigInfosByCondition(Map condition)  throws SQLException{
		return (List<ConfigInfo>) queryForList("findConfigInfosByCondition", condition);
	}
	
	public ConfigInfo saveConfigInfo(ConfigInfo configInfo) throws SQLException {
		try {
			insert("saveConfigInfo", configInfo);
			return configInfo;
		} catch (IctException e) {
			logger.debug("保存配置信息失败");
			throw new SQLException("保存配置信息失败");
		}
	}
	
	public ConfigInfo updateConfigInfo(ConfigInfo configInfo) throws SQLException {
		try {
			update("updateConfigInfo", configInfo);
		} catch (IctException e) {
			logger.debug("更新配置信息失败");
			throw new SQLException("更新配置信息失败");
		}
		return configInfo;
	}
	
	public void deleteConfigInfoById(String configCode) throws SQLException {
		if(!StringUtil.isEmpty(configCode)){
			if(configCode.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(configCode,0);
				if(ids != null)
					try {
						update("deleteConfigInfoByIds", ids);
					} catch (IctException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				try {
					update("deleteConfigInfoById", configCode);
				} catch (IctException e) {
					// TODO Auto-generated catch block
					logger.debug("删除配置信息失败");
					throw new SQLException("删除配置信息失败");
				}
			}
		}
	}
}
