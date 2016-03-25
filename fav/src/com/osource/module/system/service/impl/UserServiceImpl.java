package com.osource.module.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.module.system.dao.UserDao;
import com.osource.module.system.model.UserInfo;
import com.osource.module.system.model.UserRole;
import com.osource.module.system.service.UserService;
import com.osource.orm.ibatis.BaseServiceImpl;

@Service
@Scope("prototype")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserInfo> implements UserService {

    public long countByIdCardOrRegNumber(Map<String, String> condition) {
        return getDao().countByIdCardOrRegNumber(condition);
    }

    /** setter and getter methods **/

    protected UserDao getDao() {
        return (UserDao) super.getDao();
    }

    @Autowired
    public void setDao(UserDao userDao) {
        super.setDao(userDao);
    }

    public List findAllIdByLoginUser(String loginNames) {
        List<String> list = new ArrayList<String>();
        for (String loginName : loginNames.split(",")) {
            list.add(loginName);
        }
        return getDao().findAllIdByLoginUser(list);
    }
    
    public UserRole saveUserRole(UserRole userRole) {
    	return getDao().saveUserRole(userRole);
    }
}
