/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladora.Main;
import Excepciones.CloseDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andrés
 */
public class VistaParteLogis extends javax.swing.JFrame {

    /**
     * Creates new form VistaParteLogis
     */
    
    public VistaParteLogis() {
        initComponents();
        setLocationRelativeTo(null);
        rellenarCombo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bCrear = new javax.swing.JButton();
        bModificar = new javax.swing.JButton();
        bBorrar = new javax.swing.JButton();
        bAlbaranes = new javax.swing.JButton();
        bCerrar = new javax.swing.JButton();
        cbIdParte = new javax.swing.JComboBox();
        tfEstado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("PARTES DE TRABAJO");

        bCrear.setText("CREAR");
        bCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCrearActionPerformed(evt);
            }
        });

        bModificar.setText("MODIFICAR");
        bModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModificarActionPerformed(evt);
            }
        });

        bBorrar.setText("BORRAR");
        bBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorrarActionPerformed(evt);
            }
        });

        bAlbaranes.setText("ALBARANES");
        bAlbaranes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAlbaranesActionPerformed(evt);
            }
        });

        bCerrar.setText("CERRAR PARTE");
        bCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCerrarActionPerformed(evt);
            }
        });

        cbIdParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdParteActionPerformed(evt);
            }
        });

        jLabel6.setText("IDs de los partes abarcados");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setText("Estado del Parte");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jToggleButton1.setText("VOLVER");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bAlbaranes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbIdParte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbIdParte, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bAlbaranes, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAlbaranesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAlbaranesActionPerformed
        Main.irVA(Integer.parseInt(cbIdParte.getSelectedItem().toString()));
    }//GEN-LAST:event_bAlbaranesActionPerformed

    private void bModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModificarActionPerformed
        Main.irVMP();    
        try {  
            Main.buscarPartePorID(Integer.parseInt(cbIdParte.getSelectedItem().toString())); 
        }
        catch(CloseDB e){
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexion de la base de datos.");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }//GEN-LAST:event_bModificarActionPerformed

    private void bCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCrearActionPerformed
        Main.irVCP();  
    }//GEN-LAST:event_bCrearActionPerformed

    private void bBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorrarActionPerformed
        try {
            Main.borrarParte(Integer.parseInt(cbIdParte.getSelectedItem().toString()));
            JOptionPaneWithTimeOut.showDialog(this, "El parte ha sido borrado satisfactoriamente.", "AVISO", 1500);
            rellenarCombo();
        } catch (Exception ex) {
            Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bBorrarActionPerformed

    private void bCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCerrarActionPerformed
        try {
            Main.cerrarParte();
            rellenarCombo();
        } catch (Exception ex) {
            Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCerrarActionPerformed

    private void cbIdParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdParteActionPerformed
        if(cbIdParte.getSelectedIndex()!=-1){
            try {
                tfEstado.setText(Main.getEstadoParte(cbIdParte.getSelectedIndex()));
            } catch (Exception ex) {
                Logger.getLogger(VistaParteLogis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbIdParteActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    public void rellenarCombo(){
        try
        {   
            cbIdParte.removeAllItems();
            ArrayList<Integer> listaIds = Main.rellenarComboPartesLogis();
            for(int x = 0; x < listaIds.size();x++)
                cbIdParte.insertItemAt(listaIds.get(x), x);
            tfEstado.setText(null);
        }
        catch(Exception e)
        {
            javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());
        }          
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaParteLogis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaParteLogis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaParteLogis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaParteLogis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaParteLogis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAlbaranes;
    private javax.swing.JButton bBorrar;
    private javax.swing.JButton bCerrar;
    private javax.swing.JButton bCrear;
    private javax.swing.JButton bModificar;
    private javax.swing.JComboBox cbIdParte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField tfEstado;
    // End of variables declaration//GEN-END:variables
}