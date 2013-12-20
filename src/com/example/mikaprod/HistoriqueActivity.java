package com.example.mikaprod;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.classes.mikaprod.Historique;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;

import controles.CtrlUtilisateur;

import DB.DbHistorique;
import activities.LoginActivity;
import activities.MenuActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class HistoriqueActivity extends SherlockActivity {
	
	private Utilisateur utilisateur;
	private Poste poste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historique);
		
		utilisateur = (Utilisateur) this.getIntent().getSerializableExtra(
				"utilisateur");

		poste = (Poste) this.getIntent().getSerializableExtra("poste");

		this.setTitle("Traçabilité");
		
		PopulationListeHisto();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.historique, menu);
		return true;
	}

	// Ajout des items dans le spinner produit
	public void PopulationListeHisto() {

		// Recup element dans le layout
		ListView histoListe = (ListView) findViewById(R.id.HistoListe);

		// Recup de tous les produits
		ArrayList<Historique> listeHisto = DbHistorique
				.GetAll(HistoriqueActivity.this);

		// Population du Listview
		ArrayAdapter<Historique> dataAdapter = new ArrayAdapter<Historique>(this,
				android.R.layout.simple_list_item_1, listeHisto);

		histoListe.setAdapter(dataAdapter);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.deco_id:

			CtrlUtilisateur ctrlUtilisateur = new CtrlUtilisateur();

			if (ctrlUtilisateur.DeconnexionDuPoste(utilisateur,
					HistoriqueActivity.this)) {

				Intent intentLogin = new Intent(HistoriqueActivity.this,
						LoginActivity.class);

				Toast.makeText(HistoriqueActivity.this,
						"Déconnexion du poste actuel", Toast.LENGTH_SHORT)
						.show();

				startActivity(intentLogin);
				HistoriqueActivity.this.finish();
			} else {
				Toast.makeText(HistoriqueActivity.this,
						"Erreur pendant la déconnexion", Toast.LENGTH_SHORT)
						.show();
			}
			return true;

		case R.id.poste_id:
			Intent posteIntent = new Intent(HistoriqueActivity.this,
					MenuActivity.class);
			
			posteIntent.putExtra("poste", poste);
			posteIntent.putExtra("utilisateur", utilisateur);
			
			startActivity(posteIntent);
			HistoriqueActivity.this.finish();
			
			return true;
			
		}
		return false;
	}

}
