/**
 * @author zhouhao
 * @create 2009-5-11
 * @file BorderSettingForm.java
 * @since v0.1
 */
package com.osource.module.map.web.form;

import java.util.List;

import com.osource.base.web.form.BaseForm;
import com.osource.module.map.model.AreaCodeBean;
import com.osource.module.map.model.RailingsInfo;
/**
 * 围栏管理
 * @author zhouhao
 * @create 2009-5-11
 * @file BorderSettingForm.java
 * @since v0.1
 */
public class RailingsForm extends BaseForm {

	// 经度
	private Double longitude;
	// 纬度
	private Double latitude;
	// 缩放级别
	private Double zoomLevel;
	//围栏名称
	private String railingsName;
	//围栏类型
	private String railingsType;
	//围栏id集
	private String ids;
	// 经度集
	private String longitudes;
	// 纬度集
	private String latitudes;
	// 围栏类别:0: 折线, 1: 范围, 2:点
	private String overlayType;
	// 备注
	private String remark;
	//围栏模型 
	private RailingsInfo railingsInfo;
	
	//区域集
	private List<AreaCodeBean> areaCodeList;
	//区域
	private AreaCodeBean areaCode;
	
	public String getLongitudes() {
		return longitudes;
	}
	public void setLongitudes(String longitudes) {
		this.longitudes = longitudes;
	}
	public String getLatitudes() {
		return latitudes;
	}
	public void setLatitudes(String latitudes) {
		this.latitudes = latitudes;
	}

	public String getOverlayType() {
		return overlayType;
	}
	public void setOverlayType(String overlayType) {
		if((overlayType != null) && (!overlayType.equals(""))) {
			this.overlayType = overlayType;
		} else {
			this.overlayType = "1";
		}
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
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
	public String getRailingsName() {
		return railingsName;
	}
	public void setRailingsName(String railingsName) {
		this.railingsName = railingsName;
	}
	public String getRailingsType() {
		return railingsType;
	}
	public void setRailingsType(String railingsType) {
		this.railingsType = railingsType;
	}
	public RailingsInfo getRailingsInfo() {
		return railingsInfo;
	}
	public void setRailingsInfo(RailingsInfo railingsInfo) {
		this.railingsInfo = railingsInfo;
	}
	public List<AreaCodeBean> getAreaCodeList() {
		return areaCodeList;
	}
	public void setAreaCodeList(List<AreaCodeBean> areaCodeList) {
		this.areaCodeList = areaCodeList;
	}
	public AreaCodeBean getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(AreaCodeBean areaCode) {
		this.areaCode = areaCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
