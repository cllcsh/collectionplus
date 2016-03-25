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
@DBTable(name="tb_auction_collection_bid")
public class AuctionCollectionBidBean {
    @DBField(name="id")
    private BigDecimal id;
    // 藏品id
    @DBField(name="collection_id")
    private BigDecimal collection_id;
    // 用户id
    @DBField(name="userid")
    private BigDecimal userid;
    // 用户名
    @DBField(name="username")
    private String username;
    // 竞价价格
    @DBField(name="price")
    private double price;
    // 竞价价格单位
    @DBField(name="price_unit")
    private String price_unit;
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
    public BigDecimal getUserid() {
        return userid;
    }
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPrice_unit() {
        return price_unit;
    }
    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
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