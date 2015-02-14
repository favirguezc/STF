/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion.cargarRegistros.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fredy
 */
public class DateParser {

    public static Date parseFecha(String fecha) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Date parseHora(String fecha) {
        try {
            return new SimpleDateFormat("kk:mm:ss").parse(fecha);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
