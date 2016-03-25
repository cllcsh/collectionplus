package com.front.db.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;

/**
 * @author gaoxiang
 * 评论顶表
 * @date 2015-11-05
 */
@DBTable(name="tb_comment_top")
public class CommentTopBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 动态id
    @DBField(name="source_id")
    private BigDecimal source_id;
    //评论来源类型 0：藏品 1：动态
    @DBField(name="source_type")
    private String source_type;
    // 评论id
    @DBField(name="comment_id")
    private BigDecimal comment_id;
    // 好友用户id
    @DBField(name="friend_id")
    private BigDecimal friend_id;
    // 顶时间
    @DBField(name="top_time")
    private Date top_time;
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

	public BigDecimal getComment_id() {
		return comment_id;
	}

	public void setComment_id(BigDecimal commentId) {
		comment_id = commentId;
	}

	public BigDecimal getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(BigDecimal friendId) {
		friend_id = friendId;
	}

	public Date getTop_time() {
		return top_time;
	}

	public void setTop_time(Date topTime) {
		top_time = topTime;
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

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}