package org.mql.java.models;

public class ElementData {
	private String nom;
	private Object valeur;
	public ElementData() {
		
	}
	public ElementData(String nom, Object valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Object getValeur() {
		return valeur;
	}
	public void setValeur(Object valeur) {
		this.valeur = valeur;
	}
	

}
