package com.classes.mikaprod;

public class Utilisateur {

	private int id;
	private String nom;
	private Poste poste;

	// GETTERS
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Poste getPoste() {
		return poste;
	}

	// SETTERS
	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	// CONSTUCTORS
	public Utilisateur(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public Utilisateur() {
	}

	@Override
	public String toString() {
		return this.getNom();
	}

}
