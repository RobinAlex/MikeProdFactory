package controles;

import java.util.ArrayList;

import DB.DbPoste;
import android.content.Context;

import com.classes.mikaprod.Poste;

public class CtrlPoste {
	
	
	/**
	 * Recupere tout les postes
	 * @return ArrayList de type Poste
	 * @param Context
	 */
	public static ArrayList<Poste> GetAll(Context context) {
		
		ArrayList<Poste> ListePoste = new ArrayList<Poste>() ;
		
		// SELECT * FROM Poste ;
		
		// TODO : ListePoste = SQLPoste.selectAll()
		
		ListePoste = DbPoste.GetAll(context);
		
		return ListePoste;
		
	}
	
}
