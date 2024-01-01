package org.mql.java.models;

import java.util.List;

public class Project {
	private String name;
	private List<Package> packages;
	public Project() {
		
	}
	public Project(String name, List<Package> packages) {
		this.name = name;
		this.packages = packages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}

	
}
