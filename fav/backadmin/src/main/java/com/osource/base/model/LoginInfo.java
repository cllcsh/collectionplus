package com.osource.base.model;

import com.osource.core.model.BaseModel;

public class LoginInfo extends BaseModel {

    private static final long serialVersionUID = -8324261871041163899L;

    // 管理员编号
    private String managerId;

    // 司法单位编号
    private String elisorId;

    // 数据权限
    private String dataType;

    // 角色编号
    private String roleid;

    // 权限类别
    private String roleType;

    // 用户编号
    private String userId;

    // 登录用户名
    private String loginname;

    // 密码
    private String password;

    // 用户名
    private String username;

    // 机构名称
    private String deptName;

    // 电话号码
    private String phoneNo;

    private String deptNode;
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

    // 审批权限 '0',无审批权限; '1',有审批权限
    private String approveFlag;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getElisorId() {
        return elisorId;
    }

    public void setElisorId(String elisorId) {
        this.elisorId = elisorId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * phoneNo
     * 
     * @return the phoneNo
     * @since CodingExample Ver(编码范例查看) 1.0
     */

    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo
     *            the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDeptNode() {
        return deptNode;
    }

    public void setDeptNode(String deptNode) {
        this.deptNode = deptNode;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

}
