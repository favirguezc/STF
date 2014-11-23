package modelo.administracion;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Basic
    private String rol;
    @Basic
    private boolean exigeContraseña;

    public Rol() {
    }

    public Rol(String rol, boolean exigeContraseña) {
        this.rol = rol;
        this.exigeContraseña = exigeContraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isExigeContraseña() {
        return exigeContraseña;
    }

    public void setExigeContraseña(boolean exigeContraseña) {
        this.exigeContraseña = exigeContraseña;
    }

    @Override
    public String toString() {
        return rol;
    }
}
