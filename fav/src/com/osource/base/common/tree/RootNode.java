/**
 * @author luoj
 * @create 2009-6-8
 * @file RootNode.java
 * @since v0.1
 * 
 */
package com.osource.base.common.tree;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author luoj
 *
 */
@XStreamAlias("tree")
public class RootNode extends Node {

	public RootNode(){
		
	}
	/* (non-Javadoc)
	 * @see com.osource.base.util.tree.Node#getType()
	 */
	@Override
	public String getType() {
		return null;
	}

}
