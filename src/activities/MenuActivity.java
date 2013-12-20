package activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Produit;
import com.classes.mikaprod.Utilisateur;
import com.example.mikaprod.HistoriqueActivity;
import com.example.mikaprod.R;

import controles.CtrlUtilisateur;

import DB.DbPoste;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends SherlockActivity {

	private Utilisateur utilisateur;
	private Poste poste;
	private Produit produitEnCours;
	private Button fileBtn;
	private Produit produitSurPoste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		utilisateur = (Utilisateur) this.getIntent().getSerializableExtra(
				"utilisateur");

		poste = (Poste) this.getIntent().getSerializableExtra("poste");

		produitEnCours = (Produit) this.getIntent().getSerializableExtra(
				"produitEnCour");

		fileBtn = (Button) findViewById(R.id.fileBtn);

		Button finirBtn = (Button) findViewById(R.id.finirProduitBtn);
		finirBtn.setVisibility(View.GONE);

		TextView titre = (TextView) findViewById(R.id.ProduitEnCour);
		titre.setVisibility(View.GONE);

		TextView produitDesc1 = (TextView) findViewById(R.id.ProduitDesc1);
		produitDesc1.setVisibility(View.GONE);

		TextView produitDesc2 = (TextView) findViewById(R.id.ProductDesc2);
		produitDesc2.setVisibility(View.GONE);

		TextView produitDesc3 = (TextView) findViewById(R.id.ProduitDesc3);
		produitDesc3.setVisibility(View.GONE);

		this.setTitle(utilisateur.getNom() + "@" + poste.getNom());

		fileBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intentFile = new Intent(MenuActivity.this,
						FileAttenteActivity.class);
				intentFile.putExtra("poste", poste);
				intentFile.putExtra("utilisateur", utilisateur);
				startActivity(intentFile);

			}

		});

		if (produitEnCours != null
				|| DbPoste.ProduitDejaEngageAuPoste(poste, MenuActivity.this)) {

			fileBtn.setVisibility(View.GONE);
			finirBtn.setVisibility(View.VISIBLE);
			titre.setVisibility(View.VISIBLE);

			produitDesc1.setVisibility(View.VISIBLE);
			produitDesc2.setVisibility(View.VISIBLE);
			produitDesc3.setVisibility(View.VISIBLE);

			if (produitEnCours != null) {
				produitSurPoste = produitEnCours;
			} else {
				produitSurPoste = DbPoste.GetProduitEngage(poste,
						MenuActivity.this);
			}

			if (produitSurPoste != null) {
				produitDesc1.setText(produitSurPoste.getCommande().getType());
				produitDesc2
						.setText(produitSurPoste.getCommande().getMatiere());
				produitDesc3.setText(produitSurPoste.getCommande().getClient());

				
			}
			
			finirBtn.setOnClickListener(new View.OnClickListener() {
				@SuppressLint("NewApi")
				public void onClick(View v) {

					/*
					 * if
					 * (DbPoste.TerminerTraitementProduit(produitSurPoste,
					 * poste, utilisateur, MenuActivity.this)) {
					 * 
					 * finish(); startActivity(getIntent()); }
					 */

					Intent refreshIntent = MenuActivity.this.getIntent();
					
					refreshIntent.putExtra("poste", poste);
					refreshIntent.putExtra("utilisateur", utilisateur);
					
					MenuActivity.this.recreate();

				}

			});

		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.deco_id:

			CtrlUtilisateur ctrlUtilisateur = new CtrlUtilisateur();

			if (ctrlUtilisateur.DeconnexionDuPoste(utilisateur,
					MenuActivity.this)) {

				Intent intentLogin = new Intent(MenuActivity.this,
						LoginActivity.class);

				Toast.makeText(MenuActivity.this,
						"Déconnexion du poste actuel", Toast.LENGTH_SHORT)
						.show();

				startActivity(intentLogin);
			} else {
				Toast.makeText(MenuActivity.this,
						"Erreur pendant la déconnexion", Toast.LENGTH_SHORT)
						.show();
			}
			return true;

		case R.id.trace_id:
			Intent histoIntent = new Intent(MenuActivity.this,
					HistoriqueActivity.class);

			histoIntent.putExtra("poste", poste);
			histoIntent.putExtra("utilisateur", utilisateur);

			startActivity(histoIntent);

			return true;
		}
		return false;
	}

}
