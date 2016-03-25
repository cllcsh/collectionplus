/**
 * 文件名：LocaQueryForm.java
 * 
 * 
 */
package com.osource.module.map.web.form;

import java.util.List;

import com.osource.base.util.Entry;
import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：HistoryTraceForm
 * <p>类描述：
 * <p>创建人：weiwu
 * <p>创建时间：2010-02-24
 * @version 2.0
 */
@SuppressWarnings("serial")
public class HistoryTraceForm extends BaseForm {

	/**
	 * 司法单位列表
	 */
	private List<Entry<String, String>> deptList;
	
	/**
	 * 查询对象类型(0、矫正对象；1、矫正工作者)
	 */
	private String objType;
	
	private String selectedDeptId;

	public List<Entry<String, String>> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Entry<String, String>> deptList) {
		this.deptList = deptList;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public String getSelectedDeptId() {
		return selectedDeptId;
	}

	public void setSelectedDeptId(String selectedDeptId) {
		this.selectedDeptId = selectedDeptId;
	}
}