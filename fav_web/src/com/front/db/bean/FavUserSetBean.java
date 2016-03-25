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
 * 用户设置表
 */
@DBTable(name="tb_fav_user_set")
public class FavUserSetBean {
    // Id
    @DBField(name="id")
    private BigDecimal id;
    // 用户ID
    @DBField(name="user_id")
    private BigDecimal user_id;
    // 好友用户id
    @DBField(name="friend_id")
    private BigDecimal friend_id;
    //是否屏蔽私信  1:屏蔽;0:开放
    @DBField(name="block_msg")
    private String block_msg;
    //是否屏蔽话题  1:屏蔽;0:开放
    @DBField(name="block_dynamic")
    private String block_dynamic;
    //是否屏蔽回复  1:屏蔽;0:开放
    @DBField(name="block_reply")
    private String block_reply;
    //是否屏蔽评论  1:屏蔽;0:开放
    @DBField(name="block_comment")
    private String block_comment;
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
    public BigDecimal getUser_id() {
        return user_id;
    }
    public void setUser_id(BigDecimal user_id) {
        this.user_id = user_id;
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
    public BigDecimal getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(BigDecimal friendId) {
		friend_id = friendId;
	}
	public String getBlock_msg() {
		return block_msg;
	}
	public void setBlock_msg(String blockMsg) {
		block_msg = blockMsg;
	}
	public String getBlock_dynamic() {
		return block_dynamic;
	}
	public void setBlock_dynamic(String blockDynamic) {
		block_dynamic = blockDynamic;
	}
	public String getBlock_reply() {
		return block_reply;
	}
	public void setBlock_reply(String blockReply) {
		block_reply = blockReply;
	}
	public String getBlock_comment() {
		return block_comment;
	}
	public void setBlock_comment(String blockComment) {
		block_comment = blockComment;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}