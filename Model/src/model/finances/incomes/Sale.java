/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.incomes;

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
import model.administration.Person;

/**
 *
 * @author JohnFredy
 */
@Entity
public class Sale implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date saleDate;
    @ManyToOne(optional = false)
    private Person customer;
    @ManyToOne(optional = false)
    private Farm farm;
    @Column
    private float saleTotalValue;
    
    /**
     *
     */
    public Sale() {
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
    
    /**
     *
     * @return
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     *
     * @param saleDate
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     *
     * @return
     */
    public Person getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     */
    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    /**
     *
     * @return
     */
    public Farm getFarm() {
        return farm;
    }

    /**
     *
     * @param farm
     */
    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     */
    public float getSaleTotalValue() {
        return saleTotalValue;
    }

    /**
     *
     * @param saleTotalValue
     */
    public void setSaleTotalValue(float saleTotalValue) {
        this.saleTotalValue = saleTotalValue;
    }
    
    /**
     *
     * @param s
     */
    public void sumar(Sale s) {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.saleDate);
        hash = 89 * hash + Objects.hashCode(this.customer);
        hash = 89 * hash + Objects.hashCode(this.farm);
        hash = 59 * hash + Float.floatToIntBits(this.saleTotalValue);
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
        final Sale other = (Sale) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.saleDate, other.saleDate)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        if (Float.floatToIntBits(this.saleTotalValue) != Float.floatToIntBits(other.saleTotalValue)) {
            return false;
        }
        return true;
    }
}
