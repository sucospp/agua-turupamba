/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBDTests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import BaseDatos.ConexionBD;

/**
 *
 * @author SUCOSPP
 */
public class ConexionBdTest {
    private static ConexionBD conexion;
    
    public ConexionBdTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        conexion =new ConexionBD();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
      conexion.cerrarConexion();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void probarConexionP(){
        assertEquals(true, conexion.crearConexion());
    }
    
  
}
