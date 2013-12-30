package DB;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;

public class DbProduit {

	public static final String TABLE_NAME = "Produit";
	public static final String COL_ID = "id_produit";
	public static final String COL_ID_COMMANDE = "id_commande";
	public static final String COL_ID_POSTE = "id_poste";
	public static final String COL_FLAG_ATTENTE = "flag_attente";
	public static final String COL_FLAG_TERMINE = "flag_termine";
	
	public static final String[] COLS = new String[] {COL_ID, COL_ID_COMMANDE, COL_ID_POSTE, 
		COL_FLAG_ATTENTE, COL_FLAG_TERMINE};

	
	/**
	 * Cherche le produit correspondant au ID passé en paramètre.
	 * @param idProduit
	 * @param context
	 * @return Produit ou null si aucun produit trouvé.
	 */
	public static Produit GetProduitById(int idProduit, Context context)
	{
		Produit p = new Produit();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(DbProduit.TABLE_NAME, 
				COLS, 
				COL_ID + " = ? ", 
				new String[] { String.valueOf(idProduit)}, 
				null, 
				null, 
				null);
		
		if(cursor.moveToFirst())
		{
			p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
			String tempIdPoste = cursor.getString(cursor.getColumnIndex(COL_ID_POSTE));
			Poste posteProduit = null;
			if(tempIdPoste != null && !tempIdPoste.equals("NULL"))
	        {
	        	posteProduit = DbPoste.GetPosteById(Integer.parseInt(tempIdPoste), context);
	        }
			p.setPoste(posteProduit);
			
			Boolean flagEnAttente = Integer.parseInt(
					cursor.getString(
					cursor.getColumnIndex(COL_FLAG_ATTENTE))) == 1 ? true : false;
			Boolean flagTermine = Integer.parseInt(
					cursor.getString(
					cursor.getColumnIndex(COL_FLAG_TERMINE))) == 1 ? true : false;
			p.setFlagEnAttente(flagEnAttente);
			p.setFlagProduitTermine(flagTermine);
			p.setCommande(
				DbCommande.GetCommandeById(
					Integer.parseInt(
					cursor.getString(
					cursor.getColumnIndex(COL_ID_COMMANDE))), context));
		}else
		{
			p = null;
		}
		
		return p;
	}
	
	
	public static ArrayList<Produit> GetProduitsATravailler(Poste poste, Context context)
	{
		ArrayList<Produit> produits = new ArrayList<Produit>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = null;
		
		if(poste.getId() == DbPoste.GetIdPremierPoste(context))
		{//si on se trouve au premier poste
			cursor =db.query(TABLE_NAME,
					COLS, 
					COL_ID_POSTE + " IS NULL",
					null,
					null,
					null,
					COL_ID + " ASC");
			
		}else if(poste.getId() == DbPoste.GetIdDernierPoste(context))
		{//si on se trouve au dernier poste
			cursor =db.query(TABLE_NAME,
					COLS, 
					COL_ID_POSTE + " = ? AND " + COL_FLAG_ATTENTE + " = ? AND " 
					+ COL_FLAG_TERMINE + " = ?",
					new String[]{ String.valueOf(poste.getId()), "1", "0"},
					null,
					null,
					COL_ID);
		}
		else
		{//si on se trouve aux autres postes
			cursor =db.query(TABLE_NAME,
					COLS, 
					COL_ID_POSTE + " = ? AND " + COL_FLAG_ATTENTE + " = ?",
					new String[]{ String.valueOf(poste.getId()), "1"},
					null,
					null,
					COL_ID);
		}
		
		if(cursor.moveToFirst())
		{
			do{
				Produit p = new Produit();
				int idProduit = Integer.parseInt(
						cursor.getString(cursor.getColumnIndex(COL_ID)));
			 	p = DbProduit.GetProduitById(idProduit, context);
				produits.add(p);
			}while(cursor.moveToNext());
		}
		
		db.close();
		return produits;
	}
}
