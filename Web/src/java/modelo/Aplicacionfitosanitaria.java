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
@Table(name = "aplicacionfitosanitaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aplicacionfitosanitaria.findAll", query = "SELECT a FROM Aplicacionfitosanitaria a"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findById", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.id = :id"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByAgua", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.agua = :agua"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByCantidad", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.cantidad = :cantidad"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByEquipo", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.equipo = :equipo"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByFecha", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByJornales", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.jornales = :jornales"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByMotivo", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.motivo = :motivo"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByObservaciones", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByPc", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.pc = :pc"),
    @NamedQuery(name = "Aplicacionfitosanitaria.findByTr", query = "SELECT a FROM Aplicacionfitosanitaria a WHERE a.tr = :tr")})
public class Aplicacionfitosanitaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AGUA")
    private Float agua;
    @Column(name = "CANTIDAD")
    private Float cantidad;
    @Column(name = "EQUIPO")
    private String equipo;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "JORNALES")
    private Float jornales;
    @Column(name = "MOTIVO")
    private String motivo;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "PC")
    private Boolean pc;
    @Column(name = "TR")
    private Boolean tr;
    @JoinColumn(name = "APROBANTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona aprobanteId;
    @JoinColumn(name = "ASISTENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona asistenteId;
    @JoinColumn(name = "PRODUCTOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona productorId;
    @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Insumo productoId;
    @JoinColumn(name = "RESPONSABLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona responsableId;

    public Aplicacionfitosanitaria() {
    }

    public Aplicacionfitosanitaria(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAgua() {
        return agua;
    }

    public void setAgua(Float agua) {
        this.agua = agua;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getPc() {
        return pc;
    }

    public void setPc(Boolean pc) {
        this.pc = pc;
    }

    public Boolean getTr() {
        return tr;
    }

    public void setTr(Boolean tr) {
        this.tr = tr;
    }

    public Persona getAprobanteId() {
        return aprobanteId;
    }

    public void setAprobanteId(Persona aprobanteId) {
        this.aprobanteId = aprobanteId;
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

    public Insumo getProductoId() {
        return productoId;
    }

    public void setProductoId(Insumo productoId) {
        this.productoId = productoId;
    }

    public Persona getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Persona responsableId) {
        this.responsableId = responsableId;
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
        if (!(object instanceof Aplicacionfitosanitaria)) {
            return false;
        }
        Aplicacionfitosanitaria other = (Aplicacionfitosanitaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Aplicacionfitosanitaria[ id=" + id + " ]";
    }
    
}
