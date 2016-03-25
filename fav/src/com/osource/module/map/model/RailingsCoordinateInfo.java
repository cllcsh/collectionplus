/**
 * @author zhouhao	
 * @create 2009-11-5
 * @file RailingCoordinateInfo.java
 * @since v0.1
 */
package com.osource.module.map.model;

import com.osource.core.model.BaseModel;

/**
 * 电子围栏坐标点
 * 
 * @author zhouhao
 * @create 2009-11-5
 * @file RailingCoordinateInfo.java
 * @since v0.1
 */
public class RailingsCoordinateInfo extends BaseModel {
    private static final long serialVersionUID = -8780582425285488912L;
    // 电子围栏编号
    private Integer railingsId;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;
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
     * @return the railingsId
     */
    public Integer getRailingsId() {
        return railingsId;
    }

    /**
     * @param railingsId
     *            the railingsId to set
     */
    public void setRailingsId(Integer railingsId) {
        this.railingsId = railingsId;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
