package com.osource.module.system.model;

import com.osource.core.model.BaseModel;

public class FunctionInfo extends BaseModel {
    private static final long serialVersionUID = -2380807816399066107L;
    private String functionName;
    private String userType;
    private String link;
    private Integer frontFucntionId;
    private String frontFucntionName;
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

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getFrontFucntionId() {
        return frontFucntionId;
    }

    public void setFrontFucntionId(Integer frontFucntionId) {
        this.frontFucntionId = frontFucntionId;
    }

    public String getFrontFucntionName() {
        return frontFucntionName;
    }

    public void setFrontFucntionName(String frontFucntionName) {
        this.frontFucntionName = frontFucntionName;
    }
}
