package org.mql.java.xml.dom;

import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	
	//class raper
	
	private Node node;
	private XMLNode children[]; // des elements 

	public XMLNode(String sources) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		//factory.setValidating(true);// Parseur validant
		try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(sources);
		
		
		Node node  = document.getFirstChild();
		
		
		// document.getDocumentElement;  =>Element
		while(node.getNodeType() != Node.ELEMENT_NODE) {
		node = node.getNextSibling();
		}
		
		setNode(node);
		
		/*System.out.println(
				node.getNodeName()+","+
				node.getNodeType()+","+
				node.getNodeValue()
				);*/
	/*	
		System.out.println("Comment Node:"+Node.COMMENT_NODE);
		System.out.println("Element Node:"+Node.ELEMENT_NODE);
		System.out.println("Comment Node:"+Node.COMMENT_NODE);
		System.out.println("Document Type Node:"+Node.DOCUMENT_TYPE_NODE);*/
		}
		catch(Exception e) {
			System.out.println("erreur"+e.getMessage());
		}
	}

	public XMLNode(Node node) {
		super();
		this.node = node;
		setNode(node);
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
		extractChildren();
	}
	
	private void extractChildren() {
		NodeList list = node.getChildNodes();
		LinkedList<XMLNode>  nodes = new LinkedList<XMLNode>();
		
		for(int i=0; i<list.getLength(); i++) {
			if(list.item(i).getNodeType() == Node.ELEMENT_NODE ) {
				nodes.add(new XMLNode(list.item(i)));
			//System.out.println(list.item(i).getNodeName());
			}
		}
		
		children = new XMLNode[nodes.size()];
		nodes.toArray(children);
	}
	
	public XMLNode[] getChildren() {
		return children;
		
	}
	public boolean isNamed(String name) {
		return node.getNodeName().equals(name);
	}
	
	public XMLNode getChild(String name) {
		for(XMLNode child : children) {
			if(child.isNamed(name)) {
				return child;
			}
		}
		return null;
		
	}
	
	
	public String getValue() {
		Node child = node.getFirstChild();
		if(child != null && child.getNodeType() == Node.TEXT_NODE) {
			child.getNodeValue();
			return child.getNodeValue();	}
		return null;
	}
	
	public String getAttribute(String name) {
		Node att = node.getAttributes().getNamedItem(name);
		node.getAttributes().getNamedItem(name).getChildNodes();
		
		if(att == null) return "";
		return att.getNodeValue();
	}
 
	public int getIntAttribute(String name) {
		String s = getAttribute(name);
		int value = 0;
		
		try {
			value = Integer.parseInt(s);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return value;
	}
}
