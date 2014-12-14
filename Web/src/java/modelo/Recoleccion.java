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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recoleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recoleccion.findAll", query = "SELECT r FROM Recoleccion r"),
    @NamedQuery(name = "Recoleccion.findById", query = "SELECT r FROM Recoleccion r WHERE r.id = :id"),
    @NamedQuery(name = "Recoleccion.findByCuartagramos", query = "SELECT r FROM Recoleccion r WHERE r.cuartagramos = :cuartagramos"),
    @NamedQuery(name = "Recoleccion.findByDanadagramos", query = "SELECT r FROM Recoleccion r WHERE r.danadagramos = :danadagramos"),
    @NamedQuery(name = "Recoleccion.findByExtragramos", query = "SELECT r FROM Recoleccion r WHERE r.extragramos = :extragramos"),
    @NamedQuery(name = "Recoleccion.findByFecha", query = "SELECT r FROM Recoleccion r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Recoleccion.findByPrimeragramos", query = "SELECT r FROM Recoleccion r WHERE r.primeragramos = :primeragramos"),
    @NamedQuery(name = "Recoleccion.findByQuintagramos", query = "SELECT r FROM Recoleccion r WHERE r.quintagramos = :quintagramos"),
    @NamedQuery(name = "Recoleccion.findBySegundagramos", query = "SELECT r FROM Recoleccion r WHERE r.segundagramos = :segundagramos"),
    @NamedQuery(name = "Recoleccion.findByTerceragramos", query = "SELECT r FROM Recoleccion r WHERE r.terceragramos = :terceragramos")})
public class Recoleccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CUARTAGRAMOS")
    private Float cuartagramos;
    @Column(name = "DANADAGRAMOS")
    private Float danadagramos;
    @Column(name = "EXTRAGRAMOS")
    private Float extragramos;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "PRIMERAGRAMOS")
    private Float primeragramos;
    @Column(name = "QUINTAGRAMOS")
    private Float quintagramos;
    @Column(name = "SEGUNDAGRAMOS")
    private Float segundagramos;
    @Column(name = "TERCERAGRAMOS")
    private Float terceragramos;
    @JoinColumn(name = "RECOLECTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona recolectorId;

    public Recoleccion() {
    }

    public Recoleccion(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCuartagramos() {
        return cuartagramos;
    }

    public void setCuartagramos(Float cuartagramos) {
        this.cuartagramos = cuartagramos;
    }

    public Float getDanadagramos() {
        return danadagramos;
    }

    public void setDanadagramos(Float danadagramos) {
        this.danadagramos = danadagramos;
    }

    public Float getExtragramos() {
        return extragramos;
    }

    public void setExtragramos(Float extragramos) {
        this.extragramos = extragramos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getPrimeragramos() {
        return primeragramos;
    }

    public void setPrimeragramos(Float primeragramos) {
        this.primeragramos = primeragramos;
    }

    public Float getQuintagramos() {
        return quintagramos;
    }

    public void setQuintagramos(Float quintagramos) {
        this.quintagramos = quintagramos;
    }

    public Float getSegundagramos() {
        return segundagramos;
    }

    public void setSegundagramos(Float segundagramos) {
        this.segundagramos = segundagramos;
    }

    public Float getTerceragramos() {
        return terceragramos;
    }

    public void setTerceragramos(Float terceragramos) {
        this.terceragramos = terceragramos;
    }

    public Persona getRecolectorId() {
        return recolectorId;
    }

    public void setRecolectorId(Persona recolectorId) {
        this.recolectorId = recolectorId;
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
        if (!(object instanceof Recoleccion)) {
            return false;
        }
        Recoleccion other = (Recoleccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Recoleccion[ id=" + id + " ]";
    }
    
}
