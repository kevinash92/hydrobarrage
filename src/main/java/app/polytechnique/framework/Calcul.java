/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.polytechnique.framework;
import java.util.*;

/**
 *
 * @author BMF
 */
public class Calcul implements Icalcul{
	public Calcul(){

	}
	static float val = (float) (0.85*1000*9.81*Math.pow(10, -3));// constant devant servir dans le calcul des puissances 
	@Override
	public double DemandeEnergetique(double besoinParMenage, double chiffrePop, double tcroissance, int m, int n){
		if(m == 0){
			System.out.println("le nombre de personne par menage doit etre non nul");
			return 0;
		}
		else{



			tcroissance++;
			//double taux = Math.pow(tcroissance, n);
			return besoinParMenage*chiffrePop*Math.pow(tcroissance, n)/m ;

		}
	}

	@Override
	public double potentielEnergetique(double debit, double hauteur) {


		return val*hauteur*debit;

	}

	@Override
	public boolean compaDemandePotentiel(double demandeEnergetique, double potentielEnergetique) {
		if(potentielEnergetique==0){
			System.out.println("la valeur du potentiel doit etre non nuls");
			return false;
		}
		else if(potentielEnergetique<0 || demandeEnergetique <0){
			System.out.println("les parametre doivent etre toutes positives"); 
			return false;
		}
		else{


			//  double valcontrol = Math.pow(10,-15);
			return (demandeEnergetique/potentielEnergetique)<=1; 



		}
	}

	@Override
	public double puissanceInst(double debitAmenagement, double hauteur) {

		return val*debitAmenagement*hauteur;
	}

	@Override
	public double puissanceGarantie(double debit90, double hauteur) {

		return val*debit90*hauteur;

	}

	@Override
	public double productible(double puissaneInst) {
		double tempsAn = 24*365; // calcul du temps annuelle en heures;

		return puissaneInst*tempsAn; //exprimé en kilo wattheur



	}

	@Override
	public double recette(double productible) {
		double c = 0.7*50;
		return productible*c;

	}

	@Override
	public double charges(double coutOuvrage) {

		return 0.3*coutOuvrage;
	}

	@Override
	public boolean rentabilite(List<Double> r, List<Double> c) {

		// retourne vrai si le projet est rentable et faux sinon 
		double val1,s=0; 
		if(r==null||c==null){
			System.out.println("liste vide  ");
			return false;
		}
		else if(r.isEmpty()){
			System.out.println("aucune données pour le programme ");
			return false;
		}
		else if(r.size()!=c.size())
		{
			System.out.println("donées non valides ...!!!!! Veuillez verifié la taille des données passés en parametre");
			return false;
		}

		else
		{
			int cpt=r.size();
			for(int i=0;i<cpt;i++)
			{
				val1 = Math.pow(1.125, i);     
				s+=((r.get(i)-c.get(i))/val1); 
			}    
		}

		if(s==Math.max(s, 1))
		{
			return true;
		}
		else
		{
			return true;
		}
	}

	@Override
	public double barageDeDerivation(double hauteur, double largeur) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		return 1.8*(largeur-0.2*hauteur)*Math.pow(hauteur, 1.5);
	}

	@Override
	public double priseEau(double diametre, double vitesse) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		return 0.3*vitesse*Math.sqrt(diametre);

	}

	@Override
	public double dimensionCanal(double debit, double vitesse) {
		double s=0;
		if(0.29>=vitesse || vitesse >=0.68){

			System.out.println("vitesse d'ecoulement dans le canal non conforme");
		}
		else{ 
			s = debit/vitesse ;
		} 
		return Math.sqrt(s/(Math.PI));



	}

	@Override
	public double dimensionChambre(double debit, double vitesse) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		return debit/vitesse;
	}

	@Override
	public List<Double> dimensionConduite(double debit, double longueur, double largeur) {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		double d, t; 
		List<Double> l = new ArrayList<Double>();
		d=2.69*(Math.pow(0.8, 2)*Math.pow(debit, 2)*longueur/largeur);
		t=1.2+(d+508)/400 ;
		l.add(t);
		l.add(d);
		return l;

	}

	@Override
	public double caracteristiqueUsine(double nbre, double longueurQuai, double diametre) {
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		return longueurQuai+nbre*(2.5+5*diametre);


	}

	@Override
	public List<Integer> coutOuvrage(double puissance, boolean locale) {
		// la puisssance ici doit etre exprimé en kilo watt        
		// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		List<Integer> cout = new ArrayList<Integer>();


		if(!locale&&puissance<=1000){

			if(puissance==1000){
				cout.add(1000000);
				cout.add(12000000);

			}

			else if(puissance>500)
			{
				cout.add(750000);
				cout.add(10000000);

			}

			else if (puissance<500 && puissance>100){
				cout.add(450000);
				cout.add(750000);    
			}


			else {
				cout.add(150000);
			}
		}
		/**
		 * s'il ya contribution de la fabrication locale alors les couts ci haut sont reduire de 20% 
		 * d'ou le traitement ci aprés
		 */
		else if(locale && puissance<=500){
			if (puissance>100){
				cout.add(45000*8);
				cout.add(75000*8);    
			}


			else if(puissance<100){
				cout.add(15000*8);
			}


			else 
				cout.clear();


		}
		// conversion en francs CFA
		if(!cout.isEmpty())

			if(cout.size()==1)
				cout.add(0, cout.get(0)*575);
			else{
				cout.add(0, cout.get(0)*575);
				cout.add(1, cout.get(1)*575);
			}
		return cout;
	}

	@Override
	public String choixOption(double hauteur, double debit) {

		List<String> t = new ArrayList<String>();
		t.add("Turbine KAPLAN");
		t.add("Turbine FRANCIS");
		t.add("Turbine PELTON");
		t.add("Turbine TURBO");
		t.add("Turbine BANKI-MICHELL");
		t.add("rien");
		int i;


		if(hauteur<=15 && (debit<10 && debit>0.5))
			i=0;
		else if((hauteur<=50 && hauteur>= 15 ) && ( debit<=10))
			i=1;
		else if((hauteur<=200 && hauteur >=30) && (debit<=1 ))
			i=2;
		else if((hauteur<=250 && hauteur >=50) && (debit<=3 ))
			i=3;
		else if((hauteur<=30) && (debit<=0.5 ))
			i=4;
		else 
			i=5;
		return t.get(i);
	}
	/*
    @Override
    public double conversionDollarFranc(List<Double> dollar,float unite) {
       if(dollar.isEmpty())
           return -1;
       else if(dollar.size()==1)
           return dollar.get(0)*unite;
       else 
           s

    }

	 */  

	List<Double> dimensionConduite(double n, double debit, double longueur, double largeur) {
		double d, t; 
		List<Double> l = new ArrayList<Double>();
		d=2.69*(Math.pow(n, 2)*Math.pow(debit, 2)*longueur/largeur);
		t=1.2+(d+508)/400 ;
		l.add(t);
		l.add(d);
		return l;
	}

	@Override
	public String regimeJuridiqiue(double puissance, boolean autoproduction) {
		String regime;  
		if(autoproduction){
			if(puissance>1000){
				regime = "REGIME:AUTORISATION";
			}
			else if(puissance<=1000 && puissance>=100)
				regime = "REGIME:DECLARATION";
			else
				regime = "REGIME:LIBERTE";   
		}
		else{
			if(puissance<=100)
				regime = "REGIME:AUTORISATION";
			else
				regime = "REGIME PAS PRIS EN COMPTE";
		}

		return regime;

	}
}







