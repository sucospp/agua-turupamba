/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrar;

import BaseDatos.ConexionBD;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import imprimir.ObjetoDeImpresion;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
public class Facturar extends javax.swing.JFrame {

    /**
     * Creates new form Clientes
     */
    public Facturar() {
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

        reciboMes.setText(listaMes.getSelectedItem().toString());
        reciboAno.setText(listaAno.getSelectedItem().toString());
        cobroBasico.setText("");
        lecturaAnterior.setText("");
        lecturaActual.setText("");
        ResultSet datos = conexion.ejecutarSQLSelect("select case when max(idfacturas) is null then '0' else max(idfacturas) end as idFacturas from facturas ");

        while (datos.next()) {

            numeroFactura.setText(String.valueOf(Integer.parseInt(datos.getString("idfacturas")) + 1));

        }
        conexion.cerrarConexion();

        datos = conexion.ejecutarSQLSelect("SELECT  CURDATE() as fechaActual");

        while (datos.next()) {

            fechaActual1.setText(datos.getString("fechaActual"));

        }
        conexion.cerrarConexion();

       /* datos = conexion.ejecutarSQLSelect("SELECT  Case when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=1 then 'ENERO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=2 then 'FEBRERO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=3 then 'MARZO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=4 then 'ABRIL'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=5 then 'MAYO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=6 then 'JUNIO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=7 then 'JULIO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=8 then 'AGOSTO'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=9 then 'SEPTIEMBRE'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=10 then 'OCTUBRE'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=11 then 'NOVIEMBRE'\n"
                + "when MONTH(DATE_ADD(CURDATE(), INTERVAL -1 MONTH))=12 then 'DICIEMBRE'\n"
                + " end as fechaAnterior");

        while (datos.next()) {

            reciboMes.setText(datos.getString("fechaAnterior"));

        }
        conexion.cerrarConexion();*/

        datos = conexion.ejecutarSQLSelect("SELECT Lectura from lecturas where  "+listaAno.getSelectedItem().toString()+" = year(Fecha_lectura) and "+listaMes.getSelectedIndex()+" = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");
//System.out.println("SELECT Lectura from lecturas where  year( DATE_ADD('"+fechaActual1.getText().split("-")[0]+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) = year(Fecha_lectura) and "+listaMes.getSelectedIndex()+" = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");
        while (datos.next()) {

            lecturaActual.setText(datos.getString("Lectura"));

        }
        conexion.cerrarConexion();

        datos = conexion.ejecutarSQLSelect("SELECT Lectura from lecturas where year( DATE_ADD('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) = year(Fecha_lectura) and  MONTH( DATE_ADD('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");

        //System.out.println("SELECT Lectura from lecturas where year( DATE_ADD('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) = year(Fecha_lectura) and  MONTH( DATE_ADD('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) = MONTH(Fecha_lectura) and Personas_idPersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");
        while (datos.next()) {
System.out.println();
            lecturaAnterior.setText(datos.getString("Lectura"));

        }

        conexion.cerrarConexion();

        datos = conexion.ejecutarSQLSelect("select valor from configuracion where idconfiguracion=3");

        while (datos.next()) {

            cobroBasico.setText(datos.getString("valor"));

        }
        conexion.cerrarConexion();

        listaMultas.removeAllItems();
        datos = conexion.ejecutarSQLSelect("select descripcion_multa from multas where idmultas >2 ");
        listaMultas.addItem("Seleccione multa");
        while (datos.next()) {

            listaMultas.addItem(datos.getString("descripcion_multa"));

        }
        conexion.cerrarConexion();

        double valor = 0;
        double multaExceso = 0;
double valorRestado=Double.parseDouble(lecturaActual.getText()) - Double.parseDouble( lecturaAnterior.getText());
        //calculo las multas por exceso
        if (lecturaActual.getText().equals("")) {

        } else {

            if (valorRestado <= 15) {
                multaExceso = 0;
            } else {
                if (valorRestado >15 && valorRestado <= 20) {
                    multaExceso = 0.50;
                } else {
                    if (valorRestado > 20 && valorRestado <= 30) {
                        multaExceso = 0.75;
                    } else {
                        if (valorRestado > 30) {

                            multaExceso = (valorRestado-30);
                        }

                    }

                }

            }
        }

        /*datos = conexion.ejecutarSQLSelect("select count(*) as multa from Lista where asiste=0 and fecha_asistencia<= last_day( DATE_ADD(CURDATE(), INTERVAL -1 MONTH)) and  personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");
        double multaAsamblea = 0;
        while (datos.next()) {
            
            multaAsamblea = Double.parseDouble(datos.getString("multa"));
            
        }
        conexion.cerrarConexion();*/
 /* datos = conexion.ejecutarSQLSelect("select count(*) as multa from Lista_MInga where asiste=0 and fecha_minga<=last_day( DATE_ADD(CURDATE(), INTERVAL -1 MONTH)) and personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')");
        double multaMinga = 0;
        while (datos.next()) {
            
            multaMinga = Double.parseDouble(datos.getString("multa"));
            
        }
        conexion.cerrarConexion();*/
 
 
      //   datos = conexion.ejecutarSQLSelect("select count(idfacturas) as moras, personas_idpersonas from facturas where total is null and fecha_factura<last_day( DATE_ADD('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', INTERVAL -1 MONTH)) and personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"') group by personas_idpersonas having count(idfacturas) >2");
             //datos = conexion.ejecutarSQLSelect("select -month(curdate()) - month('"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01') as moras ");
             datos = conexion.ejecutarSQLSelect("SELECT TIMESTAMPDIFF(month, '"+listaAno.getSelectedItem().toString()+"-"+listaMes.getSelectedIndex()+"-01', curdate()) as moras ");

//listaMultas.addItem("Seleccione multa");
        double multaMora=0;
        int cantMesMora=0;
        while (datos.next()) {
            cantMesMora=Integer.parseInt(datos.getString("moras"));
            //multaMora=Double.parseDouble(datos.getString("moras")) *0.5;
           // listaMultas.addItem(datos.getString("descripcion_multa"));

        }
        //System.out.print(cantMesMora+"");
        conexion.cerrarConexion();
 if(cantMesMora>2){
 multaMora=(cantMesMora-2)*0.5;
 }
 
        DefaultTableModel modelo2 = (DefaultTableModel) jMultas.getModel();

        modelo2.setRowCount(0);

        modelo2.addRow(new Object[]{"MULTA POR EXCESO", multaExceso});
        modelo2.addRow(new Object[]{"MULTA POR MORA", multaMora});

        //modelo2.addRow(new Object[]{"MULTA POR ASAMBLEA", (multaAsamblea * 15)});
        // modelo2.addRow(new Object[]{"MULTA POR MINGA", (multaMinga * 25)});

        if (medidoreslist.getModel().getSize() != 0) {
            medidortxt.setText(medidoreslist.getSelectedValue().toString());
        } else {
            medidortxt.setText("");
        }

        double total = 0;
        total = Double.parseDouble(cobroBasico.getText());
        double subtotal = 0;
        for (int i = 0; i < jMultas.getRowCount(); i++) {
            subtotal = subtotal + Double.parseDouble(jMultas.getValueAt(i, 1).toString());

        }
        total = total + subtotal;
        Total.setText(total + "");
        System.out.println("total " + total + " subtotal " + subtotal);

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
        jButton1 = new javax.swing.JButton();
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
        fechaActual = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cobroBasico = new javax.swing.JTextField();
        fechaActual1 = new javax.swing.JLabel();
        lecturaAnterior = new javax.swing.JTextField();
        fechaActual2 = new javax.swing.JLabel();
        lecturaActual = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jMultas = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        medidortxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        listaMultas = new javax.swing.JComboBox<>();
        listaMes = new javax.swing.JComboBox<>();
        listaAno = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        reciboAno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURAR");
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
        getContentPane().add(cedulatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 188, -1));

