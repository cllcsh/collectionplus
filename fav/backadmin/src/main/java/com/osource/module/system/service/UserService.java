package com.osource.module.system.service;

import java.util.List;
import java.util.Map;

import com.osource.orm.ibatis.BaseService;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.model.UserRole;

public interface UserService extends BaseService<UserInfo> {
	public long countByIdCardOrRegNumber(Map<String, String> condition);
	
	public List findAllIdByLoginUser(String loginNames);
	
	public UserRole saveUserRole(UserRole userRole);
}