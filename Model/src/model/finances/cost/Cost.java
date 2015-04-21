/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.finances.cost;

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
import model.administration.ModuleClass;

/**
 *
 * @author John Fredy
 */
@Entity
public class Cost implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date costDate;
    @Column(nullable = false)
    private CostTypeEnum type;
    @Column
    private String subType; //enum(?)
    @Column(nullable = false)
    private String name;
    @Column
    private CostItemEnum item;
    @Column(nullable = false)
    private float quantity;
    @Column
    private float unitPrice;
    @ManyToOne(optional = false)
    private ModuleClass moduleObject;

    public Cost() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCostDate() {
        return costDate;
    }

    public void setCostDate(Date costDate) {
        this.costDate = costDate;
    }

    public CostTypeEnum getType() {
        return type;
    }

    public void setType(CostTypeEnum type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CostItemEnum getItem() {
        return item;
    }

    public void setItem(CostItemEnum item) {
        this.item = item;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public float getTotalPrice(){
        return quantity * unitPrice;
    }
    
    public ModuleClass getModuleObject() {
        return moduleObject;
    }

    public void setModuleObject(ModuleClass moduleObject) {
        this.moduleObject = moduleObject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 13 * hash + Objects.hashCode(this.costDate);
        hash = 13 * hash + Objects.hashCode(this.type);
        hash = 13 * hash + Objects.hashCode(this.subType);
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.item);
        hash = 13 * hash + Float.floatToIntBits(this.quantity);
        hash = 13 * hash + Float.floatToIntBits(this.unitPrice);
        hash = 13 * hash + Objects.hashCode(this.moduleObject);
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
        final Cost other = (Cost) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.costDate, other.costDate)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.subType, other.subType)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.item != other.item) {
            return false;
        }
        if (Float.floatToIntBits(this.quantity) != Float.floatToIntBits(other.quantity)) {
            return false;
        }
        if (Float.floatToIntBits(this.unitPrice) != Float.floatToIntBits(other.unitPrice)) {
            return false;
        }
        if (!Objects.equals(this.moduleObject, other.moduleObject)) {
            return false;
        }
        return true;
    }

    
}
