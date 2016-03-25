package com.osource.base.ws.model;

import java.util.Date;

public class RiskInfo {
	private Header header;
	
	private Integer id;//风险Id，针对修改和删除
	private String opFlag;//01：新增,02：修改,03：删除
	private Integer kind;//风险类型 :10=漏洞风险,20=基线风险,30=攻击预警风险,40=病毒风险
	private String name;//风险名称
	private Integer deptId;//组织ID （type:10,20,30,40）
	private Integer sourceId;//节点ID （type:10,20,30）
	private String description;//风险描述（type:10,20,30,40）
	private String srcIp; //源地址（type:10,20,30,40）
	private String dstIp; //目的地址（type:10,20,30）
	
	private Integer levelHigh; //高危数（type:10,30）
    private Integer levelMid;  //中危数（type:10,30）
    private Integer levelLow;  //低危数（type:10,30）
    
    private Integer cent1;//权重1（type:20）
    private Integer cent2; //权重2（type:20）
    private Integer cent3; //权重3（type:20）
    private Integer cent4; //权重4（type:20）
    private Integer cent5; //权重5（type:20）
    
    private Integer num;   //病毒风险数量（type:40）

    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private Integer isHandled; //当前状态:0-未处理，1-已处理
    
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public String getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getSrcIp() {
		return srcIp;
	}

	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}

	public String getDstIp() {
		return dstIp;
	}

	public void setDstIp(String dstIp) {
		this.dstIp = dstIp;
	}

	public Integer getLevelHigh() {
		return levelHigh;
	}

	public void setLevelHigh(Integer levelHigh) {
		this.levelHigh = levelHigh;
	}

	public Integer getLevelMid() {
		return levelMid;
	}

	public void setLevelMid(Integer levelMid) {
		this.levelMid = levelMid;
	}

	public Integer getLevelLow() {
		return levelLow;
	}

	public void setLevelLow(Integer levelLow) {
		this.levelLow = levelLow;
	}

	public Integer getCent1() {
		return cent1;
	}

	public void setCent1(Integer cent1) {
		this.cent1 = cent1;
	}

	public Integer getCent2() {
		return cent2;
	}

	public void setCent2(Integer cent2) {
		this.cent2 = cent2;
	}

	public Integer getCent3() {
		return cent3;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCent3(Integer cent3) {
		this.cent3 = cent3;
	}

	public Integer getCent4() {
		return cent4;
	}

	public void setCent4(Integer cent4) {
		this.cent4 = cent4;
	}

	public Integer getCent5() {
		return cent5;
	}

	public void setCent5(Integer cent5) {
		this.cent5 = cent5;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIsHandled() {
		return isHandled;
	}

	public void setIsHandled(Integer isHandled) {
		this.isHandled = isHandled;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    
}
