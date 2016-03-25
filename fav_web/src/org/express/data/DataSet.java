package org.express.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**  
     * 此类描述的是：  保存一组DataTable的数据集合对象，用于统一管理这组对象
     */
public class DataSet implements Collection<DataTable> {

	private String name;
	private Set<String> nameList;
	private List<DataTable> tables; //数据集合集	

	/**  
	 * 功能描述：  获取当前Dataset对象内包括的数据表集合
	 * @param
	 * @return: List<DataTable>
	 */
	public List<DataTable> getDataTables() {
		return this.tables;
	}

	/**  
	 * 创建一个新的实例 DataSet.     
	 */
	public DataSet() {
		if (tables == null) {
			tables = new Vector<DataTable>();
		}
		
		if(nameList==null)
			nameList = new HashSet<String>();
	}

	/**  
	 * 创建一个新的实例 DataSet.   
	 * @param name  
	 */
	public DataSet(String name) {
		this();
		this.name = name;
	}

	/**  
	 * 功能描述：  获取当前Dataset的名称
	 * @param
	 * @return: String
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 功能描述：  设置数据集对象的名称
	 * @param
	 * @return: void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 功能描述：  添加一个新的数据表到集合
	 * @param
	 * @return: void
	 */
	public boolean addDataTable(DataTable dtb) {
		if(nameList.contains(dtb.getTableName()))
			return false;
		else
		{
			nameList.add(dtb.getTableName());
			dtb.setDataSet(this);
			this.tables.add(dtb);
			return true;
		}
	}
	
	/**
	 * 合并表名和数据结构都相同的两张表
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public DataSet marge(DataTable dt) throws Exception
	{
		if(nameList.contains(dt.getTableName()))
		{
			DataTable original = this.getDataTable(dt.getTableName());
			original.marge(dt);
		}
		else
		{
			throw new Exception("数据集中不包含名称相同的数据表");
		}
		return this;
	}

	/**  
	 * 功能描述：  获取一个已经存在的数据表
	 * @param
	 * @return: DataTable
	 */
	public DataTable getDataTable(int index) {
		return this.tables.get(index);
	}

	/**  
	 * 功能描述：  获取指定名称的数据表对象
	 * @param
	 * @return: DataTable
	 */
	public DataTable getDataTable(String tableName) {
		for (DataTable table : tables) {
			if (table != null && table.getTableName().equals(tableName))
				return table;
		}
		return null;
	}

	/**  
	 * 功能描述：  删除指定名称的数据表
	 * @param
	 * @return: void
	 */
	public void RemoveDataTable(String tableName) {
		int i = -1;
		for (DataTable table : tables) {
			if (table != null && table.getTableName().equals(tableName))
				i++;
		}
		if (i > -1)
			RemoveDataTable(i);

	}

	/**  
	 * 功能描述：  删除指定位置的数据表
	 * @param
	 * @return: void
	 */
	public void RemoveDataTable(int tableIndex) {
		tables.remove(tableIndex);
	}

	/**  
	 * 功能描述：  获取当前数据集合包括的数据表对象
	 * @param
	 * @return: int
	 */
	public int size() {
		return this.tables.size();
	}

	@Override
	public boolean isEmpty() {
		return tables.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return tables.contains(o);
	}

	@Override
	public Iterator<DataTable> iterator() {
		return tables.iterator();
	}

	@Override
	public Object[] toArray() {
		return tables.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return tables.toArray(a);
	}

	@Override
	public boolean add(DataTable e) {
		if(nameList.contains(e.getTableName()))
			return false;
		else
		{
			nameList.add(e.getTableName());
			e.setDataSet(this);
			this.tables.add(e);
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		return tables.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return tables.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends DataTable> c) {
		return tables.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return tables.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return tables.retainAll(c);
	}

	@Override
	public void clear() {
		tables.clear();
	}
}
