package controles;

import DB.DbPoste;
import DB.DbProduit;
import android.content.Context;

import com.classes.mikaprod.Commande;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;

public class CtrlProduit {

	
	/**
	 * Passe le produit en cours au poste suivant (en file d'attente)
	 * @param produit
	 * @param context
	 * @return Boolean status
	 */
	public boolean PasserAuPosteSuivant(Produit produit, Context context) { 
		
		boolean status = false;	
		
		Poste dernierPoste = DbPoste.GetPosteById(DbPoste.GetIdDernierPoste(context), context);
		Poste premierPoste = DbPoste.GetPosteById(DbPoste.GetIdPremierPoste(context), context);
		
		if (produit.getPoste() == null) {
			// Au stock
			produit.setPoste(premierPoste);
			status = true;
			
		} else if (produit.getPoste().getId() == dernierPoste.getId()) {
			// Au dernier poste
			produit.setFlagProduitTermine(true);
			status = true;
		
		} else  {
			// Si en prod, mais pas au dernier poste ni au premier
			produit.setPoste(DbPoste.GetPosteSuivant(produit.getPoste(), context));
			status = true;
		}
		
		return status;
	}
	
	/**
	 * Engage un produit sur un poste
	 * @param produit
	 * @param poste
	 * @param utilisateur
	 * @return Boolean status
	 */
	public Boolean EngagerProduit(Produit produit, Poste poste, Utilisateur utilisateur, Context context) {
		
		Boolean status = false;
		
		if (DbPoste.CommencerTraitementProduit(produit, poste, utilisateur, context)) {
			status = true;
		}
		
		return status;
		
	}
}
