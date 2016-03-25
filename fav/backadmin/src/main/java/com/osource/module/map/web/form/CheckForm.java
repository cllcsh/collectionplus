/**
 * 文件名：CheckForm.java
 * 
 */
package com.osource.module.map.web.form;


import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：CheckForm
 * <p>类描述：
 * <p>创建人：xiaoxubing
 * <p>创建时间：2010-02-23
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CheckForm extends BaseForm {
	private String criminalId;
	private String phone;
	private String criminalName;
	private String checkResult;
	private String checkResultName;
	private String checkDate;
	private String startDate;
	private String endDate;
	private String cent;
	private String checkExplain;
	private String remark;


	public String getCriminalId() {
		return criminalId;
	}

	public void setCriminalId(String criminalId) {
		this.criminalId = criminalId;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}


	public String getCent() {
		return cent;
	}

	public void setCent(String cent) {
		this.cent = cent;
	}

	public String getCheckExplain() {
		return checkExplain;
	}

	public void setCheckExplain(String checkExplain) {
		this.checkExplain = checkExplain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getCriminalName() {
		return criminalName;
	}

	public void setCriminalName(String criminalName) {
		this.criminalName = criminalName;
	}

	public String getCheckResultName() {
		return checkResultName;
	}

	public void setCheckResultName(String checkResultName) {
		this.checkResultName = checkResultName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}