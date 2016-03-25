package org.express.common.expression.SQL;

public class RightBrace extends ExpressionElement {
	private Integer index;
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public RightBrace(Integer index)
	{
		super.setDeep(index);
		super.setRightBrace(true);
		this.index = index;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return ")";
	}
}
