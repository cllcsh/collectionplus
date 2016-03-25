/**
 * 
 */
package com.osource.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangsen
 * 
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer insertId;
    private Date insertDate;
    private Integer updateId;
    private Date updateDate;
    private String useFlag;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the insertId
     */
    public Integer getInsertId() {
        return insertId;
    }

    /**
     * @param insertId
     *            the insertId to set
     */
    public void setInsertId(Integer insertId) {
        this.insertId = insertId;
    }

    /**
     * @return the insertDate
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     *            the insertDate to set
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * @return the updateId
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * @param updateId
     *            the updateId to set
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * @return the updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     *            the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the useFlag
     */
    public String getUseFlag() {
        return useFlag;
    }

    /**
     * @param useFlag
     *            the useFlag to set
     */
    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }
}
