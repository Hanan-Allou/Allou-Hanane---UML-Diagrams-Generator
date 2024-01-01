package org.mql.java.models;

import java.lang.reflect.Method;
import java.util.List;

public class Interface {
	private String name;
	private List<Method> methods;
	private int modifier;
	private List<Annotation> annotations;
	public Interface() {
		
	}
	public Interface(String name, List<Method> methods, int modifier, List<Annotation> annotations) {
		this.name = name;
		this.methods = methods;
		this.modifier = modifier;
		this.annotations = annotations;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public int getModifier() {
		return modifier;
	}
	public void setModifier(int modifier) {
		this.modifier = modifier;
	}
	public List<Annotation> getAnnotations() {
		return annotations;
	}
	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

}
