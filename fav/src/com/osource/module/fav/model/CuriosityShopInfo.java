/**
 * 文件名：CuriosityShopInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：CuriosityShopInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CuriosityShopInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_curiosity_shop";
	}
	// 名称
    private String name;
    // 地址
    private String address;
    // 电话
    private String phone;
    // 简介
    private String introduction;
    // 纬度
    private String lat;
    // 经度
    private String longitude;
    // 省份
    private String province;
    // 城市
    private String city;
    // 区县
    private String county;
    // 图标
    private String icon;
    private String images;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
}