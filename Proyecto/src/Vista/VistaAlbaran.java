package Vista;

import Controladora.Main;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class VistaAlbaran extends javax.swing.JFrame {
    private static final Dimension THUMB_SIZE = new Dimension(170, 150);
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static JPanel thumbPanel;
    
    private static ArrayList<Panel> rows;
    private static boolean panelValidado;
    private static boolean albaranes;
    
    public VistaAlbaran()throws HeadlessException{
        initComponents();
        setLocationRelativeTo(null);
        
        
        thumbPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        rows = new ArrayList();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.add(thumbPanel, BorderLayout.NORTH);
        setLayout(new BorderLayout());
        add(new JScrollPane(jPanel2), BorderLayout.CENTER);
        
        try{
            if (!Main.getAlbaranes().isEmpty()) {
                for (int i = 0; i < Main.getAlbaranes().size(); i++) {
                    Panel panel = new Panel();
                    thumbPanel.add(panel);
                    panel.getNumAlbaran().setText(Main.getAlbaranes().get(i).getIdAlbaran());
                    
                    Timestamp time = new Timestamp(Main.getAlbaranes().get(i).getHoraSalida().getTime());
                    time.setTime(time.getTime() + (((2*60*60)* 1000)));
                    Date date = new Date(time.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("hh");
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String hora = format.format(date);
                    
                    panel.getHora().setSelectedItem(hora);
                    
                    time = new Timestamp(Main.getAlbaranes().get(i).getHoraSalida().getTime());
                    date = new Date(time.getTime());
                    format = new SimpleDateFormat("mm");
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String min = format.format(date);
                    
                    panel.getMin().setSelectedItem(min);
                    
                    time = new Timestamp(Main.getAlbaranes().get(i).getHoraLlegada().getTime());
                    time.setTime(time.getTime() + (((2*60*60)* 1000)));
                    date = new Date(time.getTime());
                    format = new SimpleDateFormat("hh");
                    format.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String hora2 = format.format(date);
                    
                    panel.getHora2().setSelectedItem(hora2);
                    
                    Timestamp time3 = new Timestamp(Main.getAlbaranes().get(i).getHoraLlegada().getTime());
                    Date date3 = new Date(time3.getTime());
                    SimpleDateFormat format3 = new SimpleDateFormat("mm");
                    format3.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String min2 = format3.format(date3);
                    
                    panel.getMin2().setSelectedItem(min2);
                    thumbPanel.revalidate();
                    thumbPanel.repaint();
                    rows.add(panel);
                    
                    /*
                    panelValidado = validatePanel(rows.get(i));

                    if (panelValidado) {
                        Panel panel1 = new Panel();
                        thumbPanel.add(panel1);
                        panel1.getNumAlbaran().setText(Main.getAlbaranes().get(i).getIdAlbaran());

                        Timestamp time4= new Timestamp(Main.getAlbaranes().get(i).getHoraSalida().getTime());
                        Date date4 = new Date(time.getTime());
                        SimpleDateFormat format4 = new SimpleDateFormat("hh");
                        format4.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String hora4 = format4.format(date4);

                        panel.getHora().setSelectedItem(hora4);

                        Timestamp time5 = new Timestamp(Main.getAlbaranes().get(i).getHoraSalida().getTime());
                        Date date5 = new Date(time.getTime());
                        SimpleDateFormat format5 = new SimpleDateFormat("mm");
                        format5.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String min5 = format5.format(date5);

                        panel.getMin().setSelectedItem(min5);

                        Timestamp time6 = new Timestamp(Main.getAlbaranes().get(i).getHoraLlegada().getTime());
                        Date date6 = new Date(time.getTime());
                        SimpleDateFormat format6 = new SimpleDateFormat("hh");
                        format6.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String hora6 = format6.format(date6);

                        panel.getHora2().setSelectedItem(hora2);

                        Timestamp time7 = new Timestamp(Main.getAlbaranes().get(i).getHoraLlegada().getTime());
                        Date date7 = new Date(time.getTime());
                        SimpleDateFormat format7 = new SimpleDateFormat("mm");
                        format.setTimeZone(TimeZone.getTimeZone("GMT"));
                        String min7 = format7.format(date7);

                        panel.getMin2().setSelectedItem(min7);
                        thumbPanel.revalidate();
                        thumbPanel.repaint();
                        rows.add(panel);
                    }
                    */
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
       return new Dimension(screen.width / 10 * 6, screen.height / 10 * 6);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jmAddAlbaran = new javax.swing.JMenu();
        jmSave = new javax.swing.JMenu();
        jmClose = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jmAddAlbaran.setText("Añadir Albarán");
        jmAddAlbaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmAddAlbaranMouseClicked(evt);
            }
        });
        jMenuBar2.add(jmAddAlbaran);

        jmSave.setText("Guadar Cambios");
        jmSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmSaveMouseClicked(evt);
            }
        });
        jMenuBar2.add(jmSave);

        jmClose.setText("Salir");
        jmClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jmCloseMouseClicked(evt);
            }
        });
        jMenuBar2.add(jmClose);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmAddAlbaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmAddAlbaranMouseClicked
        addRow();
        revalidate();
        repaint();
    }//GEN-LAST:event_jmAddAlbaranMouseClicked
    
    private void addRow(){
        //panel.setSize(50, 100);
        //panel.setLocation(5, n);
        int i = rows.size();
        
        if (i == 0) {
            addPanel();
        }
        else{
            panelValidado = validatePanel(rows.get(i-1));
            
            if (panelValidado) {
                addPanel();
            }   
        }
    }
    
    private void addPanel(){
        Panel panel = new Panel();
        thumbPanel.add(panel);
        thumbPanel.revalidate();
        thumbPanel.repaint();
        rows.add(panel);
    }
    
    public static boolean validatePanel(Panel panel){
        return panel.validateAll();
    }
    
    public static void dropRow(JPanel panel){
        thumbPanel.remove(panel);
        thumbPanel.revalidate();
        thumbPanel.repaint();
        rows.remove(panel);
    }
    
    private void jmSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmSaveMouseClicked
        try {
            Main.insert(rows);
            JOptionPaneWithTimeOut.showDialog(this, "Los albaranes se han guardado correctamente", "AVISO", 1500);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jmSaveMouseClicked

    private void jmCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_jmCloseMouseClicked
    
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
            java.util.logging.Logger.getLogger(VistaAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaAlbaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaAlbaran().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenu jmAddAlbaran;
    private javax.swing.JMenu jmClose;
    private javax.swing.JMenu jmSave;
    // End of variables declaration//GEN-END:variables
}
