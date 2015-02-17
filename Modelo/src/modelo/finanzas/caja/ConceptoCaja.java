/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.caja;

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
import javax.persistence.Transient;

/**
 *
 * @author JohnFredy
 */
@Entity
public class ConceptoCaja implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Column
    private String descripcion;
    @Column
    private boolean entrada;
    @Column
    private float valor;
    @ManyToOne
    private Caja caja;
    @Transient
    private float saldo;
    
    public ConceptoCaja() {
    }

    public ConceptoCaja(Date fecha, String descripcion, boolean entrada, float valor, Caja caja) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.entrada = entrada;
        this.valor = valor;
        this.caja = caja;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = 0;
        if(entrada){
            this.saldo = saldo + valor;
        }
        else{
            this.saldo = saldo - valor;
        }
    }
    
    @Override
    public String toString() {
        return fecha + "  " + descripcion + "en " + caja;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 37 * hash + Objects.hashCode(this.fecha);
        hash = 37 * hash + Objects.hashCode(this.descripcion);
        hash = 37 * hash + (this.entrada ? 1 : 0);
        hash = 37 * hash + Float.floatToIntBits(this.valor);
        hash = 37 * hash + Objects.hashCode(this.caja);
        hash = 37 * hash + Float.floatToIntBits(this.saldo);
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
        final ConceptoCaja other = (ConceptoCaja) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (this.entrada != other.entrada) {
            return false;
        }
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.caja, other.caja)) {
            return false;
        }
        if (Float.floatToIntBits(this.saldo) != Float.floatToIntBits(other.saldo)) {
            return false;
        }
        return true;
    }
    
    
}
