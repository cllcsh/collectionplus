/**
 * 文件名：UserInteresCategoryInfo.java
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
 * <p>类名称：UserInteresCategoryInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-12
 * @version 2.0
 */
@SuppressWarnings("serial")
public class UserInteresCategoryInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_user_interes_category";
	}
	
    // 用户id
    private int userId;
    // 藏品类别id
    private int categoryId;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
