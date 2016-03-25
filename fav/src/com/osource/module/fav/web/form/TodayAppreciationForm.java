/**
 * 文件名：TodayAppreciationForm.java
 * 
 * 版本信息： 2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.web.form;

import java.util.Date;

import com.osource.base.web.form.BaseForm;

/**
 * 项目名称：osource
 * <p>类名称：TodayAppreciationForm
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class TodayAppreciationForm extends BaseForm {
	// 标题
    private String title;
    // 内容
    private String content;
    // 发布时间
    private Date releaseTime;
    // 上传图片
    private String image;
    private int displayOrder;
    private String isShow;
    private String isShowDesc;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
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
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getIsShowDesc() {
		return isShowDesc;
	}
	public void setIsShowDesc(String isShowDesc) {
		this.isShowDesc = isShowDesc;
	}
}