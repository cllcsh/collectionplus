/**
 * 文件名：CollectionCategoryInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-12-11
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：CollectionCategoryInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-12-11
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionCategoryInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_collection_category";
	}
	
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
