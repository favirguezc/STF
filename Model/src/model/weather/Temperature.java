package model.weather;

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
import model.administration.ModuleClass;

/**
 *
 * @author fredy
 */
@Entity
public class Temperature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date measurementDate;
    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date measurementTime;
    @Column(nullable = false)
    private float temperature;
    @Column(nullable = false)
    private float humidity;
    @Column(nullable = false)
    private float dewPoint;
    @ManyToOne(optional = false)
    private ModuleClass moduleObject;

    /**
     *
     */
    public Temperature() {
        measurementDate = new Date();
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
    public Temperature(Date fecha, Date hora, float temperatura, float humedad, float puntoDeRocio, ModuleClass modulo) {
        this.measurementDate = fecha;
        this.measurementTime = hora;
        this.temperature = temperatura;
        this.humidity = humedad;
        this.dewPoint = puntoDeRocio;
        this.moduleObject = modulo;
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
    public Date getMeasurementDate() {
        return measurementDate;
    }

    /**
     *
     * @param measurementDate
     */
    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    /**
     *
     * @return
     */
    public Date getMeasurementTime() {
        return measurementTime;
    }

    /**
     *
     * @param measurementTime
     */
    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    /**
     *
     * @return
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     *
     * @return
     */
    public float getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     */
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     */
    public float getDewPoint() {
        return dewPoint;
    }

    /**
     *
     * @param dewPoint
     */
    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     *
     * @return
     */
    public ModuleClass getModuleObject() {
        return moduleObject;
    }

    /**
     *
     * @param moduleObject
     */
    public void setModuleObject(ModuleClass moduleObject) {
        this.moduleObject = moduleObject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.measurementDate);
        hash = 71 * hash + Objects.hashCode(this.measurementTime);
        hash = 71 * hash + Float.floatToIntBits(this.temperature);
        hash = 71 * hash + Float.floatToIntBits(this.humidity);
        hash = 71 * hash + Float.floatToIntBits(this.dewPoint);
        hash = 71 * hash + Objects.hashCode(this.moduleObject);
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
        final Temperature other = (Temperature) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.measurementDate, other.measurementDate)) {
            return false;
        }
        if (!Objects.equals(this.measurementTime, other.measurementTime)) {
            return false;
        }
        if (Float.floatToIntBits(this.temperature) != Float.floatToIntBits(other.temperature)) {
            return false;
        }
        if (Float.floatToIntBits(this.humidity) != Float.floatToIntBits(other.humidity)) {
            return false;
        }
        if (Float.floatToIntBits(this.dewPoint) != Float.floatToIntBits(other.dewPoint)) {
            return false;
        }
        if (!Objects.equals(this.moduleObject, other.moduleObject)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param t
     */
    public void sumar(Temperature t) {
        this.temperature += t.temperature;
        this.humidity += t.humidity;
        this.dewPoint += t.dewPoint;
    }

    /**
     *
     * @param size
     */
    public void dividir(int size) {
        this.temperature = this.temperature / size;
        this.humidity = this.humidity / size;
        this.dewPoint = this.dewPoint / size;
    }

}
