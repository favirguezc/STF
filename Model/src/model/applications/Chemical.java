package model.applications;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fredy
 */
@Entity
public class Chemical implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Enumerated(EnumType.STRING)
    private ApplicationTypeEnum applicationType;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String activeComponent;
    @Enumerated(EnumType.STRING)
    private ApplicationMeasurementUnitEnum applicationMeasurementUnit;
    private float waitingPeriod;
    private float reEntryTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApplicationTypeEnum getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationTypeEnum applicationType) {
        this.applicationType = applicationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActiveComponent() {
        return activeComponent;
    }

    public void setActiveComponent(String activeComponent) {
        this.activeComponent = activeComponent;
    }

    public ApplicationMeasurementUnitEnum getApplicationMeasurementUnit() {
        return applicationMeasurementUnit;
    }

    public void setApplicationMeasurementUnit(ApplicationMeasurementUnitEnum applicationMeasurementUnit) {
        this.applicationMeasurementUnit = applicationMeasurementUnit;
    }

    public float getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(float waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public float getReEntryTime() {
        return reEntryTime;
    }

    public void setReEntryTime(float reEntryTime) {
        this.reEntryTime = reEntryTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 43 * hash + Objects.hashCode(this.applicationType);
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.activeComponent);
        hash = 43 * hash + Objects.hashCode(this.applicationMeasurementUnit);
        hash = 43 * hash + Float.floatToIntBits(this.waitingPeriod);
        hash = 43 * hash + Float.floatToIntBits(this.reEntryTime);
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
        final Chemical other = (Chemical) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.applicationType != other.applicationType) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.activeComponent, other.activeComponent)) {
            return false;
        }
        if (this.applicationMeasurementUnit != other.applicationMeasurementUnit) {
            return false;
        }
        if (Float.floatToIntBits(this.waitingPeriod) != Float.floatToIntBits(other.waitingPeriod)) {
            return false;
        }
        if (Float.floatToIntBits(this.reEntryTime) != Float.floatToIntBits(other.reEntryTime)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return name;
    }

    
}
