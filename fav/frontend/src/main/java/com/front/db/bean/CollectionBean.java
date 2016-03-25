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
@DBTable(name="tb_collection")
public class CollectionBean {
    // Id
    @DBField(name="id")
    private BigDecimal id;
    // 标题
    @DBField(name="title")
    private String title;
    // 藏品类别
    @DBField(name="category_id")
    private BigDecimal category_id;
    // 所属时期ID
    @DBField(name="collection_period_id")
    private BigDecimal collection_period_id;
    // 藏品简介
    @DBField(name="introduction")
    private String introduction;
    // 是否愿意送拍（Y:是,N:否）
    @DBField(name="is_send_racket")
    private String is_send_racket;
    // 是否愿意出售（Y:是,N:否）
    @DBField(name="is_sold")
    private String is_sold;
    // 是否鉴定(Y:已鉴定(,N:没鉴定)
    @DBField(name="is_identify")
    private String is_identify;
    // 标签id
    @DBField(name="label_id")
    private BigDecimal label_id;
    // 图标
    @DBField(name="icon_img")
    private String icon_img;
    // 热度
    @DBField(name="heat")
    private Integer heat;
    // 鉴定结果
    @DBField(name="identify_result")
    private String identify_result;
    // 状态，展示：collection_status_show，送拍：collection_status_send_racket，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid
    @DBField(name="status")
    private String status;
    // 拍卖行id
    @DBField(name="auction_id")
    private BigDecimal auction_id;
    // 估价
    @DBField(name="appraisal")
    private String appraisal;
    // 估价单位
    @DBField(name="appraisal_unit")
    private String appraisal_unit;
    // 估价时间
    @DBField(name="appraisal_time")
    private Date appraisal_time;
    // 估计人id
    @DBField(name="appraisal_user_id")
    private BigDecimal appraisal_user_id;
    // 估计人
    @DBField(name="appraisal_user_name")
    private String appraisal_user_name;
    // 成交价
    @DBField(name="transaction_price")
    private String transaction_price;
    // 成交价单位
    @DBField(name="transaction_price_unit")
    private String transaction_price_unit;
    // 成交时间
    @DBField(name="transaction_price_time")
    private Date transaction_price_time;
    // 成交人id
    @DBField(name="transaction_user_id")
    private BigDecimal transaction_user_id;
    // 成交人
    @DBField(name="transaction_user_name")
    private String transaction_user_name;
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
    //成交说明
    @DBField(name="transaction_desc")
    private String transaction_desc;
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
    public BigDecimal getCategory_id() {
        return category_id;
    }
    public void setCategory_id(BigDecimal category_id) {
        this.category_id = category_id;
    }
    public BigDecimal getCollection_period_id() {
        return collection_period_id;
    }
    public void setCollection_period_id(BigDecimal collection_period_id) {
        this.collection_period_id = collection_period_id;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIs_send_racket() {
        return is_send_racket;
    }
    public void setIs_send_racket(String is_send_racket) {
        this.is_send_racket = is_send_racket;
    }
    public String getIs_sold() {
        return is_sold;
    }
    public void setIs_sold(String is_sold) {
        this.is_sold = is_sold;
    }
    public String getIs_identify() {
        return is_identify;
    }
    public void setIs_identify(String is_identify) {
        this.is_identify = is_identify;
    }
    public BigDecimal getLabel_id() {
        return label_id;
    }
    public void setLabel_id(BigDecimal label_id) {
        this.label_id = label_id;
    }
    public String getIcon_img() {
        return icon_img;
    }
    public void setIcon_img(String icon_img) {
        this.icon_img = icon_img;
    }
    public Integer getHeat() {
        return heat;
    }
    public void setHeat(Integer heat) {
        this.heat = heat;
    }
    public String getIdentify_result() {
        return identify_result;
    }
    public void setIdentify_result(String identify_result) {
        this.identify_result = identify_result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public BigDecimal getAuction_id() {
        return auction_id;
    }
    public void setAuction_id(BigDecimal auction_id) {
        this.auction_id = auction_id;
    }
    public String getAppraisal() {
        return appraisal;
    }
    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }
    public String getAppraisal_unit() {
        return appraisal_unit;
    }
    public void setAppraisal_unit(String appraisal_unit) {
        this.appraisal_unit = appraisal_unit;
    }
    public Date getAppraisal_time() {
        return appraisal_time;
    }
    public void setAppraisal_time(Date appraisal_time) {
        this.appraisal_time = appraisal_time;
    }
    public BigDecimal getAppraisal_user_id() {
        return appraisal_user_id;
    }
    public void setAppraisal_user_id(BigDecimal appraisal_user_id) {
        this.appraisal_user_id = appraisal_user_id;
    }
    public String getAppraisal_user_name() {
        return appraisal_user_name;
    }
    public void setAppraisal_user_name(String appraisal_user_name) {
        this.appraisal_user_name = appraisal_user_name;
    }
    public String getTransaction_price() {
        return transaction_price;
    }
    public void setTransaction_price(String transaction_price) {
        this.transaction_price = transaction_price;
    }
    public String getTransaction_price_unit() {
        return transaction_price_unit;
    }
    public void setTransaction_price_unit(String transaction_price_unit) {
        this.transaction_price_unit = transaction_price_unit;
    }
    public Date getTransaction_price_time() {
        return transaction_price_time;
    }
    public void setTransaction_price_time(Date transaction_price_time) {
        this.transaction_price_time = transaction_price_time;
    }
    public BigDecimal getTransaction_user_id() {
        return transaction_user_id;
    }
    public void setTransaction_user_id(BigDecimal transaction_user_id) {
        this.transaction_user_id = transaction_user_id;
    }
    public String getTransaction_user_name() {
        return transaction_user_name;
    }
    public void setTransaction_user_name(String transaction_user_name) {
        this.transaction_user_name = transaction_user_name;
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
    public String getTransaction_desc() {
		return transaction_desc;
	}
	public void setTransaction_desc(String transactionDesc) {
		transaction_desc = transactionDesc;
	}
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}