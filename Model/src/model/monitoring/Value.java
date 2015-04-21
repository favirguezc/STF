/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.monitoring;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author fredy
 */
@Embeddable
public class Value implements Serializable {

    private int count;
    private String relation;
    private RiskEnum risk;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        setValue(count);
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        setValue(relation);
    }

    public RiskEnum getRisk() {
        return risk;
    }

    public void setRisk(RiskEnum risk) {
        setValue(risk);
    }

    private void setValue(Object value) {
        if(value==null){
            return;
        }
        if (value instanceof Integer) {
            count = (Integer) value;
        } else if (value instanceof RiskEnum) {
            risk = (RiskEnum) value;
        } else if (value instanceof String) {
            relation = (String) value;
        }
    }

    public Object get() {
        if (relation != null && !relation.equals("")) {
            return relation;
        }
        if (risk != null) {
            return risk;
        }
        return count;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}
