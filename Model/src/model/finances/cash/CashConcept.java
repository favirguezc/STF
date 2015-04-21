/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.cash;

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
import javax.persistence.Transient;

/**
 *
 * @author JohnFredy
 */
@Entity
public class CashConcept implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date conceptDate;
    @Column(nullable = false)
    private String description;
    @Column
    private boolean income;
    @Column(nullable = false)
    private float conceptValue;
    @ManyToOne(optional = false)
    private Cash cash;
    @Transient
    private float balance;
    
    public CashConcept() {
    }

    public CashConcept(Date conceptDate, String description, boolean income, float conceptValue, Cash cash) {
        this.conceptDate = conceptDate;
        this.description = description;
        this.income = income;
        this.conceptValue = conceptValue;
        this.cash = cash;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getConceptDate() {
        return conceptDate;
    }

    public void setConceptDate(Date conceptDate) {
        this.conceptDate = conceptDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public float getConceptValue() {
        return conceptValue;
    }

    public void setConceptValue(float conceptValue) {
        this.conceptValue = conceptValue;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = 0;
        if(income){
            this.balance = balance + conceptValue;
        }
        else{
            this.balance = balance - conceptValue;
        }
    }
    
    @Override
    public String toString() {
        return conceptDate + "  " + description + "en " + cash;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.conceptDate);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + (this.income ? 1 : 0);
        hash = 37 * hash + Float.floatToIntBits(this.conceptValue);
        hash = 37 * hash + Objects.hashCode(this.cash);
        hash = 37 * hash + Float.floatToIntBits(this.balance);
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
        final CashConcept other = (CashConcept) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.conceptDate, other.conceptDate)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (this.income != other.income) {
            return false;
        }
        if (Float.floatToIntBits(this.conceptValue) != Float.floatToIntBits(other.conceptValue)) {
            return false;
        }
        if (!Objects.equals(this.cash, other.cash)) {
            return false;
        }
        if (Float.floatToIntBits(this.balance) != Float.floatToIntBits(other.balance)) {
            return false;
        }
        return true;
    }
    
    
}