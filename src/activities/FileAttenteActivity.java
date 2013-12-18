package activities;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;
import com.example.mikaprod.R;

import controles.CtrlProduit;
import controles.CtrlUtilisateur;

import DB.DbProduit;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FileAttenteActivity extends SherlockActivity {
	
	private Poste poste;
	private Utilisateur utilisateur;
	private Spinner spinnerProduit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_attente);
		
		poste = (Poste) this.getIntent().getSerializableExtra("poste");
		utilisateur = (Utilisateur) this.getIntent().getSerializableExtra(
				"utilisateur");
		
		this.setTitle(poste.getNom());
		
		PopulationSpinnerProduit();
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.deco_id:

			CtrlUtilisateur ctrlUtilisateur = new CtrlUtilisateur();

			if (ctrlUtilisateur.DeconnexionDuPoste(utilisateur,
					FileAttenteActivity.this)) {

				Intent intentLogin = new Intent(FileAttenteActivity.this,
						LoginActivity.class);

				Toast.makeText(FileAttenteActivity.this,
						"Déconnexion du poste actuel", Toast.LENGTH_SHORT)
						.show();

				startActivity(intentLogin);
			} else {
				Toast.makeText(FileAttenteActivity.this,
						"Erreur pendant la déconnexion", Toast.LENGTH_SHORT)
						.show();
			}
			return true;
		}
		return false;
	}
	
	// Ajout des items dans le spinner produit
	public void PopulationSpinnerProduit() {

		// Recup element dans le layout
		spinnerProduit = (Spinner) findViewById(R.id.spinnerChoixProduit);

		// Recup de tous les produits
		ArrayList<Produit> ListeProduit = DbProduit.GetProduitsATravailler(poste, FileAttenteActivity.this);

		// Population du spinner
		ArrayAdapter<Produit> dataAdapter = new ArrayAdapter<Produit>(
				this, android.R.layout.simple_spinner_item, ListeProduit);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerProduit.setAdapter(dataAdapter);
	}
	
	

}
