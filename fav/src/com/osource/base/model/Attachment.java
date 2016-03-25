/**   
 * 文件名：Attachment.java   
 *   
 * 版本信息： v2.0 
 *   
 */
package com.osource.base.model;

import com.osource.core.model.BaseModel;

/**
 * 
 * 项目名称：osource 类名称：Attachment 类描述： 创建人：weiwu 创建时间：Nov 6, 2009 2:20:53 PM 修改人：Administrator 修改时间：Nov 6, 2009 2:20:53 PM
 * 修改备注：
 * 
 * @version
 * 
 */
public class Attachment extends BaseModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String name;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private Integer downCount;
    private String description;

    private Integer relevanceId;
    private String type;
    private Integer orderId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRelevanceId() {
        return relevanceId;
    }

    public void setRelevanceId(Integer relevanceId) {
        this.relevanceId = relevanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
