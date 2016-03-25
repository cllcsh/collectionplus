/**
 * 文件名：DynamicLikeInfo.java
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
 * <p>类名称：DynamicLikeInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicLikeInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_dynamic_like";
	}
	 // 动态id
    private int dynamicId;
    private String dynamicContent;
    // 好友用户id
    private int friendId;
    private String friendName;
    // 点赞时间
    private Date likeTime;
    // 评论类型 0：赞成 1：反对
    private String type;
    public int getDynamicId() {
        return dynamicId;
    }
    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }
    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
    public Date getLikeTime() {
        return likeTime;
    }
    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	public String getDynamicContent() {
		return dynamicContent;
	}
	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
}