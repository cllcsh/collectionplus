/**
 * 文件名：UserTitleInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-12
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：UserTitleInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-12
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserTitleInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_user_title";
	}
    // 称号
    private String name;
    // 序号
    private int displayOrder;
    private String imgPath;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
