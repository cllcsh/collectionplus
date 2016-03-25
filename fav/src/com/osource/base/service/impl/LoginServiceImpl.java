package com.osource.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osource.base.dao.LoginDao;
import com.osource.base.model.LoginInfo;
import com.osource.base.model.LoginLog;
import com.osource.base.service.LoginService;
import com.osource.core.exception.IctException;
import com.osource.module.system.model.UserInfo;
import com.osource.orm.ibatis.BaseServiceImpl;
import com.osource.util.Md5PwdEncoder;

@Service
@Scope("prototype")
@Transactional
public class LoginServiceImpl extends BaseServiceImpl<LoginInfo> implements LoginService {

    @Transactional(readOnly = true)
    public UserInfo getByLoginName(String loginName) {
        return getDao().getByLoginName(loginName);
    }

    public LoginLog saveLoginLog(LoginLog loginLog) throws IctException {
        // 更新登录次数
        getDao().updateLoginCount(loginLog.getLoginName());
        return getDao().saveLoginLog(loginLog);
    }

    public void updateLoginDate(Integer id) throws IctException {
        getDao().updateLoginDate(id);
    }

    public void updateLoginTimes(Integer id, Integer times) throws IctException {
        getDao().updateLoginTimes(id, times);
    }

    /** setter and getter methods **/

    protected LoginDao getDao() {
        return (LoginDao) super.getDao();
    }

    @Autowired
    public void setDao(LoginDao loginDao) {
        super.setDao(loginDao);
    }

    public void updateLoginLogTimes(Integer id, String times) throws IctException {
        getDao().updateLoginLogTimes(id, times);

    }

    public boolean checkPasswordByMobile(String userTel, String password) {
        // 密码是明文传递，需进行MD5加密
        Md5PwdEncoder encoder = new Md5PwdEncoder();
        password = encoder.encodePassword(password);
        LoginInfo info = getDao().checkPasswordByMobile(userTel, password);
        if (null != info) {
            return true;
        }
        return false;
    }

    public LoginInfo getLoginfoByMobile(String userTel, String password) {
        // 密码是明文传递，需进行MD5加密
        Md5PwdEncoder encoder = new Md5PwdEncoder();
        password = encoder.encodePassword(password);
        return getDao().checkPasswordByMobile(userTel, password);
    }

}
