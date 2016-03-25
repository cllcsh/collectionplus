package com.osource.module.system.dao;

import java.util.List;
import java.util.Map;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.RoleBean;
import com.osource.module.system.model.RoleFunction;

@SuppressWarnings("unchecked")
public interface RoleInfoDao {
	public RoleBean findRoleInfoById(String id);

	public List<RoleBean> findRoleInfoList(Map condition);

	public List<RoleBean> findRolesByDepartmentId(Map condition);

	public RoleBean saveRoleInfo(RoleBean roleInfo) throws IctException;

	public RoleBean updateRoleInfo(RoleBean roleInfo) throws IctException;

	public long getAllRoleNum();

	// public List<RoleBean> findRoleInfoList(String orderby, int ascOrDesc, int
	// start, int limit);
	public long getRoleNumByCondition(Map condition);

	public List<RoleBean> findRoleInfoListByCondition(Map condition, int start,
			int limit);

	public void deleteRoleInfoById(String id) throws IctException;

	public void deleteRoleInfoByIds(String id) throws IctException;

	// edit by weiwu
	public RoleFunction saveRoleFunction(RoleFunction roleFunctionForm)
			throws IctException;

	public void deleteRoleFunctionFormById(String roleId) throws IctException;

	public List<RoleFunction> findRoleFunctionById(Integer roleId);
}
