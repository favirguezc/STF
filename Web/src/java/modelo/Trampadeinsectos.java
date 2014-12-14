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
@Table(name = "trampadeinsectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trampadeinsectos.findAll", query = "SELECT t FROM Trampadeinsectos t"),
    @NamedQuery(name = "Trampadeinsectos.findById", query = "SELECT t FROM Trampadeinsectos t WHERE t.id = :id"),
    @NamedQuery(name = "Trampadeinsectos.findByCambiodepegante", query = "SELECT t FROM Trampadeinsectos t WHERE t.cambiodepegante = :cambiodepegante"),
    @NamedQuery(name = "Trampadeinsectos.findByEspecie", query = "SELECT t FROM Trampadeinsectos t WHERE t.especie = :especie"),
    @NamedQuery(name = "Trampadeinsectos.findByFecha", query = "SELECT t FROM Trampadeinsectos t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Trampadeinsectos.findByIndividuos", query = "SELECT t FROM Trampadeinsectos t WHERE t.individuos = :individuos"),
    @NamedQuery(name = "Trampadeinsectos.findByNombre", query = "SELECT t FROM Trampadeinsectos t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Trampadeinsectos.findByObservaciones", query = "SELECT t FROM Trampadeinsectos t WHERE t.observaciones = :observaciones")})
public class Trampadeinsectos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CAMBIODEPEGANTE")
    private Boolean cambiodepegante;
    @Basic(optional = false)
    @Column(name = "ESPECIE")
    private String especie;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "INDIVIDUOS")
    private Integer individuos;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ASISTENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona asistenteId;
    @JoinColumn(name = "PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona productorId;

    public Trampadeinsectos() {
    }

    public Trampadeinsectos(Long id) {
        this.id = id;
    }

    public Trampadeinsectos(Long id, String especie, String nombre) {
        this.id = id;
        this.especie = especie;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCambiodepegante() {
        return cambiodepegante;
    }

    public void setCambiodepegante(Boolean cambiodepegante) {
        this.cambiodepegante = cambiodepegante;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIndividuos() {
        return individuos;
    }

    public void setIndividuos(Integer individuos) {
        this.individuos = individuos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Trampadeinsectos)) {
            return false;
        }
        Trampadeinsectos other = (Trampadeinsectos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Trampadeinsectos[ id=" + id + " ]";
    }
    
}
