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
@DBTable(name="tb_user_fans")
public class UserFansBean {
    // Id
    @DBField(name="id")
    private BigDecimal id;
    // 关注人
    @DBField(name="user_id")
    private BigDecimal user_id;
    // 粉丝
    @DBField(name="fan_id")
    private BigDecimal fan_id;
    // 关注时间
    @DBField(name="concern_time")
    private Date concern_time;
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
    public BigDecimal getFan_id() {
        return fan_id;
    }
    public void setFan_id(BigDecimal fan_id) {
        this.fan_id = fan_id;
    }
    public Date getConcern_time() {
        return concern_time;
    }
    public void setConcern_time(Date concern_time) {
        this.concern_time = concern_time;
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
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}