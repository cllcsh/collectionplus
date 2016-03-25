package com.front.db.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.front.web.framework.database.annotation.DBField;
import com.front.web.framework.database.annotation.DBTable;

/**
 * @author gaoxiang
 * 热度表
 * @date 2015-11-05
 */
@DBTable(name="tb_heat")
public class HeatBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 类型名称
    @DBField(name="name")
    private String name;
    // 系数
    @DBField(name="value")
    private Integer value;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public BigDecimal getUse_flag() {
		return use_flag;
	}

	public void setUse_flag(BigDecimal useFlag) {
		use_flag = useFlag;
	}

	public Date getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(Date insertDate) {
		insert_date = insertDate;
	}

	public BigDecimal getInsert_id() {
		return insert_id;
	}

	public void setInsert_id(BigDecimal insertId) {
		insert_id = insertId;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date updateDate) {
		update_date = updateDate;
	}

	public BigDecimal getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(BigDecimal updateId) {
		update_id = updateId;
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}