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
@DBTable(name="tb_comment_like")
public class CommentLikeBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    
    // 评论来源id
    @DBField(name="source_id")
    private BigDecimal source_id;
    
    //评论来源类型 0：藏品 1：动态
    @DBField(name="source_type")
    private String source_type;
    
    // 动态id
    @DBField(name="comment_id")
    private BigDecimal comment_id;
    // 好友用户id
    @DBField(name="friend_id")
    private BigDecimal friend_id;
    //评论类型 0：赞成 1：反对
    @DBField(name="type")
    private String type;
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
	public void setComments_id(BigDecimal commentsId) {
		comment_id = commentsId;
	}
	public BigDecimal getFriend_id() {
        return friend_id;
    }
    public void setFriend_id(BigDecimal friend_id) {
        this.friend_id = friend_id;
    }
    public BigDecimal getUse_flag() {
        return use_flag;
    }
    public void setUse_flag(BigDecimal use_flag) {
        this.use_flag = use_flag;
    }
    public Date getInsert_date() {
        return insert_date;
    }
    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }
    public BigDecimal getInsert_id() {
        return insert_id;
    }
    public void setInsert_id(BigDecimal insert_id) {
        this.insert_id = insert_id;
    }
    public Date getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
    public BigDecimal getUpdate_id() {
        return update_id;
    }
    public void setUpdate_id(BigDecimal update_id) {
        this.update_id = update_id;
    }
    
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}