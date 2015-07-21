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
import model.administration.Farm;

/**
 *
 * @author fredy
 */
@Entity
public class RainFall implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date rainDate;
    @Column(nullable = false)
    private float milimeters;
    @ManyToOne(optional = false)
    private Farm farm;

    public RainFall() {
        rainDate = new Date();
    }

    /**
     *
     * @return
     */
    public Date getRainDate() {
        return rainDate;
    }

    /**
     *
     * @param rainDate
     */
    public void setRainDate(Date rainDate) {
        this.rainDate = rainDate;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     */
    public float getMilimeters() {
        return milimeters;
    }

    /**
     *
     * @param milimeters
     */
    public void setMilimeters(float milimeters) {
        this.milimeters = milimeters;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 11 * hash + Objects.hashCode(this.rainDate);
        hash = 11 * hash + Float.floatToIntBits(this.milimeters);
        hash = 11 * hash + Objects.hashCode(this.farm);
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
        final RainFall other = (RainFall) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.rainDate, other.rainDate)) {
            return false;
        }
        if (Float.floatToIntBits(this.milimeters) != Float.floatToIntBits(other.milimeters)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        return true;
    }
}
