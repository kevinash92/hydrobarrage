package app.polytechnique.hydrobarrage.domain;

import app.polytechnique.model.Chute;
import app.polytechnique.model.Demande;
import javafx.scene.control.TableView;

public class DataModel {
	private static String nom;
	
	private static Chute chute = new Chute();
	
	private static Demande demande = new Demande();
	
	private static double demandeEnergetique;
	
	private static double potentielEnergetique;
	
	private static double debit;
	
	private static double puissanceInstallee;
	
	private static TableView<RowData> pluvioTableview;
	
	public static String getNom() {
		return nom;
	}
	
	public static TableView<RowData> getPluvioTableview() {
		return pluvioTableview;
	}
	
	public static void setPluvioTableview(TableView<RowData> pluvioTableview) {
		DataModel.pluvioTableview = pluvioTableview;
	}
	
	public static void setNom(String nom) {
		DataModel.nom = nom;
	}
	
	public static Chute getChute() {
		return chute;
	}
	
	public static void setChute(Chute chute) {
		DataModel.chute = chute;
	}

	public static Demande getDemande() {
		return demande;
	}

	public static void setDemande(Demande demande) {
		DataModel.demande = demande;
	}
	
	public static double getDemandeEnergetique() {
		return demandeEnergetique;
	}
	
	public static void setDemandeEnergetique(double demandeEnergetique) {
		DataModel.demandeEnergetique = demandeEnergetique;
	}
	
	public static double getPotentielEnergetique() {
		return potentielEnergetique;
	}
	
	public static void setPotentielEnergetique(double potentielEnergetique) {
		DataModel.potentielEnergetique = potentielEnergetique;
	}
	
	public static double getDebit() {
		return debit;
	}
	
	public static void setDebit(double debit) {
		DataModel.debit = debit;
	}
	
	public static double getPuissanceInstallee() {
		return puissanceInstallee;
	}
	
	public static void setPuissanceInstallee(double puissanceInstallee) {
		DataModel.puissanceInstallee = puissanceInstallee;
	}
}
