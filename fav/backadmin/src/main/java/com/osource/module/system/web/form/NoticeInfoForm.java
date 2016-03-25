package com.osource.module.system.web.form;

import java.io.File;

import com.osource.base.web.form.BaseForm;

public class NoticeInfoForm extends BaseForm{
	private String noticeTitle;
	private String noticeContent;
	private String status;
	private String startDate;
	private String endDate;
	private Integer noticeType;
	private String displayPosition;
	private boolean display;
	private String corpNode;
	private File[] accessory;
	private String[] accessoryFileName;
	private String[] accessoryContentType;
	
	
	public String getCorpNode() {
		return corpNode;
	}
	public void setCorpNode(String corpNode) {
		this.corpNode = corpNode;
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean isDisplay) {
		this.display = isDisplay;
		if(this.display == true)
			this.status = "2";
		else this.status = "1";
	}
	public String getDisplayPosition() {
		return displayPosition;
	}
	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		if(this.status.equals("2"))
			this.display = true;
		else this.display = false;
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
	public Integer getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}
	public File[] getAccessory() {
		return accessory;
	}
	public void setAccessory(File[] accessory) {
		this.accessory = accessory;
	}
	public String[] getAccessoryFileName() {
		return accessoryFileName;
	}
	public void setAccessoryFileName(String[] accessoryFileName) {
		this.accessoryFileName = accessoryFileName;
	}
	public String[] getAccessoryContentType() {
		return accessoryContentType;
	}
	public void setAccessoryContentType(String[] accessoryContentType) {
		this.accessoryContentType = accessoryContentType;
	}

}
