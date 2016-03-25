/**
 * @author luoj
 * @create 2009-6-8
 * @file NormalNode.java
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
public class NormalNode extends Node {
	@XStreamAsAttribute
	private String action;
	@XStreamAsAttribute
	private String target;
	@XStreamAsAttribute
	private String type = "normal";
	
	
	
	/**
	 * 
	 */
	public NormalNode() {
		super();
	}
	
	
	/**
	 * @param text
	 */
	public NormalNode(String text) {
		super(text);
	}



	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String getType() {
		return type;
	}
}
