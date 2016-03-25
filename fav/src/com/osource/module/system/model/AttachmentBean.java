/**
 * 文件名：AttachmentBean.java

 * 
 */
package com.osource.module.system.model;

import com.osource.core.model.BaseModel;
import com.osource.orm.ibatis.TableNameAware;

/**
 * 项目名称：osource
 * <p>
 * 类名称：AttachmentBean
 * <p>
 * 类描述：
 * <p>
 * 创建人：weiwu
 * <p>
 * 创建时间：2010-12-28
 * 
 * @version 2.0
 */
@SuppressWarnings("serial")
public class AttachmentBean extends BaseModel implements TableNameAware {
    private String name;
    private String fileName;
    private String filePath;
    private String fileSize;
    private String downCount;
    private String description;
    private String deptName;
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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownCount() {
        return downCount;
    }

    public void setDownCount(String downCount) {
        this.downCount = downCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDbTableName() {
        return "tb_attachment";
    }
}