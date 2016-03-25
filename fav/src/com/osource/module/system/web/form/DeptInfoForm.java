package com.osource.module.system.web.form;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.module.system.model.DeptEntity;

public class DeptInfoForm{

	  	private List<Entry<String, String>> domainList;
	    private List<Entry<String, String>> deptList;
	    private boolean editFlag;
	    private String name;
	    private String manager;
	    private String phoneNo;
	    private String faxNo;
	    private String address;
	    private String postcode;
	    private String rank;
	    private Integer upperDept;
	    private String code;
	    private String jgbm;
	    private DeptEntity deptEntity;
	    private Integer deptId;
	    
		public Integer getDeptId() {
			return deptId;
		}
		public void setDeptId(Integer deptId) {
			this.deptId = deptId;
		}
		public DeptEntity getDeptEntity() {
			return deptEntity;
		}
		public void setDeptEntity(DeptEntity deptEntity) {
			this.deptEntity = deptEntity;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getManager() {
			return manager;
		}
		public void setManager(String manager) {
			this.manager = manager;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getFaxNo() {
			return faxNo;
		}
		public void setFaxNo(String faxNo) {
			this.faxNo = faxNo;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getRank() {
			return rank;
		}
		public void setRank(String rank) {
			this.rank = rank;
		}
		public Integer getUpperDept() {
			return upperDept;
		}
		public void setUpperDept(Integer upperDept) {
			this.upperDept = upperDept;
		}
		public List<Entry<String, String>> getDomainList() {
			return domainList;
		}
		public void setDomainList(List<Entry<String, String>> domainList) {
			this.domainList = domainList;
		}
		public List<Entry<String, String>> getDeptList() {
			return deptList;
		}
		public void setDeptList(List<Entry<String, String>> deptList) {
			this.deptList = deptList;
		}
		public boolean isEditFlag() {
			return editFlag;
		}
		public void setEditFlag(boolean editFlag) {
			this.editFlag = editFlag;
		}
		public String getJgbm() {
			return jgbm;
		}
		public void setJgbm(String jgbm) {
			this.jgbm = jgbm;
		}
	    
	    
	    
}
