/**
 * 文件名：AuctionDynamicImagesInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-08
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：AuctionDynamicImagesInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AuctionDynamicImagesInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_auction_dynamic_images";
	}
	// 动态id
    private int auctionDynamicId;
    private String auctionDynamicTitle;
    // 内容
    private String content;
    // 上传图片
    private String images;
    public int getAuctionDynamicId() {
        return auctionDynamicId;
    }
    public void setAuctionDynamicId(int auctionDynamicId) {
        this.auctionDynamicId = auctionDynamicId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
	public String getAuctionDynamicTitle() {
		return auctionDynamicTitle;
	}
	public void setAuctionDynamicTitle(String auctionDynamicTitle) {
		this.auctionDynamicTitle = auctionDynamicTitle;
	}
}