package org.express.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.express.data.DataRow.DataRowStatus;
import org.express.data.DataTypes.DataType;
import org.express.util.Convert;

/**
 * 数据表(提供模拟数据库表对象的数据存储容器)
 * @author Rei Ayanami
 *
 */
public final class DataTable implements Serializable {
	private static final long serialVersionUID = 9999L;
	private DataRowCollection rows; // 用于保存DataRow的集合对象
    private DataColumnCollection columns; // 用于保存DataColumn的对象
    private String tableName; // 表名
    private String tableLocalName; // 表中文名
    private String dataSourceName; // 数据源名
    private DataSet dataSet;

    public DataTable() {
        this.columns = new DataColumnCollection();
        this.rows = new DataRowCollection();
        this.rows.setColumns(columns);
    }

    /**
     * 构造函数
     * @param 表名
     */
    public DataTable(String dataTableName) {
        this(); 
        this.tableName = dataTableName;
    }
    
    /**
     * 清空表的行和列
     * @return
     */
    public DataTable clearAll()
    {
    	this.rows.clear();
    	this.columns.clear();
    	return this;
    }
    
    /**
     * 清空数据行
     */
    public void clearRows()
    {
    	this.rows.clear();
    }
    
    /**
     * 删除行
     * @param index
     * @return
     */
    public DataRow remove(Integer index)
    {
    	if(index > rows.size() - 1)
    		return null;
    	else {
			DataRow dr = this.rows.get(index);
			this.rows.remove(dr);
			return dr;
		}
    }
    
    /**
     * 删除行
     * @param index
     * @return
     */
    public boolean remove(DataRow dr)
    {
    	if(this.rows.contains(dr))
    	{
    		return this.rows.remove(dr);
    	}
    	else 
    	{
    		return false;
		}
    }

