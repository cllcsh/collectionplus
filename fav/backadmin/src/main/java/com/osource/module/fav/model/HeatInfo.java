/**
 * 文件名：HeatInfo.java
 * 
 * 版本信息：  2.0
 * 日期：2015-11-10
 * 
 */
package com.osource.module.fav.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>类名称：HeatInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-10
 * @version 2.0
 */
@SuppressWarnings("serial")
public class HeatInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_heat";
	}
	// 类型名称
    private String name;
    // 类型名称
    private String nameDesc;
    // 系数
    private int value;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
	public String getNameDesc() {
		return nameDesc;
	}
	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
	}
}