package model.work;

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
import model.administration.Cultivation;
import model.administration.Person;

/**
 *
 * @author fredy
 */
@Entity
public class Work implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date workDate;
    @ManyToOne
    private Cultivation cultivation;
    @ManyToOne(optional = false)
    private Job job;
    @ManyToOne(optional = false)
    private Person worker;
    private float hourlyRate;
    private float hoursSpent;
    private String observations;

    public Work() {
        workDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        this.worker = worker;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
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
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.workDate);
        hash = 47 * hash + Objects.hashCode(this.cultivation);
        hash = 47 * hash + Objects.hashCode(this.job);
        hash = 47 * hash + Objects.hashCode(this.worker);
        hash = 47 * hash + Float.floatToIntBits(this.hourlyRate);
        hash = 47 * hash + Float.floatToIntBits(this.hoursSpent);
        hash = 47 * hash + Objects.hashCode(this.observations);
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
        final Work other = (Work) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.workDate, other.workDate)) {
            return false;
        }
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.worker, other.worker)) {
            return false;
        }
        if (Float.floatToIntBits(this.hourlyRate) != Float.floatToIntBits(other.hourlyRate)) {
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
