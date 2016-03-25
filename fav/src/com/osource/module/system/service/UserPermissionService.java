package com.osource.module.system.service;

import java.util.List;

import com.osource.core.exception.IctException;
import com.osource.module.system.model.UserPermissionInfo;
import com.osource.module.system.model.UserRole;
import com.osource.orm.ibatis.BaseService;

public interface UserPermissionService extends BaseService<UserPermissionInfo> {
	public List<UserRole> findUserRoleList(Integer userId);

	public UserPermissionInfo saveUserPermissionInfo(
			UserPermissionInfo userPermissionInfo) throws IctException;

	public UserPermissionInfo updateUserPermissionInfo(
			UserPermissionInfo userPermissionInfo) throws IctException;
	
	public UserPermissionInfo saveUserRoleInfo(
			UserPermissionInfo userPermissionInfo) throws IctException;

}