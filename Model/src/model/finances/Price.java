/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author John Fredy
 */
@Entity
public class Price implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(unique = true,nullable = false)
    String item;
    @Column(nullable = false)
    float priceValue;

    public Price() {
    }

    public Price(String item, float priceValue) {
        this.item = item;
        this.priceValue = priceValue;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public float getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(float priceValue) {
        this.priceValue = priceValue;
    }    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 83 * hash + Objects.hashCode(this.item);
        hash = 83 * hash + Float.floatToIntBits(this.priceValue);
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
        final Price other = (Price) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        if (Float.floatToIntBits(this.priceValue) != Float.floatToIntBits(other.priceValue)) {
            return false;
        }
        return true;
    }
    
}
