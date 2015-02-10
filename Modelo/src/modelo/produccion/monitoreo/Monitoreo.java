/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author fredy
 */
@Entity
public class Monitoreo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique = true)
    private long numeroDeMonitoreo;    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    /**
     *
     */
    public Monitoreo() {
    }

    /**
     *
     * @param numeroDeMonitoreo
     * @param fecha
     */
    public Monitoreo(long numeroDeMonitoreo, Date fecha) {
        this.numeroDeMonitoreo = numeroDeMonitoreo;
        this.fecha = fecha;
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
    public long getNumeroDeMonitoreo() {
        return numeroDeMonitoreo;
    }

    /**
     *
     * @param numeroDeMonitoreo
     */
    public void setNumeroDeMonitoreo(long numeroDeMonitoreo) {
        this.numeroDeMonitoreo = numeroDeMonitoreo;
    }

    /**
     *
     * @return
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + (int) (this.numeroDeMonitoreo ^ (this.numeroDeMonitoreo >>> 32));
        hash = 73 * hash + Objects.hashCode(this.fecha);
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
        final Monitoreo other = (Monitoreo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.numeroDeMonitoreo != other.numeroDeMonitoreo) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return true;
    }
    
}
