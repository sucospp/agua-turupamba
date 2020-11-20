/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;

import Administrar.*;
import BaseDatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class consultaFactura extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    public consultaFactura() {
        initComponents();
    }
    ConexionBD conexion = new ConexionBD();
    DefaultTableModel modelo = new DefaultTableModel();

    public void cargarDatos(String where) {
        try {

            ResultSet datos = conexion.ejecutarSQLSelect("select nombres as nombres ,cedula from personas " + where);
            ResultSetMetaData Mdatos = datos.getMetaData();

            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            int columna = 1;

            while (columna <= Mdatos.getColumnCount()) {
                modelo.addColumn(Mdatos.getColumnName(columna));
                columna++;
            }

            while (datos.next()) {

                modelo.addRow(new Object[]{datos.getString("nombres"), datos.getString("cedula")});

            }

            jTabla.setModel(modelo);
            conexion.cerrarConexion();
// TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(consultas.Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenarFactura() throws SQLException {

        reciboMes.setText("");
        cobroBasico.setText("");
        lecturaActual.setText("");
        ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as contar from facturas where personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"') and year(fecha_factura)="+ String.valueOf(Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaCalendar.getDate()))) +" and month(fecha_factura)=" + String.valueOf(Integer.parseInt(new SimpleDateFormat("MM").format(fechaCalendar.getDate()))) + " ");
        int cont=1;
        while (datos.next()) {
                    if(datos.getString("contar").equals("0")){
           // System.out.println("lala no hay ");
           cont=0;
            }
        }

        if(cont==1){
        
         datos = conexion.ejecutarSQLSelect("select idfacturas from facturas where personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"') and year(fecha_factura)="+ String.valueOf(Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaCalendar.getDate()))) +" and month(fecha_factura)=" + String.valueOf(Integer.parseInt(new SimpleDateFormat("MM").format(fechaCalendar.getDate()))) + " ");

        while (datos.next()) {

            numeroFactura.setText(String.valueOf(Integer.parseInt(datos.getString("idfacturas")) ));
            System.out.println("lala "+datos.getString("idfacturas"));
        }
        conexion.cerrarConexion();


      datos = conexion.ejecutarSQLSelect("SELECT  fecha_factura from facturas where idfacturas="+numeroFactura.getText()+" ");

        while (datos.next()) {

            fechaActual1.setText(datos.getString("fecha_factura"));

        }
        conexion.cerrarConexion();
        
        
        
        
        datos = conexion.ejecutarSQLSelect("SELECT  mes_recibo from facturas where personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"') and year(fecha_factura)="+ String.valueOf(Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaCalendar.getDate()))) +" and month(fecha_factura)=" + String.valueOf(Integer.parseInt(new SimpleDateFormat("MM").format(fechaCalendar.getDate()))) + " ");

        while (datos.next()) {

            reciboMes.setText(datos.getString("mes_recibo"));

        }
        conexion.cerrarConexion();

        /*datos = conexion.ejecutarSQLSelect("SELECT Lectura from lecturas where MONTH( DATE_ADD(CURDATE(), INTERVAL -1 MONTH)) = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");

        while (datos.next()) {

            lecturaActual.setText(datos.getString("Lectura"));

        }
        conexion.cerrarConexion();

        datos = conexion.ejecutarSQLSelect("SELECT Lectura from lecturas where MONTH( DATE_ADD(CURDATE(), INTERVAL -2 MONTH)) = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");

        while (datos.next()) {

            lecturaAnterior.setText(datos.getString("Lectura"));

        }

        conexion.cerrarConexion();*/

        datos = conexion.ejecutarSQLSelect("select valor from configuracion where idconfiguracion=3");

        while (datos.next()) {

            cobroBasico.setText(datos.getString("valor"));

        }
        conexion.cerrarConexion();

       datos = conexion.ejecutarSQLSelect("select lectura from lecturas inner join factura_detalle_lecturas on lecturas_idlecturas=idlecturas inner join facturas a on facturas_idfacturas=idfacturas where year(fecha_factura)="+ String.valueOf(Integer.parseInt(new SimpleDateFormat("yyyy").format(fechaCalendar.getDate()))) +" and month(fecha_factura)="+ String.valueOf(Integer.parseInt(new SimpleDateFormat("MM").format(fechaCalendar.getDate()))) +" and a.personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"')");

        while (datos.next()) {

            lecturaActual.setText(datos.getString("lectura"));

        }
        conexion.cerrarConexion();

        
           DefaultTableModel modelo2 = (DefaultTableModel) jMultas.getModel();

        modelo2.setRowCount(0);
        
         datos = conexion.ejecutarSQLSelect("select descripcion_multa,valor from factura_detalle_multas inner join facturas on facturas_idfacturas=idfacturas inner join multas on multas_idmultas=idmultas where idfacturas="+numeroFactura.getText()+"");

        while (datos.next()) {

        modelo2.addRow(new Object[]{datos.getString("descripcion_multa"), datos.getString("valor")});

        }
        conexion.cerrarConexion();

        
        datos = conexion.ejecutarSQLSelect("select total from facturas where idfacturas="+ numeroFactura.getText()+"");

        while (datos.next()) {

        Total.setText(datos.getString("total"));

        }
        conexion.cerrarConexion();

     datos = conexion.ejecutarSQLSelect("select numero_medidor from medidor inner join facturas on medidor_idmedidor=idmedidor and idfacturas="+numeroFactura.getText());

        while (datos.next()) {

        medidortxt.setText(datos.getString("numero_medidor"));

        }
        conexion.cerrarConexion();


        }else{
          JOptionPane.showMessageDialog(this, "Esa factura no existe", "Error", JOptionPane.ERROR_MESSAGE);
          jButton3.doClick();
        
        }
        
  

    }

    public void rellenarCasillas() {
        try {

            ResultSet datos = conexion.ejecutarSQLSelect("select nombres as nombres ,cedula,direccion,email, activo from personas where cedula='" + cedulatxt.getText() + "'");

            while (datos.next()) {

                emailtxt.setText(datos.getString("email"));
                direcciontxt.setText(datos.getString("direccion"));
                nombretxt.setText(datos.getString("nombres"));

            }
            conexion.cerrarConexion();
            datos = conexion.ejecutarSQLSelect("select telefono from personas inner join telefono on idpersonas=personas_idpersonas where cedula='" + cedulatxt.getText() + "'");

            DefaultListModel modelo = new DefaultListModel();
            while (datos.next()) {
                modelo.addElement(datos.getString("telefono"));

            }
            telefonolist.setModel(modelo);
            conexion.cerrarConexion();

            datos = conexion.ejecutarSQLSelect("select numero_medidor from personas inner join medidor on idpersonas=personas_idpersonas where cedula='" + cedulatxt.getText() + "'");

            DefaultListModel modelo2 = new DefaultListModel();
            while (datos.next()) {
                modelo2.addElement(datos.getString("numero_medidor"));

            }
            medidoreslist.setModel(modelo2);
            if (modelo2.getSize() != 0) {
                medidoreslist.setSelectedIndex(0);
            }

            conexion.cerrarConexion();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable(){public boolean isCellEditable(int row, int column){
            return false;
        }};
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        telefonolist = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        medidoreslist = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numeroFactura = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        reciboMes = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cobroBasico = new javax.swing.JTextField();
        fechaActual1 = new javax.swing.JLabel();
        fechaActual2 = new javax.swing.JLabel();
        lecturaActual = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jMultas = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        medidortxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        fechaCalendar = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA FACTURAS");
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
        getContentPane().add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 188, -1));

        jLabel1.setText("Cédula");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        nombretxt.setEditable(false);
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
        getContentPane().add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 270, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        direcciontxt.setEditable(false);
        getContentPane().add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 210, -1));

        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, -1, -1));

        emailtxt.setEditable(false);
        getContentPane().add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 220, 30));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

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

                    }else{
                        cedulatxt.setText(jTabla.getValueAt(jTabla.getSelectedRow(),1).toString());
                        cedulatxt.setEnabled(false);

                    }

                    rellenarCasillas();
                    try {
                        llenarFactura();
                    } catch (SQLException ex) {
                        Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
                    }
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 363, -1));

        jLabel7.setText("Teléfonos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        telefonolist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(telefonolist);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 160, 67));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 540, 160, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/goma-de-borrar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 390, 160, 41));

        jLabel8.setText("Medidores");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        medidoreslist.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(medidoreslist);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 160, 67));

        jLabel5.setText("Lista de Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, -10, 20, 640));

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel9.setText("FACTURA");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, -1, -1));

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel10.setText("CLIENTES");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel6.setText("Número");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, -1, -1));

        numeroFactura.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        getContentPane().add(numeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 0, 60, 30));

        jLabel11.setText("Recibo del Mes de");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));

        reciboMes.setEditable(false);
        getContentPane().add(reciboMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 130, 160, -1));

        jLabel12.setText("Fecha");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        jLabel13.setText("Valor por Cobro Básico($)");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, -1, -1));

        cobroBasico.setEditable(false);
        getContentPane().add(cobroBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 160, -1));
        getContentPane().add(fechaActual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 110, 20));

        fechaActual2.setText("Lectura Actual");
        getContentPane().add(fechaActual2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, 100, -1));

        lecturaActual.setEditable(false);
        getContentPane().add(lecturaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 240, 160, -1));

        jMultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jMultas);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 310, 310, 130));

        jLabel14.setText("Medidor");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, -1, -1));

        medidortxt.setEditable(false);
        getContentPane().add(medidortxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 160, -1));

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel15.setText("Total");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 470, -1, -1));

        Total.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        Total.setText("0.00");
        getContentPane().add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 470, -1, -1));

        jLabel17.setText("Fecha Búsqueda");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        getContentPane().add(fechaCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 170, 40));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/busqueda.png"))); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 160, 40));

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
        cargarDatos("");
        
        try {
            ResultSet datos = conexion.ejecutarSQLSelect(" Select  DATE_FORMAT(curdate(), \"%Y-%m-%d\")  as fecha");
            
                while (datos.next()) {
         fechaCalendar.setDate(datos.getDate("fecha"));

            //numeroFactura.setText(String.valueOf(Integer.parseInt(datos.getString("idfacturas")) + 1));

        }
        conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(consultaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void cedulatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulatxtActionPerformed
        if (cedulatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cédula", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

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

                } else {
                    cedulatxt.setEnabled(false);

                }

            } catch (SQLException ex) {
                Logger.getLogger(consultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            }

            rellenarCasillas();
            try {
                llenarFactura();
            } catch (SQLException ex) {
                Logger.getLogger(consultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_cedulatxtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cedulatxt.setEnabled(true);

        cedulatxt.setText("");
        nombretxt.setText("");
        direcciontxt.setText("");
        emailtxt.setText("");
        reciboMes.setText("");
        cobroBasico.setText("");
        lecturaActual.setText("");
        DefaultListModel modelo = new DefaultListModel();
        modelo.clear();
        telefonolist.setModel(modelo);
        medidoreslist.setModel(modelo);
        DefaultTableModel modelo2 = (DefaultTableModel) jMultas.getModel();

        //modelo.setRowCount(0);
        modelo2.setRowCount(0);
        cargarDatos("");
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 if (cedulatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cédula", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

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

                } else {
                    cedulatxt.setEnabled(false);

                }

            } catch (SQLException ex) {
                Logger.getLogger(consultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            }

            rellenarCasillas();
            try {
                llenarFactura();
            } catch (SQLException ex) {
                Logger.getLogger(consultaFactura.class.getName()).log(Level.SEVERE, null, ex);
            }  
 }// TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(consultaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultaFactura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Total;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cedulatxt;
    private javax.swing.JTextField cobroBasico;
    private javax.swing.JTextField direcciontxt;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JLabel fechaActual1;
    private javax.swing.JLabel fechaActual2;
    private com.toedter.calendar.JDateChooser fechaCalendar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTable jMultas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabla;
    private javax.swing.JTextField lecturaActual;
    private javax.swing.JList<String> medidoreslist;
    private javax.swing.JTextField medidortxt;
    private javax.swing.JTextField nombretxt;
    private javax.swing.JLabel numeroFactura;
    private javax.swing.JTextField reciboMes;
    private javax.swing.JList<String> telefonolist;
    // End of variables declaration//GEN-END:variables
}
