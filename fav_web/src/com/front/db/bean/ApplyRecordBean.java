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
@DBTable(name="tb_apply_record")
public class ApplyRecordBean {
    @DBField(name="id")
    private BigDecimal id;
    // 申请拍卖的藏品id
    @DBField(name="collection_id")
    private BigDecimal collection_id;
    // 申请人Id
    @DBField(name="applier_id")
    private BigDecimal applier_id;
    // 申请状态，等审核：collection_status_wait_apply，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid
    @DBField(name="status")
    private String status;
    // 审核备注
    @DBField(name="remark")
    private String remark;
    // 申请时间
    @DBField(name="apply_time")
    private Date apply_time;
    // 申请类型
    @DBField(name="apply_type")
    private String apply_type;
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
    public BigDecimal getCollection_id() {
        return collection_id;
    }
    public void setCollection_id(BigDecimal collection_id) {
        this.collection_id = collection_id;
    }
    public BigDecimal getApplier_id() {
        return applier_id;
    }
    public void setApplier_id(BigDecimal applier_id) {
        this.applier_id = applier_id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getApply_time() {
        return apply_time;
    }
    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }
    public String getApply_type() {
        return apply_type;
    }
    public void setApply_type(String apply_type) {
        this.apply_type = apply_type;
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
    
    public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}