package com.front.db.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;

/**
 * @author gaoxiang
 * @date 2015-11-05
 * 用户表
 */
@DBTable(name="tb_fav_user")
public class FavUserBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 名称
    @DBField(name="user_name")
    private String user_name;
    // 账号(手机号)
    @DBField(name="account")
    private String account;
    // 手机号
    @DBField(name="phone")
    private String phone;
    // 热度
    @DBField(name="heat")
    private Integer heat;
    // 密码，加密存储
    @DBField(name="password")
    private String password;
    // 个性签名
    @DBField(name="signature")
    private String signature;
    // 用户等级
    @DBField(name="user_level")
    private String user_level;
    // 用户称号
    @DBField(name="user_title")
    private String user_title;
    // 个人头像
    @DBField(name="avatar")
    private String avatar;
    // 更换头像时间
    @DBField(name="upate_avatar_time")
    private Date upate_avatar_time;
    // 描述
    @DBField(name="description")
    private String description;
    // 用户当前积分
    @DBField(name="user_points")
    private Integer user_points;
    // 用户累计积分
    @DBField(name="user_allPoints")
    private Integer user_allPoints;
    // 经验值
    @DBField(name="experience")
    private Integer experience;
    //个人私信设置
    @DBField(name="personal_msg_set")
    private String personal_msg_set;
    @DBField(name="use_flag")
    private BigDecimal use_flag;
    @DBField(name="insert_date")
    private Date insert_date;
    @DBField(name="insert_id")
    private BigDecimal insert_id;
    @DBField(name="update_date")
    private Date update_date;
    @DBField(name="update_id")
    private BigDecimal update_id;
    public BigDecimal getId() {
        return id;
    }
    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
    public Integer getHeat() {
        return heat;
    }
    public void setHeat(Integer heat) {
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
    public String getUser_level() {
        return user_level;
    }
    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }
    public String getUser_title() {
        return user_title;
    }
    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public Date getUpate_avatar_time() {
        return upate_avatar_time;
    }
    public void setUpate_avatar_time(Date upate_avatar_time) {
        this.upate_avatar_time = upate_avatar_time;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getUser_points() {
        return user_points;
    }
    public void setUser_points(int user_points) {
        this.user_points = user_points;
    }
    public Integer getUser_allPoints() {
        return user_allPoints;
    }
    public void setUser_allPoints(Integer user_allPoints) {
        this.user_allPoints = user_allPoints;
    }
    public BigDecimal getUse_flag() {
        return use_flag;
    }
    public void setUse_flag(BigDecimal use_flag) {
        this.use_flag = use_flag;
    }
    public Date getInsert_date() {
        return insert_date;
    }
    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }
    public BigDecimal getInsert_id() {
        return insert_id;
    }
    public void setInsert_id(BigDecimal insert_id) {
        this.insert_id = insert_id;
    }
    public Date getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
    public BigDecimal getUpdate_id() {
        return update_id;
    }
    public void setUpdate_id(BigDecimal update_id) {
        this.update_id = update_id;
    }
    public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public void setUser_points(Integer userPoints) {
		user_points = userPoints;
	}
	public String getPersonal_msg_set() {
		return personal_msg_set;
	}
	public void setPersonal_msg_set(String personalMsgSet) {
		personal_msg_set = personalMsgSet;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}