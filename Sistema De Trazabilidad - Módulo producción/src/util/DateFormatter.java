/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author fredy
 */
public class DateFormatter {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", new Locale("es", "CO"));
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");

    public static String formatDate(Date d) {
        return dateFormat.format(d);
    }

    public static String formatTime(Date d) {
        return timeFormat.format(d);
    }
}
