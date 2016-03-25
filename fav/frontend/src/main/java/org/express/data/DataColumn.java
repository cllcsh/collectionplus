package org.express.data;

import org.express.data.DataTypes.DataType;
import org.express.util.StringUtil;

public class DataColumn {
    private boolean readOnly; // 只读

    private DataTable table; // dataTable的引用

    private String columnName; // 列名

    private String captionName; // 显示名称

    private Object tag;// //通过tag对象保存公式配置

    private int columnIndex;// 列索引

    private DataType dataType;// 列数据类型

    private boolean isDisplayed = true; // 是否显示
    
    private boolean isPrimaryColumn = false;

    public DataColumn() {
        this("default1");
    }

    public DataColumn(DataType dataType) {
        this("default1", dataType);
    }

    public DataColumn(String columnName) {
        this(columnName, DataType.String);
    }

    public DataColumn(String columnName, DataType dataType) {
        this.setDataType(dataType);
        this.columnName = columnName;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        if (StringUtil.isNullorEmpty(captionName)) captionName = columnName;
    }

    public String getCaptionName() {
        return captionName;
    }

    public void setCaptionName(String captionName) {
        this.captionName = captionName;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public DataTable getTable() {
        return this.table;
    }

    public void setTable(DataTable table) {
        this.table = table;
    }

    /**
     * @param dataType
     */
    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * @return the dataType
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * @param columnIndex
     */
    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * @return the columnIndex
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * 功能描述： 将输入数据转为当前列的数据类型返回
     * 
     * @param
     * @return: Object
     */
    public Object convertTo(Object value) {
        return value;
    }

    @Override
    public String toString() {
        return this.columnName + "["+ dataType.name() +"]";
    }

    /**
     * @param tag
     */
    public void setTag(Object tag) {
        this.tag = tag;
    }

    /**
     * @return the tag
     */
    public Object getTag() {
        return tag;
    }

    /**
     * @return the isDisplayed
     */
    public boolean isDisplayed() {
        return isDisplayed;
    }

    /**
     * @param isDisplayed
     */
    public void setDisplayed(boolean isDisplayed) {
        this.isDisplayed = isDisplayed;
    }

	public boolean isPrimaryColumn() {
		return isPrimaryColumn;
	}

	public void setPrimaryColumn(boolean isPrimaryColumn) {
		this.isPrimaryColumn = isPrimaryColumn;
	}
}
