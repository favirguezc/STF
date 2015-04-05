/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.contabilidad;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
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
    private String nombre;
    private int numeroFincasPermitidas;
    private int numeroTrabajadoresPorFinca;
    private float precio;
    private int diasDeDuracion;
    
}
