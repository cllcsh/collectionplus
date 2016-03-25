/**
 * 文件名：DynamicCommentsLikeForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-11
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：DynamicCommentsLikeForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-11
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicCommentsLikeForm extends BaseForm {
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
    // 评论类型 0：赞成 1：反对
    private String type;
    // 评论类型 0：赞成 1：反对
    private String typeDesc;
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
}