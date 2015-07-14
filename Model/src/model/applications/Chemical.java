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
import javax.persistence.ManyToOne;
import model.monitoring.MonitorableParameter;

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
    private ChemicalTypeEnum chemicalType;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String activeComponent;
    @Enumerated(EnumType.STRING)
    private ApplicationMeasurementUnitEnum applicationMeasurementUnit;
    @ManyToOne
    private MonitorableParameter commonReasonForUse;
    private String commonReasonsForUse;
    private float waitingPeriod;
    private float reEntryTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ChemicalTypeEnum getChemicalType() {
        return chemicalType;
    }

    public void setChemicalType(ChemicalTypeEnum chemicalType) {
        this.chemicalType = chemicalType;
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

    public MonitorableParameter getCommonReasonForUse() {
        return commonReasonForUse;
    }

    public void setCommonReasonForUse(MonitorableParameter commonReasonForUse) {
        this.commonReasonForUse = commonReasonForUse;
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

    public String getCommonReasonsForUse() {
        return commonReasonsForUse;
    }

    public void setCommonReasonsForUse(String commonReasonsForUse) {
        this.commonReasonsForUse = commonReasonsForUse;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.chemicalType);
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.activeComponent);
        hash = 53 * hash + Objects.hashCode(this.applicationMeasurementUnit);
        hash = 53 * hash + Objects.hashCode(this.commonReasonForUse);
        hash = 53 * hash + Objects.hashCode(this.commonReasonsForUse);
        hash = 53 * hash + Float.floatToIntBits(this.waitingPeriod);
        hash = 53 * hash + Float.floatToIntBits(this.reEntryTime);
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
        if (this.chemicalType != other.chemicalType) {
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
        if (!Objects.equals(this.commonReasonForUse, other.commonReasonForUse)) {
            return false;
        }
        if (!Objects.equals(this.commonReasonsForUse, other.commonReasonsForUse)) {
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
