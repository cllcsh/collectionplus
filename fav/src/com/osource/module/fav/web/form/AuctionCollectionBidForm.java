/**
 * 文件名：AuctionCollectionBidForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-08
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：AuctionCollectionBidForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AuctionCollectionBidForm extends BaseForm {
	// 藏品id
    private int collectionId;
    private String collectionTitle;
    // 用户id
    private int userid;
    // 用户名
    private String username;
    private String usernameDesc;
    private String sexNick;
    private String sexNickDesc;
    // 竞价价格
    private double price;
    // 竞价价格单位
    private String priceUnit;
    private String priceDesc;
    private Date bidDate;
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPriceUnit() {
        return priceUnit;
    }
    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }
	public String getCollectionTitle() {
		return collectionTitle;
	}
	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public Date getBidDate() {
		return bidDate;
	}
	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}
	public String getUsernameDesc() {
		return usernameDesc;
	}
	public void setUsernameDesc(String usernameDesc) {
		this.usernameDesc = usernameDesc;
	}
	public String getSexNick() {
		return sexNick;
	}
	public void setSexNick(String sexNick) {
		this.sexNick = sexNick;
	}
	public String getSexNickDesc() {
		return sexNickDesc;
	}
	public void setSexNickDesc(String sexNickDesc) {
		this.sexNickDesc = sexNickDesc;
	}
}