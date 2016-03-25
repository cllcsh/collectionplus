/**
 * 文件名：DynamicImagesInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-15
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：DynamicImagesInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-15
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicImagesInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_dynamic_images";
	}
	
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