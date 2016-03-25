/**
 * @author luoj
 * @create 2009-5-11
 * @file Menu.java
 * @since v0.1
 * 
 */
package com.osource.base.model;

/**
 * @author luoj
 *
 */
public class Function {
	private Integer id;
	private String name;
	private String link;
	

	
	/**
	 * 
	 */
	public Function() {
	}
	/**
	 * @param id
	 * @param link
	 * @param name
	 */
	public Function(Integer id, String name, String link) {
		this.id = id;
		this.link = link;
		this.name = name;
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
	
	
}
