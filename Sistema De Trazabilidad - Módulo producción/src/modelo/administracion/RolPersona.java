package modelo.administracion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author fredy
 */
@Entity
public class RolPersona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Basic
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Persona persona;
    @Basic
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rol rol;
    @Basic
    private String contraseña;

    public RolPersona() {
    }

    public RolPersona(Persona persona, Rol rol) {
        this.persona = persona;
        this.rol = rol;
        this.contraseña = null;
    }

    public RolPersona(Persona persona, Rol rol, String contraseña) {
        this.persona = persona;
        this.rol = rol;
        this.contraseña = contraseña;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
