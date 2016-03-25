/**
 * @author luoj
 * @create 2009-3-30
 * @file Permission.java
 * @since v0.1
 * 
 */
package com.osource.base.model;

import com.osource.core.model.BaseModel;

/**
 *
 */
public class Permission extends BaseModel {

    private static final long serialVersionUID = -207363687605744232L;

    private String link;
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

    public Permission() {
    }

    public Permission(String link) {
        this.link = link;
    }

    public String toString() {
        return link;
    }

}
