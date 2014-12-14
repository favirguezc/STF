/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fredy
 */
@Entity
@Table(name = "rolpersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolpersona.findAll", query = "SELECT r FROM Rolpersona r"),
    @NamedQuery(name = "Rolpersona.findById", query = "SELECT r FROM Rolpersona r WHERE r.id = :id"),
    @NamedQuery(name = "Rolpersona.findByContrasena", query = "SELECT r FROM Rolpersona r WHERE r.contrasena = :contrasena")})
public class Rolpersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CONTRASENA")
    private String contrasena;
    @JoinColumn(name = "PERSONA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Persona personaId;
    @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Rol rolId;

    public Rolpersona() {
    }

    public Rolpersona(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
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
        if (!(object instanceof Rolpersona)) {
            return false;
        }
        Rolpersona other = (Rolpersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Rolpersona[ id=" + id + " ]";
    }
    
}
