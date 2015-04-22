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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date plantingDate;
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

    public Date getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(Date plantingDate) {
        this.plantingDate = plantingDate;
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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.moduleObject);
        hash = 67 * hash + Objects.hashCode(this.variety);
        hash = 67 * hash + Objects.hashCode(this.plantingDate);
        hash = 67 * hash + (this.active ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.observations);
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
        if (!Objects.equals(this.plantingDate, other.plantingDate)) {
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
