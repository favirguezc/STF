/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fredy
 */
@Entity
@Table(name = "insumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i"),
    @NamedQuery(name = "Insumo.findById", query = "SELECT i FROM Insumo i WHERE i.id = :id"),
    @NamedQuery(name = "Insumo.findByIngredienteactivo", query = "SELECT i FROM Insumo i WHERE i.ingredienteactivo = :ingredienteactivo"),
    @NamedQuery(name = "Insumo.findByNombre", query = "SELECT i FROM Insumo i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Insumo.findByUnidades", query = "SELECT i FROM Insumo i WHERE i.unidades = :unidades")})
public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "INGREDIENTEACTIVO")
    private String ingredienteactivo;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "UNIDADES")
    private Integer unidades;
    @OneToMany(mappedBy = "productoId")
    private List<Aplicacionfitosanitaria> aplicacionfitosanitariaList;

    public Insumo() {
    }

    public Insumo(Long id) {
        this.id = id;
    }

    public Insumo(Long id, String ingredienteactivo, String nombre) {
        this.id = id;
        this.ingredienteactivo = ingredienteactivo;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredienteactivo() {
        return ingredienteactivo;
    }

    public void setIngredienteactivo(String ingredienteactivo) {
        this.ingredienteactivo = ingredienteactivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    @XmlTransient
    public List<Aplicacionfitosanitaria> getAplicacionfitosanitariaList() {
        return aplicacionfitosanitariaList;
    }

    public void setAplicacionfitosanitariaList(List<Aplicacionfitosanitaria> aplicacionfitosanitariaList) {
        this.aplicacionfitosanitariaList = aplicacionfitosanitariaList;
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
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Insumo[ id=" + id + " ]";
    }
    
}
