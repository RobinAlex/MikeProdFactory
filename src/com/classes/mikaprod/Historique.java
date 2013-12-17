package com.classes.mikaprod;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	//METHODES
	public Boolean AjouterLigneHistorique(Poste poste, Utilisateur utilisateur, Produit produit, Boolean entree) {
		
		Boolean status = false;
		Date dateActuelle = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
		
		if (entree) {
			// Si entree == true -> Ajout d'une ligne d'entree
			this.dateDebut = formatDate.format(dateActuelle);
			this.dateFin = null;
		} else if (!entree) {
			// Si entree == false -> Ajout d'une ligne de fin
			this.dateFin = formatDate.format(dateActuelle);
			this.dateDebut = null;
		} else {
			// Sinon status de la methode false
			status = false;
		}
		
		if (poste != null) {
			this.poste = poste;
		} else {
			status = false;
		}
		
		if (utilisateur != null) {
			this.utilisateur = utilisateur;
		} else {
			status = false;
		}
		
		if (produit != null) {
			this.produit = produit;
		} else {
			status = false;
		}		
		
		
		if (status) {
			// TODO : Ajout de la ligne en db
			// INSERT INTO Historique (idPoste, idUser, idProduit, dateDebut, 
			// dateFin) VALUES (poste.getId, utilisateur.getId, produit.getId, 
			// this.dateDebut, this.dateFin);
		} 
		
		return status;
		
	}
	
	
	
	
}
