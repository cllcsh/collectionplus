package org.express.common.expression.SQL;

public class Field extends ExpressionElement {
	private String field;
	public Field(String f , Integer deep)
	{
		super.setDeep(deep);
		field = f;
		super.setField(true);
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@Override
	public String print() {
		// TODO Auto-generated method stub
		return field;
	}
}
