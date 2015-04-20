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
    private ApplicationMeasurementUnitEnum applicationMeasurementUnit;
    private float waitingPeriod;
    private float reEntryTime;

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
    public ApplicationTypeEnum getApplicationType() {
        return applicationType;
    }

    /**
     *
     * @param applicationType
     */
    public void setApplicationType(ApplicationTypeEnum applicationType) {
        this.applicationType = applicationType;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getActiveComponent() {
        return activeComponent;
    }

    /**
     *
     * @param activeComponent
     */
    public void setActiveComponent(String activeComponent) {
        this.activeComponent = activeComponent;
    }

    /**
     *
     * @return
     */
    public ApplicationMeasurementUnitEnum getApplicationMeasurementUnit() {
        return applicationMeasurementUnit;
    }

    /**
     *
     * @param applicationMeasurementUnit
     */
    public void setApplicationMeasurementUnit(ApplicationMeasurementUnitEnum applicationMeasurementUnit) {
        this.applicationMeasurementUnit = applicationMeasurementUnit;
    }

    /**
     *
     * @return
     */
    public float getWaitingPeriod() {
        return waitingPeriod;
    }

    /**
     *
     * @param waitingPeriod
     */
    public void setWaitingPeriod(float waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    /**
     *
     * @return
     */
    public float getReEntryTime() {
        return reEntryTime;
    }

    /**
     *
     * @param reEntryTime
     */
    public void setReEntryTime(float reEntryTime) {
        this.reEntryTime = reEntryTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.activeComponent);
        hash = 83 * hash + Objects.hashCode(this.applicationMeasurementUnit);
        hash = 83 * hash + Float.floatToIntBits(this.waitingPeriod);
        hash = 83 * hash + Float.floatToIntBits(this.reEntryTime);
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
