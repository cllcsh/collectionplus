/**
 * 文件名：VoiceCheckBean.java

 * 
 */
package com.osource.module.map.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>
 * 类名称：VoiceCheckBean
 * <p>
 * 类描述：
 * <p>
 * 创建人：zhouhaiyan
 * <p>
 * 创建时间：2012-05-16
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class VoiceCheckBean extends BaseModel implements TableNameAware {
    public String getDbTableName() {
        return "tb_voice_check";
    }

    private String streamNo; // 流水号
    private String msdn; // 手机号
    private String resultCode; // 验证结果码
    private Date startDate; // 外呼开始时间
    private Date endDate; // 外呼结束时间
    private String summary; // 摘要
    private String criminalId; // 矫正对象id
    private String criminalName; // 矫正对象名称
    private String staffName; // 矫正工作者
    private String voiceNo; // 声纹识别号
    private String state;
    private String voicePath; // 声纹路径
    private String oldVoicePath; // 声纹录入路径
    private Integer deptId;

    /**
     * @return the deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(String criminalId) {
        this.criminalId = criminalId;
    }

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
    }

    public String getMsdn() {
        return msdn;
    }

    public void setMsdn(String msdn) {
        this.msdn = msdn;
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

    public String getVoicePath() {
        return voicePath;
    }

    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }

    public String getOldVoicePath() {
        return oldVoicePath;
    }

    public void setOldVoicePath(String oldVoicePath) {
        this.oldVoicePath = oldVoicePath;
    }

}