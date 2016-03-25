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
@DBTable(name="tb_apply_record_line")
public class ApplyRecordLineBean {
    @DBField(name="id")
    private BigDecimal id;
    // 申请记录id
    @DBField(name="apply_record_id")
    private BigDecimal apply_record_id;
    // 申请人Id
    @DBField(name="applier_id")
    private BigDecimal applier_id;
    // 审批人Id
    @DBField(name="approver_id")
    private BigDecimal approver_id;
    // 是否通过 Y:通过，N：不通过
    @DBField(name="is_pass")
    private String is_pass;
    // 审批时间
    @DBField(name="apply_time")
    private Date apply_time;
    // 审批意见
    @DBField(name="feed_back")
    private String feed_back;
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
    public BigDecimal getApply_record_id() {
        return apply_record_id;
    }
    public void setApply_record_id(BigDecimal apply_record_id) {
        this.apply_record_id = apply_record_id;
    }
    public BigDecimal getApplier_id() {
        return applier_id;
    }
    public void setApplier_id(BigDecimal applier_id) {
        this.applier_id = applier_id;
    }
    public BigDecimal getApprover_id() {
        return approver_id;
    }
    public void setApprover_id(BigDecimal approver_id) {
        this.approver_id = approver_id;
    }
    public String getIs_pass() {
        return is_pass;
    }
    public void setIs_pass(String is_pass) {
        this.is_pass = is_pass;
    }
    public Date getApply_time() {
        return apply_time;
    }
    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }
    public String getFeed_back() {
        return feed_back;
    }
    public void setFeed_back(String feed_back) {
        this.feed_back = feed_back;
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