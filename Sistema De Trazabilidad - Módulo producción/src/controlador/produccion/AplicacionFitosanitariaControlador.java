/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.produccion;

import dao.exceptions.NonexistentEntityException;
import dao.produccion.AplicacionFitosanitariaDAO;
import dao.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.produccion.AplicacionFitosanitaria;
import modelo.produccion.ProductoFitosanitario;

/**
 *
 * @author fredy
 */
public class AplicacionFitosanitariaControlador {

    AplicacionFitosanitariaDAO dao;

    public AplicacionFitosanitariaControlador() {
        dao = new AplicacionFitosanitariaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
    }

    public AplicacionFitosanitaria nuevo(Date fecha, ProductoFitosanitario producto, String motivo, boolean pc, boolean tr, float cantidad, float agua, String equipo, Persona responsable, Persona aprobante, float jornales, String observaciones, Lote lote, Persona asistente, Persona productor) {
        return new AplicacionFitosanitaria(fecha, producto, motivo, pc, tr, cantidad, agua, equipo, responsable, aprobante, jornales, observaciones, lote, asistente, productor);
    }

    public AplicacionFitosanitaria buscar(long id) {
        return dao.findAplicacionFitosanitaria(id);
    }

    public void eliminar(long id) throws NonexistentEntityException {
        dao.destroy(id);
    }

    public void guardar(AplicacionFitosanitaria af) {
        dao.create(af);
    }

    public void editar(AplicacionFitosanitaria af) throws Exception {
        dao.edit(af);
    }

    public List<AplicacionFitosanitaria> leerLista(Lote lote, Date fecha1, Date fecha2) {
        return dao.findAplicacionFitosanitariaEntities(lote, fecha1, fecha2);
    }

    public boolean validar(AplicacionFitosanitaria af) {
        if (af.getProducto() == null) {
            JOptionPane.showMessageDialog(null, "El campo producto no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (af.getMotivo() == null || af.getMotivo().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo motivo no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (af.getEquipo() == null || af.getEquipo().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo equipo no puede estar vacío.", "Error de datos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        
        return true;
    }
}
