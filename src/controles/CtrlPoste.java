package controles;

import java.util.ArrayList;

import DB.DbPoste;
import DB.DbUtilisateur;
import android.content.Context;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;

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
	
	public ArrayList<Produit> GetProduitATravailler(Poste poste) {
		
		ArrayList<Produit> listeProduit = new ArrayList<Produit>();
		
		// TODO : get ListeProduitaTravailler
		
		return null;
		
	}
	

	/**
	 * Check si le poste est libre (aucun utilisateur connecté dessus)
	 * 
	 * @param poste
	 * @param context
	 * @return Boolean
	 */
	public Boolean LePosteEstLibre(Poste poste,	Context context) {
		ArrayList<Utilisateur> listeUtilisateur = DbUtilisateur.GetAll(context);

		for (Utilisateur utilisateur : listeUtilisateur) {

			if (utilisateur.getPoste() != null 
					&& utilisateur.getPoste().getId() == poste.getId()) {
				return false;
			} 		
		}

		return true;
	}
	
}
