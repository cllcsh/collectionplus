/**
 * 文件名：DynamicCommentTopInfo.java
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
 * <p>类名称：DynamicCommentTopInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicCommentTopInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_dynamic_comment_top";
	}
	// 评论来源id
    private int sourceId;
    // 评论来源
    private String sourceName;
    // 评论来源类型
    private String sourceType;
    // 评论来源类型
    private String sourceTypeDesc;
    // 评论id
    private int commentId;
    // 评论id
    private String commentContent;
    // 好友用户id
    private int friendId;
    // 评论人id
    private String userName;
    // 顶时间
    private Date topTime;
    
    public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceTypeDesc() {
		return sourceTypeDesc;
	}
	public void setSourceTypeDesc(String sourceTypeDesc) {
		this.sourceTypeDesc = sourceTypeDesc;
	}
	public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
    public Date getTopTime() {
        return topTime;
    }
    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}