package DB;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;

public class DbPoste {

	public static final String TABLE_NAME = "Poste";
	public static final String COL_ID = "id_poste";
	public static final String COL_NOM = "nom";
	public static final String COL_ORDRE_FLUX = "ordre_flux";
	public static final String COL_POSTE_FINAL = "flag_final";
	public static final String[] COLS = 
			new String[] {COL_ID, COL_NOM, COL_ORDRE_FLUX, COL_POSTE_FINAL};

	
	/**
	 * Retourne le poste qui correspond à l'ID passé en paramètre.
	 * @param idPoste
	 * @param context
	 * @return Poste, null si aucun object trouvé en base
	 */
	public static Poste GetPosteById(int idPoste, Context context) 
	{
		Poste poste = new Poste();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, COLS, null, null, null, null,
				null, null);
		if(cursor.moveToFirst())
		{
			poste.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
			poste.setNom(cursor.getString(cursor.getColumnIndex(COL_NOM)));
			poste.setOrdreFlux(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ORDRE_FLUX))));
			poste.setFlagFluxFinal(
					Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_POSTE_FINAL))) == 1 ? true : false);
		}else
		{
			poste = null;
		}
		db.close();
		return poste;
	}
	
	/**
	 * Retourne tous les postes de la base triés par l'ordre des flux.
	 * @param context
	 * @return int
	 */
	public static ArrayList<Poste> GetAll(Context context)
	{
		ArrayList<Poste> postes = new ArrayList<Poste>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, 
				COLS, 
				null, null, null, null, COL_ORDRE_FLUX);
		if(cursor.moveToFirst())
		{
			do{
				Poste p = new Poste();
				p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
				p.setNom(cursor.getString(cursor.getColumnIndex(COL_NOM)));
				p.setOrdreFlux(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ORDRE_FLUX))));
				p.setFlagFluxFinal(
					Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_POSTE_FINAL))) == 1 ? true : false);
				
				postes.add(p);
			}while(cursor.moveToNext());
		}
		db.close();
		return postes;
	}
	
	/**
	 * Cherche l'id du dernier poste de la chaîne de production.
	 * @param context
	 * @return int
	 */
	public static int GetIdDernierPoste(Context context)
	{
		int idDernierPoste;
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		
		String requete = "SELECT "+ COL_ID 
				+" FROM " + TABLE_NAME 
				+" ORDER BY " + COL_ID + " DESC";
		
		Cursor cursor = db.rawQuery(requete, null);
		if(cursor.moveToFirst())
		{
			idDernierPoste = Integer.parseInt(
					cursor.getString(cursor.getColumnIndex(COL_ID)));
		}else
		{
			idDernierPoste = -1;
		}
		db.close();
		return idDernierPoste;
	}
	
	/**
	 * Cherche l'id du premier poste de la chaîne de production
	 * @param context
	 * @return int
	 */
	public static int GetIdPremierPoste(Context context)
	{
		int idPremierPoste;
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		
		String requete = "SELECT "+ COL_ID 
				+" FROM " + TABLE_NAME 
				+" ORDER BY " + COL_ID + " ASC";
		
		Cursor cursor = db.rawQuery(requete, null);
		
		if(cursor.moveToFirst())
		{
			idPremierPoste = Integer.parseInt(
					cursor.getString(cursor.getColumnIndex(COL_ID)));
		}else
		{
			idPremierPoste = -1;
		}
		db.close();
		return idPremierPoste;
	}
	

	/**
	 * Retourne le poste suivant dans le flux.
	 * @param poste
	 * @param context
	 * @return Poste
	 */
	public static Poste GetPosteSuivant(Poste poste, Context context)
	{
		Poste posteSuivant = null;
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		
		String requete = "SELECT * FROM " + TABLE_NAME
				+ " WHERE " + COL_ORDRE_FLUX +" > " + poste.getOrdreFlux()
				+ " ORDER BY "+COL_ORDRE_FLUX
				+ " LIMIT 1;";
		
		Cursor cursor = db.rawQuery(requete, null);
		if(cursor.moveToFirst())
		{
			Poste p = new Poste();
			p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
			p.setNom(cursor.getString(cursor.getColumnIndex(COL_NOM)));
			p.setOrdreFlux(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ORDRE_FLUX))));
			p.setFlagFluxFinal(
				Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_POSTE_FINAL))) == 1 ? true : false);
				
		}
		db.close();		
		return posteSuivant;
	}
	
	
	/**
	 * Regarde si un produit est déjà engagé dans ce poste.
	 * @param poste
	 * @param context
	 * @return Boolean
	 */
	public static Boolean ProduitDejaEngageAuPoste(Poste poste, Context context)
	{
		Boolean resultat = false;
		
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(DbProduit.TABLE_NAME, 
				new String[] {"COUNT("+DbProduit.COL_ID_POSTE+")"}, 
				DbProduit.COL_ID_POSTE +" = ?", 
				new String[] { String.valueOf(poste.getId()) }, 
				null, null, null);
		if (cursor.getCount() > 0) {
			resultat = true;
		}
		db.close();
		return resultat;
	}
	
	
	/**
	 * Engage le produit en fabrication sur le poste passé en paramètre.
	 * La traçabilité de l'opération est assurée dans la méthode.
	 * ! NE PAS ASSURER LA TRACABILITE EN DEHORS DE CETTE METHODE !
	 * @param produit
	 * @param poste
	 * @param utilisateur
	 * @param context
	 * @return Boolean
	 */
	public static Boolean CommencerTraitementProduit(Produit produit, 
			Poste poste, Utilisateur utilisateur, Context context)
	{
		Boolean resultat = false;
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(DbProduit.COL_ID_POSTE, poste.getId());
        
		int retour = db.update(DbProduit.TABLE_NAME, 
				values, 
				DbProduit.COL_ID + " = ?", 
				new String[] { String.valueOf(produit.getId()) });
		
		if(retour == 1)
		{
			resultat = true;
			
			if(!DbHistorique.TracerDebutDeTraitement(produit, 
					poste, utilisateur, context))
			{
				resultat = false;
			}
		}else
		{
			resultat = false;
		}
		db.close();
		return resultat;
	}
	
	/**
	 * Désengage le produit en fabrication sur le poste passé en paramètre.
	 * La traçabilité de l'opération est assurée dans la méthode.
	 * ! NE PAS ASSURER LA TRACABILITE EN DEHORS DE CETTE METHODE !
	 * @param produit Doit déjà être réglé sur le poste suivant.
	 * @param poste
	 * @param utilisateur
	 * @param context
	 * @return Boolean
	 */
	public static Boolean TerminerTraitementProduit(Produit produit, 
			Poste poste, Utilisateur utilisateur, Context context)
	{
		Boolean resultat = false;
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(DbProduit.COL_ID_POSTE, produit.getPoste().getId());
        
		int retour = db.update(DbProduit.TABLE_NAME, 
				values, 
				DbProduit.COL_ID + " = ?", 
				new String[] { String.valueOf(produit.getId()) });
		
		if(retour == 1)
		{
			resultat = true;
			
			if(!DbHistorique.TracerDebutDeTraitement(produit, 
					poste, utilisateur, context))
			{
				resultat = false;
			}
		}else
		{
			resultat = false;
		}
		db.close();
		return resultat;
	}
	
	/**
	 * Si un produit est engagé sur le poste, il sera retourné.
	 * ! LE TEST DE VERIFICATION (si un produit est engagé) 
	 * EST INCLUS DANS LA METHODE ! 
	 * 
	 * Ne retourne que le PREMIER produit engagé.
	 * @param poste
	 * @param context
	 * @return Le PREMIER Produit engagé ou null si aucun produit n'est 
	 * engagé sur le poste
	 */
	public static Produit GetProduitEngage(Poste poste, Context context)
	{
		Produit produit = new Produit();
		if(DbPoste.ProduitDejaEngageAuPoste(poste, context))
		{
			SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
			Cursor cursor = db.query(DbProduit.TABLE_NAME, 
					new String[] {COL_ID}, 
					DbProduit.COL_ID_POSTE +" = ?", 
					new String[] { String.valueOf(poste.getId()) }, 
					null, null, null);

			if(cursor.moveToFirst())
			{
			   produit = DbProduit.GetProduitById(
						   Integer.parseInt(
							   cursor.getString(
								   cursor.getColumnIndex(DbProduit.COL_ID))), 
								   context);
			}
			
			db.close();
		}else
		{
			produit = null;
		}
		
		return produit;
	}
}
