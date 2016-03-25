package com.osource.module.system.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

@SuppressWarnings("serial")
public class RoleFunction extends BaseModel implements TableNameAware {
    public String getDbTableName() {
        return "tb_role_func";
    }

    private Integer functionId;
    private Integer roleId;
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

    public RoleFunction(Integer functionId, Integer roleId) {
        this.functionId = functionId;
        this.roleId = roleId;
    }

    public RoleFunction() {
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
