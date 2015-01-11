/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.monitoreo;

import java.io.Serializable;
import java.util.Date;
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

    public Monitoreo() {
    }

    public Monitoreo(long numeroDeMonitoreo, Date fecha) {
        this.numeroDeMonitoreo = numeroDeMonitoreo;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNumeroDeMonitoreo() {
        return numeroDeMonitoreo;
    }

    public void setNumeroDeMonitoreo(long numeroDeMonitoreo) {
        this.numeroDeMonitoreo = numeroDeMonitoreo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
