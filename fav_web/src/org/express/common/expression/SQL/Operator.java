package org.express.common.expression.SQL;

public class Operator extends ExpressionElement {
	private String operator;
	public Operator(String o , Integer deep)
	{
		super.setDeep(deep);
		this.operator = o;
		super.setOperator(true);
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@Override
	public String print() {
		// TODO Auto-generated method stub
		return operator;
	}
}
