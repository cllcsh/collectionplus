package com.osource.module.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.module.system.dao.UserDao;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.model.UserRole;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserInfo> implements UserDao {

    public long countByIdCardOrRegNumber(Map<String, String> condition) {
        Long result = (Long) queryForObject(getEntityName() + "_countByIdCardOrRegNumber", condition);
        return result.longValue();
    }

    @Override
    public String getEntityName() {
        return "system_user";
    }

    @SuppressWarnings("unchecked")
    public List<String> findAllIdByLoginUser(List<String> loginNames) {
        return this.queryForList(getEntityName() + "_findAllIdByLoginUser", loginNames);
    }
    
    public UserRole saveUserRole(UserRole userRole){
		try {
			insert("system_user_saveUserRole", userRole);
		} catch(Exception e) {
			logger.error(e);
		}
		return userRole;
	}

}