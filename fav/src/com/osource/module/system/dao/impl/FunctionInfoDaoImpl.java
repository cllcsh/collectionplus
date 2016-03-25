package com.osource.module.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.core.exception.IctException;
import com.osource.module.system.dao.FunctionInfoDao;
import com.osource.module.system.model.FunctionInfo;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;

@SuppressWarnings("unchecked")
@Repository
public class FunctionInfoDaoImpl extends BaseDaoImpl implements FunctionInfoDao {

	public void deleteFunctionInfoById(Integer id) throws IctException {
		update("system_deleteFunctionById", id);
	}
	public void deleteFunctionInfoByIds(String id) throws IctException {
		if(!StringUtil.isEmpty(id)){
			if(id.indexOf(",") > -1){
				String ids = StringUtil.toSqlInStr(id,0);
				if(ids != null)
				update("system_deleteFunctionInIds", ids);
			} else {
				update("system_deleteFunctionById", Integer.valueOf(id));
			}
		}
	}

	public FunctionInfo findFunctionInfoById(Integer id) {
		return (FunctionInfo) queryForObject("system_findFunctionById", id);
	}

	public List<FunctionInfo> findFunctionInfoList(String orderby, int ascOrDesc, int start, int limit) {
		return null;
	}

	public List<FunctionInfo> findFunctionInfoListByCondition(Map condition, int start, int limit) {
		return queryForList("system_findFunctionByCondition",condition, start, limit);
	}

	public long getAllFunctionNum() {
		int num = 0;
		num = (Integer) queryForObject("system_getAllFunctionNum");
		return num;
	}

	public long getFunctionNumByCondition(Map condition) {
		int num = 0;
		num = (Integer) queryForObject("system_getAllFunctionNumByCondition", condition);
		return num;
	}

	public FunctionInfo saveFunctionInfo(FunctionInfo functionInfo)	throws IctException {
		return (FunctionInfo)insert("system_saveFunction", functionInfo);
	}

	public FunctionInfo updateFunctionInfo(FunctionInfo functionInfo) throws IctException {
		update("system_updateFunction", functionInfo);
		return functionInfo;
	}
	public List findAllFunctionList() {
		return queryForList("system_findAllFunction");
	}
	
	public List findFunctionListByUserId(Integer userId) {
		return queryForList("system_findFunctionListByUserId", userId);
	}
	public List findFrontList(){
		return queryForList("system_findFrontFunction");
	}

}
