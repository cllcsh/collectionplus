package com.osource.base.web.form;

import java.io.Serializable;
import java.util.Date;

public class BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7151517165644857321L;
	private Integer id;
	private Integer deptId;
	private String useFlag;
	private Date insertDate;
	private Integer insertId;
	private Date updateDate;
	private Integer updateId;
	
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getInsertId() {
		return insertId;
	}
	public void setInsertId(Integer insertId) {
		this.insertId = insertId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	
}
