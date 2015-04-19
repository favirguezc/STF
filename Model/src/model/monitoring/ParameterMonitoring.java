/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.monitoring;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import model.administration.Cultivation;

/**
 *
 * @author fredy
 */
@Entity
public class ParameterMonitoring implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Monitoring monitoring;
    @ManyToOne(optional = false)
    private Cultivation cultivation;
    @ManyToOne(optional = false)
    private MonitorableParameter parameter;
    private Value monitoringValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Monitoring getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(Monitoring monitoring) {
        this.monitoring = monitoring;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void setCultivation(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public MonitorableParameter getParameter() {
        return parameter;
    }

    public void setParameter(MonitorableParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.monitoring);
        hash = 47 * hash + Objects.hashCode(this.cultivation);
        hash = 47 * hash + Objects.hashCode(this.parameter);
        hash = 47 * hash + Objects.hashCode(this.monitoringValue);
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
        final ParameterMonitoring other = (ParameterMonitoring) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.monitoring, other.monitoring)) {
            return false;
        }
        if (!Objects.equals(this.cultivation, other.cultivation)) {
            return false;
        }
        if (!Objects.equals(this.parameter, other.parameter)) {
            return false;
        }
        if (!Objects.equals(this.monitoringValue, other.monitoringValue)) {
            return false;
        }
        return true;
    }
    
}
