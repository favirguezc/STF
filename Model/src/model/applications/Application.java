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
    private Chemical chemical1;
    private float quantity1;
    @ManyToOne
    private Chemical chemical2;
    private float quantity2;
    @ManyToOne
    private Chemical chemical3;
    private float quantity3;
    @ManyToOne
    private Chemical chemical4;
    private float quantity4;
    @ManyToOne
    private Chemical chemical5;
    private float quantity5;
    @ManyToOne(optional = false)
    private Person responsible1;
    @ManyToOne
    private Person responsible2;
    @ManyToOne
    private Person responsible3;
    @ManyToOne(optional = false)
    private Person authorizes;
    private float hoursSpent;
    private String observations;

    public Application() {
        authorizationDate = new Date();
        applicationDate = new Date();
    }

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

    public Chemical getChemical1() {
        return chemical1;
    }

    public void setChemical1(Chemical chemical1) {
        this.chemical1 = chemical1;
    }

    public float getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(float quantity1) {
        this.quantity1 = quantity1;
    }

    public Chemical getChemical2() {
        return chemical2;
    }

    public void setChemical2(Chemical chemical2) {
        this.chemical2 = chemical2;
    }

    public float getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(float quantity2) {
        this.quantity2 = quantity2;
    }

    public Chemical getChemical3() {
        return chemical3;
    }

    public void setChemical3(Chemical chemical3) {
        this.chemical3 = chemical3;
    }

    public float getQuantity3() {
        return quantity3;
    }

    public void setQuantity3(float quantity3) {
        this.quantity3 = quantity3;
    }

    public Chemical getChemical4() {
        return chemical4;
    }

    public void setChemical4(Chemical chemical4) {
        this.chemical4 = chemical4;
    }

    public float getQuantity4() {
        return quantity4;
    }

    public void setQuantity4(float quantity4) {
        this.quantity4 = quantity4;
    }

    public Chemical getChemical5() {
        return chemical5;
    }

    public void setChemical5(Chemical chemical5) {
        this.chemical5 = chemical5;
    }

    public float getQuantity5() {
        return quantity5;
    }

    public void setQuantity5(float quantity5) {
        this.quantity5 = quantity5;
    }

    public Person getResponsible1() {
        return responsible1;
    }

    public void setResponsible1(Person responsible1) {
        this.responsible1 = responsible1;
    }

    public Person getResponsible2() {
        return responsible2;
    }

    public void setResponsible2(Person responsible2) {
        this.responsible2 = responsible2;
    }

    public Person getResponsible3() {
        return responsible3;
    }

    public void setResponsible3(Person responsible3) {
        this.responsible3 = responsible3;
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
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.cultivation);
        hash = 97 * hash + Objects.hashCode(this.method);
        hash = 97 * hash + Objects.hashCode(this.authorizationDate);
        hash = 97 * hash + Objects.hashCode(this.applicationDate);
        hash = 97 * hash + Float.floatToIntBits(this.waterLiters);
        hash = 97 * hash + Objects.hashCode(this.chemical1);
        hash = 97 * hash + Float.floatToIntBits(this.quantity1);
        hash = 97 * hash + Objects.hashCode(this.chemical2);
        hash = 97 * hash + Float.floatToIntBits(this.quantity2);
        hash = 97 * hash + Objects.hashCode(this.chemical3);
        hash = 97 * hash + Float.floatToIntBits(this.quantity3);
        hash = 97 * hash + Objects.hashCode(this.chemical4);
        hash = 97 * hash + Float.floatToIntBits(this.quantity4);
        hash = 97 * hash + Objects.hashCode(this.chemical5);
        hash = 97 * hash + Float.floatToIntBits(this.quantity5);
        hash = 97 * hash + Objects.hashCode(this.responsible1);
        hash = 97 * hash + Objects.hashCode(this.responsible2);
        hash = 97 * hash + Objects.hashCode(this.responsible3);
        hash = 97 * hash + Objects.hashCode(this.authorizes);
        hash = 97 * hash + Float.floatToIntBits(this.hoursSpent);
        hash = 97 * hash + Objects.hashCode(this.observations);
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
        if (!Objects.equals(this.chemical1, other.chemical1)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity1) != Float.floatToIntBits(other.quantity1)) {
            return false;
        }
        if (!Objects.equals(this.chemical2, other.chemical2)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity2) != Float.floatToIntBits(other.quantity2)) {
            return false;
        }
        if (!Objects.equals(this.chemical3, other.chemical3)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity3) != Float.floatToIntBits(other.quantity3)) {
            return false;
        }
        if (!Objects.equals(this.chemical4, other.chemical4)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity4) != Float.floatToIntBits(other.quantity4)) {
            return false;
        }
        if (!Objects.equals(this.chemical5, other.chemical5)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity5) != Float.floatToIntBits(other.quantity5)) {
            return false;
        }
        if (!Objects.equals(this.responsible1, other.responsible1)) {
            return false;
        }
        if (!Objects.equals(this.responsible2, other.responsible2)) {
            return false;
        }
        if (!Objects.equals(this.responsible3, other.responsible3)) {
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
