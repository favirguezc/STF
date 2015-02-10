/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion.monitoreo;

import datos.exceptions.NonexistentEntityException;
import datos.produccion.monitoreo.VariableDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.List;
import modelo.produccion.monitoreo.TipoDeValoracion;
import modelo.produccion.monitoreo.Variable;

/**
 *
 * @author fredy
 */
public class VariableControlador {

    private VariableDAO dao;
    
    public VariableControlador() {
        dao = new VariableDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Variable nuevo(String nombre, String abreviacion, TipoDeValoracion tipoDeValoracion) {
        return new Variable(nombre, abreviacion, tipoDeValoracion);
    }

    public Variable buscar(long id) {
        return dao.findVariable(id);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Variable t) {
        dao.create(t);
    }

    public void editar(Variable t) throws Exception {
        dao.edit(t);
    }

    public List<Variable> leerLista() {
        return dao.findVariableEntities();
    }
    
}
