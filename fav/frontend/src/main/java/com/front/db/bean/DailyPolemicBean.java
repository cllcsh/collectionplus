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
@DBTable(name="tb_daily_polemic")
public class DailyPolemicBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 标题
    @DBField(name="title")
    private String title;
    // 内容
    @DBField(name="content")
    private String content;
    // 藏品图片，多个用,分隔
    @DBField(name="images")
    private String images;
    // 甲方观点
    @DBField(name="a_viewpoint")
    private String a_viewpoint;
    // 乙方观点
    @DBField(name="b_viewpoint")
    private String b_viewpoint;
    // 支持甲方观点票数
    @DBField(name="support_a_viewpoint")
    private int support_a_viewpoint;
    // 支持乙方观点票数
    @DBField(name="support_b_viewpoint")
    private int support_b_viewpoint;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public String getA_viewpoint() {
        return a_viewpoint;
    }
    public void setA_viewpoint(String a_viewpoint) {
        this.a_viewpoint = a_viewpoint;
    }
    public String getB_viewpoint() {
        return b_viewpoint;
    }
    public void setB_viewpoint(String b_viewpoint) {
        this.b_viewpoint = b_viewpoint;
    }
    public int getSupport_a_viewpoint() {
        return support_a_viewpoint;
    }
    public void setSupport_a_viewpoint(int support_a_viewpoint) {
        this.support_a_viewpoint = support_a_viewpoint;
    }
    public int getSupport_b_viewpoint() {
        return support_b_viewpoint;
    }
    public void setSupport_b_viewpoint(int support_b_viewpoint) {
        this.support_b_viewpoint = support_b_viewpoint;
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