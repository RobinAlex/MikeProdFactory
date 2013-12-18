package controles;

import java.util.ArrayList;

import com.classes.mikaprod.Commande;
import com.classes.mikaprod.Produit;

public class CtrlCommande {
	
	/**
	 * Creer les produit d'une commande
	 * @return ArrayList de type Produit
	 */
	public ArrayList<Produit> CreerProduits() {
		
		ArrayList<Produit> listeProduit = new ArrayList<Produit>(); 
		
		Commande commande = new Commande();
		
		for (int i = 0; i < commande.getQuantite(); i++) {
			listeProduit.add(new Produit(-1, commande, null, true, false));
			
			/*
			 INSERT INTO Produit (idCommande, enAttente, termine) VALUES (this.getId, true, false);
			 */
		}
		
		return listeProduit;
	}
	
}
