package DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.classes.mikaprod.Commande;

public class DbCommande {

	public static final String TABLE_NAME = "Commande";
	public static final String COL_ID = "id_commande";
	public static final String COL_CLIENT = "client";
	public static final String COL_QTE = "quantite";
	public static final String COL_TYPE = "type";
	public static final String COL_MATIERE = "matiere";
	public static final String[] COLS = 
			new String[] {COL_ID, COL_CLIENT, COL_QTE, COL_TYPE, COL_MATIERE};
	
	
	/**
	 * Retourne une commande qui correspond à l'id passé en paramètre.
	 * @param idCommande
	 * @param context
	 * @return null si aucun object trouvé en base
	 */
	public static Commande GetCommandeById(int idCommande, Context context)
	{
		Commande commande = new Commande();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, 
							COLS, 
							COL_ID + " = ?", 
							new String[]{String.valueOf(idCommande)}, 
							null, 
							null, 
							null);
		
		if(cursor.moveToFirst())
		{
			commande.setId(
					Integer.parseInt(
							cursor.getString(cursor.getColumnIndex(COL_ID))));
			commande.setClient(
					cursor.getString(cursor.getColumnIndex(COL_CLIENT)));
			commande.setMatiere(
					cursor.getString(cursor.getColumnIndex(COL_MATIERE)));
			commande.setType(
					cursor.getString(cursor.getColumnIndex(COL_TYPE)));
			commande.setQuantite(
					Integer.parseInt(
							cursor.getString(cursor.getColumnIndex(COL_QTE))));
		}else
		{
			commande = null;
		}
		
		return commande;
	}
}
