/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turupamba_agua;

import BaseDatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SUCOSPP
 */
public class Turupamba_Agua {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        ConexionBD conexion = new ConexionBD();
        conexion.crearConexion();

        int activado = 0;
        ResultSet datos = conexion.ejecutarSQLSelect("select count(*) as activado from configuracion where idconfiguracion=2 and valor=1");
        while (datos.next()) {
            activado = Integer.parseInt(datos.getString("activado"));

        }

        if (activado == 1) {
            Inicio_Sesion a = new Inicio_Sesion();
            a.setVisible(true);
        } else {
            //System.out.println("producto desactivado");
            Activar a = new Activar();
            a.setVisible(true);
        }
        conexion.cerrarConexion();

    }

}
