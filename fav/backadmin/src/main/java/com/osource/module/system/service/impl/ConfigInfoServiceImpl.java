package com.osource.module.system.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.model.ConfigInfo;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.dao.ConfigInfoDao;
import com.osource.module.system.service.ConfigInfoService;
import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.util.StringUtil;

@Service
@Scope("prototype")
@Transactional
public class ConfigInfoServiceImpl extends BaseServiceImpl implements ConfigInfoService{
	private static final Log logger = LogFactory.getLog(ConfigInfoServiceImpl.class);
	@Autowired
	private ConfigInfoDao configInfoDao;
	
	public ConfigInfoServiceImpl() {
		/*super();
		this.setConfigInfoDao(new ConfigInfoDaoImpl());*/
	}
	
	public ConfigInfo select(String configKey) throws IctException {
		try {
			return configInfoDao.select(configKey);
		} catch (SQLException e) {
			logger.debug("查询配置信息失败");
			logger.debug(e);
			throw new IctException("查询配置信息失败");
		}
	}
	
	@SuppressWarnings("unchecked")
	public PageList findConfigInfoListByCondition(Map condition,Pages pages){
		PageList result = new PageList();
		result.setPages(pages);
		try {
			pages.setTotal(configInfoDao.getAllConfigNumByCondition(condition));
			pages.executeCount();
			result.addAll(configInfoDao.findConfigInfosByCondition(condition, pages.getStart(), pages.getLimit()));
			return result;
		} catch (SQLException e) {
			logger.debug("查询分页配置表失败");
			logger.debug(e);
			return result;
		}
	}
	
	/**
	 * <通过条件查询信息列表>
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ConfigInfo> findConfigInfosByCondition(Map condition) throws IctException{
		try {
			List<ConfigInfo> list = configInfoDao.findConfigInfosByCondition(condition);
			return list;
		} catch (SQLException e) {
			logger.debug("查询配置信息失败");
			logger.debug(e);
			throw new IctException("查询配置信息失败");
		}
	}
	
	/**
	 * 保存ConfigInfo对象
	 * 
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 * @throws IctException
	 */
	public ConfigInfo saveConfigInfo(ConfigInfo configInfo) throws IctException {
		try {
			//判断是否配置键有重名
			ConfigInfo oldConfigInfo=select(configInfo.getConfigKey());
			if(oldConfigInfo!=null){
				logger.debug("配置键已存在");
				throw new IctException("配置键已存在");
			}
			configInfo = configInfoDao.saveConfigInfo(configInfo);
		} catch (SQLException e) {
			logger.debug("保存配置信息失败");
			throw new IctException("保存配置信息失败");
		}
		return configInfo;
	}
	
	/**
	 * 更新ConfigInfo对象
	 * 
	 * @param configInfo
	 *            ConfigInfo
	 * @return ConfigInfo
	 * @throws IctException
	 */
	public ConfigInfo updateConfigInfo(ConfigInfo configInfo) throws IctException {
		try {
			//判断是否配置键有重名
			ConfigInfo oldConfigInfo=select(configInfo.getConfigKey());
			if(oldConfigInfo!=null){
				//如果修改的配置编号与数据记录中的配置编号不符，则报出配置键已存在
				if(!configInfo.getConfigCode().equals(oldConfigInfo.getConfigCode())){
					logger.debug("配置键已存在");
					throw new IctException("配置键已存在");
				}
			}
			configInfo = configInfoDao.updateConfigInfo(configInfo);
			return configInfo;
		} catch (SQLException e) {
			logger.debug("更新用户信息失败");
			throw new IctException("更新用户信息失败");
		}
	}
	/**
	   * 根据用户名删除配置对象
	   * @param configCode String
	   */
	public void deleteConfigInfoById(String configCode) throws IctException {
		if (!StringUtil.isEmpty(configCode)) {
			try {
				configInfoDao.deleteConfigInfoById(configCode);
			} catch (Exception e) {
				logger.debug("根据配置编号删除配置对象失败");
				logger.error(e);
				throw new IctException(e);
			}
		}
	}
	
	public ConfigInfoDao getConfigInfoDao() {
		return configInfoDao;
	}
	public void setConfigInfoDao(ConfigInfoDao configInfoDao) {
		this.configInfoDao = configInfoDao;
	}
}
