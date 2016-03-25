package com.osource.base.service;

import com.osource.base.model.LoginInfo;
import com.osource.base.model.LoginLog;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.UserInfo;
import com.osource.orm.ibatis.BaseService;

public interface LoginService extends BaseService<LoginInfo> {
    public UserInfo getByLoginName(String loginName);

    public LoginLog saveLoginLog(LoginLog loginLog) throws IctException;

    public void updateLoginDate(Integer id) throws IctException;

    public void updateLoginTimes(Integer id, Integer times) throws IctException;

    public void updateLoginLogTimes(Integer id, String times) throws IctException;

    public boolean checkPasswordByMobile(String userTel, String password);

    public LoginInfo getLoginfoByMobile(String userTel, String password);
}