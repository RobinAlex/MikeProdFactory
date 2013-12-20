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
	 * @return boolean qui indique si la connexion est OK ou non
	 */
	public Boolean ConnexionAuPoste(Utilisateur utilisateur, Poste poste, Context context) {
		
		Boolean status = false;
		
		if (DbUtilisateur.ConnexionAuPoste(utilisateur, poste, context)) {
			utilisateur.setPoste(poste);
			status = true;
		}
				
		return status;		
		
	}
	
	/**
	 * Deconnecte un utilisateur de son poste actuel
	 * @return boolean qui indique si la deconnexion est OK ou non
	 */
	public Boolean DeconnexionDuPoste(Utilisateur utilisateur, Context context) {
		
		Boolean status = false;	
		
		if (DbUtilisateur.DeconnexionDuPoste(utilisateur, context)) {
			utilisateur.setPoste(null);
			status = true;
		}
				
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
	
	public Boolean CheckPosteLibre(Poste poste) {
		
		Boolean status = false;
		
		int posteId = poste.getId();
		
		//DbUtilisateur.GetAll(CtrlUtilisateur.this);
		
		return status;
		
	}
	
}
