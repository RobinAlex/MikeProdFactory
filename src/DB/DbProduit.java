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

	
	public static ArrayList<Produit> GetProduitsATravailler(Poste poste, Context context)
	{
		ArrayList<Produit> produits = new ArrayList<Produit>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = null;
		
		if(poste.getId() == DbPoste.GetIdPremierPoste(context))
		{//si on se trouve au premier poste
			cursor =db.query(TABLE_NAME,
					new String[] {COL_ID, COL_ID_COMMANDE, COL_ID_POSTE, 
					COL_FLAG_ATTENTE, COL_FLAG_TERMINE}, 
					COL_ID_POSTE + " = NULL AND " + COL_FLAG_ATTENTE + " = 0",
					null,
					null,null,
					COL_ID);
		}else
		{//si on se trouve aux autres postes
			cursor =db.query(TABLE_NAME,
					new String[] {COL_ID, COL_ID_COMMANDE, COL_ID_POSTE, 
					COL_FLAG_ATTENTE, COL_FLAG_TERMINE}, 
					COL_ID_POSTE + " = ? AND " + COL_FLAG_ATTENTE + " = 1",
					new String[]{ String.valueOf(poste.getId())},
					null,null,
					COL_ID);
		}
		
		if(cursor.moveToFirst())
		{
			do{
				Produit p = new Produit();
				p.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
				String tempIdPoste = cursor.getString(cursor.getColumnIndex(COL_ID_POSTE));
				Poste posteProduit = null;
				if(tempIdPoste != "NULL")
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
				
				produits.add(p);
			}while(cursor.moveToNext());
		}
		
		db.close();
		return produits;
	}
}
