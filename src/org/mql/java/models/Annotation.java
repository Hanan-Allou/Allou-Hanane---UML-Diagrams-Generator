package org.mql.java.models;

import java.util.List;

public class Annotation {
	private String name;
	private List<ElementData> elements;
	
	public Annotation() {
		
	}

	public Annotation(String name, List<ElementData> elements) {
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

	@Override
	public String toString() {
		return "name=" + name + ", elements=" + elements ;
	}
	

}
