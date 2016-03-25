package com.front.db.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;

/**
 * @author gaoxiang
 * @date 2015-11-05
 */
@DBTable(name="ts_codebook")
public class CodebookBean {
    @DBField(name="code_type")
    private String code_type;
    @DBField(name="code_key")
    private String code_key;
    @DBField(name="code_value")
    private String code_value;
    @DBField(name="order_no")
    private String order_no;
    @DBField(name="code_desc")
    private String code_desc;
    @DBField(name="use_flag")
    private BigDecimal use_flag;
    public String getCode_type() {
        return code_type;
    }
    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }
    public String getCode_key() {
        return code_key;
    }
    public void setCode_key(String code_key) {
        this.code_key = code_key;
    }
    public String getCode_value() {
        return code_value;
    }
    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }
    public String getOrder_no() {
        return order_no;
    }
    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }
    public String getCode_desc() {
        return code_desc;
    }
    public void setCode_desc(String code_desc) {
        this.code_desc = code_desc;
    }
    public BigDecimal getUse_flag() {
        return use_flag;
    }
    public void setUse_flag(BigDecimal use_flag) {
        this.use_flag = use_flag;
    }
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}