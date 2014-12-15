/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JComboBox;

/**
 *
 * @author fredy
 */
public class BooleanComboBox extends JComboBox<String> {
    
    private final String no="No",si="Si";
    
    public BooleanComboBox() {
        super();
        super.removeAllItems();
        addItem(si);
        addItem(no);
    }
    
    public Boolean isSelected() {
        return super.getSelectedItem().equals("Si");
    }
    
    public void setSelected(boolean b) {
        if (b) {
            setSelectedItem(si);
        } else {
            setSelectedItem(no);
        }
    }
}
