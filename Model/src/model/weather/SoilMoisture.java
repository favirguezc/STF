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
import model.administration.Farm;

/**
 *
 * @author fredy
 */
@Entity
public class SoilMoisture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date measurementDate;
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date measurementTime;
    @Column(nullable = false)
    private float valueIn30Centimeters;
    @Column(nullable = false)
    private float valueIn15Centimeters;
    @ManyToOne(optional = false)
    private Farm farm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Date getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Date measurementTime) {
        this.measurementTime = measurementTime;
    }

    public float getValueIn30Centimeters() {
        return valueIn30Centimeters;
    }

    public void setValueIn30Centimeters(float valueIn30Centimeters) {
        this.valueIn30Centimeters = valueIn30Centimeters;
    }

    public float getValueIn15Centimeters() {
        return valueIn15Centimeters;
    }

    public void setValueIn15Centimeters(float valueIn15Centimeters) {
        this.valueIn15Centimeters = valueIn15Centimeters;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.measurementDate);
        hash = 47 * hash + Objects.hashCode(this.measurementTime);
        hash = 47 * hash + Float.floatToIntBits(this.valueIn30Centimeters);
        hash = 47 * hash + Float.floatToIntBits(this.valueIn15Centimeters);
        hash = 47 * hash + Objects.hashCode(this.farm);
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
        final SoilMoisture other = (SoilMoisture) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.measurementDate, other.measurementDate)) {
            return false;
        }
        if (!Objects.equals(this.measurementTime, other.measurementTime)) {
            return false;
        }
        if (Float.floatToIntBits(this.valueIn30Centimeters) != Float.floatToIntBits(other.valueIn30Centimeters)) {
            return false;
        }
        if (Float.floatToIntBits(this.valueIn15Centimeters) != Float.floatToIntBits(other.valueIn15Centimeters)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        return true;
    }

    public void sumSoilMoisture(SoilMoisture h) {
        this.valueIn15Centimeters += h.valueIn15Centimeters;
        this.valueIn30Centimeters += h.valueIn30Centimeters;
    }

    public void divideSoilMoistureBy(int n) {
        this.valueIn15Centimeters = this.valueIn15Centimeters / n;
        this.valueIn30Centimeters = this.valueIn30Centimeters / n;
    }
}
