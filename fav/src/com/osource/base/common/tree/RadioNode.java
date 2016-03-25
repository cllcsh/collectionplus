/**
 * @author luoj
 * @create 2009-6-8
 * @file RadioNode.java
 * @since v0.1
 * 
 */
package com.osource.base.common.tree;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author luoj
 *
 */
@XStreamAlias("tree")
public class RadioNode extends Node {
	@XStreamAsAttribute
	private String value;
	@XStreamAsAttribute
	private Boolean checked;
	@XStreamAsAttribute
	private Boolean disabled;
	@XStreamAsAttribute
	private String onchange;
	@XStreamAsAttribute
	private String type = "radio";
	
	
	/**
	 * 
	 */
	public RadioNode() {
		super();
	}
	/**
	 * @param text
	 * @param value
	 */
	public RadioNode(String text, String value) {
		super(text);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean isChecked() {
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
	public String getType() {
		return type;
	}
}
