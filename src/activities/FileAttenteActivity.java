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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FileAttenteActivity extends SherlockActivity implements
		OnItemClickListener {

	private Poste poste;
	private Utilisateur utilisateur;
	private Produit produit;
	private ListView listeFile;
	private ArrayAdapter<Produit> dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_attente);

		poste = (Poste) this.getIntent().getSerializableExtra("poste");
		utilisateur = (Utilisateur) this.getIntent().getSerializableExtra(
				"utilisateur");

		this.setTitle(poste.getNom());

		PopulationListeProduit();

		this.listeFile.setOnItemClickListener(this);
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
	public void PopulationListeProduit() {

		// Recup element dans le layout
		listeFile = (ListView) findViewById(R.id.listeFile);

		// Recup de tous les produits
		ArrayList<Produit> ListeProduit = DbProduit.GetProduitsATravailler(
				poste, FileAttenteActivity.this);

		// Population du Listview
		dataAdapter = new ArrayAdapter<Produit>(this,
				android.R.layout.simple_list_item_1, ListeProduit);

		listeFile.setAdapter(dataAdapter);
	}

	@Override
	public void onItemClick(final AdapterView<?> adapter, View v, int position,
			long id) {
		
		//produit = dataAdapter.getItem(position);
		produit = (Produit) this.listeFile.getItemAtPosition(position);
		
		AlertDialog.Builder dialogue = new AlertDialog.Builder(
				FileAttenteActivity.this);

		dialogue.setTitle("Produit");

		dialogue.setMessage("Engager le produit ?").setCancelable(false)

		.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}

		})

		.setPositiveButton("Engager", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				

				CtrlProduit ctrlProduit = new CtrlProduit();

				if (ctrlProduit.EngagerProduit(produit, poste, utilisateur)) {
					Intent posteIntent = new Intent(FileAttenteActivity.this,
							MenuActivity.class);					
					
					posteIntent.putExtra("produitEnCour", produit);

					posteIntent.putExtra("poste", poste);
					posteIntent.putExtra("utilisateur", utilisateur);
					
					startActivity(posteIntent);
					FileAttenteActivity.this.finish();
				} else {
					Toast.makeText(
							FileAttenteActivity.this,
							"Impossible, un produit est déja engagé sur ce poste",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		AlertDialog produitDialog = dialogue.create();
		produitDialog.show();

	}

}
