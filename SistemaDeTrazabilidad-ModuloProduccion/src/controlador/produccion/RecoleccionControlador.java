/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.RecoleccionDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.Recoleccion;

/**
 *
 * @author fredy
 */
public class RecoleccionControlador {
    
    private RecoleccionDAO dao;
    
    public RecoleccionControlador() {
        dao = new RecoleccionDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }
    
    public Recoleccion nuevo(Lote lote, Date fecha, float extra, float primera, float segunda, float tercera, float cuarta, float danada, Persona recolector) {
        return new Recoleccion(lote, fecha, extra, primera, segunda, tercera, cuarta, danada, recolector);
    }
    
    public Recoleccion buscar(long id) {
        return dao.findRecoleccion(id);
    }
    
    public void editar(Recoleccion r) throws Exception {
        dao.edit(r);
    }
    
    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }
    
    public void guardar(Recoleccion r) {
        dao.create(r);
    }
    
    public List<Recoleccion> leerLista(Persona recolector, Lote lote, Date inicio, Date fin) {
        return dao.findRecoleccionEntities(recolector, lote, inicio, fin);
    }
    
    public Recoleccion sumarRegistros(Persona recolector, Lote lote, Date inicio, Date fin) {
        List<Recoleccion> leerLista = leerLista(recolector, lote, inicio, fin);
        Recoleccion suma = new Recoleccion(lote, null, 0, 0, 0, 0, 0, 0, recolector);
        leerLista.stream().forEach((r) -> {
            suma.sumar(r);
        });
        return suma;
    }
    
    public boolean validar(Recoleccion r) {
        return true;
    }
}