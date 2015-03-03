/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.administracion;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author fredy
 */
@Entity
public class Finca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne(optional = false)
    private Vereda vereda;
    private float area;
    private float altitud;
    private List<Coordenada> coordenadas;
    @ManyToOne(optional = false)
    private Persona propietario;

    public Finca() {
    }

    public Finca(String nombre, Vereda vereda, float area, float altitud, List<Coordenada> coordenadas) {
        this.nombre = nombre;
        this.vereda = vereda;
        this.area = area;
        this.altitud = altitud;
        this.coordenadas = coordenadas;
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

    public Vereda getVereda() {
        return vereda;
    }

    public void setVereda(Vereda vereda) {
        this.vereda = vereda;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getAltitud() {
        return altitud;
    }

    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.vereda);
        hash = 97 * hash + Float.floatToIntBits(this.area);
        hash = 97 * hash + Float.floatToIntBits(this.altitud);
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
        final Finca other = (Finca) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.vereda, other.vereda)) {
            return false;
        }
        if (Float.floatToIntBits(this.area) != Float.floatToIntBits(other.area)) {
            return false;
        }
        if (Float.floatToIntBits(this.altitud) != Float.floatToIntBits(other.altitud)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
