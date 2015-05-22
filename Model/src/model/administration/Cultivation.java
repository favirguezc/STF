/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.administration;

import model.crop.Variety;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author fredy
 */
@Entity
public class Cultivation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @ManyToOne(optional = false)
    private ModuleClass moduleObject;
    @ManyToOne(optional = false)
    private Variety variety;
    private String nursery;
    private float bedWidth;
    private float bedLength;
    private int beds;
    private int plants;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date plantingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date productionStartDate;
    private boolean active;
    private String observations;

    public Cultivation() {
        active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModuleClass getModuleObject() {
        return moduleObject;
    }

    public void setModuleObject(ModuleClass moduleObject) {
        this.moduleObject = moduleObject;
    }

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    public String getNursery() {
        return nursery;
    }

    public void setNursery(String nursery) {
        this.nursery = nursery;
    }

    public float getBedWidth() {
        return bedWidth;
    }

    public void setBedWidth(float bedWidth) {
        this.bedWidth = bedWidth;
    }

    public float getBedLength() {
        return bedLength;
    }

    public void setBedLength(float bedLength) {
        this.bedLength = bedLength;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getPlants() {
        return plants;
    }

    public void setPlants(int plants) {
        this.plants = plants;
    }

    public Date getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate = plantingDate;
    }

    public Date getProductionStartDate() {
        return productionStartDate;
    }

    public void setProductionStartDate(Date productionStartDate) {
        this.productionStartDate = productionStartDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.moduleObject);
        hash = 79 * hash + Objects.hashCode(this.variety);
        hash = 79 * hash + Objects.hashCode(this.nursery);
        hash = 79 * hash + Float.floatToIntBits(this.bedWidth);
        hash = 79 * hash + Float.floatToIntBits(this.bedLength);
        hash = 79 * hash + this.beds;
        hash = 79 * hash + this.plants;
        hash = 79 * hash + Objects.hashCode(this.plantingDate);
        hash = 79 * hash + Objects.hashCode(this.productionStartDate);
        hash = 79 * hash + (this.active ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.observations);
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
        final Cultivation other = (Cultivation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.moduleObject, other.moduleObject)) {
            return false;
        }
        if (!Objects.equals(this.variety, other.variety)) {
            return false;
        }
        if (!Objects.equals(this.nursery, other.nursery)) {
            return false;
        }
        if (Float.floatToIntBits(this.bedWidth) != Float.floatToIntBits(other.bedWidth)) {
            return false;
        }
        if (Float.floatToIntBits(this.bedLength) != Float.floatToIntBits(other.bedLength)) {
            return false;
        }
        if (this.beds != other.beds) {
            return false;
        }
        if (this.plants != other.plants) {
            return false;
        }
        if (!Objects.equals(this.plantingDate, other.plantingDate)) {
            return false;
        }
        if (!Objects.equals(this.productionStartDate, other.productionStartDate)) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.observations, other.observations)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return variety + ", " + moduleObject;
    }

}
