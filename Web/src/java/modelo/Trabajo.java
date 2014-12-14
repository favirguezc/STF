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
@Table(name = "trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trabajo.findAll", query = "SELECT t FROM Trabajo t"),
    @NamedQuery(name = "Trabajo.findById", query = "SELECT t FROM Trabajo t WHERE t.id = :id"),
    @NamedQuery(name = "Trabajo.findByFecha", query = "SELECT t FROM Trabajo t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Trabajo.findByJornales", query = "SELECT t FROM Trabajo t WHERE t.jornales = :jornales"),
    @NamedQuery(name = "Trabajo.findByObservaciones", query = "SELECT t FROM Trabajo t WHERE t.observaciones = :observaciones")})
public class Trabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "JORNALES")
    private Float jornales;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ASISTENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona asistenteId;
    @JoinColumn(name = "LABOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Labor laborId;
    @JoinColumn(name = "OPERARIO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona operarioId;
    @JoinColumn(name = "PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona productorId;

    public Trabajo() {
    }

    public Trabajo(Long id) {
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

    public Float getJornales() {
        return jornales;
    }

    public void setJornales(Float jornales) {
        this.jornales = jornales;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getAsistenteId() {
        return asistenteId;
    }

    public void setAsistenteId(Persona asistenteId) {
        this.asistenteId = asistenteId;
    }

    public Labor getLaborId() {
        return laborId;
    }

    public void setLaborId(Labor laborId) {
        this.laborId = laborId;
    }

    public Persona getOperarioId() {
        return operarioId;
    }

    public void setOperarioId(Persona operarioId) {
        this.operarioId = operarioId;
    }

    public Persona getProductorId() {
        return productorId;
    }

    public void setProductorId(Persona productorId) {
        this.productorId = productorId;
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
        if (!(object instanceof Trabajo)) {
            return false;
        }
        Trabajo other = (Trabajo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Trabajo[ id=" + id + " ]";
    }
    
}
