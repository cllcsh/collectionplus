package com.osource.base.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

public class LoginLog extends BaseModel implements TableNameAware {

    public String getDbTableName() {
        return "ts_login_log";
    }

    private String loginName;
    private Date loginDate;
    private String loginIp;
    private String loginResult;
    private String loginAddr;
    private Date loginFirDate;
    private Date loginEndDate;
    private Integer deptId;

    /**
     * @return the deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getLoginFirDate() {
        return loginFirDate;
    }

    public void setLoginFirDate(Date loginFirDate) {
        this.loginFirDate = loginFirDate;
    }

    public Date getLoginEndDate() {
        return loginEndDate;
    }

    public void setLoginEndDate(Date loginEndDate) {
        this.loginEndDate = loginEndDate;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    public String getLoginAddr() {
        return loginAddr;
    }

    public void setLoginAddr(String loginAddr) {
        this.loginAddr = loginAddr;
    }
}
