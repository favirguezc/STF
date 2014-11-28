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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.Recoleccion;
import pdf.ReporteDeProduccionAnualPorMes;

/**
 *
 * @author fredy
 */
public class RegistrosPrueba {

    public static void main(String[] args) {
        //quitarRegistrosRecoleccion();
        //registrosPruebaRecoleccion();
        new ReporteDeProduccionAnualPorMes(2014, "reporte.pdf");
    }

    public static void registrosPruebaRecoleccion() {
        try {
            RecoleccionControlador controlador = new RecoleccionControlador();
            Persona recolector = new PersonaControlador().buscarPorCedula(1020794235);
            Lote lote = new LoteControlador().buscar(35);
            Date fecha;
            for (int i = 0; i < 200; i++) {
                fecha = new Date(2014 - 1900, (int) (Math.random() * 12), (int) (Math.random() * 28));
                controlador.guardar(controlador.nuevo(lote,
                        fecha,
                        (float) Math.random() * 2000,
                        (float) Math.random() * 4000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 5000,
                        (float) Math.random() * 500,
                        recolector));
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
