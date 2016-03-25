/**
 * 文件名：UserBlackForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-21
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：UserBlackForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-21
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserBlackForm extends BaseForm {
	// 用户ID
    private int userId;
    // 用户ID
    private String userName;
    // 黑名单用户Id
    private int blacklistUserId;
    // 黑名单用户Id
    private String blacklistUserName;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getBlacklistUserId() {
        return blacklistUserId;
    }
    public void setBlacklistUserId(int blacklistUserId) {
        this.blacklistUserId = blacklistUserId;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBlacklistUserName() {
		return blacklistUserName;
	}
	public void setBlacklistUserName(String blacklistUserName) {
		this.blacklistUserName = blacklistUserName;
	}
}