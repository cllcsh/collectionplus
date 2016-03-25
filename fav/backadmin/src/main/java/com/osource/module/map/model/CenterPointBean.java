/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointInfo.java
 * @since v0.1
 */
package com.osource.module.map.model;

import com.osource.core.model.BaseModel;

/**
 * 中心点
 * 
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointInfo.java
 * @since v0.1
 */
public class CenterPointBean extends BaseModel {
    private static final long serialVersionUID = -4286826742307547799L;
    // 经度
    private Double longitude;
    // 纬度
    private Double latitude;
    // 缩放级别
    private Double zoomLevel;
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

    /**
     * @return the zoomLevel
     */
    public Double getZoomLevel() {
        return zoomLevel;
    }

    /**
     * @param zoomLevel
     *            the zoomLevel to set
     */
    public void setZoomLevel(Double zoomLevel) {
        this.zoomLevel = zoomLevel;
    }
}
