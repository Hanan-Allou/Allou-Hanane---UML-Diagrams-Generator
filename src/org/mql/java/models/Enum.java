package org.mql.java.models;

import java.util.List;

public class Enum {
	private String name;
	private List<ElementData> elements;
	public Enum() {
		
	}
	public Enum(String name, List<ElementData> elements) {
		this.name = name;
		this.elements = elements;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ElementData> getElements() {
		return elements;
	}
	public void setElements(List<ElementData> elements) {
		this.elements = elements;
	}

}
