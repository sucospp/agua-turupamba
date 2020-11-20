package BaseDatos;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SUCOSPP
 */
public class ConexionBD {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public boolean crearConexion() {
        try {


            Class.forName("com.mysql.jdbc.Driver");
//            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/turupamba_agua", "root", "turupamba");
conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/turupamba_agua", "root", "");
pruebaKotlin a = new pruebaKotlin();
a.prueba();



        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean ejecutarSQL(String sql) {
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet ejecutarSQLSelect(String sql) throws SQLException {
        ResultSet resultado;

        try {
            crearConexion();

            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return resultado;

    }

    public DefaultTableModel ejecutarSE(DefaultTableModel m, String sql) throws SQLException, IllegalStateException {
        try {
            for (int i = 0; i < m.getRowCount(); i++) {
                m.removeRow(i);
            }
            ResultSet k = ejecutarSQLSelect(sql);
            ResultSetMetaData metaDatos = k.getMetaData();
            int numeroColumnas = metaDatos.getColumnCount();
            Object[] nombreCampos = new Object[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++) {
                nombreCampos[i] = metaDatos.getColumnLabel(i + 1);
            }
            m.setColumnIdentifiers(nombreCampos);

            while (k.next()) {
                Object[] fila = new Object[numeroColumnas];
                for (int j = 0; j < numeroColumnas; j++) {
                    fila[j] = k.getObject(j + 1);

                }
                m.addRow(fila);

            }
            m.isCellEditable(numeroColumnas, numeroColumnas);

            k.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    public ArrayList vectorOUnitario(String sql) throws SQLException {
        ArrayList dev = new ArrayList();
        ResultSet k = ejecutarSQLSelect(sql);
        ResultSetMetaData metaDatos = k.getMetaData();
        int numeroColumnas = metaDatos.getColumnCount();

        while (k.next()) {
            Object[] fila = new Object[numeroColumnas];
            for (int j = 0; j < numeroColumnas; j++) {
                fila[j] = k.getObject(j + 1);

                dev.add(fila[j]);
            }

        }
        k.close();
        return dev;
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }

}
