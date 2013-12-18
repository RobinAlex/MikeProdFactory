package controles;

import DB.DbPoste;
import android.content.Context;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;

public class CtrlProduit {

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
}
