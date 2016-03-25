package com.osource.base.ws.model;

import java.util.ArrayList;
import java.util.List;

public class EventInfo {
	private Header header;
	private String opFlag; //1:新增 2：修改 3：删除
	private Integer id;  //ID 
	private Integer deptId; //部门ID
	private String name; //事件名称
	private String srcIp; //源地址
	private String dstIp; //目的地址
	private String effectIp;//受影响IP地址
	private Integer typeId;//事件类型Id
	
	private List<NodeEventInfo> nodeEventList = new ArrayList();
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getEffectIp() {
		return effectIp;
	}
	public void setEffectIp(String effectIp) {
		this.effectIp = effectIp;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpFlag() {
		return opFlag;
	}
	public void setOpFlag(String opFlag) {
		this.opFlag = opFlag;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public List<NodeEventInfo> getNodeEventList() {
		return nodeEventList;
	}
	public void setNodeEventList(List<NodeEventInfo> nodeEventList) {
		this.nodeEventList = nodeEventList;
	}
	public void addNodeEventInfo(NodeEventInfo info)
	{
		nodeEventList.add(info);
	}
	
}
