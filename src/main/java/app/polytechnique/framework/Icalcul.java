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
public interface Icalcul {
    /**
     * calcul la demande energétique
     * @param besoinParMenage
     * @param chiffrePop
     * @param tcroissance
     * @param m
     * @param n
     * @return demande energetique
     */
    
      double DemandeEnergetique(double besoinParMenage, double chiffrePop ,double tcroissance, int m, int n);
      /**
       * calcul le potentiel energetique
       * @param bm
       * @param chiffre
       * @return potentiel energetique
       */
      double potentielEnergetique(double bm, double chiffre);
      /**
       * compare le potentiel a la demande et retourne true si le potentiel est superieur a la demande et false sinon
       * @param demandeEnergetique
       * @param potentielEnergetique
       * @return 
       */
      boolean compaDemandePotentiel(double demandeEnergetique, double potentielEnergetique);
      /**
       * calcul la puissance installé
       * @param debitAmenagement
       * @param hauteur
       * @return 
       */
      double puissanceInst(double debitAmenagement, double hauteur);
      /**
       * calcul la puisssance garantie
       * @param debit90
       * @param hauteur
       * @return la puissance garantie
       */
      double puissanceGarantie(double debit90, double hauteur);
      /**
       * calcul le productible
       * @param puissanceInst
       * @return le productible
       */
      double productible(double puissanceInst);
      /**
       * calcul la recette généré par l'ouvrage
       * @param productible
       * @return 
       */
      double recette(double productible);
      /**
       * calcul les charges de l'ouvrage
       * @param coutOuvrage
       * @return charge 
       */
      double charges(double coutOuvrage);
      /**
       * calcul la rentabilité du projet et retourne true si le projet est rentable et faux sinon
       * @param r
       * @param c
       * @return evalue si le projet est rentable ou pas
       */
      boolean rentabilite(List<Double> r , List<Double> c);
      /**
       * caracteristique du barage de derivation
       * @param hauteur
       * @param largeur
       * @return  hauteur de derivation
       */
      double barageDeDerivation(double hauteur, double largeur);
      /**
       * calcu la hauteur minimal d'eau au dessus de la conduite
       * @param diametre
       * @param vitesse
       * @return 
       */
      double priseEau(double diametre, double vitesse);
      /**
       * dimensionement du canal d'amenée
       * @param debit
       * @param vitesse
       * @return dimension canal
       */
      double dimensionCanal(double debit, double vitesse);
      /**
       * renvoi les dimensions de la chambre de prise en charge
       * @param debit
       * @param vitesse
       * @return 
       */
      double dimensionChambre(double debit, double vitesse);
      /**
       *  renvoi dans cet ordre les dimensions de la conduite et l'epaisseur minimale reommandée
       * @param n
       * @param debit
       * @param longueur
       * @param largeur
       * @return dimension de la conduite et l'epaisseur minimale recommandé
       */
      List<Double> dimensionConduite(double debit, double longueur,double largeur);
      /**
       * renvoi les caracteristiques de l'usine centrale
       * @param nbre
       * @param longueurQuai
       * @param diametre
       * @return renvoi la  longueur de l'usine
       */
      double caracteristiqueUsine(double nbre, double longueurQuai, double diametre);
      /**
       * renvoi en generale deux valeurs sous forme de liste decrivant l'intervalle de variation du cout de l'ouvrage.
       * renvoi une liste contenant une valeur pour indique que le cout doit etre inferieur a cette valeur
       * renvoi une liste vide si la puissance est superieur a 1Mw donc n'est pas pris en compte dans l'apps
       * @param puissance
       * @param locale
       * @return les bornes de variation du cout de l'ouvrage en dollar 
       */
      
      List<Integer> coutOuvrage(double puissance,boolean locale);
      /**
       * renvoi le type de turbine a utilisé dans le projet et la chaine de caractére "rien " ne corresponde pas a une MCH
       * @param hauteur
       * @param debit
       * @return String
       */
      String choixOption(double hauteur, double debit);
      /**
       * 
       * @param puissance
       * @param autoproduction // nous renseigne si le MCH est utilisé en autoproduction ou bien en distribution
       * @return 
       */
      String regimeJuridiqiue(double puissance, boolean autoproduction);
      

    
    
    
    
}