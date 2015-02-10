/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.variablesClimaticas;

import java.io.Serializable;
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
public class Termometro implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nombre;
    private long numeroDeSerie;
    @ManyToOne(optional = false)
    private Modulo modulo;

    /**
     *
     */
    public Termometro() {
    }

    /**
     *
     * @param nombre
     * @param numeroDeSerie
     * @param modulo
     */
    public Termometro(String nombre, long numeroDeSerie, Modulo modulo) {
        this.nombre = nombre;
        this.numeroDeSerie = numeroDeSerie;
        this.modulo = modulo;
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
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public long getNumeroDeSerie() {
        return numeroDeSerie;
    }

    /**
     *
     * @param numeroDeSerie
     */
    public void setNumeroDeSerie(long numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    /**
     *
     * @return
     */
    public Modulo getModulo() {
        return modulo;
    }

    /**
     *
     * @param modulo
     */
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
}
