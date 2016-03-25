/**
 * 文件名：EnumInfo.java
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
 * <p>类名称：EnumInfo
 * <p>类描述：
 * <p>创建人：gaoxiang
 * <p>创建时间：2015-11-12
 * @version 2.0
 */
@SuppressWarnings("serial")
public class EnumInfo extends BaseModel implements TableNameAware {
	public String getDbTableName() {
		return "tb_enum";
	}
	
    // 类型
    private String enumType;
    // 类型描述
    private String enumDesc;
    // code
    private String enumCode;
    // 名称
    private String enumName;
    // 序号
    private int displayOrder;
    public String getEnumType() {
        return enumType;
    }
    public void setEnumType(String enumType) {
        this.enumType = enumType;
    }
    public String getEnumDesc() {
        return enumDesc;
    }
    public void setEnumDesc(String enumDesc) {
        this.enumDesc = enumDesc;
    }
    public String getEnumCode() {
        return enumCode;
    }
    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }
    public String getEnumName() {
        return enumName;
    }
    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

}
