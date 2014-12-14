/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fredy
 */
@Entity
@Table(name = "temperatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temperatura.findAll", query = "SELECT t FROM Temperatura t"),
    @NamedQuery(name = "Temperatura.findById", query = "SELECT t FROM Temperatura t WHERE t.id = :id"),
    @NamedQuery(name = "Temperatura.findByFecha", query = "SELECT t FROM Temperatura t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Temperatura.findByHora", query = "SELECT t FROM Temperatura t WHERE t.hora = :hora"),
    @NamedQuery(name = "Temperatura.findByHumedad", query = "SELECT t FROM Temperatura t WHERE t.humedad = :humedad"),
    @NamedQuery(name = "Temperatura.findByPuntoderocio", query = "SELECT t FROM Temperatura t WHERE t.puntoderocio = :puntoderocio"),
    @NamedQuery(name = "Temperatura.findByTemperatura", query = "SELECT t FROM Temperatura t WHERE t.temperatura = :temperatura")})
public class Temperatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HUMEDAD")
    private Float humedad;
    @Column(name = "PUNTODEROCIO")
    private Float puntoderocio;
    @Column(name = "TEMPERATURA")
    private Float temperatura;

    public Temperatura() {
    }

    public Temperatura(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Float getHumedad() {
        return humedad;
    }

    public void setHumedad(Float humedad) {
        this.humedad = humedad;
    }

    public Float getPuntoderocio() {
        return puntoderocio;
    }

    public void setPuntoderocio(Float puntoderocio) {
        this.puntoderocio = puntoderocio;
    }

    public Float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Float temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temperatura)) {
            return false;
        }
        Temperatura other = (Temperatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Temperatura[ id=" + id + " ]";
    }
    
}
