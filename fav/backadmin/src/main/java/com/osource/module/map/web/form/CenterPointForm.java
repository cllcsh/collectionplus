/**
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointForm.java
 * @since v0.1
 */
package com.osource.module.map.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 中心点
 * @author zhouhao
 * @create 2009-11-4
 * @file CenterPointForm.java
 * @since v0.1
 */
public class CenterPointForm extends BaseForm {

	private static final long serialVersionUID = 1L;
	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;
	// 缩放级别
	private Double zoomLevel;
	
	// 围栏类别:0: 折线, 1: 范围(矩形、多边形等), 2:(中心)点
	private String type;
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
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
	 * @param latitude the latitude to set
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
	 * @param zoomLevel the zoomLevel to set
	 */
	public void setZoomLevel(Double zoomLevel) {
		this.zoomLevel = zoomLevel;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
