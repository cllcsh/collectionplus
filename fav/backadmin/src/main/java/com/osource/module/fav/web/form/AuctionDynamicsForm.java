/**
 * 文件名：AuctionDynamicsForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-08
 * 
 */
package com.osource.module.fav.web.form;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：AuctionDynamicsForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AuctionDynamicsForm extends BaseForm {
	// 拍卖行标识
    private int auctionId;
    private String auctionIcon;
    // 标题
    private String title;
    // 动态类型
    private int auctionDynamicTypeId;
    private String auctionDynamicTypeName;
    private int sourceId;
    public int getAuctionId() {
        return auctionId;
    }
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getAuctionDynamicTypeId() {
        return auctionDynamicTypeId;
    }
    public void setAuctionDynamicTypeId(int auctionDynamicTypeId) {
        this.auctionDynamicTypeId = auctionDynamicTypeId;
    }
	public String getAuctionIcon() {
		return auctionIcon;
	}
	public void setAuctionIcon(String auctionIcon) {
		this.auctionIcon = auctionIcon;
	}
	public String getAuctionDynamicTypeName() {
		return auctionDynamicTypeName;
	}
	public void setAuctionDynamicTypeName(String auctionDynamicTypeName) {
		this.auctionDynamicTypeName = auctionDynamicTypeName;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
}