/**
 * 文件名：DynamicImagesForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-15
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：DynamicImagesForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-15
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicImagesForm extends BaseForm {
	// 动态id
    private int dynamicId;
    private String dynamicContent;
    // 上传图片地址
    private String image;
    // 显示顺序
    private int displayOrder;
    public int getDynamicId() {
        return dynamicId;
    }
    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
	public String getDynamicContent() {
		return dynamicContent;
	}
	public void setDynamicContent(String dynamicContent) {
		this.dynamicContent = dynamicContent;
	}
}