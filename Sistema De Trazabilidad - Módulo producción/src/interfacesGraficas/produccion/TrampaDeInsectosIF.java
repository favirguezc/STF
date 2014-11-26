/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.produccion;

import controlador.administracion.RolControlador;
import controlador.administracion.RolPersonaControlador;
import controlador.produccion.TrampaDeInsectosControlador;
import dao.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.administracion.Persona;
import modelo.administracion.Rol;
import modelo.produccion.TrampaDeInsectos;
import util.DateFormatter;
import util.TableColumnAdjuster;

/**
 *
 * @author fredy
 */
public class TrampaDeInsectosIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form TrampaDeInsectosIF
     */
    private TrampaDeInsectos registroSeleccionado = null;
    private TrampaDeInsectosControlador controlador;
    private List<TrampaDeInsectos> lista;
    
    public TrampaDeInsectosIF() {
        initComponents();
        controlador = new TrampaDeInsectosControlador();
        cargarListas();
        guardar(false);
        cargarTablaPrincipal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edicionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fechaDateChooserCombo = new datechooser.beans.DateChooserCombo();
        nombreNameField = new util.NameField();
        especieNameField = new util.NameField();
        observacionesTextField = new javax.swing.JTextField();
        asistenteComboBox = new javax.swing.JComboBox();
        productorComboBox = new javax.swing.JComboBox();
        individuosLongField = new util.LongField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cambioBooleanComboBox = new util.BooleanComboBox();
        jPanel3 = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setTitle("Registro de Trampa de Insectos");
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1118, 560));

        edicionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");

        fechaDateChooserCombo.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        fechaDateChooserCombo.setNavigateFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 14));

        nombreNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        especieNameField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        observacionesTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        asistenteComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        productorComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        individuosLongField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre de la trampa");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Especie encontrada");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Número de individuos");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cambio de hormona y/o pegante");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Observaciones");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Asistente Técnico");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Productor");

        cambioBooleanComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
        edicionPanel.setLayout(edicionPanelLayout);
        edicionPanelLayout.setHorizontalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionPanelLayout.createSequentialGroup()
                        .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(90, 90, 90)
                        .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombreNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(especieNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(individuosLongField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(observacionesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(asistenteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edicionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cambioBooleanComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        edicionPanelLayout.setVerticalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(especieNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(individuosLongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cambioBooleanComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(observacionesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asistenteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        nuevoButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nuevoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/clipboard_32x32.png"))); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        eliminarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eliminarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/trash_32x32.png"))); // NOI18N
        eliminarButton.setText("Eliminar");
        eliminarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        editarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/edit_32x32.png"))); // NOI18N
        editarButton.setText("Editar");
        editarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save_32x32.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.setEnabled(false);
        guardarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guardarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editarButton)
                        .addComponent(eliminarButton)
                        .addComponent(nuevoButton)))
                .addGap(315, 315, 315))
        );

        principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        principalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Nombre", "Especie", "Individuos", "Cambio de hormona", "Observaciones", "Asistente", "Productor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        principalTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnAdjuster tca = new TableColumnAdjuster(principalTable);
        tca.adjustColumns();
        jScrollPane2.setViewportView(principalTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edicionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        registroSeleccionado = null;
        guardar(true);
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        // TODO add your handling code here:
        if (principalTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = lista.get(principalTable.getSelectedRow());
            try {
                controlador.eliminar(registroSeleccionado.getId());
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El registro tiene datos relacionados.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
            registroSeleccionado = null;
            cargarTablaPrincipal();
        }
        guardar(false);
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        guardar(false);
        if (principalTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = lista.get(principalTable.getSelectedRow());
            guardar(true);
        }
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_editarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        boolean transacionRealizada = false;
        Date fecha = fechaDateChooserCombo.getCurrent().getTime();
        String nombre = nombreNameField.getText();
        String especie = especieNameField.getText();
        int individuos = individuosLongField.getInteger();
        boolean cambio = cambioBooleanComboBox.isSelected();
        String observaciones = observacionesTextField.getText();
        Persona asistente = (Persona) asistenteComboBox.getSelectedItem();
        Persona productor = (Persona) productorComboBox.getSelectedItem();
        if (registroSeleccionado == null) {
            TrampaDeInsectos nuevo = controlador.nuevo(fecha, nombre, especie, individuos, cambio, observaciones, asistente, productor);
            if (controlador.validar(nuevo)) {
                controlador.guardar(nuevo);
                transacionRealizada = true;
            }
        } else {
            registroSeleccionado.setFecha(fecha);
            registroSeleccionado.setNombre(nombre);
            registroSeleccionado.setEspecie(especie);
            registroSeleccionado.setIndividuos(individuos);
            registroSeleccionado.setCambio(cambio);
            registroSeleccionado.setObservaciones(observaciones);
            registroSeleccionado.setAsistente(asistente);
            registroSeleccionado.setProductor(productor);
            if (controlador.validar(registroSeleccionado)) {
                try {
                    controlador.editar(registroSeleccionado);
                    transacionRealizada = true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (transacionRealizada) {
            guardar(false);
        }
        cargarTablaPrincipal();
    }//GEN-LAST:event_guardarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox asistenteComboBox;
    private util.BooleanComboBox cambioBooleanComboBox;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private util.NameField especieNameField;
    private datechooser.beans.DateChooserCombo fechaDateChooserCombo;
    private javax.swing.JButton guardarButton;
    private util.LongField individuosLongField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private util.NameField nombreNameField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTextField observacionesTextField;
    private javax.swing.JTable principalTable;
    private javax.swing.JComboBox productorComboBox;
    // End of variables declaration//GEN-END:variables

    private void guardar(boolean b) {
        guardarButton.setEnabled(b);
        edicionPanel.setVisible(b);
        if (!b) {
            registroSeleccionado = null;
        }
    }
    
    private void cargarDatosRegistroSeleccionado() {
        if (registroSeleccionado == null) {
            fechaDateChooserCombo.setSelectedDate(GregorianCalendar.getInstance());
            nombreNameField.setText(null);
            especieNameField.setText(null);
            individuosLongField.setInteger(0);
            cambioBooleanComboBox.setSelected(true);
            observacionesTextField.setText(null);
            asistenteComboBox.setSelectedIndex(0);
            productorComboBox.setSelectedIndex(0);
            
        } else {
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(registroSeleccionado.getFecha());
            fechaDateChooserCombo.setSelectedDate(c);
            nombreNameField.setText(registroSeleccionado.getNombre());
            especieNameField.setText(registroSeleccionado.getEspecie());
            individuosLongField.setInteger(registroSeleccionado.getIndividuos());
            cambioBooleanComboBox.setSelected(registroSeleccionado.isCambio());
            observacionesTextField.setText(registroSeleccionado.getObservaciones());
            asistenteComboBox.setSelectedItem(registroSeleccionado.getAsistente());
            productorComboBox.setSelectedItem(registroSeleccionado.getProductor());
        }
    }
    
    private void cargarTablaPrincipal() {
        while (principalTable.getRowCount() > 0) {
            ((DefaultTableModel) principalTable.getModel()).removeRow(0);
        }
        lista = controlador.leerLista();
        for (TrampaDeInsectos l : lista) {
            Object[] row = {DateFormatter.formatDate(l.getFecha()),
                l.getNombre(),
                l.getEspecie(),
                l.getIndividuos(),
                l.isCambio(),
                l.getObservaciones(),
                l.getAsistente(),
                l.getProductor()};
            ((DefaultTableModel) principalTable.getModel()).addRow(row);
        }
    }
    
    private void cargarListas() {
        cargarListaAsistentes();
        cargarListaProductores();
    }
    
    private void cargarListaAsistentes() {
        asistenteComboBox.removeAllItems();
        asistenteComboBox.addItem(null);
        Rol rol = null;
        try {
            rol = new RolControlador().buscar("Asistente Técnico(a)");
        } catch (Exception ex) {
        }
        for (Persona p : new RolPersonaControlador().leerLista(rol)) {
            asistenteComboBox.addItem(p);
        }
    }
    
    private void cargarListaProductores() {
        productorComboBox.removeAllItems();
        productorComboBox.addItem(null);
        Rol rol = null;
        try {
            rol = new RolControlador().buscar("Productor(a)");
        } catch (Exception ex) {
        }
        for (Persona p : new RolPersonaControlador().leerLista(rol)) {
            productorComboBox.addItem(p);
        }
    }
}
