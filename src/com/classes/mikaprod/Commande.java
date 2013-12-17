package com.classes.mikaprod;

import java.util.ArrayList;

public class Commande {
	
	private int id;
	private String client;
	private int quantite;
	private String type;
	private String matiere;
	
	// GETTERS
	public int getId() {
		return id;
	}	
	public String getClient() {
		return client;
	}	
	public int getQuantite() {
		return quantite;
	}	
	public String getType() {
		return type;
	}	
	public String getMatiere() {
		return matiere;
	}
	
	// SETTERS
	public void setClient(String client) {
		this.client = client;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	
	// CONSTRUCTORS
	public Commande(int id, String client, int quantite, String type,
			String matiere) {
		this.id = id;
		this.client = client;
		this.quantite = quantite;
		this.type = type;
		this.matiere = matiere;
	}
	
	public Commande() {
		
	}
	
	
	// METHODES
	public ArrayList<Produit> CreerProduits() {
		
		ArrayList<Produit> listeProduit = new ArrayList<Produit>(); 
		
		for (int i = 0; i < this.getQuantite(); i++) {
			listeProduit.add(new Produit(-1, this, null, true, false));
			
			/*
			 INSERT INTO Produit (idCommande, enAttente, termine) VALUES (this.getId, true, false);
			 */
		}
		
		return listeProduit;
	}
	
	
	
}
