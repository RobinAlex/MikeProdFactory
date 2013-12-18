package controles;

import java.util.ArrayList;

import android.content.Context;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;

import DB.DbUtilisateur;


public class CtrlUtilisateur {
	
	/**
	 * Connecte un utilisateur à un poste
	 * @param poste
	 * @return boolean qui indique si OK ou non
	 */
	public Boolean ConnexionAuPoste(Poste poste) {
		
		Boolean status = false;
		
		// TODO : changer Poste
				
		return status;
		
		
	}
	
	/**
	 * Deconnecte un utilisateur de son poste actuel
	 * @return boolean qui indique si OK ou non
	 */
	public Boolean DeconnexionDuPoste() {
		
		Boolean status = false;
		
		// TODO : Enlever Poste
				
		return status;
		
		
	}
	
	/**
	 * Recupere tout les utilisateurs
	 * @param context
	 * @return ArrayList de type Utilisateur
	 */
	public static ArrayList<Utilisateur> GetAll(Context context) {
		
		ArrayList<Utilisateur> ListeUtilisateur = new ArrayList<Utilisateur>();
		
		ListeUtilisateur = DbUtilisateur.GetAll(context);
		
		return ListeUtilisateur;
		
	}
	
}
