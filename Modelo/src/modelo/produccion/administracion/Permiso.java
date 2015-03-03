/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produccion.administracion;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import modelo.util.Accion;

/**
 *
 * @author fredy
 */
@Entity
public class Permiso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Pagina pagina;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Enumerated(EnumType.STRING)
    private Accion accion;

    public Permiso() {
    }

    public Permiso(Pagina pagina, Rol rol, Accion accion) {
        this.pagina = pagina;
        this.rol = rol;
        this.accion = accion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.pagina);
        hash = 61 * hash + Objects.hashCode(this.rol);
        hash = 61 * hash + Objects.hashCode(this.accion);
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
        final Permiso other = (Permiso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.pagina != other.pagina) {
            return false;
        }
        if (this.rol != other.rol) {
            return false;
        }
        if (this.accion != other.accion) {
            return false;
        }
        return true;
    }

}
