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
@DBTable(name="tb_messages")
public class MessagesBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 发送 人（发送人的用户id）
    @DBField(name="sender")
    private BigDecimal sender;
    // 接收人（接收人的用户id）
    @DBField(name="receiver")
    private BigDecimal receiver;
    // 发送时间
    @DBField(name="send_time")
    private Date send_time;
    // 消息内容
    @DBField(name="content")
    private String content;
    // 是否已读，Y:已读，N：未读
    @DBField(name="is_read")
    private String is_read;
    // 是否删除，Y:已删除，N：未删除
    @DBField(name="is_delete")
    private String is_delete;
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
    public BigDecimal getSender() {
        return sender;
    }
    public void setSender(BigDecimal sender) {
        this.sender = sender;
    }
    public BigDecimal getReceiver() {
        return receiver;
    }
    public void setReceiver(BigDecimal receiver) {
        this.receiver = receiver;
    }
    public Date getSend_time() {
        return send_time;
    }
    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getIs_read() {
        return is_read;
    }
    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }
    public String getIs_delete() {
        return is_delete;
    }
    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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