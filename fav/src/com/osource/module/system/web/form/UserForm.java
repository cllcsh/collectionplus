/**
 * 文件名：UserForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-07-26
 * 
 */
package com.osource.module.system.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>
 * 类名称：UserForm
 * <p>
 * 类描述：
 * <p>
 * 创建人：pachong
 * <p>
 * 创建时间：2015-07-26
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserForm extends BaseForm {
    private String loginName;
    private String password;
    private String oldPassword;
    private String newPassword;
    private String formerPassword;
    private String name;
    private String idCard;
    private String email;
    private String province;
    private String city;
    private String area;
    private String address;
    private String companyName;
    private String duty;
    private String regNumber;
    private String idcardbPicPath;
    private String idcardfPicPath;
    private String businessPicPath;
    private String brand;
    private String vipPass;
    private String approveFlag;
    private String userType;
    private String imgPath;
    private String reason;
    // 银行卡号
    private String cardNo;
    // 开户银行
    private String bankName;
    // 开户人
    private String accountHolder;
    // 登录次数
    private int loginCount;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *            the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the formerPassword
     */
    public String getFormerPassword() {
        return formerPassword;
    }

    /**
     * @param formerPassword
     *            the formerPassword to set
     */
    public void setFormerPassword(String formerPassword) {
        this.formerPassword = formerPassword;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the idCard
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * @param idCard
     *            the idCard to set
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     *            the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     *            the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the duty
     */
    public String getDuty() {
        return duty;
    }

    /**
     * @param duty
     *            the duty to set
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * @return the regNumber
     */
    public String getRegNumber() {
        return regNumber;
    }

    /**
     * @param regNumber
     *            the regNumber to set
     */
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    /**
     * @return the idcardbPicPath
     */
    public String getIdcardbPicPath() {
        return idcardbPicPath;
    }

    /**
     * @param idcardbPicPath
     *            the idcardbPicPath to set
     */
    public void setIdcardbPicPath(String idcardbPicPath) {
        this.idcardbPicPath = idcardbPicPath;
    }

    /**
     * @return the idcardfPicPath
     */
    public String getIdcardfPicPath() {
        return idcardfPicPath;
    }

    /**
     * @param idcardfPicPath
     *            the idcardfPicPath to set
     */
    public void setIdcardfPicPath(String idcardfPicPath) {
        this.idcardfPicPath = idcardfPicPath;
    }

    /**
     * @return the businessPicPath
     */
    public String getBusinessPicPath() {
        return businessPicPath;
    }

    /**
     * @param businessPicPath
     *            the businessPicPath to set
     */
    public void setBusinessPicPath(String businessPicPath) {
        this.businessPicPath = businessPicPath;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     *            the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the vipPass
     */
    public String getVipPass() {
        return vipPass;
    }

    /**
     * @param vipPass
     *            the vipPass to set
     */
    public void setVipPass(String vipPass) {
        this.vipPass = vipPass;
    }

    /**
     * @return the approveFlag
     */
    public String getApproveFlag() {
        return approveFlag;
    }

    /**
     * @param approveFlag
     *            the approveFlag to set
     */
    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     *            the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the imgPath
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * @param imgPath
     *            the imgPath to set
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    /**
     * @return the loginCount
     */
    public int getLoginCount() {
        return loginCount;
    }

    /**
     * @param loginCount
     *            the loginCount to set
     */
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }
}