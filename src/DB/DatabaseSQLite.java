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
	private static final String TABLE_UTILISATEUR = "CREATE  TABLE `Utilisateur` ("
			+ "`id_utilisateur` INT NOT NULL AUTO_INCREMENT ,"
			+ "`id_poste` INT NULL COMMENT '		' ,"
			+ "`nom` VARCHAR(255) NULL ,"
			+ "PRIMARY KEY (`id_utilisateur`) ,"
			+ "INDEX `fk_Utilisateur_Poste` (`id_poste` ASC) ,"
			+ "CONSTRAINT `fk_Utilisateur_Poste`"
			+ "  FOREIGN KEY (`id_poste` )"
			+ "  REFERENCES `Poste` (`id_poste` )"
			+ "  ON DELETE NO ACTION"
			+ "  ON UPDATE NO ACTION);";

	private static final String TABLE_POSTE = "CREATE  TABLE `Poste` ("
			+ " `id_poste` INT NOT NULL AUTO_INCREMENT ,"
			+ " `nom` VARCHAR(255) NULL ," + " `ordre_flux` INT NULL ,"
			+ " `flag_final` INT NULL ," + " PRIMARY KEY (`id_poste`) );";

	private static final String TABLE_HISTORIQUE = "CREATE  TABLE `Historique` ("
			+ "`id_historique` INT NOT NULL AUTO_INCREMENT ,"
			+ "`id_poste` INT NULL ,"
			+ "`id_utilisateur` INT NULL ,"
			+ "`id_produit` INT NULL ,"
			+ "`date_Debut` VARCHAR(255) NULL ,"
			+ "`date_Fin` VARCHAR(255) NULL ,"
			+ "PRIMARY KEY (`id_historique`) ,"
			+ "INDEX `fk_Historique_Poste` (`id_poste` ASC) ,"
			+ "INDEX `fk_Historique_Utilisateur` (`id_utilisateur` ASC) ,"
			+ "INDEX `fk_Historique_Produit` (`id_produit` ASC) ,"
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
			+ "`id_commande` INT NOT NULL AUTO_INCREMENT ,"
			+ "`client` VARCHAR(255) NULL ," + "`quantite` INT NULL ,"
			+ "`type` VARCHAR(255) NULL ," + "`matiere` VARCHAR(255) NULL ,"
			+ "PRIMARY KEY (`id_commande`) );";

	private static final String TABLE_PRODUIT = "CREATE  TABLE `Produit` ("
			+ "`id_produit` INT NOT NULL AUTO_INCREMENT ,"
			+ "`id_commande` INT NULL ," + "`id_poste` INT NULL ,"
			+ "`flag_attente` INT NULL ," + "`flag_termine` INT NULL ,"
			+ "PRIMARY KEY (`id_produit`) ,"
			+ "INDEX `fk_Produit_Commande` (`id_commande` ASC) ,"
			+ "INDEX `fk_Produit_Poste` (`id_poste` ASC) ,"
			+ "CONSTRAINT `fk_Produit_Commande`"
			+ "FOREIGN KEY (`id_commande` )"
			+ "REFERENCES `Commande` (`id_commande` )" + "ON DELETE NO ACTION"
			+ "ON UPDATE NO ACTION," + "CONSTRAINT `fk_Produit_Poste`"
			+ " FOREIGN KEY (`id_poste` )"
			+ " REFERENCES `Poste` (`id_poste` )" + " ON DELETE NO ACTION"
			+ "ON UPDATE NO ACTION);";
	
	//order is important, mah niggastring
	private static final String CREATE_BDD = 
			TABLE_COMMANDE 
			+ TABLE_POSTE
			+ TABLE_UTILISATEUR 
			+ TABLE_PRODUIT 
			+ TABLE_HISTORIQUE;
	
	// ====================================

	public DatabaseSQLite(Context context) {
		//super(context, DB_NAME, factory, DB_VERSION);
		super(context, DB_NAME, DB_CURSOR_FACTORY, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
