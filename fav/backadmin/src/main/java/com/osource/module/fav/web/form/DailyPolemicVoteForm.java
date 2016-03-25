/**
 * 文件名：DailyPolemicVoteForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：DailyPolemicVoteForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DailyPolemicVoteForm extends BaseForm {
	// 天天论战id
    private int dailyPolemicId;
	// 天天论战id
    private String dailyPolemicTitle;
    // 投票人
    private int userId;
    // 投票人
    private String userName;
    // 1:反对;0:赞成
    private String type;
    // 1:反对;0:赞成
    private String typeDesc;
    // 投票日期 yyyy-MM-dd
    private String day;
    public int getDailyPolemicId() {
        return dailyPolemicId;
    }
    public void setDailyPolemicId(int dailyPolemicId) {
        this.dailyPolemicId = dailyPolemicId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
	public String getDailyPolemicTitle() {
		return dailyPolemicTitle;
	}
	public void setDailyPolemicTitle(String dailyPolemicTitle) {
		this.dailyPolemicTitle = dailyPolemicTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
}