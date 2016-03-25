package com.osource.module.admin.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 
 * 项目名称：osource 类名称：LoginLogBean 类描述： 创建人：zhangyan 创建时间：Nov 4, 2009 6:23:47 PM 修改人：Administrator 修改时间：Nov 4, 2009
 * 6:23:47 PM 修改备注：
 * 
 * @version
 * 
 */
@SuppressWarnings("serial")
public class LoginLogBean extends BaseModel implements TableNameAware {
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
