package DB;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.classes.mikaprod.Poste;

public class DbPoste {

	public static final String TABLE_NAME = "Poste";
	public static final String COL_ID = "id_poste";
	public static final String COL_NOM = "nom";
	public static final String COL_ORDRE_FLUX = "ordre_flux";
	public static final String COL_POSTE_FINAL = "flag_final";

	
	/**
	 * Retourne le poste qui correspond à l'ID passé en paramètre.
	 * @param idPoste
	 * @param context
	 * @return
	 */
	public static Poste GetPosteById(int idPoste, Context context) 
	{
		Poste poste = new Poste();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, new String[] { COL_ID, COL_NOM,
				COL_ORDRE_FLUX, COL_POSTE_FINAL }, null, null, null, null,
				null, null);
		if(cursor.moveToFirst())
		{
			poste.setId(Integer.parseInt(cursor.getString(0)));
			poste.setNom(cursor.getString(1));
			poste.setOrdreFlux(Integer.parseInt(cursor.getString(2)));
			poste.setFlagFluxFinal(
					Integer.parseInt(cursor.getString(3)) == 1 ? true : false);
		}
		
		return poste;
	}
	
	/**
	 * Retourne tous les postes de la base triés par l'ordre des flux.
	 * @param context
	 * @return
	 */
	public static ArrayList<Poste> GetAll(Context context)
	{
		ArrayList<Poste> postes = new ArrayList<Poste>();
		SQLiteDatabase db = new DatabaseSQLite(context).getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, 
				new String[] { COL_ID, COL_NOM, COL_ORDRE_FLUX, COL_POSTE_FINAL}, 
				null, null, null, null, COL_ORDRE_FLUX);
		if(cursor.moveToFirst())
		{
			do{
				Poste p = new Poste();
				p.setId(Integer.parseInt(cursor.getString(0)));
				p.setNom(cursor.getString(1));
				p.setOrdreFlux(Integer.parseInt(cursor.getString(2)));
				p.setFlagFluxFinal(
					Integer.parseInt(cursor.getString(3)) == 1 ? true : false);
				
				postes.add(p);
			}while(cursor.moveToNext());
		}
		
	return postes;
	}
}
