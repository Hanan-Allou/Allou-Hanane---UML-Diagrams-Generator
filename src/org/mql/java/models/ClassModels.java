package org.mql.java.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassModels {
	private String name;
	private int modifier;
	private Method[] methods;
	private Field[] fields;
	private java.lang.annotation.Annotation[] annotations;
	private java.lang.Class[] interfaces;
	private ClassModels parent;
	
	public ClassModels() {
		
	}

	public ClassModels(String name, int modifier, Method[] methods, Field[] fields, java.lang.annotation.Annotation[] annotations,
			java.lang.Class[] interfaces, ClassModels parent) {
		this.name = name;
		this.modifier = modifier;
		this.methods = methods;
		this.fields = fields;
		this.annotations = annotations;
		this.interfaces = interfaces;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getModifier() {
		return modifier;
	}

	public void setModifier(int modifier) {
		this.modifier = modifier;
	}

	public Method[] getMethods() {
		return methods;
	}

	public void setMethods(Method[] methods) {
		this.methods = methods;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public java.lang.annotation.Annotation[] getAnnotations() {
		return annotations;
	}

	public void setAnnotations(java.lang.annotation.Annotation[] annotations2) {
		this.annotations = annotations2;
	}

	public java.lang.Class[] getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(java.lang.Class[] interfaces) {
		this.interfaces = interfaces;
	}

	public ClassModels getParent() {
		return parent;
	}

	public void setParent(ClassModels parent) {
		this.parent = parent;
	}
	
	
	

}
