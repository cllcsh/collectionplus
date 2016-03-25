package com.osource.module.system.dao;

import java.util.List;
import java.util.Map;

import com.osource.module.system.model.UserInfo;
import com.osource.module.system.model.UserRole;
import com.osource.orm.ibatis.BaseDao;

public interface UserDao extends BaseDao<UserInfo> {
	public long countByIdCardOrRegNumber(Map<String, String> condition);
	
	public List<String> findAllIdByLoginUser(List<String> loginNames);
	
	public UserRole saveUserRole(UserRole userRole);
	
}