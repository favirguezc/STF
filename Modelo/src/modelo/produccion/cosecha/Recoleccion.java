package modelo.produccion.cosecha;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Modulo;

/**
 *
 * @author fredy
 */
@Entity
public class Recoleccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Modulo modulo;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne(optional = false)
    private Persona recolector;
    private float pesadaGramos;

    public Recoleccion() {
    }

    public Recoleccion(Modulo modulo, Date fecha, Persona recolector, float pesadaGramos) {
        this.modulo = modulo;
        this.fecha = fecha;
        this.recolector = recolector;
        this.pesadaGramos = pesadaGramos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Persona getRecolector() {
        return recolector;
    }

    public void setRecolector(Persona recolector) {
        this.recolector = recolector;
    }

    public float getPesadaGramos() {
        return pesadaGramos;
    }

    public void setPesadaGramos(float pesadaGramos) {
        this.pesadaGramos = pesadaGramos;
    }

    /**
     *
     * @param r
     */
    public void sumar(Recoleccion r) {
        this.pesadaGramos += r.pesadaGramos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.modulo);
        hash = 59 * hash + Objects.hashCode(this.fecha);
        hash = 59 * hash + Objects.hashCode(this.recolector);
        hash = 59 * hash + Float.floatToIntBits(this.pesadaGramos);
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
        final Recoleccion other = (Recoleccion) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.recolector, other.recolector)) {
            return false;
        }
        if (Float.floatToIntBits(this.pesadaGramos) != Float.floatToIntBits(other.pesadaGramos)) {
            return false;
        }
        return true;
    }

}
