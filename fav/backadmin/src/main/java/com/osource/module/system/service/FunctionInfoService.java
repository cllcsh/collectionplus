package com.osource.module.system.service;

import java.util.List;
import java.util.Map;

import com.osource.base.web.UserSession;
import com.osource.core.exception.IctException;
import com.osource.core.page.PageList;
import com.osource.core.page.Pages;
import com.osource.module.system.model.FunctionInfo;

@SuppressWarnings("unchecked")
public interface FunctionInfoService{
	
	
	public Map findAllFunctionList(UserSession userSession);
	public Map buildFrontList(UserSession userSession);
	
	/**
	 * 新增角色
	 * @param departmentInfo
	 * @return
	 * @throws IctException
	 */
	public FunctionInfo saveFunctionInfo(FunctionInfo functionInfo) throws IctException;	
	/**
	 * 更新角色
	 * @param FunctionInfo
	 * @return
	 * @throws IctException
	 */
	public FunctionInfo updateFunctionInfo(FunctionInfo functionInfo) throws IctException;
	
	/**
	 * 根据角色Id删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteFunctionInfoById(Integer id) throws IctException;
	/**
	 * 根据角色Id组删除信息
	 * @param id
	 * @throws IctException
	 */
	public void deleteFunctionInfoByIds(String ids) throws IctException;
	
	/**
	 * 根据角色Id返回角色信息
	 * @param id
	 * @return
	 */
	public FunctionInfo findFunctionInfoById(Integer id);
	/**
	 * 返回所有有效角色数量
	 * @return
	 */
	public long getAllFunctionNum();
	
	/**
	 * 返回所有功能信息
	 * @param orderby
	 * @param ascOrDesc
	 * @param pages
	 * @return
	 */
	public PageList findFunctionInfoList(String orderby, int ascOrDesc, Pages pages);
	/**
	 * 根据条件返回功能数量
	 * @param condition
	 * @return
	 */
	public long getFunctionNumByCondition(Map condition);
	
	/**
	 * 根据条件返回功能信息
	 * @param condition
	 * @param pages
	 * @return
	 */
	public PageList findUserInfoListByCondition(Map condition,Pages pages);
	
	//edit by weiwu
	/**
	 * 根据Id返回功能列表
	 * @param condition
	 * @param pages
	 * @return
	 */
	public List<FunctionInfo> getFunctionInfoList(Integer userId);
}
