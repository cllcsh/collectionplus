/**
 * 文件名：CollectionCategoryForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-12-11
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：CollectionCategoryForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-12-11
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionCategoryForm extends BaseForm {
    // 分类名称
    private String categoryName;
    // 显示顺序
    private Integer displayOrder;
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Integer getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

}
