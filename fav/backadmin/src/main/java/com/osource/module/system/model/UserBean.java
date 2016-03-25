package com.osource.module.system.model;

import java.util.Date;

import com.osource.core.model.BaseModel;

/**
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2009-11-4 15:37:48
 */
@SuppressWarnings("serial")
public class UserBean extends BaseModel {
    private String loginName;
    private String password;
    private String name;
    private String sex;
    private String idNum;
    private Date birthday;
    private String nativePlace;
    private String politicalStatus;
    private String locNo;
    private String phoneNo;
    private String workUnit;
    private String establishment;
    private String duty;
    private String address;
    private String staffType;
    private String email;
    private String faxNo;
    private String postcode;
    private String eduBg;
    private String affiance;
    private String staffStatus;
    private String workHis;
    private String studyHis;
    private String interest;
    private String picPath;
    private String userNode;
    private String formerPassword;

    private String rybm;
    private Date lastLoginTime;
    private Integer onlineTimes;

    private String nickname;
    private String userType;
    private String vipPass;
    private String country;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStaffType() {
        return staffType;
    }

    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEduBg() {
        return eduBg;
    }

    public void setEduBg(String eduBg) {
        this.eduBg = eduBg;
    }

    public String getAffiance() {
        return affiance;
    }

    public void setAffiance(String affiance) {
        this.affiance = affiance;
    }

    public String getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getWorkHis() {
        return workHis;
    }

    public void setWorkHis(String workHis) {
        this.workHis = workHis;
    }

    public String getStudyHis() {
        return studyHis;
    }

    public void setStudyHis(String studyHis) {
        this.studyHis = studyHis;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getUserNode() {
        return userNode;
    }

    public void setUserNode(String userNode) {
        this.userNode = userNode;
    }

    public String getRybm() {
        return rybm;
    }

    public void setRybm(String rybm) {
        this.rybm = rybm;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getOnlineTimes() {
        return onlineTimes;
    }

    public void setOnlineTimes(Integer onlineTimes) {
        this.onlineTimes = onlineTimes;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVipPass() {
        return vipPass;
    }

    public void setVipPass(String vipPass) {
        this.vipPass = vipPass;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFormerPassword() {
        return formerPassword;
    }

    public void setFormerPassword(String formerPassword) {
        this.formerPassword = formerPassword;
    }

}
