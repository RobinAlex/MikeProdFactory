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
	public Poste getPosteById(int id) {
		/* TODO SQL : Requete get Poste		 
		 SELECT * FROM Poste where id = id;
		*/
		 
		return poste;
	}
	
	
	public boolean PasserAuPosteSuivant() { 
		
		boolean status = false;	
		
		if (this.getPoste() == null) {
			// Au stock
			this.setPoste(this.getPosteById(Poste.getIdPremierPoste()));			
			status = true;
			
		} else if (this.getPoste().getOrdreFlux() < Poste.getIdDernierPoste()) {
			// Si en prod, mais pas au dernier poste
			this.setPoste(this.getPosteById(this.getPoste().getOrdreFlux() + 1));
			status = true;
		
		} else if (this.getPoste().getOrdreFlux() == Poste.getIdDernierPoste()) {
			// Au dernier poste
			this.setFlagProduitTermine(true);
			status = true;
			
		}
		
		return status;
	}
	
	
	
	
}
