package ine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public Votante buscarClave(String clave) throws SQLException{
        PreparedStatement query = con.prepareStatement("select * from INE.elector where CE=?"); 
        query.setString(1, clave);
        ResultSet rset = query.executeQuery();
        if(rset.next()){
            return new Votante(rset.getString("CE"),rset.getString("Nombre"),rset.getString("Sexo").charAt(0),rset.getInt("Estado"),rset.getInt("Seccion"));
        }else{
            return null;
        }
    }
    public void cerrarConexion() throws SQLException{
        con.close();
    }
}
