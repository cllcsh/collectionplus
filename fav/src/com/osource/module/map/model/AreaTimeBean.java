/**
 * 文件名：AreaTimeBean.java

 * 
 */
package com.osource.module.map.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>
 * 类名称：AreaTimeBean
 * <p>
 * 类描述：
 * <p>
 * 创建人：jingxifei
 * <p>
 * 创建时间：2013-04-17
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AreaTimeBean extends BaseModel implements TableNameAware {
    public String getDbTableName() {
        return "tb_location";
    }

    private Integer deptId;

    /**
     * @return the deptId
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the deptId to set
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}