package com.osource.module.system.web.form;

import java.util.List;

import com.osource.module.system.model.RoleFunction;

public class RoleInfoForm {
	private String roleId;
	private String roleName;
	private String departmentId;
	private String departmentName;
	private String shareFlag;
	
	//edit by weiwu
	private String functionValue;
	
	@SuppressWarnings("unused")
	private List<RoleFunction> funList;
	
	public String getFunctionValue() {
		return functionValue;
	}
	public void setFunctionValue(String functionValue) {
		this.functionValue = functionValue;
	}

	//
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getShareFlag() {
		return shareFlag;
	}
	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}

}
