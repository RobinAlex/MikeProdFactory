package com.classes.mikaprod;

public class Utilisateur {
	
	private int id;
	private String nom;
	
	// GETTERS
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	
	// SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	// CONSTUCTORS
	public Utilisateur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public Utilisateur() {
	}
	
	
}
