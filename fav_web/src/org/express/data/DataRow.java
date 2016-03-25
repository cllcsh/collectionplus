package org.express.data;

import java.util.LinkedHashMap;
import java.util.Map;

import org.express.data.DataComparator.CompareCond;
import org.express.data.DataTypes.DataType;
import org.express.util.Convert;
import org.express.util.DateUtil;

public class DataRow{
	
	public enum DataRowStatus
	{
		Add,
		Modified,
		Delete,
		Normal
	}
	
    // 定义该行记录在table所处的行数
    private int rowIndex = -1;
    private DataColumnCollection columns;
    private DataRowStatus status = DataRowStatus.Normal;

    // table的一个引用
    private DataTable table;

    // 用于存储数据的Map对象，这里保存的对象不包括顺序信息，数据获取的索引通过行信息标识
    private Map<String, Object> itemMap = new LinkedHashMap<String, Object>();

    public DataRow() {

    }

    public DataRow(DataTable table) {
        this.table = table;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public DataTable getTable() {
        return this.table;
    }
    
    protected void setTable(DataTable dt)
    {
    	this.table = dt;
    }

    protected void setColumns(DataColumnCollection columns) {
        this.columns = columns;
    }

    public DataColumnCollection getColumns() {
        return columns;
    }

    public void setValue(int index, Object value) {
        setValue(this.columns.get(index), value);
    }

    public void setValue(String columnName, Object value) 
    {
    	String colName = this.columns.get(columnName).getColumnName();
        setValue(this.columns.get(colName), value);
    }

    public void setValue(DataColumn column, Object value)
    {
        if (column != null) {
            getItemMap().put(column.getColumnName(), column.convertTo(value));
            
            if(this.status != DataRowStatus.Modified)
            	this.status = DataRowStatus.Modified;
        }
    }
    
    public Object getValue(int index) {
        String colName = this.columns.get(index).getColumnName();
        return this.getItemMap().get(colName);
    }

    public Object getValue(String columnName) {
    	String colName = this.columns.get(columnName).getColumnName();
        return this.getItemMap().get(colName);
    }
    
    public Object getValue(int index , String formatString){
    	return Convert.formatValue(getValue(index) , formatString);
    }

    public Object getValue(String columnName , String formatString) {
    	String colName = this.columns.get(columnName).getColumnName();
        return Convert.formatValue(this.getItemMap().get(colName),formatString);
    }

    /**
     * @return the itemMap
     */
    public Map<String, Object> getItemMap() {
        return itemMap;
    }

    /**
     * @param rowIndex
     */
    protected void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void copyFrom(DataRow row) {
        this.itemMap.clear();
        for (Object c : this.columns) {
            this.itemMap.put(c.toString(), row.getValue(c.toString()));
        }
    }

    public String toString() {
        return this.getItemMap().toString();
    }

	public DataRowStatus getStatus() {
		return status;
	}

	public void setStatus(DataRowStatus status) {
		this.status = status;
	}
	
	public String getPrimaryColumnValue()
	{
		StringBuilder sb = new StringBuilder();
		for (DataColumn dc : columns) {
			if(dc.isPrimaryColumn())
				sb.append(this.getValue(dc.getColumnName())).append("|");
		}
		return sb.toString();
	}
	
	@Override  
    public boolean equals(Object obj) {
        if (this == obj) return true;  
        if (obj == null) return false;  
        if (getClass() != obj.getClass()) return false;  
        DataRow other = (DataRow) obj;
        if(this.rowIndex != other.rowIndex) return false;
        if(this.itemMap.size() !=  other.itemMap.size()) return false;
        if(this.getTable() != other.getTable()) return false;
        return true;  
    }
	
	public int compare(DataRow o2 , CompareCond c)
	{
		DataType dTypes = this.getColumns().get(c.getColumnName()).getDataType();
		switch (dTypes) {
			case Integer:
				return c.getSortStr().equals("asc") ? Convert.toInt(this.getValue(c.getColumnName())).compareTo(Convert.toInt(o2.getValue(c.getColumnName()))) 
						: Convert.toInt(o2.getValue(c.getColumnName())).compareTo(Convert.toInt(this.getValue(c.getColumnName())));
			case Long:
				return c.getSortStr().equals("asc") ? Convert.toLong(this.getValue(c.getColumnName())).compareTo(Convert.toLong(o2.getValue(c.getColumnName()))) 
						: Convert.toLong(o2.getValue(c.getColumnName())).compareTo(Convert.toLong(this.getValue(c.getColumnName())));
			case Float:
				return c.getSortStr().equals("asc") ? Convert.toFloat(this.getValue(c.getColumnName())).compareTo(Convert.toFloat(o2.getValue(c.getColumnName()))) 
						: Convert.toFloat(o2.getValue(c.getColumnName())).compareTo(Convert.toFloat(this.getValue(c.getColumnName())));
			case Double:
				return c.getSortStr().equals("asc") ? Convert.toDouble(this.getValue(c.getColumnName())).compareTo(Convert.toDouble(o2.getValue(c.getColumnName()))) 
						: Convert.toDouble(o2.getValue(c.getColumnName())).compareTo(Convert.toDouble(this.getValue(c.getColumnName())));
			case Number:
				return c.getSortStr().equals("asc") ? Convert.toDecimal(this.getValue(c.getColumnName())).compareTo(Convert.toDecimal(o2.getValue(c.getColumnName()))) 
						: Convert.toDecimal(o2.getValue(c.getColumnName())).compareTo(Convert.toDecimal(this.getValue(c.getColumnName())));
			case Time:
			case TimeStamp:
			case Date:
				return c.getSortStr().equals("asc") ? DateUtil.parseObject(this.getValue(c.getColumnName())).compareTo(DateUtil.parseObject(o2.getValue(c.getColumnName()))) 
						: DateUtil.parseObject(o2.getValue(c.getColumnName())).compareTo(DateUtil.parseObject(this.getValue(c.getColumnName())));
			case Boolean:
				return c.getSortStr().equals("asc") ? Convert.toBoolean(this.getValue(c.getColumnName())).compareTo(Convert.toBoolean(o2.getValue(c.getColumnName()))) 
						: Convert.toBoolean(o2.getValue(c.getColumnName())).compareTo(Convert.toBoolean(this.getValue(c.getColumnName())));
			case Null:
				return 0;
			default:
				return c.getSortStr().equals("asc") ? Convert.toString(this.getValue(c.getColumnName())).compareTo(Convert.toString(o2.getValue(c.getColumnName()))) 
						:  Convert.toString(o2.getValue(c.getColumnName())).compareTo(Convert.toString(this.getValue(c.getColumnName())));
		}
	}
	
	@Override
	public int hashCode()
	{
		return rowIndex;
	}
}
