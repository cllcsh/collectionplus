package com.osource.base.form.web.form;

import com.osource.core.model.BaseModel;

public class FormTableForm extends BaseModel{

	private String moduleName;
	private String author;
	private String entityBean;
	private String entity;
	private String tableName;
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEntityBean() {
		return entityBean;
	}
	public void setEntityBean(String entityBean) {
		this.entityBean = entityBean;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
