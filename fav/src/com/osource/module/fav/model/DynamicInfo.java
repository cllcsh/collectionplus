/**
 * 文件名：DynamicInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.model;

import java.util.Date;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：DynamicInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class DynamicInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_dynamic";
	}
	// 发布人(用户Id)
    private int releaseBy;
    private String releaseName;
    // 动态内容
    private String dynamicContent;
    // 发布动态时间
    private Date releaseTime;
    // 是否屏蔽(Y:是，N:否)
    private String isShield;
    // 评论数
    private int commentNumber;
    // 点赞数
    private int likeNumber;
    public int getReleaseBy() {
        return releaseBy;
    }
    public void setReleaseBy(int releaseBy) {
        this.releaseBy = releaseBy;
    }
    public String getDynamicContent() {
        return dynamicContent;
    }
    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }
    public Date getReleaseTime() {
        return releaseTime;
    }
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
    public String getIsShield() {
        return isShield;
    }
    public void setIsShield(String isShield) {
        this.isShield = isShield;
    }
    public int getCommentNumber() {
        return commentNumber;
    }
    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }
    public int getLikeNumber() {
        return likeNumber;
    }
    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}
}