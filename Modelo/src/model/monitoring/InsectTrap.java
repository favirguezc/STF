package model.monitoring;

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
public class InsectTrap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date registerDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String species;
    private int individuals;
    private boolean glueChange;
    private String observations;
    @ManyToOne(optional = false)
    private Farm farm;

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
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     *
     * @param registerDate
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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
    public String getSpecies() {
        return species;
    }

    /**
     *
     * @param species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     *
     * @return
     */
    public int getIndividuals() {
        return individuals;
    }

    /**
     *
     * @param individuals
     */
    public void setIndividuals(int individuals) {
        this.individuals = individuals;
    }

    /**
     *
     * @return
     */
    public boolean isGlueChange() {
        return glueChange;
    }

    /**
     *
     * @param glueChange
     */
    public void setGlueChange(boolean glueChange) {
        this.glueChange = glueChange;
    }

    /**
     *
     * @return
     */
    public String getObservations() {
        return observations;
    }

    /**
     *
     * @param observations
     */
    public void setObservations(String observations) {
        this.observations = observations;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.registerDate);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.species);
        hash = 47 * hash + this.individuals;
        hash = 47 * hash + (this.glueChange ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.observations);
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
        final InsectTrap other = (InsectTrap) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.registerDate, other.registerDate)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.species, other.species)) {
            return false;
        }
        if (this.individuals != other.individuals) {
            return false;
        }
        if (this.glueChange != other.glueChange) {
            return false;
        }
        if (!Objects.equals(this.observations, other.observations)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        return true;
    }

}
