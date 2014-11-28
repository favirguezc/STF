/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author fredy
 */
public class DateTools {

    public static Date getPrimerDia(int mes, int año) {
        return new Date(año - 1900, mes, 1);
    }

    public static Date getUltimoDia(int mes, int año) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(getPrimerDia(mes, año));
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static String getMes(int mes) {
        switch (mes) {
            case 0: {
                return "Enero";
            }
            case 1: {
                return "Febrero";
            }
            case 2: {
                return "Marzo";
            }
            case 3: {
                return "Abril";
            }
            case 4: {
                return "Mayo";
            }
            case 5: {
                return "Junio";
            }
            case 6: {
                return "Julio";
            }
            case 7: {
                return "Agosto";
            }
            case 8: {
                return "Septiembre";
            }
            case 9: {
                return "Octubre";
            }
            case 10: {
                return "Noviembre";
            }
            case 11: {
                return "Diciembre";
            }
            default: {
                return "Error";
            }
        }
    }
}
