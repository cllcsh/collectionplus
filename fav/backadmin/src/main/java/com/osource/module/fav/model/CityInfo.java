/**
 * 文件名：CityInfo.java
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
 * <p>类名称：CityInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-12
 * @version 2.0
 */
@SuppressWarnings("serial")
public class CityInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_city";
	}
	
    // 名称
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
