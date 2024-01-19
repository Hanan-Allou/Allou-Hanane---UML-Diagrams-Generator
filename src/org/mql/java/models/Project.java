package org.mql.java.models;

import java.util.Set;


public class Project {
	private String name;
	private Set<PackageM> packages;
	public Project() {
		
	}
	public Project(String name, Set<PackageM> packages) {
		this.name = name;
		this.packages = packages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<PackageM> getPackages() {
		return packages;
	}
	public void setPackages(Set<PackageM> packages) {
		this.packages = packages;
	}

	
	
}
