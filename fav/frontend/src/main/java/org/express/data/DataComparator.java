package org.express.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataComparator
{
	private List<CompareCond> conditionList = new ArrayList<DataComparator.CompareCond>();
	private String sortString = null;
	
	public List<DataRow> sort(List<DataRow> dtRows) throws Exception
	{
		if(sortString != null && sortString.length() > 0)
		{
			String[] sortPart = sortString.split(",");
			for(String sortp : sortPart)
			{
				String[] so = sortp.trim().replaceAll("( )+", " ").split(" ");
				if(so.length == 2)
				{
					CompareCond c = new CompareCond();
					c.setColumnName(so[0]);
					c.setSortStr(so[1].toLowerCase());
					conditionList.add(c);
				}
				else if(so.length == 1)
				{
					CompareCond c = new CompareCond();
					c.setColumnName(so[0]);
					c.setSortStr("asc");
					conditionList.add(c);
				}
				else
				{
					throw new Exception("表达式有误");
				}
			}
		}
		
		if(dtRows != null && dtRows.size() > 0)
		{
			Comparator<DataRow> c = new Comparator<DataRow>() 
			{
				@Override
				public int compare(DataRow o1, DataRow o2) {
					// TODO Auto-generated method stub
					int i=0;
					for(CompareCond cc : conditionList)
					{
						i =o1.compare(o2, cc);
						if(i!=0)
							break;
					}
					return i;
				}
			};
			Collections.sort(dtRows , c);
		}
		
		return dtRows;
	}
	
	public final class CompareCond
	{
		private String columnName;
		private String sortStr;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getSortStr() {
			return sortStr;
		}
		public void setSortStr(String sortStr) {
			this.sortStr = sortStr;
		}
	}

	public String getSortString() {
		return sortString;
	}
	
	public void setSortString(String sortString) {
		this.sortString = sortString;
	}
}
