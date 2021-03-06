/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.finanzas;

import controlador.finanzas.CompraControlador;
import controlador.produccion.administracion.LoteControlador;
import controlador.produccion.aplicaciones.InsumoControlador;
import datos.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.finanzas.Precio;
import modelo.finanzas.compra.Compra;
import modelo.produccion.administracion.Lote;
import modelo.produccion.aplicaciones.Insumo;
import modelo.util.DateFormatter;

/**
 *
 * @author JohnFredy
 */
public class CompraIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form InsumosIE
     */
    private Compra registroSeleccionado = null;
    private CompraControlador controlador;
    private List<Compra> lista;
    private boolean nuePrecio;
    private boolean verificar;
    private Precio precio;
    
    public CompraIF() {
        initComponents();
        controlador = new CompraControlador();
        verificar = false;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        loteFiltroComboBox = new javax.swing.JComboBox();
        nuevoButton = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();
        edicionPanel = new javax.swing.JPanel();
        fechaDateChooserCombo = new datechooser.beans.DateChooserCombo();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        insumoComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        loteComboBox = new javax.swing.JComboBox();
        valorFloatField = new modelo.util.FloatField();
        cantidadFloatField = new modelo.util.FloatField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Insumos y Otros Gastos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Lote");

        loteFiltroComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loteFiltroComboBox.setPreferredSize(new java.awt.Dimension(28, 23));
        loteFiltroComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loteFiltroComboBoxItemStateChanged(evt);
            }
        });
        loteFiltroComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loteFiltroComboBoxActionPerformed(evt);
            }
        });

        nuevoButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nuevoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/add42.png"))); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        borrarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        borrarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/delete27.png"))); // NOI18N
        borrarButton.setText("Eliminar");
        borrarButton.setToolTipText("");
        borrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarButtonActionPerformed(evt);
            }
        });

        editarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/edit23.png"))); // NOI18N
        editarButton.setText("Editar");
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        guardarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/database13.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.setEnabled(false);
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(loteFiltroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142)
                .addComponent(nuevoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editarButton)
                .addGap(48, 48, 48)
                .addComponent(guardarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loteFiltroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borrarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        principalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Lote", "Insumo", "Cantidad", "Valor Unitario", "Valor Total"
            }
        ));
        jScrollPane1.setViewportView(principalTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        edicionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición"));

        fechaDateChooserCombo.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Fecha");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Lote");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cantidad");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Valor Unitario");

        insumoComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insumoComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Fertlizante", "Herbicida", "Fungicida", "Insecticida", "Coadyudante", "Desifectante" }));
        insumoComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                insumoComboBoxItemStateChanged(evt);
            }
        });
        insumoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insumoComboBoxActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Insumo");

        loteComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loteComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));

        valorFloatField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cantidadFloatField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
        edicionPanel.setLayout(edicionPanelLayout);
        edicionPanelLayout.setHorizontalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(41, 41, 41)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insumoComboBox, 0, 133, Short.MAX_VALUE)
                    .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(loteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valorFloatField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cantidadFloatField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        edicionPanelLayout.setVerticalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(loteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(insumoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cantidadFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(valorFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        registroSeleccionado = null;
        guardar(true);
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void loteFiltroComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loteFiltroComboBoxActionPerformed
        // TODO add your handling code here:
        cargarTablaPrincipal();
    }//GEN-LAST:event_loteFiltroComboBoxActionPerformed

    private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarButtonActionPerformed
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
    }//GEN-LAST:event_borrarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        guardar(false);
        if (principalTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = lista.get(principalTable.getSelectedRow());
            precio = controlador.getPrecioDAO().findPrecio(registroSeleccionado.getInsumo().getNombre());
            guardar(true);
        }
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_editarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        boolean transacionRealizada = false;
        Date fechaCompra = fechaDateChooserCombo.getCurrent().getTime();
        Lote lote = (Lote) loteComboBox.getSelectedItem();
        Insumo insumo = (Insumo) insumoComboBox.getSelectedItem();
        float cantidad = cantidadFloatField.getFloat();
        float valor = valorFloatField.getFloat();

        if (registroSeleccionado == null) {
            Compra nuevo = controlador.nuevo(fechaCompra, lote, insumo, valor, cantidad);
            if (controlador.validar(nuevo)) {
                guardarPrecio();
                controlador.guardar(nuevo);
                transacionRealizada = true;
            }
        } else {
            registroSeleccionado.setFechaCompra(fechaCompra);
            registroSeleccionado.setLote(lote);
            registroSeleccionado.setInsumo(insumo);
            registroSeleccionado.setCantidad(cantidad);
            registroSeleccionado.setPrecio(valor);
            if (controlador.validar(registroSeleccionado)) {
                try {
                    guardarPrecio();
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

    private void loteFiltroComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loteFiltroComboBoxItemStateChanged
        // TODO add your handling code here:
        cargarTablaPrincipal();
    }//GEN-LAST:event_loteFiltroComboBoxItemStateChanged

    private void insumoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insumoComboBoxActionPerformed
        // TODO add your handling code here:
        verificarPrecio();
    }//GEN-LAST:event_insumoComboBoxActionPerformed

    private void insumoComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_insumoComboBoxItemStateChanged
        // TODO add your handling code here:
        verificarPrecio();
    }//GEN-LAST:event_insumoComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private modelo.util.FloatField cantidadFloatField;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private datechooser.beans.DateChooserCombo fechaDateChooserCombo;
    private javax.swing.JButton guardarButton;
    private javax.swing.JComboBox insumoComboBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox loteComboBox;
    private javax.swing.JComboBox loteFiltroComboBox;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTable principalTable;
    private modelo.util.FloatField valorFloatField;
    // End of variables declaration//GEN-END:variables
    
    private void guardar(boolean b) {
        guardarButton.setEnabled(b);
        edicionPanel.setVisible(b);
        verificar = b;
        if (!b) {
            registroSeleccionado = null;
        }
    }
    
    private void cargarTablaPrincipal() {
        while (principalTable.getRowCount() > 0) {
            ((DefaultTableModel) principalTable.getModel()).removeRow(0);
        }
        Lote lote = (Lote) loteFiltroComboBox.getSelectedItem();
        
            Date inicio = null;
            Date fin = null;
            lista = controlador.leerLista(lote, inicio, fin);
        for (Compra co : lista) {
            Object[] row = {
                DateFormatter.formatDate(co.getFechaCompra()),
                co.getLote(),
                co.getInsumo(),
                co.getCantidad(),
                co.getPrecio(),
                co.getTotal()
            };
            
            ((DefaultTableModel) principalTable.getModel()).addRow(row);
        }
    }

    private void cargarListas(){
        cargarListaLotes();
        cargarListaInsumos();
    }
    
    private void cargarListaLotes() {
        loteComboBox.removeAllItems();
        loteComboBox.addItem(null);
        loteFiltroComboBox.removeAllItems();
        loteFiltroComboBox.addItem(null);
        for (Lote l : new LoteControlador().leerLista()) {
            loteComboBox.addItem(l);
            loteFiltroComboBox.addItem(l);
        }
    }
    
    private void cargarListaInsumos(){
        insumoComboBox.removeAllItems();
        insumoComboBox.addItem(null);
        for (Insumo in : new InsumoControlador().leerLista()) {
            insumoComboBox.addItem(in);
        }
    }
    
    private void cargarDatosRegistroSeleccionado() {
        if (registroSeleccionado == null) {
            fechaDateChooserCombo.setSelectedDate(GregorianCalendar.getInstance());
            fechaDateChooserCombo.setCurrent(GregorianCalendar.getInstance());
            loteComboBox.setSelectedIndex(0);
            insumoComboBox.setSelectedIndex(0);
            cantidadFloatField.setFloat(null);
            valorFloatField.setFloat(null);
            

        } else {
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(registroSeleccionado.getFechaCompra());
            fechaDateChooserCombo.setSelectedDate(c);
            fechaDateChooserCombo.setCurrent(c);
            loteComboBox.setSelectedItem(registroSeleccionado.getLote());
            insumoComboBox.setSelectedItem(registroSeleccionado.getInsumo());
            cantidadFloatField.setFloat(registroSeleccionado.getCantidad());
            valorFloatField.setFloat(registroSeleccionado.getPrecio());
        }
    }
    
    public void verificarPrecio(){
        if(verificar){
            if(insumoComboBox.getSelectedItem() != null){
                //search precio by item
                Insumo insumo = (Insumo) insumoComboBox.getSelectedItem();
                precio = controlador.getPrecioDAO().findPrecio(insumo.getNombre());
                //if exists set
                if(precio != null){
                    nuePrecio = false;
                    valorFloatField.setFloat(precio.getValor());
                }else{
                    //else create new precion
                    nuePrecio = true;
                    precio = new Precio(insumo.getNombre(),0);
                    valorFloatField.setFloat(null);
                }
            }else{
                //insert code here
            }
        }
    }
    
    public void guardarPrecio(){
        if(nuePrecio){
            precio.setValor(valorFloatField.getFloat());
            controlador.getPrecioDAO().create(precio);
        }else{
            precio.setValor(valorFloatField.getFloat());
            try {
                controlador.getPrecioDAO().edit(precio);
            } catch (Exception ex) {
                System.out.println("Error #A:  " + ex.getMessage());
            }
        }
    }
}
