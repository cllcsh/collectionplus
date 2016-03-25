/**
 * @author luoj
 * @create 2009-6-24
 * @file FuncNode.java
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
public class FuncNode {
	private Integer id;
	private String name;
	private String link;
	private Integer frontFuncId;
	private String userType;
	private String binding;
	
	private FuncNode parent;
	
	private List<FuncNode> children = new ArrayList();
	
	private Map<Integer, FuncNode> idMap = new LinkedHashMap<Integer, FuncNode>();
	
	public void addChild(FuncNode child){
		idMap.put(child.getId(), child);
		children.add(child);
		if(child.getParent() == null || !child.getParent().equals(this))
		child.setParent(this);
	}

	public void addChildren(List<FuncNode> children){
		for(FuncNode funcNode : children){
			addChild(funcNode);
		}
	}
	
	@Override
	public String toString() {
		return "FuncNode [frontFuncId=" + frontFuncId + ", id=" + id
				+ ", link=" + link + ", name=" + name + "]";
	}

	public List<FuncNode> getParents(){
		if(parent == null){
			return new ArrayList<FuncNode>();
		}
		List result = parent.getParents();
		result.add(parent);
		return result;
	} 
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getFrontFuncId() {
		return frontFuncId;
	}
	public void setFrontFuncId(Integer frontFuncId) {
		this.frontFuncId = frontFuncId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public FuncNode getParent() {
		return parent;
	}
	public void setParent(FuncNode parent) {
		this.parent = parent;
	}
	public List<FuncNode> getChildren() {
		return children;
	}
	public void setChildren(List<FuncNode> children) {
		this.children = children;
	}
	public Map<Integer, FuncNode> getIdMap() {
		return idMap;
	}
	public void setIdMap(Map<Integer, FuncNode> idMap) {
		this.idMap = idMap;
	}
	
	
}