    public int getTotalCount() {
        return rows.size();
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataRowCollection getRows() {
        return this.rows;
    }

    public DataColumnCollection getColumns() {
        return this.columns;
    }

    /**
     * 得到格式化后的字符
     * @param row 行索引
     * @param colName 列名
     * @param formatString 解析字符串
     * @return
     */
    public Object getValue(int row, String colName , String formatString)
    {
    	return this.rows.get(row).getValue(colName, formatString);
    }
    
    /**
     * 得到格式化后的字符
     * @param row 行索引
     * @param col 列索引
     * @param formatString 解析字符串
     * @return
     */
    public Object getValue(int row, int col , String formatString)
    {
    	return this.rows.get(row).getValue(col , formatString);
    }
    
    /**
     * 得到值
     * @param row 行索引
     * @param colName 列名
     * @return
     */
    public Object getValue(int row, String colName) {
        return this.rows.get(row).getValue(colName);
    }

    /**
     * 得到值
     * @param row 行索引
     * @param col 列索引
     * @return
     */
    public Object getValue(int row, int col) {
        return this.rows.get(row).getValue(col);
    }

    /**
     * 新增一行
     * @return
     */
    public DataRow newRow() {
        DataRow tempRow = new DataRow(this);
        int lastRowIndex = 0;
        if (this.rows.size() > 0) {
            lastRowIndex = this.rows.get(this.rows.size() - 1).getRowIndex();
        } else {
            lastRowIndex = 0;
        }

        tempRow.setColumns(this.columns);
        tempRow.setRowIndex(++lastRowIndex);
        return tempRow;
    }

    public void setValue(int row, int col, Object value) {
        this.rows.get(row).setValue(col, value);
    }

    public void setValue(int row, String colName, Object value) {
        this.rows.get(row).setValue(colName, value);
    }

    public DataColumn addColumn(String columnName)
    {
    	return this.columns.addColumn(columnName, DataType.String);
    }
    
    public DataColumn addColumn(String columnName, DataType dataType) {
        return this.columns.addColumn(columnName, dataType);
    }

    public DataColumn addColumnIndex(int index, String columnName, DataType dataType) {
        return this.columns.addColumn(index, columnName, dataType);

    }
    
    public boolean addColumn(DataColumn dataColumn)
    {
    	return this.columns.add(dataColumn);
    }

    /**
     * 添加一行
     * @param 数据行
     * @return
     */
    public boolean addRow(DataRow row)
    {
        if (this.rows.size() > 0) {
            row.setRowIndex(this.rows.size());
        } else {
            row.setRowIndex(0);
        }
        return this.rows.add(row);
    }
    
    /**
     * 设置是否是主键列(允许多个主键)
     * @param param
     * @return
     */
    public boolean setPrimaryColumns(String... param)
    {
    	boolean result = false;
    	
    	for(DataColumn dr : columns)
    	{
    		dr.setPrimaryColumn(false);
    	}
    	
    	for(String p : param)
    	{    		
    		if(columns.contains(p))
    		{
    			columns.get(p).setPrimaryColumn(true);
    			result = true;
    		}
    	}
    	return result;
    }

    /**
     * 克隆表结构
     * @return
     */
    public DataTable cloneTableSchema() {
        try {
            DataTable table = new DataTable();
            table.setTableName(this.getTableName());
            table.setDataSourceName(this.getDataSourceName());
            table.setTableLocalName(this.getTableLocalName());
            for (DataColumn dc : this.columns) {
                DataColumn dcc = table.addColumn(dc.getColumnName(), dc.getDataType());
                dcc.setDisplayed(dc.isDisplayed());
                dcc.setPrimaryColumn(dc.isPrimaryColumn());
                dcc.setReadOnly(dc.isReadOnly());
                dcc.setCaptionName(dc.getCaptionName());
                dcc.setTag(dc.getTag());
            }
            return table;
        } catch (Exception ex) {
            return null;
        }
    }
    
    /**
     * 克隆全表
     * @return
     */
    public DataTable cloneTable() 
    {
		DataTable dt = cloneTableSchema();
		if(dt !=null)
		{
			for(DataRow dr : this.rows)
			{
				DataRow drtemp = dt.newRow();
				for(DataColumn dc : this.columns)
				{
					drtemp.setValue(dc, dr.getValue(dc.getColumnName()));
				}
				dt.addRow(drtemp);
				drtemp.setStatus(dr.getStatus());
			}
		}
		return dt;
	}
   
    /**
     * 找到符合记录的列
     * @param condition (语法遵循基本的布尔逻辑检索,支持括号嵌套，与或非关系运算符和常用的运算符)
     * @return 符合逻辑的行
     * @throws Exception 
     */
    public List<DataRow> findWithExpression(String condition) throws Exception
    {
    	DataCalculate dc = new DataCalculate(this);
    	return dc.findWithExpression(condition);
    }
    
	/**
	 * DataRow记录排序
	 * @param sortrole 字段1+排序标识，字段2+排序标识(a asc,b desc)
	 * @throws Exception 
	 */
	public void sort(String sortrole) throws Exception
	{
		DataComparator dc = new DataComparator();
		dc.setSortString(sortrole);
		dc.sort(this.rows);
	}
    
    /**
     * 重置状态
     */
    public void resetStatus()
    {
    	for(DataRow dr : rows)
    	{
    		dr.setStatus(DataRowStatus.Normal);
    	}
    }

    public String getTableLocalName() {
        return tableLocalName;
    }

    public void setTableLocalName(String tableLocalName) {
        this.tableLocalName = tableLocalName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
    
    /**
     * 转化成键值对数组
     * @return
     */
    public List<Map<String,Object>> toMapList()
    {    	
    	List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
    	
    	for(DataRow dr : this.rows)
    	{
    		Map<String, Object> rowMap = new HashMap<String, Object>();
    		for(DataColumn dc : dr.getColumns())
    		{
    			if(dc.getDataType() == DataType.Date)
    			{
    				rowMap.put(dc.getColumnName() , dr.getValue(dc.getColumnName(),"date"));
    			}
    			else if(dc.getDataType() == DataType.Time || dc.getDataType() == DataType.TimeStamp)
    			{
    				rowMap.put(dc.getColumnName() , dr.getValue(dc.getColumnName(),"datetime"));
    			}
    			else
    			{
    				rowMap.put(dc.getColumnName() , Convert.toString(dr.getValue(dc.getColumnName())));
    			}
    		}
    		map.add(rowMap);
    	}
    	
    	return map;
    }
    
    /**
     * 转化成List数组
     * @return
     */
    public List<List<String>> toList()
    {
    	List<List<String>> map = new ArrayList<List<String>>();
    	boolean initHead = false;
    	
    	List<String> head = new ArrayList<String>();
    	for(DataRow dr : this.rows)
    	{
    		List<String> rowMap = new ArrayList<String>();
    		for(DataColumn dc : dr.getColumns())
    		{
    			if(!initHead)
    				head.add(dc.getColumnName());
    			
    			if(dc.getDataType() == DataType.Date)
    			{
    				rowMap.add(Convert.toString(dr.getValue(dc.getColumnName(),"date")));
    			}
    			else if(dc.getDataType() == DataType.Time || dc.getDataType() == DataType.TimeStamp)
    			{
    				rowMap.add(Convert.toString(dr.getValue(dc.getColumnName(),"datetime")));
    			}
    			else
    			{
    				rowMap.add(Convert.toString(dr.getValue(dc.getColumnName())));
    			}
    		}
    		
    		if(!initHead)
    			map.add(head);
    		map.add(rowMap);
    		initHead = true;
    	}
    	
    	return map;
    }
    
    /**
     * 将List<DataRow>转化成新的DataTable
     * @param list
     * @return
     */
    public static DataTable toNewTable(List<DataRow> list)
    {
    	DataTable dt = null;
    	if(list != null && !list.isEmpty())
    	{
    		dt = list.get(0).getTable().cloneTableSchema();
    		for(DataRow dr : list)
    		{
    			DataRow drtemp = dt.newRow();
    			for(DataColumn dc : list.get(0).getColumns())
    			{
    				drtemp.setValue(dc, dr.getValue(dc.getColumnName()));
    			}
    			dt.addRow(drtemp);
    			drtemp.setStatus(dr.getStatus());
    		}
    	}
    	else
    	{
    		dt = new DataTable();
    	}
    	return dt;
    }
    
    /**
	 * 合并表名和数据结构都相同的两张表
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public DataTable marge(DataTable dt) throws Exception
	{
		if(dt != null && Convert.toString(this.getTableName()).equals(Convert.toString(dt.getTableName())))
		{
			DataTable original = this;
			if(original.getColumns().size() != dt.getColumns().size())
			{
				throw new Exception("需要合并的表与原表数据列或数据列类型不一致");
			}
			
			for(DataColumn dc : original.getColumns())
			{
				if(!dt.getColumns().contains(dc.getColumnName()) || 
				   !dt.getColumns().get(dc.getColumnName()).getDataType().equals(dc.getDataType()))
				{
					throw new Exception("需要合并的表与原表数据列或数据列类型不一致");
				}
			}
			
			for(DataRow dr : dt.getRows())
			{
				DataRow newRow = original.newRow();
				for(DataColumn dc : dt.getColumns())
				{
					newRow.setValue(dc.getColumnName() , dr.getValue(dc.getColumnName()));
				}
				original.addRow(newRow);
			}
		}
		else
		{
			throw new Exception("数据表为空或者数据表的名称不相同");
		}
		return this;
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}
}
