/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.monitoring;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author fredy
 */
@Entity
public class MonitorableParameter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String abbreviation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValuationTypeEnum valuationType;

    /**
     *
     */
    public MonitorableParameter() {
    }

    /**
     *
     * @param nombre
     * @param abreviacion
     * @param tipoDeValoracion
     */
    public MonitorableParameter(String nombre, String abreviacion, ValuationTypeEnum tipoDeValoracion) {
        this.name = nombre;
        this.abbreviation = abreviacion;
        this.valuationType = tipoDeValoracion;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     *
     * @param abbreviation
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     *
     * @return
     */
    public ValuationTypeEnum getValuationType() {
        return valuationType;
    }

    /**
     *
     * @param valuationType
     */
    public void setValuationType(ValuationTypeEnum valuationType) {
        this.valuationType = valuationType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.abbreviation);
        hash = 59 * hash + Objects.hashCode(this.valuationType);
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
        final MonitorableParameter other = (MonitorableParameter) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.abbreviation, other.abbreviation)) {
            return false;
        }
        if (this.valuationType != other.valuationType) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
