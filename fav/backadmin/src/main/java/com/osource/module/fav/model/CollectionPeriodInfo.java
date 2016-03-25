/**
 * 文件名：CollectionPeriodInfo.java
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
 * <p>类名称：CollectionPeriodInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-12-11
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionPeriodInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_collection_period";
	}
	
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
