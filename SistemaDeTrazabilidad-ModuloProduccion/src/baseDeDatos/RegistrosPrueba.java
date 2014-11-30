/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;

import controlador.administracion.LoteControlador;
import controlador.administracion.PersonaControlador;
import controlador.produccion.RecoleccionControlador;
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
import util.DateTools;

/**
 *
 * @author fredy
 */
public class RegistrosPrueba {

    public static void main(String[] args) {
//        quitarRegistrosRecoleccion();
//        registrosPruebaRecoleccion();
//        new ReporteMensual(null, true).setVisible(true);
        new ReporteAnual(null, true, ReporteAnual.POR_DIA).setVisible(true);
//        new ReporteSemanal(null, true).setVisible(true);
    }

    public static void registrosPruebaRecoleccion() {
        try {
            RecoleccionControlador controlador = new RecoleccionControlador();
            List<Persona> recolectores = new PersonaControlador().leerLista();
            List<Lote> lotes = new LoteControlador().leerLista();
            Date fecha;
            for (int i = 0; i < 500; i++) {
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
}
