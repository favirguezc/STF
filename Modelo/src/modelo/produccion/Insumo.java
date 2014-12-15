package modelo.produccion;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ingredienteActivo;
    private Unidades unidades;

    public Insumo() {
    }

    public Insumo(String nombre, String ingredienteActivo, Unidades unidades) {
        this.nombre = nombre;
        this.ingredienteActivo = ingredienteActivo;
        this.unidades = unidades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredienteActivo() {
        return ingredienteActivo;
    }

    public void setIngredienteActivo(String ingredienteActivo) {
        this.ingredienteActivo = ingredienteActivo;
    }

    public Unidades getUnidades() {
        return unidades;
    }

    public void setUnidades(Unidades unidades) {
        this.unidades = unidades;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.ingredienteActivo);
        hash = 79 * hash + Objects.hashCode(this.unidades);
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
        final Insumo other = (Insumo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.ingredienteActivo, other.ingredienteActivo)) {
            return false;
        }
        if (this.unidades != other.unidades) {
            return false;
        }
        return true;
    }

}
