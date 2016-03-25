
package com.osource.base.common.tree;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("tree")
public class CheckboxNode extends Node {
	
	@XStreamAsAttribute
	private String value;
	@XStreamAsAttribute
	private Boolean checked;
	@XStreamAsAttribute
	private Boolean disabled;
	@XStreamAsAttribute
	private String onchange;
	@XStreamAsAttribute
	private String type = "check";

	/**
	 * 
	 */
	public CheckboxNode() {
		super();
	}
	/**
	 * @param text
	 * @param value
	 */
	public CheckboxNode(String text, String value) {
		super(text,value);
		this.value = value;
	}
	public CheckboxNode(String text, String value,String src) {
		super(text,value,src);
		this.value = value;
	}
	public CheckboxNode(String text,String id, String value,String src) {
		super(text,id,src);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	/* (non-Javadoc)
	 * @see com.osource.base.util.tree.Node#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

}
