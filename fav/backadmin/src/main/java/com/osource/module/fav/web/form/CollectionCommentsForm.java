/**
 * 文件名：CollectionCommentsForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-06
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：CollectionCommentsForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-06
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionCommentsForm extends BaseForm {
	// 评论来源id
    private int sourceId;
    // 评论来源
    private String sourceName;
    // 评论来源类型
    private String sourceType;
    // 评论来源类型
    private String sourceTypeDesc;
    // 内容
    private String content;
    // 评论人id
    private int friendId;
    // 评论人id
    private String userName;
    // 评分
    private int point;
    // 评论发表时间
    private Date commentTime;
    // 评论类型
    private String type;
    private int replyId;
    private String replyContent;
    private String replyUserName;
    // 评论类型
    private String typeDesc;
    // 顶的数量
    private int topSize;
    // 赞同的数量
    private int likeSize;
    // 反对的数量分
    private int opposeSize;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
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
	public int getTopSize() {
		return topSize;
	}
	public void setTopSize(int topSize) {
		this.topSize = topSize;
	}
	public int getLikeSize() {
		return likeSize;
	}
	public void setLikeSize(int likeSize) {
		this.likeSize = likeSize;
	}
	public int getOpposeSize() {
		return opposeSize;
	}
	public void setOpposeSize(int opposeSize) {
		this.opposeSize = opposeSize;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyUserName() {
		return replyUserName;
	}
	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}
}