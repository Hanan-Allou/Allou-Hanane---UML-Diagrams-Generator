package org.mql.java.models;

import java.util.ArrayList;
import java.util.List;


public class Project {
	private String name;
	private List<String> packages;
	public Project() {
		
	}
	public Project(String name, List<String> packages) {
		this.name = name;
		this.packages = packages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPackages() {
		return packages;
	}
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}

	
	
}
