/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id = :id"),
    @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Persona.findByApellido2", query = "SELECT p FROM Persona p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula"),
    @NamedQuery(name = "Persona.findByGruposanguineo", query = "SELECT p FROM Persona p WHERE p.gruposanguineo = :gruposanguineo"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Persona.findByNombre2", query = "SELECT p FROM Persona p WHERE p.nombre2 = :nombre2"),
    @NamedQuery(name = "Persona.findByRh", query = "SELECT p FROM Persona p WHERE p.rh = :rh"),
    @NamedQuery(name = "Persona.findBySexo", query = "SELECT p FROM Persona p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Column(name = "CEDULA")
    private BigInteger cedula;
    @Column(name = "GRUPOSANGUINEO")
    private String gruposanguineo;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NOMBRE2")
    private String nombre2;
    @Column(name = "RH")
    private String rh;
    @Column(name = "SEXO")
    private String sexo;
    @Column(name = "TELEFONO")
    private BigInteger telefono;
    @OneToMany(mappedBy = "asistenteId")
    private List<Trabajo> trabajoList;
    @OneToMany(mappedBy = "operarioId")
    private List<Trabajo> trabajoList1;
    @OneToMany(mappedBy = "productorId")
    private List<Trabajo> trabajoList2;
    @OneToMany(mappedBy = "personaId")
    private List<Rolpersona> rolpersonaList;
    @OneToMany(mappedBy = "recolectorId")
    private List<Recoleccion> recoleccionList;
    @OneToMany(mappedBy = "asistenteId")
    private List<Trampadeinsectos> trampadeinsectosList;
    @OneToMany(mappedBy = "productorId")
    private List<Trampadeinsectos> trampadeinsectosList1;
    @OneToMany(mappedBy = "aprobanteId")
    private List<Aplicacionfitosanitaria> aplicacionfitosanitariaList;
    @OneToMany(mappedBy = "asistenteId")
    private List<Aplicacionfitosanitaria> aplicacionfitosanitariaList1;
    @OneToMany(mappedBy = "productorId")
    private List<Aplicacionfitosanitaria> aplicacionfitosanitariaList2;
    @OneToMany(mappedBy = "responsableId")
    private List<Aplicacionfitosanitaria> aplicacionfitosanitariaList3;

    public Persona() {
    }

    public Persona(Long id) {
        this.id = id;
    }

    public Persona(Long id, String apellido, String nombre) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public BigInteger getCedula() {
        return cedula;
    }

    public void setCedula(BigInteger cedula) {
        this.cedula = cedula;
    }

    public String getGruposanguineo() {
        return gruposanguineo;
    }

    public void setGruposanguineo(String gruposanguineo) {
        this.gruposanguineo = gruposanguineo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Trabajo> getTrabajoList() {
        return trabajoList;
    }

    public void setTrabajoList(List<Trabajo> trabajoList) {
        this.trabajoList = trabajoList;
    }

    @XmlTransient
    public List<Trabajo> getTrabajoList1() {
        return trabajoList1;
    }

    public void setTrabajoList1(List<Trabajo> trabajoList1) {
        this.trabajoList1 = trabajoList1;
    }

    @XmlTransient
    public List<Trabajo> getTrabajoList2() {
        return trabajoList2;
    }

    public void setTrabajoList2(List<Trabajo> trabajoList2) {
        this.trabajoList2 = trabajoList2;
    }

    @XmlTransient
    public List<Rolpersona> getRolpersonaList() {
        return rolpersonaList;
    }

    public void setRolpersonaList(List<Rolpersona> rolpersonaList) {
        this.rolpersonaList = rolpersonaList;
    }

    @XmlTransient
    public List<Recoleccion> getRecoleccionList() {
        return recoleccionList;
    }

    public void setRecoleccionList(List<Recoleccion> recoleccionList) {
        this.recoleccionList = recoleccionList;
    }

    @XmlTransient
    public List<Trampadeinsectos> getTrampadeinsectosList() {
        return trampadeinsectosList;
    }

    public void setTrampadeinsectosList(List<Trampadeinsectos> trampadeinsectosList) {
        this.trampadeinsectosList = trampadeinsectosList;
    }

    @XmlTransient
    public List<Trampadeinsectos> getTrampadeinsectosList1() {
        return trampadeinsectosList1;
    }

    public void setTrampadeinsectosList1(List<Trampadeinsectos> trampadeinsectosList1) {
        this.trampadeinsectosList1 = trampadeinsectosList1;
    }

    @XmlTransient
    public List<Aplicacionfitosanitaria> getAplicacionfitosanitariaList() {
        return aplicacionfitosanitariaList;
    }

    public void setAplicacionfitosanitariaList(List<Aplicacionfitosanitaria> aplicacionfitosanitariaList) {
        this.aplicacionfitosanitariaList = aplicacionfitosanitariaList;
    }

    @XmlTransient
    public List<Aplicacionfitosanitaria> getAplicacionfitosanitariaList1() {
        return aplicacionfitosanitariaList1;
    }

    public void setAplicacionfitosanitariaList1(List<Aplicacionfitosanitaria> aplicacionfitosanitariaList1) {
        this.aplicacionfitosanitariaList1 = aplicacionfitosanitariaList1;
    }

    @XmlTransient
    public List<Aplicacionfitosanitaria> getAplicacionfitosanitariaList2() {
        return aplicacionfitosanitariaList2;
    }

    public void setAplicacionfitosanitariaList2(List<Aplicacionfitosanitaria> aplicacionfitosanitariaList2) {
        this.aplicacionfitosanitariaList2 = aplicacionfitosanitariaList2;
    }

    @XmlTransient
    public List<Aplicacionfitosanitaria> getAplicacionfitosanitariaList3() {
        return aplicacionfitosanitariaList3;
    }

    public void setAplicacionfitosanitariaList3(List<Aplicacionfitosanitaria> aplicacionfitosanitariaList3) {
        this.aplicacionfitosanitariaList3 = aplicacionfitosanitariaList3;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Persona[ id=" + id + " ]";
    }
    
}
