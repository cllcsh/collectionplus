package org.express.common.expression.SQL;

public abstract class ExpressionElement 
{
	private boolean isLeftBrace;
	private boolean isRightBrace;
	private boolean isField;
	private boolean isOperator;
	private boolean isValue;
	private boolean isLogicOperator;
	private boolean isExpressionGroup;
	private Integer deep;
	
	public abstract String print();
	public boolean isLeftBrace() {
		return isLeftBrace;
	}
	public void setLeftBrace(boolean isLeftBrace) {
		this.isLeftBrace = isLeftBrace;
	}
	public boolean isRightBrace() {
		return isRightBrace;
	}
	public void setRightBrace(boolean isRightBrace) {
		this.isRightBrace = isRightBrace;
	}
	public boolean isField() {
		return isField;
	}
	public void setField(boolean isField) {
		this.isField = isField;
	}
	public boolean isOperator() {
		return isOperator;
	}
	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}
	public boolean isValue() {
		return isValue;
	}
	public void setValue(boolean isValue) {
		this.isValue = isValue;
	}
	public boolean isLogicOperator() {
		return isLogicOperator;
	}
	public void setLogicOperator(boolean isLogicOperator) {
		this.isLogicOperator = isLogicOperator;
	}
	public Integer getDeep() {
		return deep;
	}
	public void setDeep(Integer deep) {
		this.deep = deep;
	}
	public boolean isExpressionGroup() {
		return isExpressionGroup;
	}
	public void setExpressionGroup(boolean isExpressionGroup) {
		this.isExpressionGroup = isExpressionGroup;
	}
}
