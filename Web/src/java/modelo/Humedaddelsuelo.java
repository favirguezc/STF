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
@Table(name = "humedaddelsuelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Humedaddelsuelo.findAll", query = "SELECT h FROM Humedaddelsuelo h"),
    @NamedQuery(name = "Humedaddelsuelo.findById", query = "SELECT h FROM Humedaddelsuelo h WHERE h.id = :id"),
    @NamedQuery(name = "Humedaddelsuelo.findByFecha", query = "SELECT h FROM Humedaddelsuelo h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Humedaddelsuelo.findByHora", query = "SELECT h FROM Humedaddelsuelo h WHERE h.hora = :hora"),
    @NamedQuery(name = "Humedaddelsuelo.findByValoren15cms", query = "SELECT h FROM Humedaddelsuelo h WHERE h.valoren15cms = :valoren15cms"),
    @NamedQuery(name = "Humedaddelsuelo.findByValoren30cms", query = "SELECT h FROM Humedaddelsuelo h WHERE h.valoren30cms = :valoren30cms")})
public class Humedaddelsuelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOREN15CMS")
    private Float valoren15cms;
    @Column(name = "VALOREN30CMS")
    private Float valoren30cms;

    public Humedaddelsuelo() {
    }

    public Humedaddelsuelo(Long id) {
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

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Float getValoren15cms() {
        return valoren15cms;
    }

    public void setValoren15cms(Float valoren15cms) {
        this.valoren15cms = valoren15cms;
    }

    public Float getValoren30cms() {
        return valoren30cms;
    }

    public void setValoren30cms(Float valoren30cms) {
        this.valoren30cms = valoren30cms;
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
        if (!(object instanceof Humedaddelsuelo)) {
            return false;
        }
        Humedaddelsuelo other = (Humedaddelsuelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Humedaddelsuelo[ id=" + id + " ]";
    }
    
}
