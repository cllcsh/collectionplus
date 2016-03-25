package com.osource.module.system.model;

import java.io.Serializable;
import java.util.Date;

public class CircleUserModel implements Serializable {
	/**
	 * 系统ID，采用32位UUID
	 */
	private String id;
	private Date updateTime = new Date();
	
	private Date createTime = new Date();
	
	private String createUser;
	private CircleUserModel updateUser;
	private CircleUserModel deleteUser;
	
	private Integer delFlag = 0;
	
	private String userName;// 用户名
	private String userPhoneNum;// 电话号码
	private String userLoginID;// 登录名
	private String userPassword;// 密码
	private String userPhotoPath;// 头像url
	private String pinyin;// 全拼
	private String pinyin2;// 简拼
	
	private String isAdmin;
	private String userType; //用户类型
	private String pushToken;  //手机推送ID
	private String remark; //备注
	
	private String  userUnit; // 单位
	private String  userJob; // 职务

	public String getUserUnit() {
		return userUnit;
	}

	public void setUserUnit(String userUnit) {
		this.userUnit = userUnit;
	}

	public String getUserJob() {
		return userJob;
	}

	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNum() {
		return userPhoneNum;
	}
	
	public void setUserPhoneNum(String userPhoneNum) {
		this.userPhoneNum = userPhoneNum;
	}

	public String getUserLoginID() {
		return userLoginID;
	}
	
	public void setUserLoginID(String userLoginID) {
		this.userLoginID = userLoginID;
	}
	

	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

	public String getUserPhotoPath() {
		return userPhotoPath;
	}
	
	public void setUserPhotoPath(String userPhotoPath) {
		this.userPhotoPath = userPhotoPath;
	}
	

	public String getPinyin() {
		return pinyin;
	}
	
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	

	public String getPinyin2() {
		return pinyin2;
	}
	
	public void setPinyin2(String pinyin2) {
		this.pinyin2 = pinyin2;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public CircleUserModel getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(CircleUserModel updateUser) {
		this.updateUser = updateUser;
	}

	public CircleUserModel getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(CircleUserModel deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
