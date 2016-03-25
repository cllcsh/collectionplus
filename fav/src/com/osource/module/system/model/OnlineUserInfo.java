/**
 * 文件名：OnlineUserInfo.java

 * 
 */
package com.osource.module.system.model;

import com.osource.core.model.BaseModel;

/**
 * 项目名称：osource
 * <p>
 * 类名称：OnlineUserInfo
 * <p>
 * 类描述：
 * <p>
 * 创建人：luoj
 * <p>
 * 创建时间：2010-01-07
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class OnlineUserInfo extends BaseModel {
    private String name;
    private String deptName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}