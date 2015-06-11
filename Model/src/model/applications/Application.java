package model.applications;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import model.administration.Cultivation;
import model.administration.Person;
import model.monitoring.MonitorableParameter;

/**
 *
 * @author fredy
 */
@Entity
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Enumerated(EnumType.STRING)
    private ApplicationTypeEnum type;
    @ManyToOne(optional = false)
    private Cultivation cultivation;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date authorizationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date applicationDate;
    @ManyToOne(optional = false)
    private Chemical chemical;
    @ManyToOne(optional = true)
    private MonitorableParameter reason1;
    @ManyToOne(optional = true)
    private MonitorableParameter reason2;
    @ManyToOne(optional = true)
    private MonitorableParameter reason3;
    private float quantity;
    private float waterLiters;
    @Enumerated(EnumType.STRING)
    private ApplicationMethodEnum method;
    @ManyToOne(optional = false)
    private Person responsible;
    @ManyToOne(optional = false)
    private Person authorizes;
    private float hoursSpent;
    private String observations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApplicationTypeEnum getType() {
        return type;
    }

    public void setType(ApplicationTypeEnum type) {
        this.type = type;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public Date getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Date authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Chemical getChemical() {
        return chemical;
    }

    public void setChemical(Chemical chemical) {
        this.chemical = chemical;
    }

    public MonitorableParameter getReason1() {
        return reason1;
    }

    public void setReason1(MonitorableParameter reason1) {
        this.reason1 = reason1;
    }

    public MonitorableParameter getReason2() {
        return reason2;
    }

    public void setReason2(MonitorableParameter reason2) {
        this.reason2 = reason2;
    }

    public MonitorableParameter getReason3() {
        return reason3;
    }

    public void setReason3(MonitorableParameter reason3) {
        this.reason3 = reason3;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getWaterLiters() {
        return waterLiters;
    }

    public void setWaterLiters(float waterLiters) {
        this.waterLiters = waterLiters;
    }

    public ApplicationMethodEnum getMethod() {
        return method;
    }

    public void setMethod(ApplicationMethodEnum method) {
        this.method = method;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Person getAuthorizes() {
        return authorizes;
    }

    public void setAuthorizes(Person authorizes) {
        this.authorizes = authorizes;
    }

    public float getHoursSpent() {
        return hoursSpent;
    }

    public void setHoursSpent(float hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.cultivation);
        hash = 17 * hash + Objects.hashCode(this.authorizationDate);
        hash = 17 * hash + Objects.hashCode(this.applicationDate);
        hash = 17 * hash + Objects.hashCode(this.chemical);
        hash = 17 * hash + Objects.hashCode(this.reason1);
        hash = 17 * hash + Objects.hashCode(this.reason2);
        hash = 17 * hash + Objects.hashCode(this.reason3);
        hash = 17 * hash + Float.floatToIntBits(this.quantity);
        hash = 17 * hash + Float.floatToIntBits(this.waterLiters);
        hash = 17 * hash + Objects.hashCode(this.method);
        hash = 17 * hash + Objects.hashCode(this.responsible);
        hash = 17 * hash + Objects.hashCode(this.authorizes);
        hash = 17 * hash + Float.floatToIntBits(this.hoursSpent);
        hash = 17 * hash + Objects.hashCode(this.observations);
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
        final Application other = (Application) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (!Objects.equals(this.authorizationDate, other.authorizationDate)) {
            return false;
        }
        if (!Objects.equals(this.applicationDate, other.applicationDate)) {
            return false;
        }
        if (!Objects.equals(this.chemical, other.chemical)) {
            return false;
        }
        if (!Objects.equals(this.reason1, other.reason1)) {
            return false;
        }
        if (!Objects.equals(this.reason2, other.reason2)) {
            return false;
        }
        if (!Objects.equals(this.reason3, other.reason3)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity) != Float.floatToIntBits(other.quantity)) {
            return false;
        }
        if (Float.floatToIntBits(this.waterLiters) != Float.floatToIntBits(other.waterLiters)) {
            return false;
        }
        if (this.method != other.method) {
            return false;
        }
        if (!Objects.equals(this.responsible, other.responsible)) {
            return false;
        }
        if (!Objects.equals(this.authorizes, other.authorizes)) {
            return false;
        }
        if (Float.floatToIntBits(this.hoursSpent) != Float.floatToIntBits(other.hoursSpent)) {
            return false;
        }
        if (!Objects.equals(this.observations, other.observations)) {
            return false;
        }
        return true;
    }
    
        
}
