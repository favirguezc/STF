/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.variablesClimaticas;

import dao.util.EntityManagerFactorySingleton;
import dao.variablesClimaticas.TemperaturaDAO;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.variablesClimaticas.Temperatura;

/**
 *
 * @author fredy
 */
public class TemperaturaControlador {

    private TemperaturaDAO dao;

    public TemperaturaControlador() {
        dao = new TemperaturaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public Temperatura nuevo(Date fecha, Date horaMax, Date horaMin, float tempMax, float tempMin) {
        Temperatura nueva = new Temperatura();
        nueva.setFecha(fecha);
        nueva.setHoraMax(horaMax);
        nueva.setHoraMin(horaMin);
        nueva.setTempMax(tempMax);
        nueva.setTempMin(tempMin);
        return nueva;
    }

    public boolean validar(Temperatura t, Boolean nueva) {
        if (t.getTempMax() < t.getTempMin()) {
            JOptionPane.showMessageDialog(null, "La temperatura mínima no puede ser mayor a la temperatura máxima.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            if (nueva && buscar(t.getFecha()) != null) {
                JOptionPane.showMessageDialog(null, "Ya existe un registro para la fecha.", "Registro duplicado", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }

    public Temperatura buscar(Date fecha) throws Exception {
        return dao.findTemperatura(fecha);
    }

    public void eliminar(Long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(Temperatura nueva) throws Exception {
        dao.create(nueva);
    }

    public void editar(Temperatura seleccionada) throws Exception {
        dao.edit(seleccionada);
    }

    public List<Temperatura> buscarLista(Date esteMes, Date siguienteMes) {
        return dao.findTemperaturaEntities(esteMes, siguienteMes);
    }

}
