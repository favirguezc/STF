/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.contabilidad;

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
public class Paquete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private int numeroFincasPermitidas;
    @Column(nullable = false)
    private int numeroTrabajadoresPermitidosPorFinca;
    @Column(nullable = false)
    private float precio;
    @Column(nullable = false)
    private int cantidadPeriodos;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPeriodo tipoPeriodo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroFincasPermitidas() {
        return numeroFincasPermitidas;
    }

    public void setNumeroFincasPermitidas(int numeroFincasPermitidas) {
        this.numeroFincasPermitidas = numeroFincasPermitidas;
    }

    public int getNumeroTrabajadoresPermitidosPorFinca() {
        return numeroTrabajadoresPermitidosPorFinca;
    }

    public void setNumeroTrabajadoresPermitidosPorFinca(int numeroTrabajadoresPermitidosPorFinca) {
        this.numeroTrabajadoresPermitidosPorFinca = numeroTrabajadoresPermitidosPorFinca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidadPeriodos() {
        return cantidadPeriodos;
    }

    public void setCantidadPeriodos(int cantidadPeriodos) {
        this.cantidadPeriodos = cantidadPeriodos;
    }

    public TipoPeriodo getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + this.numeroFincasPermitidas;
        hash = 17 * hash + this.numeroTrabajadoresPermitidosPorFinca;
        hash = 17 * hash + Float.floatToIntBits(this.precio);
        hash = 17 * hash + this.cantidadPeriodos;
        hash = 17 * hash + Objects.hashCode(this.tipoPeriodo);
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
        final Paquete other = (Paquete) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.numeroFincasPermitidas != other.numeroFincasPermitidas) {
            return false;
        }
        if (this.numeroTrabajadoresPermitidosPorFinca != other.numeroTrabajadoresPermitidosPorFinca) {
            return false;
        }
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (this.cantidadPeriodos != other.cantidadPeriodos) {
            return false;
        }
        if (this.tipoPeriodo != other.tipoPeriodo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
