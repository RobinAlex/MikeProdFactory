package controles;

import java.util.ArrayList;

import android.content.Context;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;

import DB.DbUtilisateur;


public class CtrlUtilisateur {
	
	public Boolean ConnexionAuPoste(Poste poste) {
		
		Boolean status = false;
		
		// TODO : changer Poste
				
		return status;
		
		
	}
	
	public Boolean DeconnexionDuPoste() {
		
		Boolean status = false;
		
		// TODO : Enlever Poste
				
		return status;
		
		
	}
	
	public static ArrayList<Utilisateur> GetAll(Context context) {
		
		ArrayList<Utilisateur> ListeUtilisateur = new ArrayList<Utilisateur>();
		
		ListeUtilisateur = DbUtilisateur.GetAll(context);
		
		return ListeUtilisateur;
		
	}
	
}
