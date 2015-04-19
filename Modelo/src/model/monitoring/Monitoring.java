/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Monitoring implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private long number;    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date monitoringDate;    
    @ManyToOne(optional = false)
    private Farm farm;

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public long getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    public Date getMonitoringDate() {
        return monitoringDate;
    }

    /**
     *
     * @param monitoringDate
     */
    public void setMonitoringDate(Date monitoringDate) {
        this.monitoringDate = monitoringDate;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + (int) (this.number ^ (this.number >>> 32));
        hash = 67 * hash + Objects.hashCode(this.monitoringDate);
        hash = 67 * hash + Objects.hashCode(this.farm);
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
        final Monitoring other = (Monitoring) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.monitoringDate, other.monitoringDate)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        return true;
    }
    
}
