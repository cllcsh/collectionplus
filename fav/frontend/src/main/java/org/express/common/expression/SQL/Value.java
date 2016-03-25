package org.express.common.expression.SQL;

import org.express.util.StringUtil;

public class Value  extends ExpressionElement{
	private String value;
	private String originalvalue;
	public Value(String v , Integer deep)
	{
		super.setDeep(deep);
		originalvalue = v;
		value = originalToReal();
		super.setValue(true);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOriginalvalue() {
		return originalvalue;
	}
	public void setOriginalvalue(String originalvalue) {
		this.originalvalue = originalvalue;
	}
	@Override
	public String print() {
		// TODO Auto-generated method stub
		return originalvalue;
	}
	
	private String originalToReal()
	{
		return StringUtil.Trim(this.originalvalue).replaceAll("'", "");
	}
}
