/**
 * 文件名：LocaQueryBean.java

 * 
 */
package com.osource.module.map.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>
 * 类名称：LocaQueryBean
 * <p>
 * 类描述：
 * <p>
 * 创建人：yangs
 * <p>
 * 创建时间：2010-02-24
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class LocaQueryBean extends BaseModel implements TableNameAware {
    public String getDbTableName() {
        return "tb_location";
    }

    private Integer num;// 序号

    private String name;

    private String checked;

    // 定位记录编号
    private Integer locId;

    // 定位号码
    private String locNum;

    // 定位时间
    private String locDate;

    // 结果代码
    private String locCode;

    // 经度
    private Double longitude;

    // 纬度
    private Double latitude;

    // 纠偏经度
    private Double rectifyLong;

    // 纠偏纬度
    private Double rectifyLat;

    // 类型
    private String type;

    // 定位表中围栏编号
    private Integer railingsId;

    // 矫正对象活动区域Id
    private String areaId;

    // 矫正对象活动区域名称
    private String areaName;

    // 持有者
    private String holder;

    // imsi号
    private String imsi;

    // 报警状态
    private String alarmStatus;

    // 地名
    private String placeName;

    private String pattern;

    // 偏差半径
    private String radius;

    // 定义位置来源 0-未使用；6-网络小区号；18-手机AGPS（包括AGPS+AFLT的混合模式）；20-手机AFLT；默认值 0
    private String posour;

    // 用来封装相关参数信息
    private String paramStr;

    // 照片
    private String pic_path;

    /* add by liurenbao start */

    private Integer staffId;
    private String staffName;
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

    public final Integer getStaffId() {
        return staffId;
    }

    public final void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public final String getStaffName() {
        return staffName;
    }

    public final void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    /* add by liurenbao end */

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getLocNum() {
        return locNum;
    }

    public void setLocNum(String locNum) {
        this.locNum = locNum;
    }

    public String getLocDate() {
        return locDate;
    }

    public void setLocDate(String locDate) {
        this.locDate = locDate;
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

    public Double getRectifyLong() {
        return rectifyLong;
    }

    public void setRectifyLong(Double rectifyLong) {
        this.rectifyLong = rectifyLong;
    }

    public Double getRectifyLat() {
        return rectifyLat;
    }

    public void setRectifyLat(Double rectifyLat) {
        this.rectifyLat = rectifyLat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRailingsId() {
        return railingsId;
    }

    public void setRailingsId(Integer railingsId) {
        this.railingsId = railingsId;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getPosour() {
        return posour;
    }

    public void setPosour(String posour) {
        this.posour = posour;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String picPath) {
        pic_path = picPath;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}