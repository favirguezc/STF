/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.util;

import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author fredy
 */
public class NameTextDocument extends PlainDocument {

    private final static Pattern DIGITS = Pattern.compile("[a-z A-Z]+");

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        String oldString = getText(0, getLength());
        String newString = oldString.substring(0, offs) + str
                + oldString.substring(offs);
        if (DIGITS.matcher(newString).matches()) {
            super.insertString(offs, str, a);
        }
    }
    
}
