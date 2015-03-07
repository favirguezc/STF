/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosDePrueba;

import controlador.produccion.administracion.ModuloControlador;
import controlador.produccion.administracion.PersonaControlador;
import controlador.produccion.cosecha.RecoleccionControlador;
import controlador.produccion.variablesClimaticas.LluviaControlador;
import controlador.produccion.variablesClimaticas.HumedadDelSueloControlador;
import controlador.produccion.variablesClimaticas.TemperaturaControlador;
import datos.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.administracion.Persona;
import modelo.produccion.cosecha.Recoleccion;
import modelo.produccion.variablesClimaticas.Lluvia;
import modelo.produccion.variablesClimaticas.HumedadDelSuelo;
import modelo.produccion.variablesClimaticas.Temperatura;
import modelo.util.DateTools;

/**
 *
 * @author fredy
 */
public class RegistrosPrueba {

    public static void main(String[] args) {
//        quitarRegistrosRecoleccion();
//        registrosPruebaRecoleccion();
        quitarRegistrosTemperatura();
        registrosPruebaTemperatura();
//        quitarRegistrosLluvia();
//        registrosPruebaLluvia();
//        quitarRegistrosHumedad();
//        registrosPruebaHumedad();
//        new ReporteMensual(null, true).setVisible(true);
//        new ReporteAnual(null, true, ReporteAnual.POR_DIA).setVisible(true);
//        new ReporteAnual(null, true, ReporteAnual.POR_MES).setVisible(true);
//        new ReporteAnual(null, true, ReporteAnual.POR_SEMANA).setVisible(true);
//        new ReporteSemanal(null, true).setVisible(true);
    }

    public static void registrosPruebaTemperatura() {
        Calendar c = GregorianCalendar.getInstance();
        TemperaturaControlador controlador = new TemperaturaControlador();
        for (int y = 2015; y < 2016; y++) {
            System.out.println("Año " + y);
            c.setTime(DateTools.getDate(y, 0, 1));
            for (int dia = 0; dia < 31; dia++) {
                System.out.println(dia + 1);
                for (int hora = 0; hora < 24; hora++) {
                    for (int paso = 0; paso < 2; paso++) {
                        try {
                            controlador.guardar(
                                    controlador.nuevo(
                                            c.getTime(),
                                            DateTools.getDate(0, 0, 0, hora, paso * 10),
                                            (float) (Math.random() * 30),
                                            (float) (Math.random() * 30),
                                            (float) (Math.random() * 20),
                                            null));
                        } catch (Exception ex) {
                            Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    }

    public static void registrosPruebaRecoleccion() {
        try {
            RecoleccionControlador controlador = new RecoleccionControlador();
            List<Persona> recolectores = new PersonaControlador().leerLista();
            List<Modulo> modulos = new ModuloControlador().leerLista();
            Calendar c = GregorianCalendar.getInstance();
            for (int y = 2014; y < 2015; y++) {
                System.out.println("año " + y);
                c.setTime(DateTools.getDate(y, 0, 1));
                for (int i = 0; i < 365; i++) {
                    System.out.print((i + 1) + " ");
                    for (int r = 0; r < 15; r++) {
                        controlador.guardar(controlador.nuevo(
                                modulos.get((int) (Math.random() * (modulos.size()))),
                                c.getTime(),
                                (float) Math.random() * 2000,
                                recolectores.get((int) (Math.random() * (recolectores.size())))));
                    }
                    c.add(Calendar.DAY_OF_MONTH, 1);
                }
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
        System.out.println("registros recoleccion eliminados");
    }

    public static void quitarRegistrosTemperatura() {
        System.out.println("Eliminando registros de temperatura");
        TemperaturaControlador controlador = new TemperaturaControlador();
        for (Temperatura t : controlador.buscarLista(DateTools.getDate(0, 0, 1), DateTools.getDate(2000, 0, 1))) {
            try {
                controlador.eliminar(t.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        System.out.println("Registros de temperatura eliminados");
    }

    private static void registrosPruebaLluvia() {
        LluviaControlador controlador3 = new LluviaControlador();
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(DateTools.getDate(2014, 0, 1));
        for (int i = 0; i < 365; i++) {
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

    private static void quitarRegistrosLluvia() {
        LluviaControlador controlador3 = new LluviaControlador();
        for (Lluvia c : controlador3.buscarLista(DateTools.getDate(0, 0, 1), DateTools.getDate(2000, 0, 1))) {
            try {
                controlador3.eliminar(c.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Registros de temperatura eliminados");
    }

    private static void quitarRegistrosHumedad() {
        HumedadDelSueloControlador controlador2 = new HumedadDelSueloControlador();
        for (HumedadDelSuelo r : controlador2.buscarLista(DateTools.getDate(0, 0, 1), DateTools.getDate(2000, 0, 1))) {
            try {
                controlador2.eliminar(r.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void registrosPruebaHumedad() {
        HumedadDelSueloControlador controlador2 = new HumedadDelSueloControlador();
        Calendar c = GregorianCalendar.getInstance();
        for (int y = 2012; y < 2015; y++) {
            System.out.println("Año " + y);
            c.setTime(DateTools.getDate(y, 0, 1));
            for (int i = 0; i < 365; i++) {
                System.out.println(i + 1);
                try {
                    controlador2.guardar(
                            controlador2.nuevo(
                                    c.getTime(),
                                    (float) (Math.random() * 20),
                                    (float) (Math.random() * 20 + 10),
                                    DateTools.getDate(0, 0, 0, 7, 30)));
                    controlador2.guardar(
                            controlador2.nuevo(
                                    c.getTime(),
                                    (float) (Math.random() * 20),
                                    (float) (Math.random() * 20 + 10),
                                    DateTools.getDate(0, 0, 0, 12, 30)));
                } catch (Exception ex) {
                    Logger.getLogger(RegistrosPrueba.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    }
}
