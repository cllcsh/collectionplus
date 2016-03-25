/**
 * 文件名：ActualBean.java

 * 
 */
package com.osource.module.map.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>
 * 类名称：ActualBean
 * <p>
 * 类描述：
 * <p>
 * 创建人：zhangneng
 * <p>
 * 创建时间：2013-06-28
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class ActualBean extends BaseModel implements TableNameAware {
    public String getDbTableName() {
        return "tb_criminal";
    }

    private String deptName;
    private String staffName;
    private String criminalName;
    private String sex;
    private String chargeDetail;
    private String locNo;
    private Date locDate;
    private String placeName;
    private String locCode;
    private Integer lastLocationId;
    private String locDateStr;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCriminalName() {
        return criminalName;
    }

    public void setCriminalName(String criminalName) {
        this.criminalName = criminalName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChargeDetail() {
        return chargeDetail;
    }

    public void setChargeDetail(String chargeDetail) {
        this.chargeDetail = chargeDetail;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public Date getLocDate() {
        return locDate;
    }

    public void setLocDate(Date locDate) {
        this.locDate = locDate;
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

    public Integer getLastLocationId() {
        return lastLocationId;
    }

    public void setLastLocationId(Integer lastLocationId) {
        this.lastLocationId = lastLocationId;
    }

    public String getLocDateStr() {
        return locDateStr;
    }

    public void setLocDateStr(String locDateStr) {
        this.locDateStr = locDateStr;
    }
}