/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.expenses;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Column;
import model.administration.Farm;
import model.administration.Person;

/**
 *
 * @author John Fredy
 */
@Entity
public class Payroll implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateUntil;
    @ManyToOne(optional = false)
    private Person worker;
    @Column(nullable = false)
    private float total;
    @ManyToOne(optional = false)
    private Farm farm;

    public Payroll() {
    }

    public Payroll(Date dateuntil, Person worker) {
        this.dateUntil = dateuntil;
        this.worker = worker;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    public Date getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(Date dateUntil) {
        this.dateUntil = dateUntil;
    }

    public Person getWorker() {
        return worker;
    }

    public void setWorker(Person worker) {
        this.worker = worker;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.dateUntil);
        hash = 71 * hash + Objects.hashCode(this.worker);
        hash = 71 * hash + Float.floatToIntBits(this.total);
        hash = 71 * hash + Objects.hashCode(this.farm);
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
        final Payroll other = (Payroll) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.dateUntil, other.dateUntil)) {
            return false;
        }
        if (!Objects.equals(this.worker, other.worker)) {
            return false;
        }
        if (Float.floatToIntBits(this.total) != Float.floatToIntBits(other.total)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        return true;
    }
    
    
}
