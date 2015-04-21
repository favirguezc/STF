/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.sales;

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
    //ALL OF THIS WILL CHANGE IN ORDER TO USE THE VARIETY CLASS
    @Column
    private float extraGrams;
    @Column
    private float firstGrams;
    @Column
    private float secondGrams;
    @Column
    private float thirdGrams;
    @Column
    private float fourthGrams;
    @Column
    private float fifthGrams;
    @Column
    private float extraUnitPrice;
    @Column
    private float firstUnitPrice;
    @Column
    private float secondUnitPrice;
    @Column
    private float thirdUnitPrice;
    @Column
    private float fourthUnitPrice;
    @Column
    private float fifthUnitPrice;
    /*
    private float extraPrecioTotal;
    private float primeraPrecioTotal;
    private float segundaPrecioTotal;
    private float terceraPrecioTotal;
    private float cuartaPrecioTotal;
    private float quintaPrecioTotal;
    */

    /**
     *
     */
    public Sale() {
    }
    
    /**
     *
     * @param saleDate
     * @param customer
     * @param extraGrams
     * @param firstGrams
     * @param secondGrams
     * @param thirdGrams
     * @param fourthGrams
     * @param fifthGrams
     * @param extraUnitPirce
     * @param firstUnitPrice
     * @param secondUnitPrice
     * @param thirdUnitPrice
     * @param fourthUnitPrice
     * @param fifthUnitPrice
     */
    public Sale(Date saleDate, Person customer, float extraGrams, float firstGrams,
            float secondGrams, float thirdGrams, float fourthGrams, float fifthGrams, 
            float extraUnitPirce, float firstUnitPrice, float secondUnitPrice,
            float thirdUnitPrice, float fourthUnitPrice, float fifthUnitPrice) {
        this.saleDate = saleDate;
        this.customer = customer;
        this.extraGrams = extraGrams;
        this.firstGrams = firstGrams;
        this.secondGrams = secondGrams;
        this.thirdGrams = thirdGrams;
        this.fourthGrams = fourthGrams;
        this.fifthGrams = fifthGrams;
        this.extraUnitPrice = extraUnitPirce;
        this.firstUnitPrice = firstUnitPrice;
        this.secondUnitPrice = secondUnitPrice;
        this.thirdUnitPrice = thirdUnitPrice;
        this.fourthUnitPrice = fourthUnitPrice;
        this.fifthUnitPrice = fifthUnitPrice;
    }

    /**
     *
     * @param saleDate
     * @param customer
     * @param extraGrams
     * @param firstGrams
     * @param secondGrams
     * @param thirdGrams
     * @param fourthGrams
     * @param fifthGrams
     */
    public Sale(Date saleDate, Person customer, float extraGrams, float firstGrams,
            float secondGrams, float thirdGrams, float fourthGrams, float fifthGrams) {
        this.saleDate = saleDate;
        this.customer = customer;
        this.extraGrams = extraGrams;
        this.firstGrams = firstGrams;
        this.secondGrams = secondGrams;
        this.thirdGrams = thirdGrams;
        this.fourthGrams = fourthGrams;
        this.fifthGrams = fifthGrams;
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
    public float getExtraGrams() {
        return extraGrams;
    }

    /**
     *
     * @param extraGrams
     */
    public void setExtraGrams(float extraGrams) {
        this.extraGrams = extraGrams;
    }

    /**
     *
     * @return
     */
    public float getFirstGrams() {
        return firstGrams;
    }

    /**
     *
     * @param firstGrams
     */
    public void setFirstGrams(float firstGrams) {
        this.firstGrams = firstGrams;
    }

    /**
     *
     * @return
     */
    public float getSecondGrams() {
        return secondGrams;
    }

    /**
     *
     * @param secondGrams
     */
    public void setSecondGrams(float secondGrams) {
        this.secondGrams = secondGrams;
    }

    /**
     *
     * @return
     */
    public float getThirdGrams() {
        return thirdGrams;
    }

    /**
     *
     * @param thirdGrams
     */
    public void setThirdGrams(float thirdGrams) {
        this.thirdGrams = thirdGrams;
    }

    /**
     *
     * @return
     */
    public float getFourthGrams() {
        return fourthGrams;
    }

    /**
     *
     * @param fourthGrams
     */
    public void setFourthGrams(float fourthGrams) {
        this.fourthGrams = fourthGrams;
    }

    /**
     *
     * @return
     */
    public float getFifthGrams() {
        return fifthGrams;
    }

    /**
     *
     * @param fifthGrams
     */
    public void setFifthGrams(float fifthGrams) {
        this.fifthGrams = fifthGrams;
    }

    /**
     *
     * @return
     */
    public float getExtraUnitPrice() {
        return extraUnitPrice;
    }

    /**
     *
     * @param extraUnitPrice
     */
    public void setExtraUnitPrice(float extraUnitPrice) {
        this.extraUnitPrice = extraUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFirstUnitPrice() {
        return firstUnitPrice;
    }

    /**
     *
     * @param firstUnitPrice
     */
    public void setFirstUnitPrice(float firstUnitPrice) {
        this.firstUnitPrice = firstUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getSecondUnitPrice() {
        return secondUnitPrice;
    }

    /**
     *
     * @param secondUnitPrice
     */
    public void setSecondUnitPrice(float secondUnitPrice) {
        this.secondUnitPrice = secondUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getThirdUnitPrice() {
        return thirdUnitPrice;
    }

    /**
     *
     * @param thirdUnitPrice
     */
    public void setThirdUnitPrice(float thirdUnitPrice) {
        this.thirdUnitPrice = thirdUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFourthUnitPrice() {
        return fourthUnitPrice;
    }

    /**
     *
     * @param fourthUnitPrice
     */
    public void setFourthUnitPrice(float fourthUnitPrice) {
        this.fourthUnitPrice = fourthUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFifthUnitPrice() {
        return fifthUnitPrice;
    }

    /**
     *
     * @param fifthUnitPrice
     */
    public void setFifthUnitPrice(float fifthUnitPrice) {
        this.fifthUnitPrice = fifthUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getExtraTotalPrice() {
        return extraGrams * extraUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFirstTotalPrice() {
        return firstGrams * firstUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getSecondTotalPrice() {
        return secondGrams * secondUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getThirdTotalPrice() {
        return thirdGrams * thirdUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFourthTotalPrice() {
        return fourthGrams * fourthUnitPrice;
    }

    /**
     *
     * @return
     */
    public float getFifthTotalPrice() {
        return fifthGrams * fifthUnitPrice;
    }
    
    /**
     *
     * @return
     */
    public float getTotalQuality (){
        return extraGrams + firstGrams + secondGrams + 
                thirdGrams + fourthGrams + fifthGrams;
    }
    
    /**
     *
     * @return
     */
    public float getSaleTotal (){
        return getExtraTotalPrice() + getFirstTotalPrice() + 
               getSecondTotalPrice() + getThirdTotalPrice() + 
                getFourthTotalPrice() + getFifthTotalPrice();
    }
    
    /**
     *
     * @param s
     */
    public void sumar(Sale s) {
        this.extraGrams += s.extraGrams;
        this.firstGrams += s.firstGrams;
        this.secondGrams += s.secondGrams;
        this.thirdGrams += s.thirdGrams;
        this.fourthGrams += s.fourthGrams;
        this.fifthGrams += s.fifthGrams;
        this.extraUnitPrice += s.extraUnitPrice;
        this.firstUnitPrice += s.firstUnitPrice;
        this.secondUnitPrice += s.secondUnitPrice;
        this.thirdUnitPrice += s.thirdUnitPrice;
        this.fourthUnitPrice += s.fourthUnitPrice;
        this.fifthUnitPrice += s.fifthUnitPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.saleDate);
        hash = 89 * hash + Objects.hashCode(this.customer);
        hash = 89 * hash + Objects.hashCode(this.farm);
        hash = 89 * hash + Float.floatToIntBits(this.extraGrams);
        hash = 89 * hash + Float.floatToIntBits(this.firstGrams);
        hash = 89 * hash + Float.floatToIntBits(this.secondGrams);
        hash = 89 * hash + Float.floatToIntBits(this.thirdGrams);
        hash = 89 * hash + Float.floatToIntBits(this.fourthGrams);
        hash = 89 * hash + Float.floatToIntBits(this.fifthGrams);
        hash = 89 * hash + Float.floatToIntBits(this.extraUnitPrice);
        hash = 89 * hash + Float.floatToIntBits(this.firstUnitPrice);
        hash = 89 * hash + Float.floatToIntBits(this.secondUnitPrice);
        hash = 89 * hash + Float.floatToIntBits(this.thirdUnitPrice);
        hash = 89 * hash + Float.floatToIntBits(this.fourthUnitPrice);
        hash = 89 * hash + Float.floatToIntBits(this.fifthUnitPrice);
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
        if (Float.floatToIntBits(this.extraGrams) != Float.floatToIntBits(other.extraGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.firstGrams) != Float.floatToIntBits(other.firstGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.secondGrams) != Float.floatToIntBits(other.secondGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.thirdGrams) != Float.floatToIntBits(other.thirdGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.fourthGrams) != Float.floatToIntBits(other.fourthGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.fifthGrams) != Float.floatToIntBits(other.fifthGrams)) {
            return false;
        }
        if (Float.floatToIntBits(this.extraUnitPrice) != Float.floatToIntBits(other.extraUnitPrice)) {
            return false;
        }
        if (Float.floatToIntBits(this.firstUnitPrice) != Float.floatToIntBits(other.firstUnitPrice)) {
            return false;
        }
        if (Float.floatToIntBits(this.secondUnitPrice) != Float.floatToIntBits(other.secondUnitPrice)) {
            return false;
        }
        if (Float.floatToIntBits(this.thirdUnitPrice) != Float.floatToIntBits(other.thirdUnitPrice)) {
            return false;
        }
        if (Float.floatToIntBits(this.fourthUnitPrice) != Float.floatToIntBits(other.fourthUnitPrice)) {
            return false;
        }
        if (Float.floatToIntBits(this.fifthUnitPrice) != Float.floatToIntBits(other.fifthUnitPrice)) {
            return false;
        }
        return true;
    }
}
