/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app.polytechnique.framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BMF
 */
public class Stat implements Istat{

    @Override
    public double moyenne(List<Double> l) {
       double s=0,moy=0; 
       for(double d:l){
           s+=d;
         //  System.out.println("le couple s,d est donné par :( "+s+","+d+ ")");
       } 
         // System.out.println("la taille de la liste est :"+l.size());
            try {
                moy = s/(l.size());
        } catch (Exception e) {
            e.getStackTrace();
    }
            return moy;
    }

    @Override
    public Map<Double, Double> tabStat(List<Double> l) {
        Map<Double, Double> tab = new HashMap<Double, Double>();
        Collections.sort(l);
       
//        for(double v:l){
//            
//            System.out.println("val:"+v);
//        }
              
        int s=0,i,k=0,k1=0,ctrl;
        double v1,v2,val = 0;
         i=1;
         ctrl=l.size();
        while (k < ctrl-1) {
     
            k1=k+1;
            v1=l.get(k);
            v2=l.get(k1);
            if(v1==v2){
                i++;
                }
            else{ 
                 s = s+i;
             val = s * 100.0 / (l.size());
            tab.put(l.get(k), val);
              i=1;
                
            }
//            System.out.println(k);
            ++k;
        }
        if(i!=1){
             s = s+i;
             val = s * 100.0 / (l.size());
            tab.put(l.get(k), val);
              i=1;
        }
//        System.out.println((s!=ctrl));
        
         if(s!=ctrl){
                
            val = (s+1) * 100.0 / (l.size());
                tab.put(l.get(s),val);
         }
        return tab;
    }      
}
