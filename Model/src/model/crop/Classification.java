/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author fredy
 */
@Entity
public class Classification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date classificationDate;
    @ManyToOne(optional = false)
    private Cultivation cultivation;
    @ManyToOne(optional = false)
    private ClassificationTypeEnum type;
    private float weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getClassificationDate() {
        return classificationDate;
    }

    public void setClassificationDate(Date classificationDate) {
        this.classificationDate = classificationDate;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public ClassificationTypeEnum getType() {
        return type;
    }

    public void setType(ClassificationTypeEnum type) {
        this.type = type;
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
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.classificationDate);
        hash = 47 * hash + Objects.hashCode(this.cultivation);
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + Float.floatToIntBits(this.weight);
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
        final Classification other = (Classification) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.classificationDate, other.classificationDate)) {
            return false;
        }
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        return true;
    }

}
