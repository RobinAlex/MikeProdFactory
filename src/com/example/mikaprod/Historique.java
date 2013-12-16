package com.example.mikaprod;

public class Historique {
	
	private Utilisateur utilisateur;
	private Poste poste;
	private Produit produit;	
	private String dateDebut;
	private String dateFin = null;
	
	// GETTERS
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public Poste getPoste() {
		return poste;
	}
	
	public Produit getProduit() {
		return produit;
	}
	
	public String getDateDebut() {
		return dateDebut;
	}
	
	public String getDateFin() {
		return dateFin;
	}

	// SETTERS
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	// CONSTRUCTORS
	public Historique() {
	
	}

	public Historique(Utilisateur utilisateur, Poste poste, Produit produit,
			String dateDebut, String dateFin) {
		super();
		this.utilisateur = utilisateur;
		this.poste = poste;
		this.produit = produit;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	
	
}
