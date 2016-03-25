/**
 * 文件名：FamousHomeForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：FamousHomeForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class FamousHomeForm extends BaseForm {
	// 名字
    private String name;
    // 图标
    private String icon;
    // 个人简介
    private String introduction;
    // 是否入驻
    private String status;
    // 是否入驻
    private String statusDesc;
    // 专项多个逗号隔开
    private String specialids;
    // 专项多个逗号隔开
    private String specialidsDesc;
    // 0：专家组 1：收藏大咖
    private String type;
    // 0：专家组 1：收藏大咖
    private String typeDesc;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSpecialids() {
        return specialids;
    }
    public void setSpecialids(String specialids) {
        this.specialids = specialids;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getSpecialidsDesc() {
		return specialidsDesc;
	}
	public void setSpecialidsDesc(String specialidsDesc) {
		this.specialidsDesc = specialidsDesc;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
}