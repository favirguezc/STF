package modelo.produccion.variablesClimaticas;

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
import modelo.produccion.administracion.Finca;

/**
 *
 * @author fredy
 */
@Entity
public class Lluvia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @Column(nullable = false)
    private float mm;
    @ManyToOne(optional = false)
    private Finca finca;

    /**
     *
     * @return
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    /**
     *
     * @return
     */
    public float getMm() {
        return mm;
    }

    /**
     *
     * @param mm
     */
    public void setMm(float mm) {
        this.mm = mm;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.fecha);
        hash = 11 * hash + Float.floatToIntBits(this.mm);
        hash = 11 * hash + Objects.hashCode(this.finca);
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
        final Lluvia other = (Lluvia) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (Float.floatToIntBits(this.mm) != Float.floatToIntBits(other.mm)) {
            return false;
        }
        if (!Objects.equals(this.finca, other.finca)) {
            return false;
        }
        return true;
    }
}
