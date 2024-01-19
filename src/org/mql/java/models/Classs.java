package org.mql.java.models;

import java.util.List;

public class Classs {
	private String name;
	private List<FieldsModels> fields;
	private List<MethodModels> methods;
	
	
	public Classs(String name, List<FieldsModels> fields, List<MethodModels> methods) {
		super();
		this.name = name;
		this.fields = fields;
		this.methods = methods;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FieldsModels> getFields() {
		return fields;
	}
	public void setFields(List<FieldsModels> fields) {
		this.fields = fields;
	}
	public List<MethodModels> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodModels> methods) {
		this.methods = methods;
	}
	

}
