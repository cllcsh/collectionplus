/**
 * 文件名：VoiceCheckForm.java
 * 
 */
package com.osource.module.map.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：VoiceCheckForm
 * <p>类描述：
 * <p>创建人：zhouhaiyan
 * <p>创建时间：2012-05-16
 * @version 2.0
 */
@SuppressWarnings("serial")
public class VoiceCheckForm extends BaseForm {
	private String criminalId;
	private String recordflag;
	private String imptype;
	private String msdn;
	
	private String streamNo;		//流水号
	private String resultCode;		//验证结果码
	private Date startDate;		//外呼开始时间
	private Date endDate;			//外呼结束时间
	private String summary;			//摘要
	private String criminalName;	//矫正对象名称
	private String staffName;		//矫正工作者
	private String voiceNo;			//声纹识别号
	private String allCriminalId;			//已经被选择的criminalId
	private String[] voicePath1;			//声音路径
	private String[] oldVoicePath1;			//声音录入路径
	
	public String getAllCriminalId() {
		return allCriminalId;
	}
	public void setAllCriminalId(String allCriminalId) {
		this.allCriminalId = allCriminalId;
	}
	public String getCriminalId() {
		return criminalId;
	}
	public void setCriminalId(String criminalId) {
		this.criminalId = criminalId;
	}
	public String getRecordflag() {
		return recordflag;
	}
	public void setRecordflag(String recordflag) {
		this.recordflag = recordflag;
	}
	public String getImptype() {
		return imptype;
	}
	public void setImptype(String imptype) {
		this.imptype = imptype;
	}
	public String getMsdn() {
		return msdn;
	}
	public void setMsdn(String msdn) {
		this.msdn = msdn;
	}
	public String getStreamNo() {
		return streamNo;
	}
	public void setStreamNo(String streamNo) {
		this.streamNo = streamNo;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCriminalName() {
		return criminalName;
	}
	public void setCriminalName(String criminalName) {
		this.criminalName = criminalName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getVoiceNo() {
		return voiceNo;
	}
	public void setVoiceNo(String voiceNo) {
		this.voiceNo = voiceNo;
	}
	public String[] getVoicePath1() {
		return voicePath1;
	}
	public void setVoicePath1(String[] voicePath1) {
		this.voicePath1 = voicePath1;
	}
	public String[] getOldVoicePath1() {
		return oldVoicePath1;
	}
	public void setOldVoicePath1(String[] oldVoicePath1) {
		this.oldVoicePath1 = oldVoicePath1;
	}
	

}