package com.classes.mikaprod;

public class Produit {
	
	private int id;
	private Commande commande;
	private Poste poste;
	private Boolean flagEnAttente;
	private Boolean flagProduitTermine;
	
	// GETTERS	
	public int getId() {
		return id;
	}
	public Commande getCommande() {
		return commande;
	}	
	public Poste getPoste() {
		return poste;
	}	
	public Boolean getFlagEnAttente() {
		return flagEnAttente;
	}	
	public Boolean getFlagProduitTermine() {
		return flagProduitTermine;
	}
	
	// SETTERS
	public void setId(int id) {
		this.id = id;
	}
	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	public void setPoste(Poste poste) {
		this.poste = poste;
	}
	public void setFlagEnAttente(Boolean flagEnAttente) {
		this.flagEnAttente = flagEnAttente;
	}
	public void setFlagProduitTermine(Boolean flagProduitTermine) {
		this.flagProduitTermine = flagProduitTermine;
	}
	
	// CONSTUCTORS
	public Produit(int id, Commande commande, Poste poste,
			Boolean flagEnAttente, Boolean flagProduitTermine) {
		this.id = id;
		this.commande = commande;
		this.poste = poste;
		this.flagEnAttente = flagEnAttente;
		this.flagProduitTermine = flagProduitTermine;
	}
	
	public Produit() {
	}
	
	
	// METHODES
	
	
	
	
	
}
