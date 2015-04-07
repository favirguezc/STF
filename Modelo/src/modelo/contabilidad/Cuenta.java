/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.contabilidad;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.produccion.administracion.Persona;
import modelo.util.DateTools;

/**
 *
 * @author fredy
 */
@Entity
public class Cuenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @ManyToOne(optional = false)
    private Persona usuario;
    @ManyToOne(optional = false)
    private Paquete paquete;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
        setFechas();
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    private void setFechas() {
        if (paquete != null && paquete.getTipoPeriodo() != null && paquete.getCantidadPeriodos() > 0) {
            this.fechaInicio = DateTools.getDate(DateTools.getYear(), DateTools.getMonth(), DateTools.getDayOfMonth());
            Calendar calendar = GregorianCalendar.getInstance();
            switch (paquete.getTipoPeriodo()) {
                case Dia:
                    calendar.add(paquete.getCantidadPeriodos(), Calendar.DAY_OF_MONTH);
                    break;
                case Mes:
                    calendar.add(paquete.getCantidadPeriodos(), Calendar.MONTH);
                    break;
                case Año:
                    calendar.add(paquete.getCantidadPeriodos(), Calendar.YEAR);
                    break;
            }
            this.fechaFinal = calendar.getTime();
        }
    }

    public boolean isActive() {
        return !(fechaInicio.before(DateTools.getDate()) || fechaFinal.after(DateTools.getDate()));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.paquete);
        hash = 67 * hash + Objects.hashCode(this.fechaInicio);
        hash = 67 * hash + Objects.hashCode(this.fechaFinal);
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
        final Cuenta other = (Cuenta) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.paquete, other.paquete)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            return false;
        }
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        return true;
    }

}
