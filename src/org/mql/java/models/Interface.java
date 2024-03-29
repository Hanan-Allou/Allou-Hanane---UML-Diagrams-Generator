package org.mql.java.models;


import java.util.List;

public class Interface {
	private String name;
	private List<MethodModels> methods;
	private List<FieldsModels> fields;
	public Interface(String name, List<MethodModels> methods, List<FieldsModels> fields) {
		super();
		this.name = name;
		this.methods = methods;
		this.fields = fields;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MethodModels> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodModels> methods) {
		this.methods = methods;
	}
	public List<FieldsModels> getFields() {
		return fields;
	}
	public void setFields(List<FieldsModels> fields) {
		this.fields = fields;
	}
	
	
	

}
