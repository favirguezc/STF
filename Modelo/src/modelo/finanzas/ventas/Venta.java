/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas.ventas;

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
import modelo.produccion.administracion.Persona;

/**
 *
 * @author JohnFredy
 */
@Entity
public class Venta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaVenta;
    @ManyToOne(optional = false)
    private Persona cliente;
    @Column
    private float extraGramos;
    @Column
    private float primeraGramos;
    @Column
    private float segundaGramos;
    @Column
    private float terceraGramos;
    @Column
    private float cuartaGramos;
    @Column
    private float quintaGramos;
    @Column
    private float extraPrecioUnid;
    @Column
    private float primeraPrecioUnid;
    @Column
    private float segundaPrecioUnid;
    @Column
    private float terceraPrecioUnid;
    @Column
    private float cuartaPrecioUnid;
    @Column
    private float quintaPrecioUnid;
    /*
    private float extraPrecioTotal;
    private float primeraPrecioTotal;
    private float segundaPrecioTotal;
    private float terceraPrecioTotal;
    private float cuartaPrecioTotal;
    private float quintaPrecioTotal;
    */

    /**
     *
     */
    public Venta() {
    }
    
    /**
     *
     * @param fechaVenta
     * @param cliente
     * @param extraGramos
     * @param primeraGramos
     * @param segundaGramos
     * @param terceraGramos
     * @param cuartaGramos
     * @param quintaGramos
     * @param extraPrecioUnid
     * @param primeraPrecioUnid
     * @param segundaPrecioUnid
     * @param terceraPrecioUnid
     * @param cuartaPrecioUnid
     * @param quintaPrecioUnid
     */
    public Venta(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos, 
            float extraPrecioUnid, float primeraPrecioUnid, float segundaPrecioUnid,
            float terceraPrecioUnid, float cuartaPrecioUnid, float quintaPrecioUnid) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.extraGramos = extraGramos;
        this.primeraGramos = primeraGramos;
        this.segundaGramos = segundaGramos;
        this.terceraGramos = terceraGramos;
        this.cuartaGramos = cuartaGramos;
        this.quintaGramos = quintaGramos;
        this.extraPrecioUnid = extraPrecioUnid;
        this.primeraPrecioUnid = primeraPrecioUnid;
        this.segundaPrecioUnid = segundaPrecioUnid;
        this.terceraPrecioUnid = terceraPrecioUnid;
        this.cuartaPrecioUnid = cuartaPrecioUnid;
        this.quintaPrecioUnid = quintaPrecioUnid;
    }

    /**
     *
     * @param fechaVenta
     * @param cliente
     * @param extraGramos
     * @param primeraGramos
     * @param segundaGramos
     * @param terceraGramos
     * @param cuartaGramos
     * @param quintaGramos
     */
    public Venta(Date fechaVenta, Persona cliente, float extraGramos, float primeraGramos,
            float segundaGramos, float terceraGramos, float cuartaGramos, float quintaGramos) {
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.extraGramos = extraGramos;
        this.primeraGramos = primeraGramos;
        this.segundaGramos = segundaGramos;
        this.terceraGramos = terceraGramos;
        this.cuartaGramos = cuartaGramos;
        this.quintaGramos = quintaGramos;
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     *
     * @return
     */
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     *
     * @param fechaVenta
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     *
     * @return
     */
    public Persona getCliente() {
        return cliente;
    }

    /**
     *
     * @param cliente
     */
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    /**
     *
     * @return
     */
    public float getExtraGramos() {
        return extraGramos;
    }

    /**
     *
     * @param extraGramos
     */
    public void setExtraGramos(float extraGramos) {
        this.extraGramos = extraGramos;
    }

    /**
     *
     * @return
     */
    public float getPrimeraGramos() {
        return primeraGramos;
    }

    /**
     *
     * @param primeraGramos
     */
    public void setPrimeraGramos(float primeraGramos) {
        this.primeraGramos = primeraGramos;
    }

    /**
     *
     * @return
     */
    public float getSegundaGramos() {
        return segundaGramos;
    }

    /**
     *
     * @param segundaGramos
     */
    public void setSegundaGramos(float segundaGramos) {
        this.segundaGramos = segundaGramos;
    }

    /**
     *
     * @return
     */
    public float getTerceraGramos() {
        return terceraGramos;
    }

    /**
     *
     * @param terceraGramos
     */
    public void setTerceraGramos(float terceraGramos) {
        this.terceraGramos = terceraGramos;
    }

    /**
     *
     * @return
     */
    public float getCuartaGramos() {
        return cuartaGramos;
    }

    /**
     *
     * @param cuartaGramos
     */
    public void setCuartaGramos(float cuartaGramos) {
        this.cuartaGramos = cuartaGramos;
    }

    /**
     *
     * @return
     */
    public float getQuintaGramos() {
        return quintaGramos;
    }

    /**
     *
     * @param quintaGramos
     */
    public void setQuintaGramos(float quintaGramos) {
        this.quintaGramos = quintaGramos;
    }

    /**
     *
     * @return
     */
    public float getExtraPrecioUnid() {
        return extraPrecioUnid;
    }

    /**
     *
     * @param extraPrecioUnid
     */
    public void setExtraPrecioUnid(float extraPrecioUnid) {
        this.extraPrecioUnid = extraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getPrimeraPrecioUnid() {
        return primeraPrecioUnid;
    }

    /**
     *
     * @param primeraPrecioUnid
     */
    public void setPrimeraPrecioUnid(float primeraPrecioUnid) {
        this.primeraPrecioUnid = primeraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getSegundaPrecioUnid() {
        return segundaPrecioUnid;
    }

    /**
     *
     * @param segundaPrecioUnid
     */
    public void setSegundaPrecioUnid(float segundaPrecioUnid) {
        this.segundaPrecioUnid = segundaPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getTerceraPrecioUnid() {
        return terceraPrecioUnid;
    }

    /**
     *
     * @param terceraPrecioUnid
     */
    public void setTerceraPrecioUnid(float terceraPrecioUnid) {
        this.terceraPrecioUnid = terceraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getCuartaPrecioUnid() {
        return cuartaPrecioUnid;
    }

    /**
     *
     * @param cuartaPrecioUnid
     */
    public void setCuartaPrecioUnid(float cuartaPrecioUnid) {
        this.cuartaPrecioUnid = cuartaPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getQuintaPrecioUnid() {
        return quintaPrecioUnid;
    }

    /**
     *
     * @param quintaPrecioUnid
     */
    public void setQuintaPrecioUnid(float quintaPrecioUnid) {
        this.quintaPrecioUnid = quintaPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getExtraPrecioTotal() {
        return extraGramos * extraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getPrimeraPrecioTotal() {
        return primeraGramos * primeraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getSegundaPrecioTotal() {
        return segundaGramos * segundaPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getTerceraPrecioTotal() {
        return terceraGramos * terceraPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getCuartaPrecioTotal() {
        return cuartaGramos * cuartaPrecioUnid;
    }

    /**
     *
     * @return
     */
    public float getQuintaPrecioTotal() {
        return quintaGramos * quintaPrecioUnid;
    }
    
    /**
     *
     * @return
     */
    public float getCantidadTotal (){
        return extraGramos + primeraGramos + segundaGramos + 
                terceraGramos + cuartaGramos + quintaGramos;
    }
    
    /**
     *
     * @return
     */
    public float getVentaTotal (){
        return getExtraPrecioTotal() + getPrimeraPrecioTotal() + 
               getSegundaPrecioTotal() + getTerceraPrecioTotal() + 
                getCuartaPrecioTotal() + getQuintaPrecioTotal();
    }
    
    /**
     *
     * @param v
     */
    public void sumar(Venta v) {
        this.extraGramos += v.extraGramos;
        this.primeraGramos += v.primeraGramos;
        this.segundaGramos += v.segundaGramos;
        this.terceraGramos += v.terceraGramos;
        this.cuartaGramos += v.cuartaGramos;
        this.quintaGramos += v.quintaGramos;
        this.extraPrecioUnid += v.extraPrecioUnid;
        this.primeraPrecioUnid += v.primeraPrecioUnid;
        this.segundaPrecioUnid += v.segundaPrecioUnid;
        this.terceraPrecioUnid += v.terceraPrecioUnid;
        this.cuartaPrecioUnid += v.cuartaPrecioUnid;
        this.quintaPrecioUnid += v.quintaPrecioUnid;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 89 * hash + Objects.hashCode(this.fechaVenta);
        hash = 89 * hash + Objects.hashCode(this.cliente);
        hash = 89 * hash + Float.floatToIntBits(this.extraGramos);
        hash = 89 * hash + Float.floatToIntBits(this.primeraGramos);
        hash = 89 * hash + Float.floatToIntBits(this.segundaGramos);
        hash = 89 * hash + Float.floatToIntBits(this.terceraGramos);
        hash = 89 * hash + Float.floatToIntBits(this.cuartaGramos);
        hash = 89 * hash + Float.floatToIntBits(this.quintaGramos);
        hash = 89 * hash + Float.floatToIntBits(this.extraPrecioUnid);
        hash = 89 * hash + Float.floatToIntBits(this.primeraPrecioUnid);
        hash = 89 * hash + Float.floatToIntBits(this.segundaPrecioUnid);
        hash = 89 * hash + Float.floatToIntBits(this.terceraPrecioUnid);
        hash = 89 * hash + Float.floatToIntBits(this.cuartaPrecioUnid);
        hash = 89 * hash + Float.floatToIntBits(this.quintaPrecioUnid);
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
        final Venta other = (Venta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.fechaVenta, other.fechaVenta)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (Float.floatToIntBits(this.extraGramos) != Float.floatToIntBits(other.extraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.primeraGramos) != Float.floatToIntBits(other.primeraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.segundaGramos) != Float.floatToIntBits(other.segundaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.terceraGramos) != Float.floatToIntBits(other.terceraGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.cuartaGramos) != Float.floatToIntBits(other.cuartaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.quintaGramos) != Float.floatToIntBits(other.quintaGramos)) {
            return false;
        }
        if (Float.floatToIntBits(this.extraPrecioUnid) != Float.floatToIntBits(other.extraPrecioUnid)) {
            return false;
        }
        if (Float.floatToIntBits(this.primeraPrecioUnid) != Float.floatToIntBits(other.primeraPrecioUnid)) {
            return false;
        }
        if (Float.floatToIntBits(this.segundaPrecioUnid) != Float.floatToIntBits(other.segundaPrecioUnid)) {
            return false;
        }
        if (Float.floatToIntBits(this.terceraPrecioUnid) != Float.floatToIntBits(other.terceraPrecioUnid)) {
            return false;
        }
        if (Float.floatToIntBits(this.cuartaPrecioUnid) != Float.floatToIntBits(other.cuartaPrecioUnid)) {
            return false;
        }
        if (Float.floatToIntBits(this.quintaPrecioUnid) != Float.floatToIntBits(other.quintaPrecioUnid)) {
            return false;
        }
        return true;
    }
}
