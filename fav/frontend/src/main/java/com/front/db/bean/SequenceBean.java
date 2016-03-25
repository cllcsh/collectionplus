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
@DBTable(name="ts_sequence")
public class SequenceBean {
    @DBField(name="name")
    private String name;
    @DBField(name="current_value")
    private BigDecimal current_value;
    @DBField(name="description")
    private String description;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getCurrent_value() {
        return current_value;
    }
    public void setCurrent_value(BigDecimal current_value) {
        this.current_value = current_value;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}