/**
 * @author luoj
 * @create 2009-3-27
 * @file UserSession.java
 * @since v0.1
 * 
 */
package com.osource.base.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//import com.osource.module.map.model.CenterPointBean;

/**
 *
 */
@SuppressWarnings("serial")
public class UserSession extends com.osource.core.AbstractUserSession implements Serializable {
    private String userName;
    private String loginName;
    // private Integer deptId; //机构编号
    private String deptName; // 机构名称
    private int maxSmsNum;// 每月最大允许发送短信数

    private String userType;

    private String remoteAddr;

    // 风格信息
    private String themeName = "default";

    // 权限信息
    private String deptNode; // 机构结点
    private Map userRoles = new HashMap();
    private Map userPermissions = new HashMap();

    private String approveFlag; // 审批权限
    
    private long notReadLetterNum; //未读站内信数量

    public UserSession(com.osource.core.AbstractUserSession userSession) {
        super(userSession);
    }

    public boolean isHavePermission(String actionName) {
        Object permission = userPermissions.get(actionName);
        return permission != null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        if (themeName != null)
            this.themeName = themeName;
    }

    public Map getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Map userRoles) {
        this.userRoles = userRoles;
    }

    public Map getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(Map userPermissions) {
        this.userPermissions = userPermissions;
    }

    // public Integer getDeptId() {
    // return deptId;
    // }
    //
    //
    // public void setDeptId(Integer deptId) {
    // this.deptId = deptId;
    // }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDeptNode() {
        return deptNode;
    }

    public void setDeptNode(String deptNode) {
        this.deptNode = deptNode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getMaxSmsNum() {
        return maxSmsNum;
    }

    public void setMaxSmsNum(int maxSmsNum) {
        this.maxSmsNum = maxSmsNum;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *            the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public long getNotReadLetterNum() {
		return notReadLetterNum;
	}

	public void setNotReadLetterNum(long notReadLetterNum) {
		this.notReadLetterNum = notReadLetterNum;
	}

    // //功能信息
    // private CenterPointBean centerPointInfo;//中心点信息
    //	
    // public CenterPointBean getCenterPointInfo() {
    // return centerPointInfo;
    // }
    //
    // public void setCenterPointInfo(CenterPointBean centerPointInfo) {
    // this.centerPointInfo = centerPointInfo;
    // }

}
