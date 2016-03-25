/**
 * @author lifa
 * @create 2009-6-29
 * @file UserRole.java
 * @since v0.1
 * 
 */
package com.osource.module.system.model;

import com.osource.core.model.BaseModel;

/**
 * @author Fun
 * 
 */
public class UserRole extends BaseModel {

    private Integer roleId;
    private Integer userId;
    private String roleName;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
