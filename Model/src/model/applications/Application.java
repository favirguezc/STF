package model.applications;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import model.administration.Cultivation;
import model.administration.Person;

/**
 *
 * @author fredy
 */
@Entity
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Cultivation cultivation;
    @Enumerated(EnumType.STRING)
    private ApplicationMethodEnum method;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date authorizationDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date applicationDate;
    private float waterLiters;
    @ManyToOne(optional = false)
    private Person authorizes;
    private float hoursSpent;
    private String observations;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ApplicatedChemical> chemicals;
    @OneToMany(fetch = FetchType.EAGER)
    private List<ApplicationResponsible> responsibles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public ApplicationMethodEnum getMethod() {
        return method;
    }

    public void setMethod(ApplicationMethodEnum method) {
        this.method = method;
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

    public float getWaterLiters() {
        return waterLiters;
    }

    public void setWaterLiters(float waterLiters) {
        this.waterLiters = waterLiters;
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

    public List<ApplicatedChemical> getChemicals() {
        return chemicals;
    }

    public void setChemicals(List<ApplicatedChemical> chemicals) {
        this.chemicals = chemicals;
    }

    public List<ApplicationResponsible> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<ApplicationResponsible> responsibles) {
        this.responsibles = responsibles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.cultivation);
        hash = 37 * hash + Objects.hashCode(this.method);
        hash = 37 * hash + Objects.hashCode(this.authorizationDate);
        hash = 37 * hash + Objects.hashCode(this.applicationDate);
        hash = 37 * hash + Float.floatToIntBits(this.waterLiters);
        hash = 37 * hash + Objects.hashCode(this.authorizes);
        hash = 37 * hash + Float.floatToIntBits(this.hoursSpent);
        hash = 37 * hash + Objects.hashCode(this.observations);
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
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (this.method != other.method) {
            return false;
        }
        if (!Objects.equals(this.authorizationDate, other.authorizationDate)) {
            return false;
        }
        if (!Objects.equals(this.applicationDate, other.applicationDate)) {
            return false;
        }
        if (Float.floatToIntBits(this.waterLiters) != Float.floatToIntBits(other.waterLiters)) {
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
