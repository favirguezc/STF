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
 * @author John Fredy
 */
@Entity
public class Payment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date paymentDate;
    @ManyToOne(optional = false)
    private Person customer;
    @Column(nullable = false)
    private PaymentMethodEnum paymentMethod;
    private BankEnum bank;
    @Column(nullable = false)
    private float paymentValue;
    @ManyToOne(optional = false)
    private Farm farm;
    @Column(nullable = false)
    private float usedValue;

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BankEnum getBank() {
        return bank;
    }

    public void setBank(BankEnum bank) {
        this.bank = bank;
    }

    public float getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(float paymentValue) {
        this.paymentValue = paymentValue;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public float getUsedValue() {
        return usedValue;
    }

    public void setUsedValue(float usedValue) {
        this.usedValue = usedValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.paymentDate);
        hash = 67 * hash + Objects.hashCode(this.customer);
        hash = 67 * hash + Objects.hashCode(this.paymentMethod);
        hash = 67 * hash + Objects.hashCode(this.bank);
        hash = 67 * hash + Float.floatToIntBits(this.paymentValue);
        hash = 67 * hash + Objects.hashCode(this.farm);
        hash = 67 * hash + Float.floatToIntBits(this.usedValue);
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
        final Payment other = (Payment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.paymentDate, other.paymentDate)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (this.paymentMethod != other.paymentMethod) {
            return false;
        }
        if (this.bank != other.bank) {
            return false;
        }
        if (Float.floatToIntBits(this.paymentValue) != Float.floatToIntBits(other.paymentValue)) {
            return false;
        }
        if (!Objects.equals(this.farm, other.farm)) {
            return false;
        }
        if (Float.floatToIntBits(this.usedValue) != Float.floatToIntBits(other.usedValue)) {
            return false;
        }
        return true;
    }
    
    
}
