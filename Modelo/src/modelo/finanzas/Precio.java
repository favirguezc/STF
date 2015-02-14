/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.finanzas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author John Fredy
 */
@Entity
public class Precio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Column(unique = true,nullable = false)
    String item;
    @Column(nullable = false)
    float valor;

    public Precio() {
    }

    public Precio(String item, float valor) {
        this.item = item;
        this.valor = valor;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
