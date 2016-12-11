package ine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseINE {
    private final String url = "jdbc:mysql://localhost/ine";
    private final String usuario = "root";
    private final String claveBase = "Andy94";
    Connection con;
    Statement stm;
    
    public void abrirConexion() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, usuario, claveBase);
    }
    public void buscarClave(Votante v){
        
    }
    public void cerrarConexion(){
        
    }
}
