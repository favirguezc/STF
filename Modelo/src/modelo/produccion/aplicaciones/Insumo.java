package modelo.produccion.aplicaciones;

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
    private float pc;
    private float tr;

    public Insumo() {
    }

    public Insumo(String nombre, String ingredienteActivo, Unidades unidades, float pc, float tr) {
        this.nombre = nombre;
        this.ingredienteActivo = ingredienteActivo;
        this.unidades = unidades;
        this.pc = pc;
        this.tr = tr;
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

    public float getPc() {
        return pc;
    }

    public void setPc(float pc) {
        this.pc = pc;
    }

    public float getTr() {
        return tr;
    }

    public void setTr(float tr) {
        this.tr = tr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.ingredienteActivo);
        hash = 83 * hash + Objects.hashCode(this.unidades);
        hash = 83 * hash + Float.floatToIntBits(this.pc);
        hash = 83 * hash + Float.floatToIntBits(this.tr);
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
        if (Float.floatToIntBits(this.pc) != Float.floatToIntBits(other.pc)) {
            return false;
        }
        if (Float.floatToIntBits(this.tr) != Float.floatToIntBits(other.tr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
}
