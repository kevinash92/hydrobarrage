/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.polytechnique.framework;

import java.util.List;
import java.util.Map;

/**
 *
 * @author BMF
 */
public interface Istat {
    
    /**
     * calculé la  moyenne d'une liste passé en parametre 
     * @param l
     * @return 
     */
    double moyenne(List<Double> l);
    /**
     * renvoi une map dont la clé est la modalité et la valeur est la frequence cumulé croissante
     * @param l
     * @return 
     */
    Map<Double, Double> tabStat(List<Double> l);
    
    
}
