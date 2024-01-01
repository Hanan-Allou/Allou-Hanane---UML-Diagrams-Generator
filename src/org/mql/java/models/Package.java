package org.mql.java.models;

import java.util.List;

public class Package {
	private String name;
	private List<Class> classs;
	private List<Package> packages;
	
	public Package() {
		
	}

	public Package(String name, List<Class> classs, List<Package> packages) {
		this.name = name;
		this.classs = classs;
		this.packages = packages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Class> getClasss() {
		return classs;
	}

	public void setClasss(List<Class> classs) {
		this.classs = classs;
	}

	public List<Package> getPackages() {
		return packages;
	}

	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	

}
