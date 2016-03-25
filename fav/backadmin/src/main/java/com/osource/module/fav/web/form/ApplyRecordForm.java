/**
 * 文件名：ApplyRecordForm.java
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
 * <p>类名称：ApplyRecordForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class ApplyRecordForm extends BaseForm {
	// 申请拍卖的藏品id
    private int collectionId;
    // 申请人Id
    private int applierId;
    // 申请状态，等审核：collection_status_wait_apply，审核中：collection_status_applying，审核通过：collection_status_pass_apply，审核不通过：collection_status_fail_apply，估价：collection_status_appraisal，拍卖中：collection_status_auction，已卖：collection_status_solded，流拍：collection_status_invalid
    private String status;
    private String remark;
    // 申请时间
    private Date applyTime;
    // 申请类型
    private String applyType;
    
    private String statusDesc;
    private String collectionTitle;
    private String applierName;
    
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public int getApplierId() {
        return applierId;
    }
    public void setApplierId(int applierId) {
        this.applierId = applierId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getApplyTime() {
        return applyTime;
    }
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public String getApplyType() {
        return applyType;
    }
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getCollectionTitle() {
		return collectionTitle;
	}
	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}
	public String getApplierName() {
		return applierName;
	}
	public void setApplierName(String applierName) {
		this.applierName = applierName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}