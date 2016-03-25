/**
 * 文件名：AttachmentForm.java

 * 
 */
package com.osource.module.system.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：AttachmentForm
 * <p>类描述：
 * <p>创建人：weiwu
 * <p>创建时间：2010-12-28
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AttachmentForm extends BaseForm {
	private String name;
	private String description;
	private String departmentId;
	private String filePath;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}