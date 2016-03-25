/**
 * 文件名：CollectionPeriodForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-12-11
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：CollectionPeriodForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-12-11
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionPeriodForm extends BaseForm {
    // 时期名称
    private String name;
    // 显示顺序
    private Integer displayOrder;
    // 上传图片
    private String imageUrl;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
