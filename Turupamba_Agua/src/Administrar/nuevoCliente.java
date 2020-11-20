/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import BaseDatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SUCOSPP
 */
public class nuevoCliente extends javax.swing.JFrame {

    /**
     * Creates new form nuevoCliente
     */
    public nuevoCliente() {
        initComponents();
    }
    ConexionBD conexion = new ConexionBD();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nombrestxt = new javax.swing.JTextField();
        cedulatxt = new javax.swing.JTextField();
        direcciontxt = new javax.swing.JTextField();
        emailtxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR CLIENTES");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nombres");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 43, -1, -1));

        jLabel2.setText("Cédula");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 92, -1, -1));

        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 141, -1, -1));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 190, -1, -1));
        getContentPane().add(nombrestxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 38, 287, -1));
        getContentPane().add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 87, 287, -1));
        getContentPane().add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 136, 287, -1));
        getContentPane().add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 185, 287, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        if (JOptionPane.showConfirmDialog(this, "Está seguro que desea ingresar el cliente", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {        // a.ejecutarSQL("UPDATE producto SET Descripcion='"+jDescripcion.getText()+"', Precio = "+jPrecio.getText()+", disponible="+jCantidad.getText()+" WHERE idProducto="+id+";") ;

            if (nombrestxt.getText().equals("") || cedulatxt.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre y la cédula", "Error", JOptionPane.ERROR_MESSAGE);

            } else {

                             
                try {
                    ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as num from personas where cedula='" + cedulatxt.getText() + "'");
               
                       int numero = 0;
            while (datos.next()) {
                numero = Integer.parseInt(datos.getString("num"));

            }
            if(numero==0){
                           conexion.crearConexion();
                conexion.ejecutarSQL("insert into personas (nombres,cedula,direccion,email,activo) values (UPPER('" + nombrestxt.getText() + "'),'" + cedulatxt.getText() + "','" + direcciontxt.getText() + "','" + emailtxt.getText() + "',1)");
                JOptionPane.showMessageDialog(this, "ingresado Correctamente", "Ingresado", JOptionPane.INFORMATION_MESSAGE);
        
            }else{
                   JOptionPane.showMessageDialog(this, "Esa cédula ya pertenece a un usuario", "Error", JOptionPane.ERROR_MESSAGE);

            }
                
 
                } catch (SQLException ex) {
                    Logger.getLogger(nuevoCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
     
            }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(nuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nuevoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JTextField direcciontxt;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombrestxt;
    // End of variables declaration//GEN-END:variables
}