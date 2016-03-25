/**
 * 文件名：HeatForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：HeatForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class HeatForm extends BaseForm {
	// 类型名称
    private String name;
    // 类型名称
    private String nameDesc;
    // 系数
    private int value;
    // 系数
    private String valueDesc;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
	public String getNameDesc() {
		return nameDesc;
	}
	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}
	public String getValueDesc() {
		return valueDesc;
	}
	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}
}