package activities;

import java.util.ArrayList;
import java.util.zip.Inflater;

import com.classes.mikaprod.Historique;
import com.example.mikaprod.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HistoriqueAdapter extends BaseAdapter {
	
	protected ArrayList<Historique> listData;
	protected Context ctx;
	protected LayoutInflater inflater;

	public HistoriqueAdapter(HistoriqueActivity historiqueActivity,
			int simpleListItem1, ArrayList<Historique> listeHisto) {
		this.listData = listeHisto;
		this.ctx = historiqueActivity;
		
		inflater = (LayoutInflater)
				this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return this.listData.size();
	}

	@Override
	public Object getItem(int position) {
		return this.listData.get(position);
	}

	@Override
	public long getItemId(int position) {		 
		 return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null)
			convertView = inflater.inflate(R.layout.row_historique, null);
		
		Historique item = (Historique) this.getItem(position);
		
		((TextView)convertView.findViewById(R.id.histo_client)).
			setText(item.getProduit().getCommande().getClient());
		((TextView)convertView.findViewById(R.id.histo_date_start)).
			setText(item.getDateDebut());
		((TextView)convertView.findViewById(R.id.histo_date_end)).
			setText(item.getDateFin());
		((TextView)convertView.findViewById(R.id.histo_matiere)).
			setText(item.getProduit().getCommande().getMatiere());
		((TextView)convertView.findViewById(R.id.histo_poste)).
			setText(item.getPoste().getNom());
		((TextView)convertView.findViewById(R.id.histo_type)).
			setText(item.getProduit().getCommande().getType());
		((TextView)convertView.findViewById(R.id.histo_utilisateur)).
			setText(item.getUtilisateur().getNom());
		
		
		return convertView;
	}

}