        jLabel1.setText("Cédula");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

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
        getContentPane().add(nombretxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 270, -1));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        direcciontxt.setEditable(false);
        getContentPane().add(direcciontxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, -1));

        jLabel3.setText("Dirección");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        emailtxt.setEditable(false);
        getContentPane().add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 220, 30));

        jLabel4.setText("Email");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

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

                        if(listaAno.getSelectedIndex()==0 || listaMes.getSelectedIndex()==0){
                            JOptionPane.showMessageDialog(null, "Debe el mes y año del Recibo o lectura", "Error", JOptionPane.ERROR_MESSAGE);

                        }else{
                            llenarFactura();

                        }
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 363, -1));

        jLabel7.setText("Teléfonos");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        jScrollPane2.setViewportView(telefonolist);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 160, 67));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 590, 150, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 590, 150, 40));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/goma-de-borrar.png"))); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 160, 41));

        jLabel8.setText("Medidores");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, -1, -1));

        jScrollPane3.setViewportView(medidoreslist);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 160, 67));

        jLabel5.setText("Lista de Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 20, 640));

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
        getContentPane().add(numeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 0, 60, 20));

        jLabel11.setText("Recibo del Mes de");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, -1, -1));

        reciboMes.setEditable(false);
        getContentPane().add(reciboMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 130, 160, -1));

        jLabel12.setText("Fecha");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        fechaActual.setText("Lectura Anterior");
        getContentPane().add(fechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 230, 120, -1));

        jLabel13.setText("Valor por Cobro Básico($)");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, -1, -1));

        cobroBasico.setEditable(false);
        getContentPane().add(cobroBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 160, -1));
        getContentPane().add(fechaActual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 110, 20));

        lecturaAnterior.setEditable(false);
        getContentPane().add(lecturaAnterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 230, 160, -1));

        fechaActual2.setText("Lectura Actual");
        getContentPane().add(fechaActual2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 100, -1));

        lecturaActual.setEditable(false);
        getContentPane().add(lecturaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 280, 160, -1));

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

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 360, 310, 130));

        jLabel14.setText("Medidor");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, -1, -1));

        medidortxt.setEditable(false);
        getContentPane().add(medidortxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, 160, -1));

        jLabel15.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel15.setText("Total");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 520, -1, -1));

        Total.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        Total.setText("0.00");
        getContentPane().add(Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 520, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/menos.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 440, 40, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/mas (1).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 390, 40, -1));

        jLabel16.setText("Multas");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, -1, -1));

        getContentPane().add(listaMultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 320, 210, -1));

        listaMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Mes Recibo", "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        getContentPane().add(listaMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        listaAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Año Recibo", "2018", "2019", "2020", "2021", "2022", "2023" }));
        getContentPane().add(listaAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jLabel17.setText("Mes/Año recibo");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        reciboAno.setEditable(false);
        getContentPane().add(reciboAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 130, 160, -1));

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
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void cedulatxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulatxtActionPerformed
        if (cedulatxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una cédula", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            if(listaAno.getSelectedIndex()==0 || listaMes.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this, "Debe el mes y año del Recibo o lectura", "Error", JOptionPane.ERROR_MESSAGE);

            
            }else{
            
            try {
                ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as num from personas where cedula='" + cedulatxt.getText() + "'");
                int numero = 0;
                while (datos.next()) {
                    numero = Integer.parseInt(datos.getString("num"));

                }

                if (numero == 0) {
                    if (JOptionPane.showConfirmDialog(null, "Esta cédula no existe. Desea ingresar un nuevo cliente?", "Nuevo Cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                        nuevoCliente a = new nuevoCliente();
                        a.setVisible(true);
                    }

                } else {
                    cedulatxt.setEnabled(false);

                }

            } catch (SQLException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }

            rellenarCasillas();
            try {
                llenarFactura();
            } catch (SQLException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_cedulatxtActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cedulatxt.setEnabled(true);
        Total.setText("0.00");
        cedulatxt.setText("");
        nombretxt.setText("");
        direcciontxt.setText("");
        emailtxt.setText("");
        reciboMes.setText("");
        reciboAno.setText("");
        cobroBasico.setText("");
        lecturaAnterior.setText("");
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
/*       int fact=0;
        try {
            ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as facturas from facturas where personas_idpersonas=(select idpersonas from personas where cedula='"+cedulatxt.getText()+"') and month(fecha_factura)=month(curdate())");
       
           while (datos.next()) {

            fact=Integer.parseInt(datos.getString("facturas"));

        }
        conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
        }

     

if(fact==0){*/
        if (JOptionPane.showConfirmDialog(null, "Está seguro que desea guardar la factura", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {        // a.ejecutarSQL("UPDATE producto SET Descripcion='"+jDescripcion.getText()+"', Precio = "+jPrecio.getText()+", disponible="+jCantidad.getText()+" WHERE idProducto="+id+";") ;
            if (nombretxt.getText().equals("") & cedulatxt.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un nombre o una cédula", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    conexion.crearConexion();
                    conexion.ejecutarSQL("insert into facturas(idfacturas,total,fecha_factura,personas_idpersonas,medidor_idmedidor,mes_recibo) values ("+numeroFactura.getText()+"," + Total.getText() + ",'" + fechaActual1.getText() + "',(select idpersonas from personas where cedula = '" + cedulatxt.getText() + "'),(select idmedidor from medidor where numero_medidor = '" + medidortxt.getText() + "'),'" + reciboMes.getText() + "')");
System.out.println("insert into facturas(idfacturas,total,fecha_factura,personas_idpersonas,medidor_idmedidor,mes_recibo) values ("+numeroFactura.getText()+"," + Total.getText() + ",'" + fechaActual1.getText() + "',(select idpersonas from personas where cedula = '" + cedulatxt.getText() + "'),(select idmedidor from medidor where numero_medidor = '" + medidortxt.getText() + "'),'" + reciboMes.getText() + "')");
                    conexion.ejecutarSQL("insert into factura_detalle_lecturas(facturas_idfacturas,lecturas_idlecturas) values (" + numeroFactura.getText() + ",(select idlecturas from lecturas where "+listaAno.getSelectedItem().toString()+" = year(Fecha_lectura) and month(fecha_lectura) = "+listaMes.getSelectedIndex()+" and personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')\n"
                            + "))");

System.out.println("insert into factura_detalle_lecturas(facturas_idfacturas,lecturas_idlecturas) values (" + numeroFactura.getText() + ",(select idlecturas from lecturas where "+listaAno.getSelectedItem().toString()+" = year(Fecha_lectura) and month(fecha_lectura) = "+listaMes.getSelectedIndex()+" and personas_idpersonas=(select idpersonas from personas where cedula='" + cedulatxt.getText() + "')\n"
                            + "))");
                    for (int i=0; i< jMultas.getRowCount();i++){
                     System.out.println(jMultas.getValueAt(i, 0)+"");
                        conexion.ejecutarSQL("insert into factura_detalle_multas(Facturas_idFacturas,multas_idmultas,valor) VALUES ("+ numeroFactura.getText() +",(select idmultas from multas where descripcion_multa ='"+jMultas.getValueAt(i, 0)+"'),"+jMultas.getValueAt(i, 1)+")");
System.out.println("insert into factura_detalle_multas(Facturas_idFacturas,multas_idmultas,valor) VALUES ("+ numeroFactura.getText() +",(select idmultas from multas where descripcion_multa ='"+jMultas.getValueAt(i, 0)+"'),"+jMultas.getValueAt(i, 1)+")");
                        
                    }
                    
                    String facturaImpresa= "FECHA: "+fechaActual1.getText()+ " \n"
                            +"CÉDULA: "+cedulatxt.getText()+ " \n"
                            + "NOMBRE: "+ nombretxt.getText()+ " \n"
                            + "DIRECCIÓN: "+direcciontxt.getText()+ " \n"
                            +   "";




                   
                    
                    
                    imprimir();
                    JOptionPane.showMessageDialog(this, "Factura creada correctamente", "Ingresado", JOptionPane.INFORMATION_MESSAGE);
                    jButton3.doClick();
                    conexion.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
/*}else{
        JOptionPane.showMessageDialog(this, "Este cliente ya tiene una factura de este mes", "Error", JOptionPane.ERROR_MESSAGE);

}*/
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed



    
    public  void imprimir(){
    String fec="";
    
String tot="";
        String impr="";
String ced="";
String nom="";
String med="";
String rec="";
String bac="";
String ant="";
String act="";
String exc="0";
String mor="0";
String min="0";
String reins="0";
String asam="0";

                   fec=fechaActual1.getText();
                    ced=cedulatxt.getText();
                    nom=nombretxt.getText();
                    tot= Total.getText();
 med=medidortxt.getText();
 rec=reciboMes.getText()+" "+reciboAno.getText();
 bac=cobroBasico.getText();
ant=lecturaAnterior.getText();
 act=lecturaActual.getText();

System.out.println(jMultas.getValueAt(0, 1).toString());
 System.out.println(jMultas.getValueAt(1, 1).toString());

 
exc=jMultas.getValueAt(0, 1).toString();
mor=jMultas.getValueAt(1, 1).toString();

if(jMultas.getRowCount()==3){
if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR MINGA")){
min=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR REINSTALACION")){
reins=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR ASAMBLEA")){
asam=jMultas.getValueAt(2, 1).toString();
}

}


if(jMultas.getRowCount()==4){
if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR MINGA")){
min=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR REINSTALACION")){
reins=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR ASAMBLEA")){
asam=jMultas.getValueAt(2, 1).toString();
}

}


if(jMultas.getRowCount()==5){
if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR MINGA")){
min=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR REINSTALACION")){
reins=jMultas.getValueAt(2, 1).toString();
}

if(jMultas.getValueAt(2, 0).toString().equals("MULTA POR ASAMBLEA")){
asam=jMultas.getValueAt(2, 1).toString();
}

}
    PrinterJob job = PrinterJob.getPrinterJob();
job.setPrintable(new ObjetoDeImpresion(fec,ced,nom,med,rec,bac,ant,act,exc,mor,min,reins,asam,tot));
//if(job.printDialog()){
try{
job.print();
}catch(PrinterException e){
System.out.println(e);
}
//}   
    
    }
    
    

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (listaMultas.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar seleccionar una multa", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            ResultSet datos;
            try {
                datos = conexion.ejecutarSQLSelect("select costo from multas where descripcion_multa='" + listaMultas.getSelectedItem().toString() + "'");

                DefaultTableModel modelo2 = (DefaultTableModel) jMultas.getModel();

                while (datos.next()) {

                    modelo2.addRow(new Object[]{listaMultas.getSelectedItem().toString(), datos.getString("costo")});

                }

                conexion.cerrarConexion();
                double total = 0;
                total = Double.parseDouble(cobroBasico.getText());
                double subtotal = 0;
                for (int i = 0; i < jMultas.getRowCount(); i++) {
                    subtotal = subtotal + Double.parseDouble(jMultas.getValueAt(i, 1).toString());

                }
                total = total + subtotal;
                Total.setText(total + "");
                System.out.println("total " + total + " subtotal " + subtotal);

            } catch (SQLException ex) {
                Logger.getLogger(Facturar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (jMultas.getRowCount() > 2) {

            if (jMultas.getSelectedRow() > 1) {
                System.out.print(jMultas.getSelectedRow() + "");

                DefaultTableModel modelo2 = (DefaultTableModel) jMultas.getModel();

                modelo2.removeRow(jMultas.getSelectedRow());

                double total = 0;
                total = Double.parseDouble(cobroBasico.getText());
                double subtotal = 0;
                for (int i = 0; i < jMultas.getRowCount(); i++) {
                    subtotal = subtotal + Double.parseDouble(jMultas.getValueAt(i, 1).toString());

                }
                total = total + subtotal;
                Total.setText(total + "");
                System.out.println("total " + total + " subtotal " + subtotal);
            } else {
                JOptionPane.showMessageDialog(this, "Esta multa no se puede eliminar", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(this, "No existen multas manuales ingresadas", "Error", JOptionPane.ERROR_MESSAGE);

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facturar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Facturar().setVisible(true);
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
    private javax.swing.JLabel fechaActual;
    private javax.swing.JLabel fechaActual1;
    private javax.swing.JLabel fechaActual2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JTable jMultas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTabla;
    private javax.swing.JTextField lecturaActual;
    private javax.swing.JTextField lecturaAnterior;
    private javax.swing.JComboBox<String> listaAno;
    private javax.swing.JComboBox<String> listaMes;
    private javax.swing.JComboBox<String> listaMultas;
    private javax.swing.JList<String> medidoreslist;
    private javax.swing.JTextField medidortxt;
    private javax.swing.JTextField nombretxt;
    private javax.swing.JLabel numeroFactura;
    private javax.swing.JTextField reciboAno;
    private javax.swing.JTextField reciboMes;
    private javax.swing.JList<String> telefonolist;
    // End of variables declaration//GEN-END:variables
}
