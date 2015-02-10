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
import javax.persistence.TemporalType;
import modelo.produccion.administracion.Modulo;

/**
 *
 * @author fredy
 */
@Entity
public class Temperatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date hora;
    @Column(nullable = false)
    private float temperatura;
    @Column(nullable = false)
    private float humedad;
    @Column(nullable = false)
    private float puntoDeRocio;
    @ManyToOne(optional = false)
    private Modulo modulo;

    /**
     *
     */
    public Temperatura() {
    }

    /**
     *
     * @param fecha
     * @param hora
     * @param temperatura
     * @param humedad
     * @param puntoDeRocio
     * @param modulo
     */
    public Temperatura(Date fecha, Date hora, float temperatura, float humedad, float puntoDeRocio, Modulo modulo) {
        this.fecha = fecha;
        this.hora = hora;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.puntoDeRocio = puntoDeRocio;
        this.modulo = modulo;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
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
    public Date getHora() {
        return hora;
    }

    /**
     *
     * @param hora
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     *
     * @return
     */
    public float getTemperatura() {
        return temperatura;
    }

    /**
     *
     * @param temperatura
     */
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    /**
     *
     * @return
     */
    public float getHumedad() {
        return humedad;
    }

    /**
     *
     * @param humedad
     */
    public void setHumedad(float humedad) {
        this.humedad = humedad;
    }

    /**
     *
     * @return
     */
    public float getPuntoDeRocio() {
        return puntoDeRocio;
    }

    /**
     *
     * @param puntoDeRocio
     */
    public void setPuntoDeRocio(float puntoDeRocio) {
        this.puntoDeRocio = puntoDeRocio;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.fecha);
        hash = 71 * hash + Objects.hashCode(this.hora);
        hash = 71 * hash + Float.floatToIntBits(this.temperatura);
        hash = 71 * hash + Float.floatToIntBits(this.humedad);
        hash = 71 * hash + Float.floatToIntBits(this.puntoDeRocio);
        hash = 71 * hash + Objects.hashCode(this.modulo);
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
        final Temperatura other = (Temperatura) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (Float.floatToIntBits(this.temperatura) != Float.floatToIntBits(other.temperatura)) {
            return false;
        }
        if (Float.floatToIntBits(this.humedad) != Float.floatToIntBits(other.humedad)) {
            return false;
        }
        if (Float.floatToIntBits(this.puntoDeRocio) != Float.floatToIntBits(other.puntoDeRocio)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param t
     */
    public void sumar(Temperatura t) {
        this.temperatura += t.temperatura;
        this.humedad += t.humedad;
        this.puntoDeRocio += t.puntoDeRocio;
    }

    /**
     *
     * @param size
     */
    public void dividir(int size) {
        this.temperatura = this.temperatura / size;
        this.humedad = this.humedad / size;
        this.puntoDeRocio = this.puntoDeRocio / size;
    }

}
