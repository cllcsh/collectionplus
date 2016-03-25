package org.express.common.expression.SQL;

import java.util.List;

import org.express.data.DataRow;

public class ExpressionGroup extends ExpressionElement {
	private List<DataRow> groupRows = null;
	
	public List<DataRow> getGroupRows() {
		return groupRows;
	}
	
	public void setGroupRows(List<DataRow> groupRows) {
		this.groupRows = groupRows;
	}
	
	public ExpressionGroup(List<DataRow> groupRows , Integer deep)
	{
		super.setDeep(deep);
		this.groupRows = groupRows;
		super.setExpressionGroup(true);
	}
	
	@Override
	public String print() {
		// TODO Auto-generated method stub
		return null;
	}
}
