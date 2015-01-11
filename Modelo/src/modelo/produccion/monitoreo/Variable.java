/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

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
public class Variable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, unique = true)
    private String abreviacion;
    @Enumerated(EnumType.STRING)
    private TipoDeValoracion tipoDeValoracion;

    public Variable() {
    }

    public Variable(String nombre, String abreviacion, TipoDeValoracion tipoDeValoracion) {
        this.nombre = nombre;
        this.abreviacion = abreviacion;
        this.tipoDeValoracion = tipoDeValoracion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public TipoDeValoracion getTipoDeValoracion() {
        return tipoDeValoracion;
    }

    public void setTipoDeValoracion(TipoDeValoracion tipoDeValoracion) {
        this.tipoDeValoracion = tipoDeValoracion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.abreviacion);
        hash = 59 * hash + Objects.hashCode(this.tipoDeValoracion);
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
        final Variable other = (Variable) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.abreviacion, other.abreviacion)) {
            return false;
        }
        if (this.tipoDeValoracion != other.tipoDeValoracion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + ": " + abreviacion + " " + tipoDeValoracion;
    }

}
