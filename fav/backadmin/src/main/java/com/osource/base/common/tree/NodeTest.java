package com.osource.base.common.tree;

import com.thoughtworks.xstream.XStream;

public class NodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node root = new RootNode();
		Node node = new NormalNode("luojin");
		Node redioNode = new RadioNode("1","罗进");
		Node checkNode = new CheckboxNode("","罗进");
		node.addChild(redioNode);
		node.addChild(checkNode);
		root.addChild(node);
		XStream xs = new XStream();
		xs.autodetectAnnotations(true);
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		System.out.println(xs.toXML(root));
	}

}
