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
@DBTable(name="ts_navigation")
public class NavigationBean {
    @DBField(name="id")
    private BigDecimal id;
    // 本字段为null时，改导航为目录
    @DBField(name="func_id")
    private BigDecimal func_id;
    @DBField(name="name")
    private String name;
    // 根据此字段order by排序
    @DBField(name="view_order")
    private String view_order;
    // 存放上级导航的编号，当本字段为null时，该导航为根导航
    @DBField(name="upper_id")
    private BigDecimal upper_id;
    // _blank，_self，_parent，_top，目标frame
    @DBField(name="target")
    private String target;
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
    public BigDecimal getFunc_id() {
        return func_id;
    }
    public void setFunc_id(BigDecimal func_id) {
        this.func_id = func_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getView_order() {
        return view_order;
    }
    public void setView_order(String view_order) {
        this.view_order = view_order;
    }
    public BigDecimal getUpper_id() {
        return upper_id;
    }
    public void setUpper_id(BigDecimal upper_id) {
        this.upper_id = upper_id;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
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