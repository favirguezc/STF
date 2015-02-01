/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import modelo.produccion.administracion.Modulo;

/**
 *
 * @author fredy
 */
@Entity
public class MonitoreoDeVariables implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Monitoreo monitoreo;
    @ManyToOne(optional = false)
    private Modulo modulo;
    @ManyToOne(optional = false)
    private Variable variable;
    private int conteo;
    private String relacion;
    private Riesgo riesgo;    

    public MonitoreoDeVariables() {        
    }

    public MonitoreoDeVariables(Monitoreo monitoreo, Modulo modulo, Variable variable, int conteo, String relacion, Riesgo riesgo) {
        this.monitoreo = monitoreo;
        this.modulo = modulo;
        this.variable = variable;
        this.conteo = conteo;
        this.relacion = relacion;
        this.riesgo = riesgo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Monitoreo getMonitoreo() {
        return monitoreo;
    }

    public void setMonitoreo(Monitoreo monitoreo) {
        this.monitoreo = monitoreo;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Riesgo getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(Riesgo riesgo) {
        this.riesgo = riesgo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 97 * hash + Objects.hashCode(this.monitoreo);
        hash = 97 * hash + Objects.hashCode(this.modulo);
        hash = 97 * hash + Objects.hashCode(this.variable);
        hash = 97 * hash + this.conteo;
        hash = 97 * hash + Objects.hashCode(this.relacion);
        hash = 97 * hash + Objects.hashCode(this.riesgo);
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
        final MonitoreoDeVariables other = (MonitoreoDeVariables) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.monitoreo, other.monitoreo)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        if (!Objects.equals(this.variable, other.variable)) {
            return false;
        }
        if (this.conteo != other.conteo) {
            return false;
        }
        if (!Objects.equals(this.relacion, other.relacion)) {
            return false;
        }
        if (this.riesgo != other.riesgo) {
            return false;
        }
        return true;
    }

}
