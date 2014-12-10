/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author fredy
 */
public class LongField extends JTextField {

    @Override
    protected Document createDefaultModel() {
        return new LongTextDocument();
    }

    public LongField() {
        super();
    }

    public int getInteger() {
        if (getText() == null || getText().equals("")) {
            return 0;
        }
        return Integer.parseInt(getText());
    }

    public void setInteger(int v) {
        setText(v + "");
    }

    public long getLong() {
        if (getText() == null || getText().equals("")) {
            return 0;
        }
        return Long.parseLong(getText());
    }

    public void setLong(long v) {
        setText(v + "");
    }
}