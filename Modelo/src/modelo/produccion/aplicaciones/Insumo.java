package modelo.produccion.aplicaciones;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Enumerated(EnumType.STRING)
    private TipoDeAplicacion tipoDeAplicacion;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String ingredienteActivo;
    private Unidades unidades;
    private float periodoDeCarencia;
    private float tiempoDeReentrada;

    public Insumo() {
    }

    public Insumo(TipoDeAplicacion tipoDeAplicacion, String nombre, String ingredienteActivo, Unidades unidades, float pc, float tr) {
        this.tipoDeAplicacion = tipoDeAplicacion;
        this.nombre = nombre;
        this.ingredienteActivo = ingredienteActivo;
        this.unidades = unidades;
        this.periodoDeCarencia = pc;
        this.tiempoDeReentrada = tr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoDeAplicacion getTipoDeAplicacion() {
        return tipoDeAplicacion;
    }

    public void setTipoDeAplicacion(TipoDeAplicacion tipoDeAplicacion) {
        this.tipoDeAplicacion = tipoDeAplicacion;
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

    public float getPeriodoDeCarencia() {
        return periodoDeCarencia;
    }

    public void setPeriodoDeCarencia(float periodoDeCarencia) {
        this.periodoDeCarencia = periodoDeCarencia;
    }

    public float getTiempoDeReentrada() {
        return tiempoDeReentrada;
    }

    public void setTiempoDeReentrada(float tiempoDeReentrada) {
        this.tiempoDeReentrada = tiempoDeReentrada;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.ingredienteActivo);
        hash = 83 * hash + Objects.hashCode(this.unidades);
        hash = 83 * hash + Float.floatToIntBits(this.periodoDeCarencia);
        hash = 83 * hash + Float.floatToIntBits(this.tiempoDeReentrada);
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
        if (Float.floatToIntBits(this.periodoDeCarencia) != Float.floatToIntBits(other.periodoDeCarencia)) {
            return false;
        }
        if (Float.floatToIntBits(this.tiempoDeReentrada) != Float.floatToIntBits(other.tiempoDeReentrada)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    
}
