package com.osource.base.common;

public class ElisorBean {

	private Integer fid;
	private Integer eid;
	private String code;
	@SuppressWarnings("unused")
	private Integer clevel;

//	public Integer getClevel() {
//		return clevel;
//	}

	public void setClevel(Integer clevel) {
		this.clevel = clevel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	private String ename;
 
}
