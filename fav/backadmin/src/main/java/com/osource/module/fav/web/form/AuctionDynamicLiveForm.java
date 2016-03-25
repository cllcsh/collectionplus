/**
 * 文件名：AuctionDynamicLiveForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-08
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：AuctionDynamicLiveForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AuctionDynamicLiveForm extends BaseForm {
	// 拍卖行动态id
    private int auctionDynamicId;
    private String auctionDynamicTitle;
    // 藏品id
    private int collectionId;
    private String collectionTitle;
    public int getAuctionDynamicId() {
        return auctionDynamicId;
    }
    public void setAuctionDynamicId(int auctionDynamicId) {
        this.auctionDynamicId = auctionDynamicId;
    }
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public String getAuctionDynamicTitle() {
		return auctionDynamicTitle;
	}
	public void setAuctionDynamicTitle(String auctionDynamicTitle) {
		this.auctionDynamicTitle = auctionDynamicTitle;
	}
	public String getCollectionTitle() {
		return collectionTitle;
	}
	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}
}