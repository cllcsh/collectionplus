package com.osource.module.system.dao;

import java.util.List;

import com.osource.module.system.model.UserPermissionInfo;
import com.osource.module.system.model.UserRole;
import com.osource.orm.ibatis.BaseDao;

public interface UserPermissionDao extends BaseDao<UserPermissionInfo> {
	public List<UserRole> findUserRoleList(Integer userId);
	public UserRole saveUserRole(UserRole userRole);
	public void deleteUserRoleByUserId(String id);
}