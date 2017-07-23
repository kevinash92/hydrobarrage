/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.polytechnique.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevinash
 */
@Entity
@Table(name = "Chute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chute.findAll", query = "SELECT c FROM Chute c")
    , @NamedQuery(name = "Chute.findById", query = "SELECT c FROM Chute c WHERE c.id = :id")
    , @NamedQuery(name = "Chute.findByRegion", query = "SELECT c FROM Chute c WHERE c.region = :region")
    , @NamedQuery(name = "Chute.findByDepartment", query = "SELECT c FROM Chute c WHERE c.department = :department")
    , @NamedQuery(name = "Chute.findByArrondissement", query = "SELECT c FROM Chute c WHERE c.arrondissement = :arrondissement")
    , @NamedQuery(name = "Chute.findByQuartier", query = "SELECT c FROM Chute c WHERE c.quartier = :quartier")
    , @NamedQuery(name = "Chute.findByHauteur", query = "SELECT c FROM Chute c WHERE c.hauteur = :hauteur")})
public class Chute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @Column(name = "arrondissement")
    private String arrondissement;
    @Basic(optional = false)
    @Column(name = "quartier")
    private String quartier;
    @Basic(optional = false)
    @Column(name = "hauteur")
    private double hauteur;

    public Chute() {
    }

    public Chute(Long id) {
        this.id = id;
    }

    public Chute(Long id, String region, String department, String arrondissement, String quartier, double hauteur) {
        this.id = id;
        this.region = region;
        this.department = department;
        this.arrondissement = arrondissement;
        this.quartier = quartier;
        this.hauteur = hauteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chute)) {
            return false;
        }
        Chute other = (Chute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.polytechnique.model.Chute[ id=" + id + " ]";
    }
    
}
