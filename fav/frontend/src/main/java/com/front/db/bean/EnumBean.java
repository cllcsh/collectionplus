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
@DBTable(name="tb_enum")
public class EnumBean {
    // id
    @DBField(name="id")
    private BigDecimal id;
    // 类型
    @DBField(name="enum_type")
    private String enum_type;
    //code
    @DBField(name="enum_code")
    private String enum_code;
    // 名称
    @DBField(name="enum_name")
    private String enum_name;
 
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getEnum_type() {
		return enum_type;
	}

	public void setEnum_type(String enumType) {
		enum_type = enumType;
	}

	public String getEnum_code() {
		return enum_code;
	}

	public void setEnum_code(String enumCode) {
		enum_code = enumCode;
	}

	public String getEnum_name() {
		return enum_name;
	}

	public void setEnum_name(String enumName) {
		enum_name = enumName;
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}