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
 */
@DBTable(name="tb_comment")
public class CommentBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 评论来源id
    @DBField(name="source_id")
    private BigDecimal source_id;
    // 评论来源类型 0：藏品 1：动态
    @DBField(name="source_type")
    private String source_type;
    // 评论人id
    @DBField(name="friend_id")
    private BigDecimal friend_id;
    // 评论内容
    @DBField(name="comment_content")
    private String comment_content;
   // 评论发表时间
    @DBField(name="comment_time")
    private Date comment_time;
    // 评分
    @DBField(name="point")
    private int point;
    // 评论类型 0：评论 1：回复
    @DBField(name="type")
    private String type;
    // 顶的数量
    @DBField(name="top_size")
    private Integer top_size;
    // 赞同的数量
    @DBField(name="like_size")
    private Integer like_size;
    // 反对的数量
    @DBField(name="oppose_size")
    private Integer oppose_size;
    
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
    
    //回复对应的评论id
    @DBField(name="reply_id")
    private BigDecimal reply_id;
    public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getSource_id() {
		return source_id;
	}

	public void setSource_id(BigDecimal sourceId) {
		source_id = sourceId;
	}

	public String getSource_type() {
		return source_type;
	}

	public void setSource_type(String sourceType) {
		source_type = sourceType;
	}

	public BigDecimal getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(BigDecimal friendId) {
		friend_id = friendId;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String commentContent) {
		comment_content = commentContent;
	}

	public Date getComment_time() {
		return comment_time;
	}

	public void setComment_time(Date commentTime) {
		comment_time = commentTime;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTop_size() {
		return top_size;
	}

	public void setTop_size(Integer topSize) {
		top_size = topSize;
	}

	public Integer getLike_size() {
		return like_size;
	}

	public void setLike_size(Integer likeSize) {
		like_size = likeSize;
	}

	public Integer getOppose_size() {
		return oppose_size;
	}

	public void setOppose_size(Integer opposeSize) {
		oppose_size = opposeSize;
	}

	public BigDecimal getUse_flag() {
		return use_flag;
	}

	public void setUse_flag(BigDecimal useFlag) {
		use_flag = useFlag;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insertDate) {
		insert_date = insertDate;
	}

	public BigDecimal getInsert_id() {
		return insert_id;
	}

	public void setInsert_id(BigDecimal insertId) {
		insert_id = insertId;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}

	public BigDecimal getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(BigDecimal updateId) {
		update_id = updateId;
	}
	public BigDecimal getReply_id() {
		return reply_id;
	}

	public void setReply_id(BigDecimal replyId) {
		reply_id = replyId;
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}