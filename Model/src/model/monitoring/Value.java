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

    public void setValue(Object value) {
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
}
