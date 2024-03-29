/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import BaseDatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUCOSPP
 */
public class ClientesAdmin extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    public ClientesAdmin() {
        initComponents();
    }
    ConexionBD conexion = new ConexionBD();
    DefaultTableModel modelo = new DefaultTableModel();

    public void cargarDatos(String where) {
        try {
            ResultSet datos = conexion.ejecutarSQLSelect("select nombres as nombres ,cedula,direccion,email, case when Activo=1 then 'ACTIVO' else 'INACTIVO' end as Actividad from personas " + where);
            ResultSetMetaData Mdatos = datos.getMetaData();

            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            int columna = 1;

            while (columna <= Mdatos.getColumnCount()) {
                modelo.addColumn(Mdatos.getColumnName(columna));
                columna++;
            }

            while (datos.next()) {

                modelo.addRow(new Object[]{datos.getString("nombres"), datos.getString("cedula"), datos.getString("direccion"), datos.getString("email"), datos.getString("Actividad")});

            }

            jTabla.setModel(modelo);
            jTabla.setAutoResizeMode(4);

// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(consultas.Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rellenarCasillas() {
        try {

            ResultSet datos = conexion.ejecutarSQLSelect("select nombres as nombres ,cedula,direccion,email, activo from personas where cedula='" + cedulatxt.getText() + "'");

            while (datos.next()) {

                emailtxt.setText(datos.getString("email"));
                direcciontxt.setText(datos.getString("direccion"));
                nombretxt.setText(datos.getString("nombres"));
                if (datos.getString("activo").equals("1")) {
                    activoradio.setSelected(true);
                } else {
                    inactivoradio.setSelected(true);
                }

            }

            datos = conexion.ejecutarSQLSelect("select telefono from personas inner join telefono on idpersonas=personas_idpersonas where cedula='" + cedulatxt.getText() + "'");

            DefaultListModel modelo = new DefaultListModel();
            while (datos.next()) {
                modelo.addElement(datos.getString("telefono"));

            }
            telefonolist.setModel(modelo);

            datos = conexion.ejecutarSQLSelect("select numero_medidor from personas inner join medidor on idpersonas=personas_idpersonas where cedula='" + cedulatxt.getText() + "'");

            DefaultListModel modelo2 = new DefaultListModel();
            while (datos.next()) {
                modelo2.addElement(datos.getString("numero_medidor"));

            }
            medidoreslist.setModel(modelo2);
// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(consultas.Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        cedulatxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nombretxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        direcciontxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        emailtxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        activoradio = new javax.swing.JRadioButton();
        inactivoradio = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable(){public boolean isCellEditable(int row, int column){
            return false;
        }};
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        telefonolist = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        agregarTelefono = new javax.swing.JButton();
        eliminarTelefono = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        medidoreslist = new javax.swing.JList<>();
        agregarMedidor = new javax.swing.JButton();
        eliminarMedidor = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMINISTRAR CLIENTES");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cedulatxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulatxtActionPerformed(evt);
            }
        });
        cedulatxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cedulatxtKeyReleased(evt);
            }
        });
        getContentPane().add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 32, 441, -1));

        jLabel1.setText("Cédula");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 32, -1, -1));

        nombretxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretxtActionPerformed(evt);
            }
        });
        nombretxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombretxtKeyReleased(evt);
            }
        });
        getContentPane().add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 75, 441, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 80, -1, -1));
        getContentPane().add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 124, 441, -1));

        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 124, -1, -1));
        getContentPane().add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 173, 441, -1));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 173, -1, -1));

        jLabel5.setText("Actividad");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        buttonGroup1.add(activoradio);
        activoradio.setText("Activo");
        getContentPane().add(activoradio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));

        buttonGroup1.add(inactivoradio);
        inactivoradio.setText("Inactivo");
        getContentPane().add(inactivoradio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, -1, -1));

        jLabel6.setText("Lista de Clientes");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 0, -1, -1));

        jTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSM = jTabla.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            //agrego el evento para detectar el cambio de seleccion de filas en la tabla
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) return;

                ListSelectionModel lsm =
                (ListSelectionModel)e.getSource();

                if (lsm.isSelectionEmpty()) {

                } else {
                    // System.out.println(jTabla.getSelectedRow()) ;

                    if(jTabla.getValueAt( jTabla.getSelectedRow(),0)==null){
                        nombretxt.setText("");
                    }else{
                        nombretxt.setText(jTabla.getValueAt( jTabla.getSelectedRow(),0).toString());
                    }

                    if(jTabla.getValueAt(jTabla.getSelectedRow(),1)==null){
                        cedulatxt.setText("");
                        cedulatxt.setEnabled(true);
                        agregarTelefono.setEnabled(false);
                        agregarMedidor.setEnabled(false);
                        eliminarTelefono.setEnabled(false);
                        eliminarMedidor.setEnabled(false);
                    }else{
                        cedulatxt.setText(jTabla.getValueAt(jTabla.getSelectedRow(),1).toString());
                        //cedulatxt.setEnabled(false);
                        agregarTelefono.setEnabled(true);
                        agregarMedidor.setEnabled(true);
                        eliminarTelefono.setEnabled(true);
                        eliminarMedidor.setEnabled(true);
                    }

                    if(jTabla.getValueAt( jTabla.getSelectedRow(),2)==null){
                        direcciontxt.setText("");
                    }else{
                        direcciontxt.setText(jTabla.getValueAt( jTabla.getSelectedRow(),2).toString());
                    }

                    if(jTabla.getValueAt( jTabla.getSelectedRow(),3)==null){
                        emailtxt.setText("");
                    }else{
                        emailtxt.setText(jTabla.getValueAt( jTabla.getSelectedRow(),3).toString());
                    }

                    if(jTabla.getValueAt( jTabla.getSelectedRow(),4).toString().equals("ACTIVO"))
                    {
                        activoradio.setSelected(true);
                    }else{
                        inactivoradio.setSelected(true);
                    };

                    rellenarCasillas();

                }
            }
        });
        jTabla.setAutoCreateRowSorter(true);
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(599, 32, 900, -1));

        jLabel7.setText("Teléfonos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jScrollPane2.setViewportView(telefonolist);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 441, 67));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 530, 150, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 530, 160, 50));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/refrescar.png"))); // NOI18N
        jButton3.setText("Limpiar/Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 531, 224, 50));

        agregarTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas (1).png"))); // NOI18N
        agregarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(agregarTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        eliminarTelefono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/menos.png"))); // NOI18N
        eliminarTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jLabel8.setText("Medidores");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jScrollPane3.setViewportView(medidoreslist);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 440, 67));

        agregarMedidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas (1).png"))); // NOI18N
        agregarMedidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarMedidorActionPerformed(evt);
            }
        });
        getContentPane().add(agregarMedidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        eliminarMedidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/menos.png"))); // NOI18N
        eliminarMedidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMedidorActionPerformed(evt);
            }
        });
        getContentPane().add(eliminarMedidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/usuario.png"))); // NOI18N
        jButton8.setText("Nuevo");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 530, 130, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents
String valor = "";
    private void cedulatxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cedulatxtKeyReleased
        cargarDatos("where cedula like '%" + cedulatxt.getText() + "%'");
// TODO add your handling code here:
    }//GEN-LAST:event_cedulatxtKeyReleased

    private void nombretxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtActionPerformed

    private void nombretxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombretxtKeyReleased
        cargarDatos("where nombres like '%" + nombretxt.getText() + "%'");
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretxtKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cargarDatos("");        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void cedulatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulatxtActionPerformed
if(cedulatxt.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Debe ingresar una cédula", "Error", JOptionPane.ERROR_MESSAGE);


}else{

        try {
            ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as num from personas where cedula='" + cedulatxt.getText() + "'");
            int numero = 0;
            while (datos.next()) {
                numero = Integer.parseInt(datos.getString("num"));

            }

            if (numero == 0) {
                if (JOptionPane.showConfirmDialog(null, "Esta cédula no existe. Desea ingresar un nuevo cliente?", "Nuevo Vliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    nuevoCliente a = new nuevoCliente();
                    a.setVisible(true);
                }
                agregarTelefono.setEnabled(false);
agregarMedidor.setEnabled(false);
eliminarTelefono.setEnabled(false);
eliminarMedidor.setEnabled(false);
            }else{
                cedulatxt.setEnabled(false);
    agregarTelefono.setEnabled(true);
agregarMedidor.setEnabled(true);
eliminarTelefono.setEnabled(true);
eliminarMedidor.setEnabled(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        rellenarCasillas(); 
}// TODO add your handling code here:
    }//GEN-LAST:event_cedulatxtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 cedulatxt.setEnabled(true);
    agregarTelefono.setEnabled(true);
agregarMedidor.setEnabled(true);
eliminarTelefono.setEnabled(true);
eliminarMedidor.setEnabled(true);

        cedulatxt.setText("");
        nombretxt.setText("");
        direcciontxt.setText("");
        emailtxt.setText("");
        DefaultListModel modelo = new DefaultListModel();
        modelo.clear();
        telefonolist.setModel(modelo);
        medidoreslist.setModel(modelo);
        cargarDatos("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Está seguro que desea guardar los cambios", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {        // a.ejecutarSQL("UPDATE producto SET Descripcion='"+jDescripcion.getText()+"', Precio = "+jPrecio.getText()+", disponible="+jCantidad.getText()+" WHERE idProducto="+id+";") ;
            try {
                if (nombretxt.getText().equals("") & cedulatxt.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un nombre o una cédula", "Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as nombres from personas  where nombres='" + nombretxt.getText() + "'");
                    String nomb = "";
                    String ced = "";
                    int activo = 0;
                    if (activoradio.isSelected()) {
                        activo = 1;
                    }
                    while (datos.next()) {
                        nomb = datos.getString("nombres");
                    }
                    if (nomb.equals("0")) {

                    } else {
                        if (cedulatxt.getText().equals("")) {
                            conexion.ejecutarSQL("update personas set  direccion='" + direcciontxt.getText() + "',email='" + emailtxt.getText() + "',activo='" + activo + "' where nombres='" + nombretxt.getText() + "'");

                        } else {
                            conexion.ejecutarSQL("update personas set cedula='" + cedulatxt.getText() + "', direccion='" + direcciontxt.getText() + "',email='" + emailtxt.getText() + "',activo='" + activo + "' where nombres='" + nombretxt.getText() + "'");
                        }
                    }

                    datos = conexion.ejecutarSQLSelect("select count(*) as cedulas from personas  where cedula='" + cedulatxt.getText() + "'");
                    while (datos.next()) {
                        ced = datos.getString("cedulas");
                    }

                    if (ced.equals("0") & nomb.equals("0")) {
                        JOptionPane.showMessageDialog(this, "Debe ingresar el nombre o la cédula de un cliente existente", "Error", JOptionPane.ERROR_MESSAGE);

                    } else {
                        if (ced.equals("0")) {
                        } else {
                            conexion.ejecutarSQL("update personas set nombres='" + nombretxt.getText() + "', direccion='" + direcciontxt.getText() + "',email='" + emailtxt.getText() + "',activo='" + activo + "' where cedula='" + cedulatxt.getText() + "'");
                        }
                    }

                    cargarDatos("");
                    jButton3.doClick();
                }

            } catch (SQLException ex) {
                Logger.getLogger(consultas.Clientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void agregarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarTelefonoActionPerformed
        if (nombretxt.getText().equals("") || cedulatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre o cédula existente", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            telefonoAdmin a = new telefonoAdmin(cedulatxt.getText());
            a.setVisible(true);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_agregarTelefonoActionPerformed

    private void eliminarTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTelefonoActionPerformed
        if (telefonolist.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un teléfono", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            conexion.ejecutarSQL("delete from telefono where telefono='" + telefonolist.getSelectedValue() + "' and personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "' or nombres='" + nombretxt.getText() + "')");
            rellenarCasillas();

        };        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarTelefonoActionPerformed

    private void agregarMedidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarMedidorActionPerformed
        if (nombretxt.getText().equals("") || cedulatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un nombre o cédula existente", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            medidoresAdmin a = new medidoresAdmin(cedulatxt.getText());
            a.setVisible(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_agregarMedidorActionPerformed

    private void eliminarMedidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMedidorActionPerformed
        if (medidoreslist.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un medidor", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            conexion.ejecutarSQL("delete from medidor where numero_medidor='" + medidoreslist.getSelectedValue() + "' and personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "' or nombres='" + nombretxt.getText() + "')");
            rellenarCasillas();

        };         // TODO add your handling code here:
    }//GEN-LAST:event_eliminarMedidorActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        nuevoCliente a = new nuevoCliente();
        a.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(ClientesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientesAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton activoradio;
    private javax.swing.JButton agregarMedidor;
    private javax.swing.JButton agregarTelefono;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JTextField direcciontxt;
    private javax.swing.JButton eliminarMedidor;
    private javax.swing.JButton eliminarTelefono;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JRadioButton inactivoradio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTabla;
    private javax.swing.JList<String> medidoreslist;
    private javax.swing.JTextField nombretxt;
    private javax.swing.JList<String> telefonolist;
    // End of variables declaration//GEN-END:variables
}
