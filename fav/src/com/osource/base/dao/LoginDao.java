package com.osource.base.dao;

import com.osource.base.model.LoginInfo;
import com.osource.base.model.LoginLog;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.UserInfo;
import com.osource.orm.ibatis.BaseDao;

public interface LoginDao extends BaseDao<LoginInfo> {
    public UserInfo getByLoginName(String loginName);

    public LoginLog saveLoginLog(LoginLog loginLog) throws IctException;

    public void updateLoginDate(Integer id) throws IctException;

    public void updateLoginTimes(Integer id, Integer times) throws IctException;

    public void updateLoginLogTimes(Integer id, String times) throws IctException;

    public LoginInfo checkPasswordByMobile(String userTel, String password);

    public void updateLoginCount(String loginName) throws IctException;
}