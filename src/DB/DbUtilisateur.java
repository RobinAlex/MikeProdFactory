package DB;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;

public class DbUtilisateur {

	public static final String TABLE_NAME = "Utilisateur";
	public static final String COL_ID = "id_utilisateur";
	public static final String COL_NOM = "nom";
	public static final String COL_POSTE = "id_poste";

	
	/**
	 * Retourne tous les utilisateurs de la base triés par le nom 
	 * de l'utilisateur.
	 * @param context
	 * @return
	 */
	public static ArrayList<Utilisateur> GetAll(Context context) 
	{
		ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_POSTE,
				COL_NOM }, null, null, null, null, COL_NOM);
		
		if (cursor.moveToFirst()) 
		{
            do {
                Utilisateur u = new Utilisateur();
                u.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_ID))));
                String tempIdPoste = cursor.getString(cursor.getColumnIndex(COL_POSTE));
                Poste p = null;
                if(tempIdPoste != null && !tempIdPoste.equals("NULL"))
                {
                	p = DbPoste.GetPosteById(Integer.parseInt(tempIdPoste), context);
                }
                
                u.setPoste(p);
                u.setNom(cursor.getString(cursor.getColumnIndex(COL_NOM)));

                utilisateurs.add(u);
            } while (cursor.moveToNext());
        }
		
		db.close();
		return utilisateurs;
	}
	
	/**
	 * Rend l'assignation d'un poste à l'utilisateur persistant en base.
	 * @param utilisateur
	 * @param poste
	 * @param context
	 * @return
	 */
	public static Boolean ConnexionAuPoste(Utilisateur utilisateur, Poste poste, Context context)
	{
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(COL_POSTE, poste.getId());
 
        int retour =  db.update(TABLE_NAME, values, COL_ID + " = ?",
                new String[] { String.valueOf(utilisateur.getId()) });
        
        db.close();
		return retour == 1 ? true : false;
	}
	
	/**
	 * Rend la suppression d'un poste de l'utilisateur persistante en base.
	 * @param utilisateur
	 * @param context
	 * @return
	 */
	public static Boolean DeconnexionDuPoste(Utilisateur utilisateur, Context context)
	{
		SQLiteDatabase db = new DatabaseSQLite(context).getWritableDatabase();
		
		ContentValues values = new ContentValues();
        values.put(COL_POSTE, "NULL");
 
        int retour =  db.update(TABLE_NAME, values, COL_ID + " = ?",
                new String[] { String.valueOf(utilisateur.getId()) });
        
        db.close();
		return retour == 1 ? true : false;
	}

}
