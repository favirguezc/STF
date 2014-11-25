package modelo.administracion;

import java.io.Serializable;
import java.util.List;
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
    private String nombre;
    @Basic
    private boolean exigeContraseña;

    public Rol() {
    }

    public Rol(String rol, boolean exigeContraseña) {
        this.nombre = rol;
        this.exigeContraseña = exigeContraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return nombre;
    }

}
