package modelo.administracion;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(nullable = false)
    private String nombre;
    private String nombre2;
    @Column(nullable = false)
    private String apellido;
    private String apellido2;
    @Column(unique = true)
    private long cedula;
    private String sexo;
    private long telefono;
    @Enumerated(EnumType.STRING)
    private GrupoSanguineo grupoSanguineo;
    @Enumerated(EnumType.STRING)
    private RH rh;

    public Persona() {
    }

    public Persona(String nombre, String nombre2, String apellido, String apellido2, long cedula, String sexo, long telefono, GrupoSanguineo grupoSanguineo, RH rh) {
        this.nombre = nombre;
        this.nombre2 = nombre2;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.cedula = cedula;
        this.sexo = sexo;
        this.telefono = telefono;
        this.grupoSanguineo = grupoSanguineo;
        this.rh = rh;
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

    public GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
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
