/**
 * 文件名：CollectionInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-03
 * 
 */
package com.osource.module.fav.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：CollectionInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-03
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CollectionInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_collection";
	}
	// 标题
    private String title;
    // 藏品类别
    private int categoryId;
    // 藏品类别
    private String categoryName;
    // 所属时期ID
    private int collectionPeriodId;
    // 所属时期
    private String collectionPeriodName;
    // 藏品简介
    private String introduction;
    // 是否愿意送拍（Y:是,N:否）
    private String isSendRacket;
    // 是否愿意送拍（Y:是,N:否）
    private String isSendRacketDesc;
    // 是否愿意出售（Y:是,N:否）
    private String isSold;
    // 是否愿意出售（Y:是,N:否）
    private String isSoldDesc;
    // 是否鉴定(Y:已鉴定(,N:没鉴定)
    private String isIdentify;
    // 是否鉴定(Y:已鉴定(,N:没鉴定)
    private String isIdentifyDesc;
    // 标签id
    private int labelId;
    // 标签
    private String labelName;
    // 图标
    private String iconImg;
    // 热度
    private int heat;
    // 鉴定结果
    private String identifyResult;
    // 状态，展示：collection_status_show，送拍：collection_status_send_racket，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid
    private String status;
    // 状态，展示：collection_status_show，送拍：collection_status_send_racket，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid
    private String statusDesc;
    // 拍卖行id
    private int auctionId;
    // 拍卖行
    private String auctionName;
    // 估价
    private String appraisal;
    // 估价单位
    private String appraisalUnit;
    // 估价
    private String appraisalDesc;
    // 估价时间
    private Date appraisalTime;
    // 估计人id
    private int appraisalUserId;
    // 估计人
    private String appraisalUserName;
    // 成交价
    private String transactionPrice;
    // 成交价
    private String transactionPriceDesc;
    // 成交价单位
    private String transactionPriceUnit;
    // 成交时间
    private Date transactionPriceTime;
    // 成交人id
    private int transactionUserId;
    // 成交人
    private String transactionUserName;
    // 成交说明
    private String transactionDesc;
    // 发布人
    private String insertUserName;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public int getCollectionPeriodId() {
        return collectionPeriodId;
    }
    public void setCollectionPeriodId(int collectionPeriodId) {
        this.collectionPeriodId = collectionPeriodId;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIsSendRacket() {
        return isSendRacket;
    }
    public void setIsSendRacket(String isSendRacket) {
        this.isSendRacket = isSendRacket;
    }
    public String getIsSold() {
        return isSold;
    }
    public void setIsSold(String isSold) {
        this.isSold = isSold;
    }
    public String getIsIdentify() {
        return isIdentify;
    }
    public void setIsIdentify(String isIdentify) {
        this.isIdentify = isIdentify;
    }
    public int getLabelId() {
        return labelId;
    }
    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
    public String getIconImg() {
        return iconImg;
    }
    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }
    public int getHeat() {
        return heat;
    }
    public void setHeat(int heat) {
        this.heat = heat;
    }
    public String getIdentifyResult() {
        return identifyResult;
    }
    public void setIdentifyResult(String identifyResult) {
        this.identifyResult = identifyResult;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getAuctionId() {
        return auctionId;
    }
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    public String getAppraisal() {
        return appraisal;
    }
    public void setAppraisal(String appraisal) {
        this.appraisal = appraisal;
    }
    public String getAppraisalUnit() {
        return appraisalUnit;
    }
    public void setAppraisalUnit(String appraisalUnit) {
        this.appraisalUnit = appraisalUnit;
    }
    public Date getAppraisalTime() {
        return appraisalTime;
    }
    public void setAppraisalTime(Date appraisalTime) {
        this.appraisalTime = appraisalTime;
    }
    public int getAppraisalUserId() {
        return appraisalUserId;
    }
    public void setAppraisalUserId(int appraisalUserId) {
        this.appraisalUserId = appraisalUserId;
    }
    public String getAppraisalUserName() {
        return appraisalUserName;
    }
    public void setAppraisalUserName(String appraisalUserName) {
        this.appraisalUserName = appraisalUserName;
    }
    public String getTransactionPrice() {
        return transactionPrice;
    }
    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
    public String getTransactionPriceUnit() {
        return transactionPriceUnit;
    }
    public void setTransactionPriceUnit(String transactionPriceUnit) {
        this.transactionPriceUnit = transactionPriceUnit;
    }
    public Date getTransactionPriceTime() {
        return transactionPriceTime;
    }
    public void setTransactionPriceTime(Date transactionPriceTime) {
        this.transactionPriceTime = transactionPriceTime;
    }
    public int getTransactionUserId() {
        return transactionUserId;
    }
    public void setTransactionUserId(int transactionUserId) {
        this.transactionUserId = transactionUserId;
    }
    public String getTransactionUserName() {
        return transactionUserName;
    }
    public void setTransactionUserName(String transactionUserName) {
        this.transactionUserName = transactionUserName;
    }
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCollectionPeriodName() {
		return collectionPeriodName;
	}
	public void setCollectionPeriodName(String collectionPeriodName) {
		this.collectionPeriodName = collectionPeriodName;
	}
	public String getIsSendRacketDesc() {
		return isSendRacketDesc;
	}
	public void setIsSendRacketDesc(String isSendRacketDesc) {
		this.isSendRacketDesc = isSendRacketDesc;
	}
	public String getIsSoldDesc() {
		return isSoldDesc;
	}
	public void setIsSoldDesc(String isSoldDesc) {
		this.isSoldDesc = isSoldDesc;
	}
	public String getIsIdentifyDesc() {
		return isIdentifyDesc;
	}
	public void setIsIdentifyDesc(String isIdentifyDesc) {
		this.isIdentifyDesc = isIdentifyDesc;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getAuctionName() {
		return auctionName;
	}
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	public String getAppraisalDesc() {
		return appraisalDesc;
	}
	public void setAppraisalDesc(String appraisalDesc) {
		this.appraisalDesc = appraisalDesc;
	}
	public String getTransactionPriceDesc() {
		return transactionPriceDesc;
	}
	public void setTransactionPriceDesc(String transactionPriceDesc) {
		this.transactionPriceDesc = transactionPriceDesc;
	}
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public String getInsertUserName() {
		return insertUserName;
	}
	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}
}