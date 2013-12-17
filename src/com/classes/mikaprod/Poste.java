package com.classes.mikaprod;

import java.util.ArrayList;

public class  Poste {
	
	private int id;
	private String nom;
	private int ordreFlux;
	private Boolean flagFluxFinal;
	
	private static int IdPremierPoste;
	private static int IdDernierPoste;
	

	// GETTERS
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getOrdreFlux() {
		return ordreFlux;
	}
	
	public Boolean getFlagFluxFinal() {
		return flagFluxFinal;
	}
	
	public static int getIdPremierPoste() {
		return IdPremierPoste;
	}	

	public static int getIdDernierPoste() {
		return IdDernierPoste;
	}
	
	// SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setOrdreFlux(int ordreFlux) {
		this.ordreFlux = ordreFlux;
	}
	
	public void setFlagFluxFinal(Boolean flagFluxFinal) {
		this.flagFluxFinal = flagFluxFinal;
	}
	
	public static void setIdPremierPoste(int idPremierPoste) {
		IdPremierPoste = idPremierPoste;
	}
	public static void setIdDernierPoste(int idDernierPoste) {
		IdDernierPoste = idDernierPoste;
	}
	
	
	// CONSTUCTORS
	public Poste(int id, String nom, int ordreFlux, Boolean flagFluxFinal) {
		this.id = id;
		this.nom = nom;
		this.ordreFlux = ordreFlux;
		this.flagFluxFinal = flagFluxFinal;
	}
	
	public Poste() {
		
	}
	
	
	public static ArrayList<Poste> GetAll() {
		
		ArrayList<Poste> ListePoste = new ArrayList<Poste>() ;
		
		// SELECT * FROM Poste ;
		
		// TODO : ListePoste = SQLPoste.selectAll()
		
		return ListePoste;
		
	}
	
	
	
}
