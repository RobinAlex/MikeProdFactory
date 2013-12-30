package controles;

import java.util.ArrayList;

import android.content.Context;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;

import DB.DbPoste;
import DB.DbUtilisateur;

public class CtrlUtilisateur {

	/**
	 * Connecte un utilisateur à un poste
	 * 
	 * @param poste
	 * @return boolean qui indique si la connexion est OK ou non
	 */
	public Boolean ConnexionAuPoste(Utilisateur utilisateur, Poste poste,
			Context context) {

		Boolean status = false;

		if (DeconnexionDuPoste(utilisateur, context)) {
			if (DbUtilisateur.ConnexionAuPoste(utilisateur, poste, context)) {
				utilisateur.setPoste(poste);
				status = true;
			}
		}

		return status;

	}

	/**
	 * Deconnecte un utilisateur de son poste actuel
	 * 
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
	 * 
	 * @param context
	 * @return ArrayList de type Utilisateur
	 */
	public static ArrayList<Utilisateur> GetAll(Context context) {

		ArrayList<Utilisateur> ListeUtilisateur = new ArrayList<Utilisateur>();

		ListeUtilisateur = DbUtilisateur.GetAll(context);

		return ListeUtilisateur;

	}

	
	/**
	 * Regarde si un utilisateur peut se connecter sur un poste.
	 * @param utilisateur
	 * @param poste
	 * @param context
	 * @return True si personne sur le poste ou si l'user est deja connecté sur
	 * ce poste.
	 */
	public Boolean PeutSeConnecter(Utilisateur utilisateur, Poste poste, Context context)
	{
		if(utilisateur.getPoste() != null)
		{
			if(utilisateur.getPoste().getId() == poste.getId())
				return true;
		}
		
		
		CtrlPoste ctrlPoste = new CtrlPoste();
		if(ctrlPoste.LePosteEstLibre(poste, context)){
			return true;
		}
		
		return false;
	}
	
	
	
	

}
