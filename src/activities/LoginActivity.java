package activities;

import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;
import com.example.mikaprod.R;

import controles.CtrlPoste;
import controles.CtrlUtilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends FragmentActivity {

	private Spinner spinnerUtilisateur, spinnerPoste;
	private Poste posteSelect;
	private Utilisateur utilisateurSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		PopulationSpinnerUtilisateur();
		PopulationSpinnerPoste();

		final Button ConnexionBtn = (Button) findViewById(R.id.ConnexionBtn);

		ConnexionBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Bouton clické

				utilisateurSelect = (Utilisateur) spinnerUtilisateur
						.getSelectedItem();
				posteSelect = (Poste) spinnerPoste.getSelectedItem();

				CtrlUtilisateur ctrlUtilisateur = new CtrlUtilisateur();

				if (ctrlUtilisateur.PeutSeConnecter(
						utilisateurSelect, posteSelect, LoginActivity.this)) {

					if (ctrlUtilisateur.ConnexionAuPoste(utilisateurSelect,
							posteSelect, LoginActivity.this)) {

						Intent intentMenu = new Intent(LoginActivity.this,
								MenuActivity.class);

						intentMenu.putExtra("utilisateur", utilisateurSelect);
						intentMenu.putExtra("poste", posteSelect);

						Toast.makeText(
								LoginActivity.this,
								"Connexion de " + utilisateurSelect + " sur "
										+ posteSelect, Toast.LENGTH_SHORT)
								.show();

						startActivity(intentMenu);
					}
				} else {
					Toast.makeText(LoginActivity.this,
							"Un utilisateur est déja connecté sur ce poste",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// Ajout des items dans le spinner utilisateur
	public void PopulationSpinnerUtilisateur() {

		// Recup element dans le layout
		spinnerUtilisateur = (Spinner) findViewById(R.id.selectUtilisateur);

		// Recup de tous les utilisateurs
		ArrayList<Utilisateur> ListeUtilisateur = CtrlUtilisateur
				.GetAll(LoginActivity.this);

		// Population du spinner
		ArrayAdapter<Utilisateur> dataAdapter = new ArrayAdapter<Utilisateur>(
				this, android.R.layout.simple_spinner_item, ListeUtilisateur);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerUtilisateur.setAdapter(dataAdapter);
	}

	// Ajout des items dans le spinner postes
	public void PopulationSpinnerPoste() {

		// Recup element dans le layout
		spinnerPoste = (Spinner) findViewById(R.id.selectPoste);

		// Recup de tout les postes
		ArrayList<Poste> ListePoste = CtrlPoste.GetAll(LoginActivity.this);

		// Population du spinner
		ArrayAdapter<Poste> dataAdapter = new ArrayAdapter<Poste>(this,
				android.R.layout.simple_spinner_item, ListePoste);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerPoste.setAdapter(dataAdapter);
	}

}
