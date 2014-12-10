/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import controlador.administracion.LoteControlador;
import controlador.administracion.PersonaControlador;
import controlador.produccion.RecoleccionControlador;
import controlador.variablesClimaticas.ControlDeLluviasControlador;
import controlador.variablesClimaticas.HumedadDelSueloControlador;
import controlador.variablesClimaticas.TemperaturaControlador;
import dao.exceptions.NonexistentEntityException;
import interfacesGraficas.graficas.RecoleccionAnualPorLoteIF;
import interfacesGraficas.reportes.ReporteAnual;
import interfacesGraficas.reportes.ReporteMensual;
import interfacesGraficas.reportes.ReporteSemanal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.Recoleccion;
import modelo.variablesClimaticas.ControlDeLluvias;
import modelo.variablesClimaticas.HumedadDelSuelo;
import modelo.variablesClimaticas.Temperatura;
import util.DateTools;

/**
 *
 * @author fredy
 */
public class RegistrosPrueba {

    public static void main(String[] args) {
//        quitarRegistrosRecoleccion();
//        registrosPruebaRecoleccion();
        quitarRegistrosTemperaturaHumedadYLluvias();
        registrosPruebaTemperaturaHumedadYLluvias();
//        new ReporteMensual(null, true).setVisible(true);
//        new ReporteAnual(null, true, ReporteAnual.POR_DIA).setVisible(true);
//        new ReporteSemanal(null, true).setVisible(true);
    }

    public static void registrosPruebaTemperaturaHumedadYLluvias() {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date(2014 - 1900, 0, 1));

        for (int i = 0; i < 365; i++) {
            TemperaturaControlador controlador = new TemperaturaControlador();
            try {
                controlador.guardar(
                        controlador.nuevo(
                                c.getTime(),
                                new Date(0, 0, 0, (int) (Math.random() * 10 + 7), (int) (Math.random() * 59)),
                                new Date(0, 0, 0, (int) (Math.random() * 10 + 7), (int) (Math.random() * 59)),
                                (float) (Math.random() * 15 + 15),
                                (float) (Math.random() * 15)));
            } catch (Exception ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            HumedadDelSueloControlador controlador2 = new HumedadDelSueloControlador();
            try {
                controlador2.guardar(
                        controlador2.nuevo(
                                c.getTime(),
                                (float) (Math.random() * 15),
                                (float) (Math.random() * 20 + 10),
                                new Date(0, 0, 0, 7, 30)));
                controlador2.guardar(
                        controlador2.nuevo(
                                c.getTime(),
                                (float) (Math.random() * 15),
                                (float) (Math.random() * 20 + 10),
                                new Date(0, 0, 0, 12, 30)));
            } catch (Exception ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            ControlDeLluviasControlador controlador3 = new ControlDeLluviasControlador();
            try {
                controlador3.guardar(
                        controlador3.nuevo(
                                c.getTime(),
                                (int) (Math.random() * 25)));
            } catch (Exception ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public static void registrosPruebaRecoleccion() {
        try {
            RecoleccionControlador controlador = new RecoleccionControlador();
            List<Persona> recolectores = new PersonaControlador().leerLista();
            List<Lote> lotes = new LoteControlador().leerLista();
            Date fecha;
            for (int i = 0; i < 1000; i++) {
                fecha = new Date(2014 - 1900, (int) (Math.random() * 12), (int) (Math.random() * 28));
                controlador.guardar(controlador.nuevo(
                        lotes.get((int) (Math.random() * (lotes.size()))),
                        fecha,
                        (float) Math.random() * 2000,
                        (float) Math.random() * 4000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 500,
                        recolectores.get((int) (Math.random() * (recolectores.size())))));
            }
        } catch (Exception ex) {
            Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void quitarRegistrosRecoleccion() {
        RecoleccionControlador controlador = new RecoleccionControlador();
        for (Recoleccion r : controlador.leerLista(null, null, null, null)) {
            try {
                controlador.eliminar(r.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static void quitarRegistrosTemperaturaHumedadYLluvias() {
        TemperaturaControlador controlador = new TemperaturaControlador();
        for (Temperatura t : controlador.buscarLista(new Date(0, 0, 1), new Date(2000, 0, 1))) {
            try {
                controlador.eliminar(t.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        HumedadDelSueloControlador controlador2 = new HumedadDelSueloControlador();
        for (HumedadDelSuelo r : controlador2.buscarLista(new Date(0, 0, 1), new Date(2000, 0, 1))) {
            try {
                controlador.eliminar(r.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        ControlDeLluviasControlador controlador3 = new ControlDeLluviasControlador();
        for (ControlDeLluvias c : controlador3.buscarLista(new Date(0, 0, 1), new Date(2000, 0, 1))) {
            try {
                controlador3.eliminar(c.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}