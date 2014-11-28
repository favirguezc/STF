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
    private String nombre;
    @Basic
    private boolean exigeContrasena;

    public Rol() {
    }

    public Rol(String rol, boolean exigeContraseña) {
        this.nombre = rol;
        this.exigeContrasena = exigeContraseña;
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

    public boolean isExigeContrasena() {
        return exigeContrasena;
    }

    public void setExigeContrasena(boolean exigeContrasena) {
        this.exigeContrasena = exigeContrasena;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
