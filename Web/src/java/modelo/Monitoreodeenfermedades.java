/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fredy
 */
@Entity
@Table(name = "monitoreodeenfermedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitoreodeenfermedades.findAll", query = "SELECT m FROM Monitoreodeenfermedades m"),
    @NamedQuery(name = "Monitoreodeenfermedades.findById", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.id = :id"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByAntracnosis", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.antracnosis = :antracnosis"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByBacteriosis", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.bacteriosis = :bacteriosis"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByBotrytis", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.botrytis = :botrytis"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByFecha", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByMildeopolvoso", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.mildeopolvoso = :mildeopolvoso"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByMycospharella", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.mycospharella = :mycospharella"),
    @NamedQuery(name = "Monitoreodeenfermedades.findByPhytophtora", query = "SELECT m FROM Monitoreodeenfermedades m WHERE m.phytophtora = :phytophtora")})
public class Monitoreodeenfermedades implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ANTRACNOSIS")
    private Boolean antracnosis;
    @Column(name = "BACTERIOSIS")
    private Boolean bacteriosis;
    @Column(name = "BOTRYTIS")
    private Integer botrytis;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "MILDEOPOLVOSO")
    private Boolean mildeopolvoso;
    @Column(name = "MYCOSPHARELLA")
    private Integer mycospharella;
    @Column(name = "PHYTOPHTORA")
    private Boolean phytophtora;

    public Monitoreodeenfermedades() {
    }

    public Monitoreodeenfermedades(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAntracnosis() {
        return antracnosis;
    }

    public void setAntracnosis(Boolean antracnosis) {
        this.antracnosis = antracnosis;
    }

    public Boolean getBacteriosis() {
        return bacteriosis;
    }

    public void setBacteriosis(Boolean bacteriosis) {
        this.bacteriosis = bacteriosis;
    }

    public Integer getBotrytis() {
        return botrytis;
    }

    public void setBotrytis(Integer botrytis) {
        this.botrytis = botrytis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getMildeopolvoso() {
        return mildeopolvoso;
    }

    public void setMildeopolvoso(Boolean mildeopolvoso) {
        this.mildeopolvoso = mildeopolvoso;
    }

    public Integer getMycospharella() {
        return mycospharella;
    }

    public void setMycospharella(Integer mycospharella) {
        this.mycospharella = mycospharella;
    }

    public Boolean getPhytophtora() {
        return phytophtora;
    }

    public void setPhytophtora(Boolean phytophtora) {
        this.phytophtora = phytophtora;
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
        if (!(object instanceof Monitoreodeenfermedades)) {
            return false;
        }
        Monitoreodeenfermedades other = (Monitoreodeenfermedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Monitoreodeenfermedades[ id=" + id + " ]";
    }
    
}
