package Vista;

import Controladora.Main;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class VistaTrabajador extends javax.swing.JFrame {
    
    public VistaTrabajador() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(java.awt.Color.decode("#1BD6B8"));
        setListaCentros();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoTra = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        tfApe1 = new javax.swing.JTextField();
        tfApe2 = new javax.swing.JTextField();
        ftfDNI = new javax.swing.JTextField();
        bCheck = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        rbAdministracion = new javax.swing.JRadioButton();
        rbLogistica = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfCalle = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfPortal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfPiso = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfMano = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfTelfMovil = new javax.swing.JTextField();
        tfTelfPersonal = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tfSalario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        bAceptar = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        bBorrar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        cbIdCentro = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tfNomCentro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DATOS DEL TRABAJADOR");

        jPanel1.setBackground(new java.awt.Color(27, 200, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("SEGUNDO APELLIDO");

        jLabel3.setText("NOMBRE");

        jLabel4.setText("PRIMER APELLIDO");

        jLabel2.setText("DNI");

        tfNombre.setEnabled(false);

        tfApe1.setEnabled(false);

        tfApe2.setEnabled(false);

        ftfDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ftfDNIActionPerformed(evt);
            }
        });

        bCheck.setText("Check");
        bCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfNombre))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfApe1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfApe2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(ftfDNI)))
                .addGap(8, 8, 8)
                .addComponent(bCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ftfDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfApe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfApe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel2.setBackground(new java.awt.Color(27, 200, 253));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "TIPO"));

        bgTipoTra.add(rbAdministracion);
        rbAdministracion.setText("Administración");
        rbAdministracion.setEnabled(false);

        bgTipoTra.add(rbLogistica);
        rbLogistica.setText("Logística");
        rbLogistica.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAdministracion)
                    .addComponent(rbLogistica))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rbAdministracion)
                .addGap(30, 30, 30)
                .addComponent(rbLogistica)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(27, 200, 253));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "DIRECCION"));

        jLabel6.setText("CALLE");

        tfCalle.setEnabled(false);

        jLabel7.setText("PORTAL");

        tfPortal.setEnabled(false);

        jLabel8.setText("PISO");

        tfPiso.setEnabled(false);

        jLabel9.setText("MANO");

        tfMano.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCalle))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPortal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMano, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 168, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfPortal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tfPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(tfMano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(27, 200, 253));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "TELEFONOS"));

        jLabel10.setText("PERSONAL");

        jLabel11.setText("MOVIL DE EMPRESA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTelfPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTelfMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(tfTelfMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTelfPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(27, 200, 253));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setText("SALARIO");

        tfSalario.setEnabled(false);

        jLabel13.setText("FECHA DE NACIMIENTO");

        dateChooserCombo1.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        bAceptar.setText("Crear");
        bAceptar.setEnabled(false);
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        bBorrar.setText("Borrar");
        bBorrar.setEnabled(false);
        bBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBorrarActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(27, 200, 253));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbIdCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdCentroActionPerformed(evt);
            }
        });

        jLabel14.setText("CENTRO");

        jLabel15.setText("NOMBRE");

        tfNomCentro.setEditable(false);
        tfNomCentro.setEnabled(false);
        tfNomCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomCentroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbIdCentro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIdCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfNomCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel7.setBackground(new java.awt.Color(27, 200, 253));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setText("USUARIO");

        jLabel17.setText("CONTRASEÑA");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(42, 42, 42)
                        .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(bAceptar)
                                .addGap(71, 71, 71)
                                .addComponent(bBorrar)
                                .addGap(76, 76, 76)
                                .addComponent(bSalir)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAceptar)
                    .addComponent(bSalir)
                    .addComponent(bBorrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void ftfDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfDNIActionPerformed
        getData();
    }//GEN-LAST:event_ftfDNIActionPerformed
    private void bCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCheckActionPerformed
        if(ftfDNI.isEnabled())
            getData();
        else{
            clearAll();
        }
    }//GEN-LAST:event_bCheckActionPerformed
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed
    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        if(validar()){
            if(Main.modificar()){
                try {
                    String tipo;
                    if(rbAdministracion.isSelected())
                        tipo = "Administracion";
                    else
                        tipo = "Logistica";      
                    Date fecha = null;
                    if(dateChooserCombo1.getSelectedDate()!=null)
                        fecha = dateChooserCombo1.getSelectedDate().getTime();
                    String pass = "";
                    for(char a : passField.getPassword())
                        pass+=a;
                    Main.actualizarUsuario(tfNombre.getText(),tfApe1.getText(),tfApe2.getText(),tfCalle.getText(),tfPortal.getText(),tfPiso.getText(),tfMano.getText(),tfTelfPersonal.getText(),tfTelfMovil.getText(),tfSalario.getText(),fecha, tipo, Integer.parseInt(cbIdCentro.getSelectedItem().toString()), tfUsuario.getText(), pass);
                } catch (SQLException ex) {
                    Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                String tipo;
                if(rbAdministracion.isSelected())
                    tipo = "Administracion";
                else
                    tipo = "Logistica";
                Date fecha = null;
                if(dateChooserCombo1.getSelectedDate()!=null)
                    fecha = dateChooserCombo1.getSelectedDate().getTime();
                String pass = "";
                for(char a : passField.getPassword())
                    pass+=a;
                try {
                    Main.crearUsuario(ftfDNI.getText(),tfNombre.getText(),tfApe1.getText(),tfApe2.getText(),tfCalle.getText(),tfPortal.getText(),tfPiso.getText(),tfMano.getText(),tfTelfPersonal.getText(),tfTelfMovil.getText(),tfSalario.getText(),fecha, tipo, Integer.parseInt(cbIdCentro.getSelectedItem().toString()), tfUsuario.getText(), pass);
                } catch (SQLException ex) {
                    Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_bAceptarActionPerformed
    private void bBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBorrarActionPerformed
        try {
            Main.borrar();
        } catch (Exception ex) {
            Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bBorrarActionPerformed

    private void tfNomCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomCentroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomCentroActionPerformed

    private void cbIdCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdCentroActionPerformed
        if(cbIdCentro.getSelectedIndex()!=-1){
            try {
                tfNomCentro.setText(Main.getCentro(cbIdCentro.getSelectedIndex()));
            } catch (SQLException ex) {
                Logger.getLogger(VistaTrabajador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cbIdCentroActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaTrabajador().setVisible(true);
            }
        });
    }
    public void clearAll(){
        editable(false);
        bCheck.setText("Check");
        ftfDNI.requestFocus();
    }
    private void editable(boolean b){
        ftfDNI.setEnabled(!b);
        tfNombre.setEnabled(b);
        tfApe1.setEnabled(b);
        tfApe2.setEnabled(b);
        tfCalle.setEnabled(b);
        tfPortal.setEnabled(b);
        tfPiso.setEnabled(b);
        tfMano.setEnabled(b);
        tfTelfPersonal.setEnabled(b);
        tfTelfMovil.setEnabled(b);
        tfSalario.setEnabled(b);
        tfSalario.setEnabled(b);
        dateChooserCombo1.setEnabled(b);
        rbAdministracion.setEnabled(b);
        rbLogistica.setEnabled(b);
        bAceptar.setEnabled(b);
        bBorrar.setEnabled(b);
        cbIdCentro.setEnabled(b);
        tfUsuario.setEnabled(b);
        passField.setEnabled(b);
        if(!b){
            tfNombre.setText(null);
            tfApe1.setText(null);
            tfApe2.setText(null);
            tfCalle.setText(null);
            tfPortal.setText(null);
            tfPiso.setText(null);
            tfMano.setText(null);
            tfTelfPersonal.setText(null);
            tfTelfMovil.setText(null);
            tfSalario.setText(null);
            dateChooserCombo1.setSelectedDate(null); 
            bgTipoTra.clearSelection();
            cbIdCentro.setSelectedIndex(-1);
            tfNomCentro.setText(null);
            tfUsuario.setText(null);
            passField.setText(null);
        }
    }
    private void getData(){
        editable(true);
        try{
            Main.rellenaDatos(ftfDNI.getText());
        }catch(SQLException ex){
            
        }
    }
    public void nuevo(){
        bAceptar.setText("Crear");
        bgTipoTra.clearSelection();
        ftfDNI.setEnabled(false);
        bCheck.setText("Cambiar");
        bBorrar.setEnabled(false);
    }
    public void modificar(){
        bAceptar.setText("Modificar");
        ftfDNI.setEnabled(false);
        bCheck.setText("Cambiar");
        bBorrar.setEnabled(true);
    }
      
    
    private boolean validar(){
                
        boolean validado = true;
        String informeinvalidos="";
        Pattern pat;
        Matcher mat;
            if(ftfDNI.getText() != null){
            pat = Pattern.compile("^[0-9]{8}[a-zA-Z]{1}$");
            mat = pat.matcher(ftfDNI.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "DNI invalido\n";}
            }else{
                validado = false;
            }
            
            if(tfNombre.getText() != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(tfNombre.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Nombre invalido\n";}
            }else{
                validado = false;
            }
            
            if(tfApe1.getText() != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(tfApe1.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "1er apellido invalido\n";}
            }else{
                validado = false;
            }
          
            if(tfApe2.getText() != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z]+$");
            mat = pat.matcher(tfApe2.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "2º apellido invalido\n";}
            }else{
                validado = false;
            }
            
            if(tfCalle.getText() != null){
            pat = Pattern.compile("^[A-Z]{1}[a-z A-Z]+$");
            mat = pat.matcher(tfCalle.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Calle invalida\n";}
            }
            
            if(tfPortal.getText() != null){
            pat = Pattern.compile("^[1-100]$");
            mat = pat.matcher(tfPortal.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Portal invalido\n";}
            }
            
            if(tfPiso.getText() != null){
            pat = Pattern.compile("^[1-10]$");
            mat = pat.matcher(tfPiso.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Piso invalido\n";}
            }
            
            if(tfMano.getText() != null){
            pat = Pattern.compile("^[ABCD]{1}$");
            mat = pat.matcher(tfMano.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Mano invalida\n";}
            }
            
            if(tfTelfMovil.getText() != null){
            pat = Pattern.compile("^[0-9]{9}$");
            mat = pat.matcher(tfTelfMovil.getText());
                if (!mat.find()) {validado = false; informeinvalidos += "Telefono movil empresa invalido\n";}
            }else{
                validado = false;
            }
            
            if(tfTelfPersonal.getText() != null || tfTelfPersonal.getText().compareTo("")==0){
            pat = Pattern.compile("^[0-9]{9}$");
            mat = pat.matcher(tfTelfPersonal.getText());
                if (!mat.find()) {
                    validado = false; informeinvalidos += "Telefono personal invalido\n";}
            }
            
            if(tfSalario.getText() == null || tfSalario.getText().compareTo("")==0){
                validado = false; informeinvalidos += "Salario invalido";}
            
            if(!validado)
                JOptionPane.showMessageDialog(null,"ERRORES:\n" + informeinvalidos);    
            
            return validado;
    }
    
    public void rellenar(String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, Float salario, Date fecha, int index, String user, String pass){
        tfNombre.setText(nombre);
        tfApe1.setText(ape1);
        tfApe2.setText(ape2);
        tfCalle.setText(calle);
        tfPortal.setText(portal);
        tfPiso.setText(piso);
        tfMano.setText(mano);
        tfTelfPersonal.setText(tlfper);
        tfTelfMovil.setText(movilemp);
        if(salario!=null)
            tfSalario.setText(salario.toString());
        else
            tfSalario.setText(null);
        if(fecha!=null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            dateChooserCombo1.setSelectedDate(cal);
        }else
            dateChooserCombo1.setSelectedDate(null);  
        cbIdCentro.setSelectedIndex(index);
        tfUsuario.setText(user);
        passField.setText(pass);
        
    }
    public void setTipo(String tipo){
        if(tipo.compareToIgnoreCase("Administracion")==0){
            rbAdministracion.setSelected(true);
        }else{
            rbLogistica.setSelected(true);
        }
    }
    public void setListaCentros(){
        cbIdCentro.removeAllItems();
        for(Integer id:Main.rellenaCentros()){
            cbIdCentro.addItem(id.toString());
        }
        cbIdCentro.setSelectedIndex(-1);
    }
    public void getReady(){
        Calendar cal = Calendar.getInstance();
        cal.set(1, cal.get(1)-10);
        dateChooserCombo1.setMaxDate(cal);
        dateChooserCombo1.setSelectedDate(null);
        editable(false);
        setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bBorrar;
    private javax.swing.JButton bCheck;
    private javax.swing.JButton bSalir;
    private javax.swing.ButtonGroup bgTipoTra;
    private javax.swing.JComboBox<String> cbIdCentro;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JTextField ftfDNI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField passField;
    private javax.swing.JRadioButton rbAdministracion;
    private javax.swing.JRadioButton rbLogistica;
    private javax.swing.JTextField tfApe1;
    private javax.swing.JTextField tfApe2;
    private javax.swing.JTextField tfCalle;
    private javax.swing.JTextField tfMano;
    private javax.swing.JTextField tfNomCentro;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfPiso;
    private javax.swing.JTextField tfPortal;
    private javax.swing.JTextField tfSalario;
    private javax.swing.JTextField tfTelfMovil;
    private javax.swing.JTextField tfTelfPersonal;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}
