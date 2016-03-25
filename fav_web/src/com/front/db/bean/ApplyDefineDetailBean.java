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
@DBTable(name="tb_apply_define_detail")
public class ApplyDefineDetailBean {
    @DBField(name="id")
    private BigDecimal id;
    // 审批流定义id
    @DBField(name="apply_define_id")
    private BigDecimal apply_define_id;
    // 审批人id
    @DBField(name="approver_id")
    private BigDecimal approver_id;
    // 序号
    @DBField(name="display_order")
    private int display_order;
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
    public BigDecimal getApply_define_id() {
        return apply_define_id;
    }
    public void setApply_define_id(BigDecimal apply_define_id) {
        this.apply_define_id = apply_define_id;
    }
    public BigDecimal getApprover_id() {
        return approver_id;
    }
    public void setApprover_id(BigDecimal approver_id) {
        this.approver_id = approver_id;
    }
    public int getDisplay_order() {
        return display_order;
    }
    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
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