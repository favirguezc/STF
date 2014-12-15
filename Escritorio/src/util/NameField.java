/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 *
 * @author fredy
 */
public class NameField extends JTextField {
    
    @Override
    protected Document createDefaultModel() {
        return new NameTextDocument();
    }
}
