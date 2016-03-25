/**
 * @author luoj
 * @create 2009-6-8
 * @file Node.java
 * @since v0.1
 * 
 */
package com.osource.base.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author luoj
 *
 */
@XStreamAlias("tree")
public abstract class Node {
	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String text;
	@XStreamAsAttribute
	private String src;
//	@SuppressWarnings("unused")
//	@XStreamAsAttribute
//	private String type;
	@XStreamAsAttribute
	private String icon;
	@XStreamAsAttribute
	private String openIcon;
	@XStreamAsAttribute
	private String color;
	@XStreamImplicit
	private List<Node> children = new ArrayList();
	
	
	public Node(){}
	
	/**
	 * @param text
	 */
	public Node(String text) {
		this.text = text;
	}
	public Node(String text,String id) {
		this.text = text;
		this.id = id;
	}
	public Node(String text,String id,String src) {
		this.text = text;
		this.id = id;
		this.src = src;
	}


	public void addChild(Node child){
		children.add(child);
	}
	
	public void addCChild(Node child){
		if(children.size()==0){
			children.add(child);
		}else{
		int n = children.size()-1;
		children.add(null);
		while(n>=0&&Integer.parseInt(child.id)<Integer.parseInt(children.get(n).id)){

			children.set(n+1, children.get(n));
			children.set(n, null);
			n--;
		}
		children.set(n+1, child);
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public abstract String getType();
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getOpenIcon() {
		return openIcon;
	}
	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<Node> getChildren() {
		return children;
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}

}
