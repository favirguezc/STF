package modelo.administracion;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import modelo.produccion.AplicacionFitosanitaria;
import modelo.produccion.Recoleccion;
import modelo.produccion.Trabajo;

@Entity
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Basic
    @Column(nullable = false)
    private String nombre;
    @Basic
    private String nombre2;
    @Basic
    @Column(nullable = false)
    private String apellido;
    @Basic
    private String apellido2;
    @Basic
    @Column(unique = true)
    private long cedula;
    @Basic
    private String sexo;
    @Basic
    private long telefono;
    @Basic
    private String sangre;

    public Persona() {
    }

    public Persona(String nombre, String nombre2, String apellido, String apellido2, long cedula, String sexo, long telefono, String sangre) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.sexo = sexo;
        this.telefono = telefono;
        this.sangre = sangre;
    }

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

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    @Override
    public String toString() {
        String cadena = nombre + " ";
        if (nombre2 != null) {
            cadena += nombre2 + " ";
        }
        cadena += apellido + " ";
        return cadena;
    }
}
