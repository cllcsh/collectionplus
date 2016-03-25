package org.express.common.expression.SQL;

public class LogicOperator extends ExpressionElement {
	private String logicoperator;

	public String getLogicoperator() {
		return logicoperator;
	}

	public void setLogicoperator(String logicoperator) {
		this.logicoperator = logicoperator;
	}
	
	public LogicOperator(String l , Integer deep)
	{
		super.setDeep(deep);
		super.setLogicOperator(true);
		logicoperator = l;
	}

	@Override
	public String print() {
		// TODO Auto-generated method stub
		return " " + logicoperator + " ";
	}
}
