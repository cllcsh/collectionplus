/**
 * @author luoj
 * @create 2009-6-22
 * @file MenuNode.java
 * @since v0.1
 * 
 */
package com.osource.base.common.menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luoj
 *
 */
public class MenuNode {
	private Integer id;
	private String name;
	private String link;
	private Integer upperId;
	private String target;
	private String userType;
	
	private MenuNode parent;
	
	private List<MenuNode> children = new ArrayList();
	
	private Map<Integer, MenuNode> idMap = new LinkedHashMap<Integer, MenuNode>();

	public MenuNode(){
	}
	/**
	 * @param id
	 * @param link
	 * @param name
	 */
	public MenuNode(Integer id, String name, String link) {
		this.id = id;
		this.link = link;
		this.name = name;
	}

	public void addChild(MenuNode child){
		idMap.put(child.getId(), child);
		children.add(child);
		if(child.getParent() == null || !child.getParent().equals(this))
		child.setParent(this);
	}

	public void addChildren(List<MenuNode> children){
		for(MenuNode menuNode : children){
			addChild(menuNode);
		}
	}
	
	public boolean hasChild(MenuNode menuNode){
		return idMap.containsKey(menuNode.getId());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUpperId() {
		return upperId;
	}

	public void setUpperId(Integer upperId) {
		this.upperId = upperId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public MenuNode getParent() {
		return parent;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setParent(MenuNode parent) {
		this.parent = parent;
		if(parent!= null && !parent.hasChild(this)){
			parent.addChild(this);
		}
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}
	
}
