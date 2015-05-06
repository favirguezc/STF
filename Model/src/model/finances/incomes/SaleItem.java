/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.incomes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import model.crop.ClassificationTypeEnum;

/**
 *
 * @author John Fredy
 */
@Entity
public class SaleItem implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Sale sale;
    @Column(nullable = false)
    private ClassificationTypeEnum type;
    @Column(nullable = false)
    private float quantity;
    @Column(nullable = false)
    private float unitValue;

    public SaleItem() {
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public ClassificationTypeEnum getType() {
        return type;
    }

    public void setType(ClassificationTypeEnum type) {
        this.type = type;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(float unitValue) {
        this.unitValue = unitValue;
    }
    
    public float getTotalValue(){
        return quantity * unitValue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.sale);
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + Float.floatToIntBits(this.quantity);
        hash = 59 * hash + Float.floatToIntBits(this.unitValue);
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
        final SaleItem other = (SaleItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.sale, other.sale)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity) != Float.floatToIntBits(other.quantity)) {
            return false;
        }
        if (Float.floatToIntBits(this.unitValue) != Float.floatToIntBits(other.unitValue)) {
            return false;
        }
        return true;
    }
}
