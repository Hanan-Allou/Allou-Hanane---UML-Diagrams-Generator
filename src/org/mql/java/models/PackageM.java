package org.mql.java.models;

import java.util.List;
import java.util.Set;

public class PackageM {
	private String name;
	private Set<Classs> classs;
	
	public PackageM() {
		
	}

	public PackageM(String name, Set<Classs> classs) {
		this.name = name;
		this.classs = classs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Classs> getClasss() {
		return classs;
	}

	public void setClasss(Set<Classs> classs) {
		this.classs = classs;
	}

	@Override
	public String toString() {
		return "Package name : "+getName()+"   "+ " classes : "+getClass();
	}
	

}
