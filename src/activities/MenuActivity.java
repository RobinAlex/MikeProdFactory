package activities;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.classes.mikaprod.Poste;
import com.classes.mikaprod.Utilisateur;
import com.example.mikaprod.R;

import controles.CtrlUtilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends SherlockActivity {

	private Utilisateur utilisateur;
	private Poste poste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		utilisateur = (Utilisateur) this.getIntent().getSerializableExtra(
				"utilisateur");
		
		poste = (Poste) this.getIntent().getSerializableExtra("poste");
		
		this.setTitle(utilisateur.getNom() +"@"+ poste.getNom());
		
		Button fileBtn = (Button) findViewById(R.id.fileBtn);
		
		fileBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				Intent intentFile = new Intent(MenuActivity.this, FileAttenteActivity.class);
				intentFile.putExtra("poste", poste);
				intentFile.putExtra("utilisateur", utilisateur);
				startActivity(intentFile);
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.menu, menu);
		return true;
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
		}
		return false;
	}

}
