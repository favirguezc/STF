package modelo.produccion.labores;

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
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author fredy
 */
@Entity
public class Trabajo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @ManyToOne(optional = false)
    private Modulo modulo;
    @ManyToOne(optional = false)
    private Labor labor;
    @ManyToOne(optional = false)
    private Persona operario;
    private float horas;
    private String observaciones;

    /**
     *
     */
    public Trabajo() {
    }

    /**
     *
     * @param fecha
     * @param modulo
     * @param labor
     * @param operario
     * @param jornales
     * @param observaciones
     */
    public Trabajo(Date fecha, Modulo modulo, Labor labor, Persona operario, float jornales, String observaciones) {
        this.fecha = fecha;
        this.modulo = modulo;
        this.labor = labor;
        this.operario = operario;
        this.horas = jornales;
        this.observaciones = observaciones;
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
    public Modulo getModulo() {
        return modulo;
    }

    /**
     *
     * @param modulo
     */
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    /**
     *
     * @return
     */
    public Labor getLabor() {
        return labor;
    }

    /**
     *
     * @param labor
     */
    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    /**
     *
     * @return
     */
    public Persona getOperario() {
        return operario;
    }

    /**
     *
     * @param operario
     */
    public void setOperario(Persona operario) {
        this.operario = operario;
    }

    /**
     *
     * @return
     */
    public float getHoras() {
        return horas;
    }

    /**
     *
     * @param horas
     */
    public void setHoras(float horas) {
        this.horas = horas;
    }

    /**
     *
     * @return
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.fecha);
        hash = 29 * hash + Objects.hashCode(this.modulo);
        hash = 29 * hash + Objects.hashCode(this.labor);
        hash = 29 * hash + Objects.hashCode(this.operario);
        hash = 29 * hash + Float.floatToIntBits(this.horas);
        hash = 29 * hash + Objects.hashCode(this.observaciones);
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
        final Trabajo other = (Trabajo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (!Objects.equals(this.labor, other.labor)) {
            return false;
        }
        if (!Objects.equals(this.operario, other.operario)) {
            return false;
        }
        if (Float.floatToIntBits(this.horas) != Float.floatToIntBits(other.horas)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        return true;
    }

}
