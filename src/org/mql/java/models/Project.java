package org.mql.java.models;

import java.util.Set;


public class Project {
	private String name;
	private Set<PackageM> packages;
	private Set<Relation> relations;
	public Project() {
		
	}
	public Project(String name, Set<PackageM> packages, Set<Relation> relations) {
		super();
		this.name = name;
		this.packages = packages;
		this.relations = relations;
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
	public Set<Relation> getRelations() {
		return relations;
	}
	public void setRelations(Set<Relation> relations) {
		this.relations = relations;
	}
	

}
