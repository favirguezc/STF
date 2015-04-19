package model.crop;

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
public class Crop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Cultivation cultivation;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date harvestDate;
    @ManyToOne(optional = false)
    private Person collector;
    private float weight;

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

    public Date getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

    public Person getCollector() {
        return collector;
    }

    public void setCollector(Person collector) {
        this.collector = collector;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 41 * hash + Objects.hashCode(this.cultivation);
        hash = 41 * hash + Objects.hashCode(this.harvestDate);
        hash = 41 * hash + Objects.hashCode(this.collector);
        hash = 41 * hash + Float.floatToIntBits(this.weight);
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
        final Crop other = (Crop) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (!Objects.equals(this.harvestDate, other.harvestDate)) {
            return false;
        }
        if (!Objects.equals(this.collector, other.collector)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        return true;
    }
    
    public void sumCrop(Crop r) {
        this.weight += r.weight;
    }

}
