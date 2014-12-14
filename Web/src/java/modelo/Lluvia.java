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
@Table(name = "lluvia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lluvia.findAll", query = "SELECT l FROM Lluvia l"),
    @NamedQuery(name = "Lluvia.findById", query = "SELECT l FROM Lluvia l WHERE l.id = :id"),
    @NamedQuery(name = "Lluvia.findByFecha", query = "SELECT l FROM Lluvia l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Lluvia.findByMm", query = "SELECT l FROM Lluvia l WHERE l.mm = :mm")})
public class Lluvia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MM")
    private Float mm;

    public Lluvia() {
    }

    public Lluvia(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getMm() {
        return mm;
    }

    public void setMm(Float mm) {
        this.mm = mm;
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
        if (!(object instanceof Lluvia)) {
            return false;
        }
        Lluvia other = (Lluvia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Lluvia[ id=" + id + " ]";
    }
    
}
