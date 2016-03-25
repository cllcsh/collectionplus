/**
 * 文件名：CollectionImagesInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-14
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：CollectionImagesInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-14
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionImagesInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_collection_images";
	}
	
	// 藏品Id
    private int collectionId;
    private String collectionTitle;
    // 上传的图片URL
    private String imageUrl;
    // 显示顺序
    private int displayOrder;
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
	public String getCollectionTitle() {
		return collectionTitle;
	}
	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}
}