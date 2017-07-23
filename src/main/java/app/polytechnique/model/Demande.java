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
@Table(name = "Demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findById", query = "SELECT d FROM Demande d WHERE d.id = :id")
    , @NamedQuery(name = "Demande.findByN", query = "SELECT d FROM Demande d WHERE d.n = :n")
    , @NamedQuery(name = "Demande.findByBm", query = "SELECT d FROM Demande d WHERE d.bm = :bm")
    , @NamedQuery(name = "Demande.findByPo", query = "SELECT d FROM Demande d WHERE d.po = :po")
    , @NamedQuery(name = "Demande.findByAlpha", query = "SELECT d FROM Demande d WHERE d.alpha = :alpha")
    , @NamedQuery(name = "Demande.findByM", query = "SELECT d FROM Demande d WHERE d.m = :m")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "n")
    private int n;
    @Basic(optional = false)
    @Column(name = "bm")
    private double bm;
    @Basic(optional = false)
    @Column(name = "po")
    private double po;
    @Basic(optional = false)
    @Column(name = "alpha")
    private double alpha;
    @Basic(optional = false)
    @Column(name = "m")
    private int m;

    public Demande() {
    }

    public Demande(Long id) {
        this.id = id;
    }

    public Demande(Long id, int n, double bm, double po, double alpha, int m) {
        this.id = id;
        this.n = n;
        this.bm = bm;
        this.po = po;
        this.alpha = alpha;
        this.m = m;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getBm() {
        return bm;
    }

    public void setBm(double bm) {
        this.bm = bm;
    }

    public double getPo() {
        return po;
    }

    public void setPo(double po) {
        this.po = po;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.polytechnique.model.Demande[ id=" + id + " ]";
    }
    
}
