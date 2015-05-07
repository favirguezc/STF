/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author fredy
 */
public class DateFormatter {

    private static final SimpleDateFormat dateFormatExtraShort = new SimpleDateFormat("dd 'de' MMMMMMMM", new Locale("es", "CO"));
    private static final SimpleDateFormat dateFormatShort = new SimpleDateFormat("dd 'de' MMMMMMMMM 'de' yyyy", new Locale("es", "CO"));
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd 'de' MMMMMMMMM 'de' yyyy", new Locale("es", "CO"));
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    /**
     *
     * @param d
     * @return
     */
    public static String formatDate(Date d) {
        return dateFormatShort.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatTime(Date d) {
        return timeFormat.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatDateLong(Date d) {
        return dateFormat.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String formatDateShort(Date d) {
        return dateFormatExtraShort.format(d);
    }
}
