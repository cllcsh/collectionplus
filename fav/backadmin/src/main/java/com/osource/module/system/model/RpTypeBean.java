/**   
 * 文件名：RpTypeBean.java   
 *   
 */
package com.osource.module.system.model;

import com.osource.core.model.BaseModel;

/**
 * 
 * 项目名称：osource 类名称：RpTypeBean 类描述： 创建人：zhangyan 创建时间：Nov 6, 2009 2:55:00 PM 修改人：zhangyan 修改时间：Nov 6, 2009 2:55:00 PM
 * 修改备注：
 * 
 * @version
 * 
 */
public class RpTypeBean extends BaseModel {

    private static final long serialVersionUID = -1248883371631388872L;

    /**
     * name:奖惩类型名称
     * 
     * @since Ver 1.1
     */
    private String rpName;

    /**
     * rp:奖惩的具体类型；
     * 
     * @since Ver 1.1
     */
    private String rp;

    /**
     * defScore:默认分值；
     * 
     * @since Ver 1.1
     */
    private String defScore;

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

    public String getRp() {
        return rp;
    }

    public void setRp(String rp) {
        this.rp = rp;
    }

    public String getDefScore() {
        return defScore;
    }

    public void setDefScore(String defScore) {
        this.defScore = defScore;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

}
