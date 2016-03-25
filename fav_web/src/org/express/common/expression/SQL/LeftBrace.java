package org.express.common.expression.SQL;

public class LeftBrace extends ExpressionElement{
	private Integer index;
	
	public LeftBrace(Integer index)
	{
		super.setDeep(index);
		super.setLeftBrace(true);
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return "(";
	}
}
