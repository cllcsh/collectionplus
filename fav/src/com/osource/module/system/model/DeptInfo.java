/**   
 * 文件名：DeptInfo.java   

 *   
 */
package com.osource.module.system.model;

import java.util.List;

import com.osource.core.model.BaseModel;
import com.osource.module.map.model.LocaQueryBean;

/**
 * 

 * @version
 * 
 */
public class DeptInfo extends BaseModel {
    private Integer id;

    private String domain;

    private String code;

    private String name;

    private String manager;

    private String phoneNo;

    private String faxNo;

    private String address;

    private String postcode;

    private String rank;

    private String description;

    private String node;

    private Integer upperDept;

    private String jgbm;// 机构编码
    private Integer organizationTypeId;// 组织类型
    private String longitude;
    private String latitude;
    private Float uvalue;// 信息损失指数
    private Float fvalue;// 风险损失指数
    private Float svalue;// 安全状态指数
    private String dzyx;

    private int listCount = 0;// 该级机构下矫正对象或矫正工作者个数，包括本级机构和所有子级机构

    private List<UserBean> userInDept;

    private List<LocaQueryBean> list = null; // 该级机构下矫正对象或矫正工作者信息列表，不包括子级机构

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public Integer getUpperDept() {
        return upperDept;
    }

    public void setUpperDept(Integer upperDept) {
        this.upperDept = upperDept;
    }

    public List<UserBean> getUserInDept() {
        return userInDept;
    }

    public void setUserInDept(List<UserBean> userInDept) {
        this.userInDept = userInDept;
    }

    public List<LocaQueryBean> getList() {
        return list;
    }

    public void setList(List<LocaQueryBean> list) {
        this.list = list;
    }

    public int getListCount() {
        return listCount;
    }

    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    public String getJgbm() {
        return jgbm;
    }

    public void setJgbm(String jgbm) {
        this.jgbm = jgbm;
    }

    public String getDzyx() {
        return dzyx;
    }

    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrganizationTypeId() {
        return organizationTypeId;
    }

    public void setOrganizationTypeId(Integer organizationTypeId) {
        this.organizationTypeId = organizationTypeId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Float getUvalue() {
        return uvalue;
    }

    public void setUvalue(Float uvalue) {
        this.uvalue = uvalue;
    }

    public Float getFvalue() {
        return fvalue;
    }

    public void setFvalue(Float fvalue) {
        this.fvalue = fvalue;
    }

    public Float getSvalue() {
        return svalue;
    }

    public void setSvalue(Float svalue) {
        this.svalue = svalue;
    }

}
