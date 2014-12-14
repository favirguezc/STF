package modelo.administracion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(unique = true)
    private String nombre;
    private boolean contrasenaNecesaria;

    public Rol() {
    }

    public Rol(String rol, boolean exigeContraseña) {
        this.nombre = rol;
        this.contrasenaNecesaria = exigeContraseña;
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

    public boolean isContrasenaNecesaria() {
        return contrasenaNecesaria;
    }

    public void setContrasenaNecesaria(boolean contrasenaNecesaria) {
        this.contrasenaNecesaria = contrasenaNecesaria;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
