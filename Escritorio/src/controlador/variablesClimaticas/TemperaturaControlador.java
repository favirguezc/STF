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

    public Temperatura nuevo(Date fecha, Date hora, float temperatura, float humedad, float puntoDeRocio) {
        return new Temperatura(fecha, hora, temperatura, humedad, puntoDeRocio, null);
    }

    public boolean validar(Temperatura t, Boolean nueva) {
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

    public float calcularPromedio(Date fecha1, Date fecha2) {
        float promedio = 0;
        List<Temperatura> buscarLista = buscarLista(fecha1, fecha2);
        for (Temperatura t : buscarLista) {
            promedio += t.getTemperatura();
        }
        if (buscarLista.size() > 1) {
            promedio = promedio / buscarLista.size();
        }
        return promedio;
    }
}
