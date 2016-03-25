/**
 * 文件名：AuctionInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-08
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：AuctionInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-08
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AuctionInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_auction";
	}
	
	// 拍卖行名字
    private String name;
    // 拍卖行图标
    private String icon;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}