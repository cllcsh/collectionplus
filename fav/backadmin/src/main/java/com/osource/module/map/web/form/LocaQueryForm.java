/**
 * 文件名：LocaQueryForm.java
 * 
 */
package com.osource.module.map.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>
 * 类名称：LocaQueryForm
 * <p>
 * 类描述：
 * <p>
 * 创建人：yangs
 * <p>
 * 创建时间：2010-02-24
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class LocaQueryForm extends BaseForm {
	private int objType; // 查询对象类型(0、矫正对象；1、矫正工作者)
	
	private String locNum;

	public int getObjType() {
		return objType;
	}

	public void setObjType(int objType) {
		this.objType = objType;
	}

	public String getLocNum() {
		return locNum;
	}

	public void setLocNum(String locNum) {
		this.locNum = locNum;
	}
}