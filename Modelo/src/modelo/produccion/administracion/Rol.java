package modelo.produccion.administracion;

import java.io.Serializable;
import java.util.Objects;
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
    @Column(unique = true, nullable = false)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + (this.contrasenaNecesaria ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.contrasenaNecesaria != other.contrasenaNecesaria) {
            return false;
        }
        return true;
    }

}
