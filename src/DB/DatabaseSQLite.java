package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLite extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "MikaProd";
	private static final CursorFactory DB_CURSOR_FACTORY = null; // <-??? Dafuq?
	// SCRIPTS DE CREATION DES TABLES
	private static final String TABLE_UTILISATEUR = "CREATE  TABLE "+DbUtilisateur.TABLE_NAME+" ("
			+ DbUtilisateur.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ DbUtilisateur.COL_POSTE +" INTEGER NULL ,"
			+ DbUtilisateur.COL_NOM + " VARCHAR(255) NULL ,"
			+ "CONSTRAINT `fk_Utilisateur_Poste`"
			+ "  FOREIGN KEY ("+DbUtilisateur.COL_ID+")"
			+ "  REFERENCES "+DbPoste.TABLE_NAME+" ("+DbUtilisateur.COL_POSTE+" )"
			+ "  ON DELETE NO ACTION"
			+ "  ON UPDATE NO ACTION);";

	private static final String TABLE_POSTE = "CREATE  TABLE "+DbPoste.TABLE_NAME+" ("
			+DbPoste.COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+DbPoste.COL_NOM+" VARCHAR(255) NULL ," 
			+DbPoste.COL_ORDRE_FLUX + " INTEGER NULL ,"
			+DbPoste.COL_POSTE_FINAL+ " INTEGER NULL );";

	private static final String TABLE_HISTORIQUE = "CREATE  TABLE "+DbHistorique.TABLE_NAME+" ("
			+ DbHistorique.COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ DbHistorique.COL_ID_POSTE + " INTEGER NULL ,"
			+ DbHistorique.COL_ID_UTILISATEUR+ " INTEGER NULL ,"
			+ DbHistorique.COL_ID_PRODUIT+" INTEGER NULL ,"
			+ DbHistorique.COL_DATE_DEBUT+" VARCHAR(255) NULL ,"
			+ DbHistorique.COL_DATE_FIN+" VARCHAR(255) NULL ,"
			+ "CONSTRAINT `fk_Historique_Poste`"
			+ "  FOREIGN KEY (`id_poste` )"
			+ "  REFERENCES `Poste` (`id_poste` )"
			+ "  ON DELETE NO ACTION"
			+ "  ON UPDATE NO ACTION,"
			+ "CONSTRAINT `fk_Historique_Utilisateur`"
			+ "  FOREIGN KEY (`id_utilisateur` )"
			+ "  REFERENCES `Utilisateur` (`id_utilisateur` )"
			+ "  ON DELETE NO ACTION"
			+ "  ON UPDATE NO ACTION,"
			+ "CONSTRAINT `fk_Historique_Produit`"
			+ "  FOREIGN KEY (`id_produit` )"
			+ "  REFERENCES `Produit` (`id_produit` )"
			+ "  ON DELETE NO ACTION" + "  ON UPDATE NO ACTION);";

	private static final String TABLE_COMMANDE = "CREATE  TABLE `Commande` ("
			+ "`id_commande` INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ "`client` VARCHAR(255) NULL ," 
			+ "`quantite` INTEGER NULL ,"
			+ "`type` VARCHAR(255) NULL ," 
			+ "`matiere` VARCHAR(255) NULL);";

	private static final String TABLE_PRODUIT = "CREATE  TABLE `Produit` ("
			+ "`id_produit` INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ "`id_commande` INTEGER NULL ," 
			+ "`id_poste` INTEGER NULL ,"
			+ "`flag_attente` INTEGER NULL ," 
			+ "`flag_termine` INTEGER NULL ,"
			+ "CONSTRAINT `fk_Produit_Commande`"
			+ "FOREIGN KEY (`id_commande` )"
			+ "REFERENCES `Commande` (`id_commande` )" 
			+ "ON DELETE NO ACTION "
			+ "ON UPDATE NO ACTION," 
			+ "CONSTRAINT `fk_Produit_Poste`"
			+ " FOREIGN KEY (`id_poste` )"
			+ " REFERENCES `Poste` (`id_poste` )" 
			+ " ON DELETE NO ACTION"
			+ " ON UPDATE NO ACTION);";

	// ====================================

	public DatabaseSQLite(Context context) {
		//super(context, DB_NAME, factory, DB_VERSION);
		super(context, DB_NAME, DB_CURSOR_FACTORY, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_COMMANDE);
		db.execSQL(TABLE_POSTE);
		db.execSQL(TABLE_UTILISATEUR);
		db.execSQL(TABLE_PRODUIT);
		db.execSQL(TABLE_HISTORIQUE);
		
		
		//Mock data
		//Commandes
		db.execSQL("INSERT INTO Commande (id_commande, client, quantite, type, matiere)"
				+"VALUES (1, 'QualiPortes', 3, 'Porte', 'Alu');");
		db.execSQL("INSERT INTO Commande (id_commande, client, quantite, type, matiere)"
				+"VALUES (2, 'QualiFenetres', 2, 'Fenetre', 'Alu');");
		db.execSQL("INSERT INTO Commande (id_commande, client, quantite, type, matiere)"
				+"VALUES (3, 'OuestFenetres', 2, 'Fenetre', 'Acier');");
		db.execSQL("INSERT INTO Commande (id_commande, client, quantite, type, matiere)"
				+"VALUES (4, 'Portes-Acier', 1, 'Porte', 'Acier');");
		
		//Produits
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (1, 1, NULL, 0, 0)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (2, 1, 2, 1, 0)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (3, 1, 2, 1, 0)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (4, 2, NULL, 0, 0)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (5, 2, 4, 1, 0)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (6, 3, 4, 1, 1)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (7, 3, 4, 0, 1)");
		db.execSQL("INSERT INTO Produit (id_produit, id_commande, id_poste, flag_attente, flag_termine) "
				+"VALUES (8, 4, 4, 0, 1)");
		
		//Postes
		db.execSQL("INSERT INTO Poste (id_poste, nom, ordre_flux, flag_final) "
						+"VALUES (1, 'Découpage', 1, 0);");
		db.execSQL("INSERT INTO Poste (id_poste,nom, ordre_flux, flag_final) "
						+"VALUES (2, 'Fabrication', 2, 0);");
		db.execSQL("INSERT INTO Poste (id_poste,nom, ordre_flux, flag_final) "
						+"VALUES (3, 'Peinture', 3, 0);");
		db.execSQL("INSERT INTO Poste (id_poste,nom, ordre_flux, flag_final) "
						+"VALUES (4, 'Assemblage', 4, 1);");
		
		//Utilisateurs
		db.execSQL("INSERT INTO Utilisateur (id_utilisateur, nom) VALUES (1, 'Didier');");
		db.execSQL("INSERT INTO Utilisateur (id_utilisateur, nom) VALUES (2, 'Michel');");
		db.execSQL("INSERT INTO Utilisateur (id_utilisateur, nom) VALUES (3, 'Robert');");
		
		//Historique
		db.execSQL("INSERT INTO Historique (id_poste, id_produit, id_utilisateur, date_Debut, date_Fin) "
				+"VALUES(1, 8, 2, '2010-07-10Z13:37:02', '2010-07-10Z14:22:09');");
		db.execSQL("INSERT INTO Historique (id_poste, id_produit, id_utilisateur, date_Debut, date_Fin) "
				+"VALUES(2, 8, 2, '2010-07-10Z14:01:22', '2010-07-10Z14:55:11');");
		db.execSQL("INSERT INTO Historique (id_poste, id_produit, id_utilisateur, date_Debut, date_Fin) "
				+"VALUES(3, 8, 2, '2010-07-10Z14:57:07', '2010-07-10Z15:32:03');");
		db.execSQL("INSERT INTO Historique (id_poste, id_produit, id_utilisateur, date_Debut, date_Fin) "
				+"VALUES(4, 8, 2, '2010-07-10Z15:44:45', '2010-07-10Z16:58:42');");
		
		/*
		db.execSQL("INSERT INTO Historique (id_poste, id_produit, id_utilisateur, date_Debut, date_Fin) "
				+"VALUES(1, 1, 1, '2013-12-20Z11:01:40', 'NULL');");
				*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
