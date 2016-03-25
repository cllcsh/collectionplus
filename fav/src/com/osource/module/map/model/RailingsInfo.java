/**
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingInfo.java
 * @since v0.1
 */
package com.osource.module.map.model;

import java.util.List;

import com.osource.core.model.BaseModel;

/**
 * 电子围栏
 * 
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingInfo.java
 * @since v0.1
 */
public class RailingsInfo extends BaseModel {
    private static final long serialVersionUID = -6761512195467029572L;
    // 名称
    private String name;
    // 类型
    private String type;
    // 电子围栏坐标点
    private List<RailingsCoordinateInfo> coordinateList;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the coordinateList
     */
    public List<RailingsCoordinateInfo> getCoordinateList() {
        return coordinateList;
    }

    /**
     * @param coordinateList
     *            the coordinateList to set
     */
    public void setCoordinateList(List<RailingsCoordinateInfo> coordinateList) {
        this.coordinateList = coordinateList;
    }
}
