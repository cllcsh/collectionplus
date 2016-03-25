package com.osource.base.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.osource.base.dao.LoginDao;
import com.osource.base.model.LoginInfo;
import com.osource.base.model.LoginLog;
import com.osource.core.IDgenerator;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.UserInfo;
import com.osource.orm.ibatis.BaseDaoImpl;

@Repository
public class LoginDaoImpl extends BaseDaoImpl<LoginInfo> implements LoginDao {

    public UserInfo getByLoginName(String loginName) {
        return (UserInfo) getSqlMapClientTemplate().queryForObject("system_user_login_getByLoginName", loginName);
    }

    public LoginLog saveLoginLog(LoginLog loginLog) throws IctException {
        loginLog.setId(IDgenerator.gettNextID(loginLog.getDbTableName()));
        return (LoginLog) getSqlMapClientTemplate().insert("ict_login_loginLog_save", loginLog);
    }

    @Override
    public String getEntityName() {
        return "ict_login";
    }

    public void updateLoginDate(Integer id) throws IctException {
        getSqlMapClientTemplate().update(getEntityName() + "_update_last_login_date", id);
    }

    public void updateLoginTimes(Integer id, Integer times) throws IctException {
        Map map = new HashMap();
        map.put("id", id);
        map.put("onlineTimes", times);
        getSqlMapClientTemplate().update(getEntityName() + "_update_online_times", map);
    }

    public void updateLoginLogTimes(Integer id, String times) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("onlineTimes", times);
        getSqlMapClientTemplate().update(getEntityName() + "_update_loginlog_onlinetime", map);
    }

    public LoginInfo checkPasswordByMobile(String userTel, String password) {
        Map map = new HashMap();
        map.put("userTel", userTel);
        map.put("password", password);
        LoginInfo info = (LoginInfo) getSqlMapClientTemplate().queryForObject(getEntityName() + "_getByLoginInfo", map);
        return info;
    }

    public void updateLoginCount(String loginName) throws IctException {
        getSqlMapClientTemplate().update(getEntityName() + "_update_login_count", loginName);
    }

}