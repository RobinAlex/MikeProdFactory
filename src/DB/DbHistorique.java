package DB;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.method.DateTimeKeyListener;

import com.classes.mikaprod.Historique;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;

public class DbHistorique {
	public static final String TABLE_NAME = "Historique";
	public static final String COL_ID = "id_historique";
	public static final String COL_ID_POSTE = "id_poste";
	public static final String COL_ID_UTILISATEUR = "id_utilisateur";
	public static final String COL_ID_PRODUIT = "id_produit";
	public static final String COL_DATE_DEBUT = "date_Debut";
	public static final String COL_DATE_FIN = "date_Fin";
	public static final String[] COLS = new String[]{COL_ID, 
													COL_ID_POSTE, 
													COL_ID_UTILISATEUR, 
													COL_ID_PRODUIT, 
													COL_DATE_DEBUT, 
													COL_DATE_FIN};
	
	/**
	 * Trace le début de la production du produit, sur le poste, par l'utilisateur.
	 * @param produit
	 * @param poste
	 * @param utilisateur
	 * @param context
	 * @return
	 */
	public static Boolean TracerDebutDeTraitement(Produit produit, 
			Poste poste, Utilisateur utilisateur, Context context)
	{
		Boolean resultat = false;
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		ContentValues values = new ContentValues();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		
		values.put(COL_ID_POSTE, String.valueOf(poste.getId()));
		values.put(COL_ID_UTILISATEUR, String.valueOf(utilisateur.getId()));
		values.put(COL_ID_PRODUIT, String.valueOf(produit.getId()));
		values.put(COL_DATE_DEBUT, dateFormat.format(new Date()));
		values.put(COL_DATE_FIN, "NULL");
		
		long retour = db.insert(TABLE_NAME, null, values);

		if(retour != -1)
		{
			resultat =  true;
		}else
		{
			resultat = false;
		}
		db.close();
		
		return resultat;
	}
	
	/**
	 * Trace la fin de la production du produit, sur le poste, par l'utilisateur.
	 * @param produit
	 * @param poste
	 * @param utilisateur
	 * @param context
	 * @return
	 */
	public static Boolean TracerFinDeTraitement(Produit produit, 
			Poste poste, Utilisateur utilisateur, Context context)
	{
		Boolean resultat = false;
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		values.put(COL_DATE_FIN, dateFormat.format(new Date()));
		long retour = db.update(TABLE_NAME, 
				values, 
				COL_ID_POSTE +" = ? "
				+ COL_ID_PRODUIT +" = ? "
				+ COL_ID_UTILISATEUR +" = ? "
				+ COL_DATE_FIN +" = ? ", 
				new String [] { String.valueOf(poste.getId()), 
								String.valueOf(produit.getId()), 
								String.valueOf(utilisateur.getId()), 
								"NULL"});

		if(retour == 1)
		{
			resultat =  true;
		}else
		{
			resultat = false;
		}
		db.close();
		return resultat;
	}
	
	
	/**
	 * Retourne tout l'historique de la base.
	 * @param context
	 * @return ArrayList
	 */
	public static ArrayList<Historique> GetAll(Context context)
	{
		ArrayList<Historique> historique = new ArrayList<Historique>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		
		Cursor cursor = db.query(DbHistorique.TABLE_NAME, 
				COLS, 
				null, null, null, null, DbHistorique.COL_ID);
		if(cursor.moveToFirst())
		{
			do{
				Historique h = new Historique();
				h.setProduit(DbProduit.GetProduitById(
						Integer.parseInt(
						cursor.getString(
						cursor.getColumnIndex(COL_ID_PRODUIT))), context));
				h.setPoste(DbPoste.GetPosteById(
						Integer.parseInt(
						cursor.getString(
						cursor.getColumnIndex(COL_ID_POSTE))), context));
				h.setUtilisateur(DbUtilisateur.GetUtilisateurById(
						Integer.parseInt(
						cursor.getString(
						cursor.getColumnIndex(COL_ID_UTILISATEUR))), context));
				h.setDateDebut(String.valueOf(
						cursor.getString(
						cursor.getColumnIndex(DbHistorique.COL_DATE_DEBUT))));
				h.setDateFin(String.valueOf(
						cursor.getString(
						cursor.getColumnIndex(DbHistorique.COL_DATE_FIN))));
				
				historique.add(h);
			}while(cursor.moveToNext());
		}
		db.close();
		return historique;
	}
}
