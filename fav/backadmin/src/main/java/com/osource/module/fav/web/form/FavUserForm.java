/**
 * 文件名：FavUserForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;
import java.util.List;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：FavUserForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class FavUserForm extends BaseForm {
	// 名称
    private String userName;
    // 账号(手机号)
    private String account;
    // 手机号
    private String phone;
    // 热度
    private int heat;
    // 热度
    private String heatDesc;
    // 密码，加密存储
    private String password;
    // 个性签名
    private String signature;
    // 用户等级
    private String userLevel;
    // 用户等级
    private String userLevelDesc;
    // 用户称号
    private String userTitle;
    // 用户称号
    private String userTitleDesc;
    private List<String> userTitleImgs;
    // 个人头像
    private String avatar;
    // 更换头像时间
    private Date upateAvatarTime;
    // 描述
    private String description;
    // 用户当前积分
    private int userPoints;
    // 用户当前积分
    private String userPointsDesc;
    // 用户累计积分
    private int userAllPoints;
    // 用户当前积分
    private String userAllPointsDesc;
    // 经验值
    private int experience;
    // 经验值
    private String experienceDesc;
    private String personalMsgSet;
    private String personalMsgSetDesc;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getHeat() {
        return heat;
    }
    public void setHeat(int heat) {
        this.heat = heat;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }
    public String getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    public String getUserTitle() {
        return userTitle;
    }
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Date getUpateAvatarTime() {
        return upateAvatarTime;
    }
    public void setUpateAvatarTime(Date upateAvatarTime) {
        this.upateAvatarTime = upateAvatarTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getUserPoints() {
        return userPoints;
    }
    public void setUserPoints(int userPoints) {
        this.userPoints = userPoints;
    }
    public int getUserAllPoints() {
        return userAllPoints;
    }
    public void setUserAllPoints(int userAllPoints) {
        this.userAllPoints = userAllPoints;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
	public String getUserLevelDesc() {
		return userLevelDesc;
	}
	public void setUserLevelDesc(String userLevelDesc) {
		this.userLevelDesc = userLevelDesc;
	}
	public String getUserTitleDesc() {
		return userTitleDesc;
	}
	public void setUserTitleDesc(String userTitleDesc) {
		this.userTitleDesc = userTitleDesc;
	}
	public String getHeatDesc() {
		return heatDesc;
	}
	public void setHeatDesc(String heatDesc) {
		this.heatDesc = heatDesc;
	}
	public String getUserPointsDesc() {
		return userPointsDesc;
	}
	public void setUserPointsDesc(String userPointsDesc) {
		this.userPointsDesc = userPointsDesc;
	}
	public String getUserAllPointsDesc() {
		return userAllPointsDesc;
	}
	public void setUserAllPointsDesc(String userAllPointsDesc) {
		this.userAllPointsDesc = userAllPointsDesc;
	}
	public String getExperienceDesc() {
		return experienceDesc;
	}
	public void setExperienceDesc(String experienceDesc) {
		this.experienceDesc = experienceDesc;
	}
	public String getPersonalMsgSet() {
		return personalMsgSet;
	}
	public void setPersonalMsgSet(String personalMsgSet) {
		this.personalMsgSet = personalMsgSet;
	}
	public String getPersonalMsgSetDesc() {
		return personalMsgSetDesc;
	}
	public void setPersonalMsgSetDesc(String personalMsgSetDesc) {
		this.personalMsgSetDesc = personalMsgSetDesc;
	}
	public List<String> getUserTitleImgs() {
		return userTitleImgs;
	}
	public void setUserTitleImgs(List<String> userTitleImgs) {
		this.userTitleImgs = userTitleImgs;
	}
}