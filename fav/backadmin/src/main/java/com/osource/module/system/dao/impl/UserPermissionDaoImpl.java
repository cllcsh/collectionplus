package com.osource.module.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.osource.module.system.dao.UserPermissionDao;
import com.osource.module.system.model.UserPermissionInfo;
import com.osource.module.system.model.UserRole;
import com.osource.orm.ibatis.BaseDaoImpl;
import com.osource.util.StringUtil;

@Repository
public class UserPermissionDaoImpl extends BaseDaoImpl<UserPermissionInfo> implements UserPermissionDao {
	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRoleList(Integer userId){
		List<UserRole> userRoleList = null;
		userRoleList = (List<UserRole>)queryForList("system_userPermission_findUserRoleList", userId);
		
		return userRoleList;
	}
	
	public UserRole saveUserRole(UserRole userRole){
		try {
			insert("system_userPermission_saveUserRole", userRole);
		} catch(Exception e) {
			logger.error(e);
		}
		return userRole;
	}
	
	public void deleteUserRoleByUserId(String id){
		try{
			if(!StringUtil.isEmpty(id)){
				if(id.indexOf(",") > -1){
					String ids = StringUtil.toSqlInStr(id,0);
					if(ids != null)
					   delete("system_userPermission_deleteUserRoleByUserIds", ids);
				} else {
					delete("system_userPermission_deleteUserRoleByUserId", id);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	@Override
	public String getEntityName() {
		return "system_userPermission";
	}
	
}