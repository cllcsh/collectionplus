/**
 * 文件名：DynamicCommentsForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：DynamicCommentsForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicCommentsForm extends BaseForm {
	// 动态id
    private int dynamicId;
    // 好友用户id
    private int friendId;
    // 评论内容
    private String commentContent;
    // 评论时间
    private Date commentTime;
    // 评论类型 0：评论 1：回复
    private String type;
    // 顶的数量
    private int topSize;
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
    public String getCommentContent() {
        return commentContent;
    }
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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
    public int getTopSize() {
        return topSize;
    }
    public void setTopSize(int topSize) {
        this.topSize = topSize;
    }
}