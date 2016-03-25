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
@DBTable(name="tb_dynamic")
public class DynamicBean {
    // Id
    @DBField(name="id")
    private BigDecimal id;
    // 发布人(用户Id)
    @DBField(name="release_by")
    private BigDecimal release_by;
    // 动态内容
    @DBField(name="dynamic_content")
    private String dynamic_content;
    // 发布动态时间
    @DBField(name="release_time")
    private Date release_time;
    // 是否屏蔽(Y:是，N:否)
    @DBField(name="is_shield")
    private String is_shield;
    // 评论数
    @DBField(name="comment_number")
    private int comment_number;
    // 点赞数
    @DBField(name="like_number")
    private int like_number;
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
    public BigDecimal getRelease_by() {
        return release_by;
    }
    public void setRelease_by(BigDecimal release_by) {
        this.release_by = release_by;
    }
    public String getDynamic_content() {
        return dynamic_content;
    }
    public void setDynamic_content(String dynamic_content) {
        this.dynamic_content = dynamic_content;
    }
    public Date getRelease_time() {
        return release_time;
    }
    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }
    public String getIs_shield() {
        return is_shield;
    }
    public void setIs_shield(String is_shield) {
        this.is_shield = is_shield;
    }
    public int getComment_number() {
        return comment_number;
    }
    public void setComment_number(int comment_number) {
        this.comment_number = comment_number;
    }
    public int getLike_number() {
        return like_number;
    }
    public void setLike_number(int like_number) {
        this.like_number = like_number;
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