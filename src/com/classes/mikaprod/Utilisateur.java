package com.classes.mikaprod;

import java.util.ArrayList;

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
	
	// METHODES
	public Boolean ConnexionAuPoste(Poste poste) {
		
		Boolean status = false;
		
		// TODO : changer Poste
				
		return status;
		
		
	}
	
	public Boolean DeconnexionDuPoste() {
		
		Boolean status = false;
		
		// TODO : Enlever Poste
				
		return status;
		
		
	}
	
	public static ArrayList<Utilisateur> GetAll() {
		
		ArrayList<Utilisateur> ListeUtilisateur = new ArrayList<Utilisateur>() ;
		
		// SELECT * FROM Utilisateur ;
		
		// TODO : ListeUtilisateur = SQLutilisateurs.selectAll()
		
		return ListeUtilisateur;
		
	}
	
	
	
}
