/**
 * 文件名：UserFansInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：UserFansInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserFansInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_user_fans";
	}
	// 关注人
    private int userId;
    private String userName;
    // 粉丝
    private int fanId;
    private String fanName;
    // 关注时间
    private Date concernTime;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getFanId() {
        return fanId;
    }
    public void setFanId(int fanId) {
        this.fanId = fanId;
    }
    public Date getConcernTime() {
        return concernTime;
    }
    public void setConcernTime(Date concernTime) {
        this.concernTime = concernTime;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFanName() {
		return fanName;
	}
	public void setFanName(String fanName) {
		this.fanName = fanName;
	}
}