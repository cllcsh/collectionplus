/**
 * 文件名：MapForm.java
 * 
 */
package com.osource.module.map.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：MapForm
 * <p>类描述：
 * <p>创建人：zhangneng
 * <p>创建时间：2013-06-28
 * @version 2.0
 */
@SuppressWarnings("serial")
public class MapForm extends BaseForm {
	private Integer locationId;
	private double rectifyLong;
	private double rectifyLat;
	private Integer railingsId;
	private String locationNum;
	private String placeName;
	private String locDate;
	private String holder;
	private String displayWay;
	private String terminalIds;
	private Double longitude;
	private Double latitude;
	private Double zoomLevel;
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public double getRectifyLong() {
		return rectifyLong;
	}
	public void setRectifyLong(double rectifyLong) {
		this.rectifyLong = rectifyLong;
	}
	public double getRectifyLat() {
		return rectifyLat;
	}
	public void setRectifyLat(double rectifyLat) {
		this.rectifyLat = rectifyLat;
	}
	public Integer getRailingsId() {
		return railingsId;
	}
	public void setRailingsId(Integer railingsId) {
		this.railingsId = railingsId;
	}
	public String getLocationNum() {
		return locationNum;
	}
	public void setLocationNum(String locationNum) {
		this.locationNum = locationNum;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getLocDate() {
		return locDate;
	}
	public void setLocDate(String locDate) {
		this.locDate = locDate;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public String getDisplayWay() {
		return displayWay;
	}
	public void setDisplayWay(String displayWay) {
		this.displayWay = displayWay;
	}
	public String getTerminalIds() {
		return terminalIds;
	}
	public void setTerminalIds(String terminalIds) {
		this.terminalIds = terminalIds;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getZoomLevel() {
		return zoomLevel;
	}
	public void setZoomLevel(Double zoomLevel) {
		this.zoomLevel = zoomLevel;
	}
}