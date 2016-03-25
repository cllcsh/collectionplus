/**
 * 文件名：FavoritesInfo.java
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
 * <p>类名称：FavoritesInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class FavoritesInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_favorites";
	}
	// 收藏人id
    private int userId;
 // 收藏人id
    private String userName;
    // 藏品id
    private int collectionId;
    // 收藏人id
    private String title;
    // 收藏时间
    private Date favoriteTime;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCollectionId() {
        return collectionId;
    }
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }
    public Date getFavoriteTime() {
        return favoriteTime;
    }
    public void setFavoriteTime(Date favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}