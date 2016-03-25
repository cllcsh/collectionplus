package com.osource.module.system.dao;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.FunctionInfo;

@SuppressWarnings("unchecked")
public interface FunctionInfoDao {
	public FunctionInfo findFunctionInfoById(Integer id);

	public FunctionInfo saveFunctionInfo(FunctionInfo functionInfo)
			throws IctException;

	public FunctionInfo updateFunctionInfo(FunctionInfo functionInfo)
			throws IctException;

	public long getAllFunctionNum();

	public List findAllFunctionList();

	public List findFrontList();

	public List<FunctionInfo> findFunctionInfoList(String orderby,
			int ascOrDesc, int start, int limit);

	public long getFunctionNumByCondition(Map condition);

	public List<FunctionInfo> findFunctionInfoListByCondition(Map condition,
			int start, int limit);

	public void deleteFunctionInfoById(Integer id) throws IctException;

	public void deleteFunctionInfoByIds(String id) throws IctException;

	/**
	 * <b>根据用户id查找用户有权限的功能<b>
	 * <p>
	 * 用户功能树结构列表调用此接口
	 * 
	 * @return
	 */
	public List findFunctionListByUserId(Integer userId);
}
