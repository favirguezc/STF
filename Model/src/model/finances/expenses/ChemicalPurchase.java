/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.expenses;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import model.administration.Farm;
import model.applications.Chemical;
import model.finances.cash.CashConcept;

/**
 *
 * @author John Fredy
 */
@Entity
public class ChemicalPurchase implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    Date purchaseDate;
    @ManyToOne(optional = false)
    Farm farm;
    @ManyToOne(optional = false)
    Chemical chemical;
    @Column(nullable = false)
    float price;
    @Column(nullable = false)
    float quantity;
    @OneToOne(cascade = CascadeType.ALL)
    private CashConcept asociatedConcept;

    public ChemicalPurchase() {
    }

    public ChemicalPurchase(Date purchaseDate, Farm farm, Chemical chemical, float price, float quantity) {
        this.purchaseDate = purchaseDate;
        this.farm = farm;
        this.chemical = chemical;
        this.price = price;
        this.quantity = quantity;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Chemical getChemical() {
        return chemical;
    }

    public void setChemical(Chemical chemical) {
        this.chemical = chemical;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    
    public float getTotal(){
        return this.quantity * this.price;
    }
    
    public void add(ChemicalPurchase c) {
        //Please insert code here.
    }

    public CashConcept getAsociatedConcept() {
        return asociatedConcept;
    }

    public void setAsociatedConcept(CashConcept asociatedConcept) {
        this.asociatedConcept = asociatedConcept;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 61 * hash + Objects.hashCode(this.purchaseDate);
        hash = 61 * hash + Objects.hashCode(this.farm);
        hash = 61 * hash + Objects.hashCode(this.chemical);
        hash = 61 * hash + Float.floatToIntBits(this.price);
        hash = 61 * hash + Float.floatToIntBits(this.quantity);
        hash = 61 * hash + Objects.hashCode(this.asociatedConcept);
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
        final ChemicalPurchase other = (ChemicalPurchase) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.purchaseDate, other.purchaseDate)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        if (!Objects.equals(this.chemical, other.chemical)) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity) != Float.floatToIntBits(other.quantity)) {
            return false;
        }
        if (!Objects.equals(this.asociatedConcept, other.asociatedConcept)) {
            return false;
        }
        return true;
    }
}
