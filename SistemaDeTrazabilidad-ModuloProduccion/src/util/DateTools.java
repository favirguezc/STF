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

    public static Date getPrimerDiaDelMes(int mes, int año) {
        return new Date(año - 1900, mes, 1);
    }

    public static String getSemana(Date fecha) {
        return DateFormatter.formatDateShort(getPrimerDiaDeLaSemana(fecha)) + " a " + DateFormatter.formatDate(getUltimoDiaDeLaSemana(fecha));
    }

    public static String getSemanaCorta(Date fecha) {
        return DateFormatter.formatDateShort(getPrimerDiaDeLaSemana(fecha)) + " a " + DateFormatter.formatDateShort(getUltimoDiaDeLaSemana(fecha));
    }

    public static Date getPrimerDiaDeLaSemana(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 2);
        return c.getTime();
    }

    public static Date getUltimoDiaDeLaSemana(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 8);
        return c.getTime();
    }

    public static Date getUltimoDiaDelMes(int mes, int año) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(getPrimerDiaDelMes(mes, año));
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static String getDia(int dia) {
        switch (dia) {
            case 1: {
                return "Lunes";
            }
            case 2: {
                return "Martes";
            }
            case 3: {
                return "Miércoles";
            }
            case 4: {
                return "Jueves";
            }
            case 5: {
                return "Viernes";
            }
            case 6: {
                return "Sábado";
            }
            case 7: {
                return "Domingo";
            }
            default: {
                return "Error";
            }
        }
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

    public static int getDiasDelAño(int año) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(new Date(año - 1900, 11, 31));
        return instance.get(Calendar.DAY_OF_YEAR);
    }

    public static int getSemanasDelAño(int año) {
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTime(new Date(año - 1900, 11, 31));
        return instance.get(Calendar.WEEK_OF_YEAR);
    }
}
