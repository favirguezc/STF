package modelo.produccion.recoleccion;

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
import modelo.produccion.administracion.Lote;

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
    private Lote lote;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne(optional = false)
    private Persona recolector;
    private float extraGramos;
    private float primeraGramos;
    private float segundaGramos;
    private float terceraGramos;
    private float cuartaGramos;
    private float quintaGramos;
    private float danadaGramos;

    /**
     *
     */
    public Recoleccion() {
    }

    /**
     *
     * @param lote
     * @param fecha
     * @param recolector
     * @param extraGramos
     * @param primeraGramos
     * @param segundaGramos
     * @param terceraGramos
     * @param cuartaGramos
     * @param quintaGramos
     * @param danadaGramos
     */
    public Recoleccion(Lote lote, Date fecha, Persona recolector, float extraGramos, float primeraGramos, float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos, float danadaGramos) {
        this.lote = lote;
        this.fecha = fecha;
        this.recolector = recolector;
        this.extraGramos = extraGramos;
        this.primeraGramos = primeraGramos;
        this.segundaGramos = segundaGramos;
        this.terceraGramos = terceraGramos;
        this.cuartaGramos = cuartaGramos;
        this.quintaGramos = quintaGramos;
        this.danadaGramos = danadaGramos;
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

    /**
     *
     * @return
     */
    public Lote getLote() {
        return lote;
    }

    /**
     *
     * @param lote
     */
    public void setLote(Lote lote) {
        this.lote = lote;
    }

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

    /**
     *
     * @return
     */
    public Persona getRecolector() {
        return recolector;
    }

    /**
     *
     * @param recolector
     */
    public void setRecolector(Persona recolector) {
        this.recolector = recolector;
    }

    /**
     *
     * @return
     */
    public float getExtraGramos() {
        return extraGramos;
    }

    /**
     *
     * @param extraGramos
     */
    public void setExtraGramos(float extraGramos) {
        this.extraGramos = extraGramos;
    }

    /**
     *
     * @return
     */
    public float getPrimeraGramos() {
        return primeraGramos;
    }

    /**
     *
     * @param primeraGramos
     */
    public void setPrimeraGramos(float primeraGramos) {
        this.primeraGramos = primeraGramos;
    }

    /**
     *
     * @return
     */
    public float getSegundaGramos() {
        return segundaGramos;
    }

    /**
     *
     * @param segundaGramos
     */
    public void setSegundaGramos(float segundaGramos) {
        this.segundaGramos = segundaGramos;
    }

    /**
     *
     * @return
     */
    public float getTerceraGramos() {
        return terceraGramos;
    }

    /**
     *
     * @param terceraGramos
     */
    public void setTerceraGramos(float terceraGramos) {
        this.terceraGramos = terceraGramos;
    }

    /**
     *
     * @return
     */
    public float getCuartaGramos() {
        return cuartaGramos;
    }

    /**
     *
     * @param cuartaGramos
     */
    public void setCuartaGramos(float cuartaGramos) {
        this.cuartaGramos = cuartaGramos;
    }

    /**
     *
     * @return
     */
    public float getQuintaGramos() {
        return quintaGramos;
    }

    /**
     *
     * @param quintaGramos
     */
    public void setQuintaGramos(float quintaGramos) {
        this.quintaGramos = quintaGramos;
    }

    /**
     *
     * @return
     */
    public float getDanadaGramos() {
        return danadaGramos;
    }

    /**
     *
     * @param danadaGramos
     */
    public void setDanadaGramos(float danadaGramos) {
        this.danadaGramos = danadaGramos;
    }

    /**
     *
     * @return
     */
    public float getBuenaGramos() {
        return extraGramos + primeraGramos + segundaGramos + terceraGramos + cuartaGramos + quintaGramos;
    }

    /**
     *
     * @return
     */
    public float getTotalGramos() {
        return getBuenaGramos() + danadaGramos;
    }

    /**
     *
     * @param r
     */
    public void sumar(Recoleccion r) {
        this.extraGramos += r.extraGramos;
        this.primeraGramos += r.primeraGramos;
        this.segundaGramos += r.segundaGramos;
        this.terceraGramos += r.terceraGramos;
        this.cuartaGramos += r.cuartaGramos;
        this.quintaGramos += r.quintaGramos;
        this.danadaGramos += r.danadaGramos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 79 * hash + Objects.hashCode(this.lote);
        hash = 79 * hash + Objects.hashCode(this.fecha);
        hash = 79 * hash + Objects.hashCode(this.recolector);
        hash = 79 * hash + Float.floatToIntBits(this.extraGramos);
        hash = 79 * hash + Float.floatToIntBits(this.primeraGramos);
        hash = 79 * hash + Float.floatToIntBits(this.segundaGramos);
        hash = 79 * hash + Float.floatToIntBits(this.terceraGramos);
        hash = 79 * hash + Float.floatToIntBits(this.cuartaGramos);
        hash = 79 * hash + Float.floatToIntBits(this.quintaGramos);
        hash = 79 * hash + Float.floatToIntBits(this.danadaGramos);
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
        if (!Objects.equals(this.lote, other.lote)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.recolector, other.recolector)) {
            return false;
        }
        if (Float.floatToIntBits(this.extraGramos) != Float.floatToIntBits(other.extraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.primeraGramos) != Float.floatToIntBits(other.primeraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.segundaGramos) != Float.floatToIntBits(other.segundaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.terceraGramos) != Float.floatToIntBits(other.terceraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.cuartaGramos) != Float.floatToIntBits(other.cuartaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.quintaGramos) != Float.floatToIntBits(other.quintaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.danadaGramos) != Float.floatToIntBits(other.danadaGramos)) {
            return false;
        }
        return true;
    }
    
}
