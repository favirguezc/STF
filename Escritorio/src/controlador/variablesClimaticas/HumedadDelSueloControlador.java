/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.variablesClimaticas;

import dao.util.EntityManagerFactorySingleton;
import dao.variablesClimaticas.HumedadDelSueloDAO;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.produccion.variablesClimaticas.HumedadDelSuelo;

/**
 *
 * @author fredy
 */
public class HumedadDelSueloControlador {

    private HumedadDelSueloDAO dao;
    private final int maxRegistros = 2;

    public HumedadDelSueloControlador() {
        dao = new HumedadDelSueloDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public HumedadDelSuelo nuevo(Date fecha, float _30Cms, float _15Cms, Date hora) {
        HumedadDelSuelo hds = new HumedadDelSuelo(fecha, _30Cms, _15Cms, hora);
        return hds;
    }

    public boolean validar(HumedadDelSuelo hds, boolean nueva) {
        List<HumedadDelSuelo> buscar = buscar(hds.getFecha());
        if (nueva && (buscar == null || buscar.size() == maxRegistros)) {
            JOptionPane.showMessageDialog(null, "Ya existen " + maxRegistros + " registros para la fecha.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public HumedadDelSuelo buscar(long id) {
        return dao.findHumedadDelSuelo(id);
    }

    public List<HumedadDelSuelo> buscar(Date fecha) {
        return dao.findHumedadDelSuelo(fecha);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(HumedadDelSuelo nueva) {
        dao.create(nueva);
    }

    public void editar(HumedadDelSuelo seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<HumedadDelSuelo> buscarLista(Date esteMes, Date siguienteMes) {
        return dao.findHumedadDelSueloEntities(esteMes, siguienteMes);
    }
}
