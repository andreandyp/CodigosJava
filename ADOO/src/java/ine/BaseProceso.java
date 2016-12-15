package ine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseProceso {
    private final String url = "jdbc:mysql://localhost/ine";
    private final String usuario = "root";
    private final String claveBase = "Andy94";
    Connection con;
    Statement stm;
    
    public void abrirConexion() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, usuario, claveBase);
    }
    
    public void subirVotos(CasillaINE cINE) throws SQLException {
        PreparedStatement query;
        BoletaElectoral [] be;
        be = cINE.getBoletas();
        String[] tablas = {"BOL_PRE","BOL_GOB","BOL_DIP"};
        for(int i = 0; i <= 3; i++){
            query = con.prepareStatement("insert into INE."+tablas[i]+" values(?,?,?,?)");
            query.setInt(1, be[i].getClaveBoleta());
            query.setInt(2, i+1);
            query.setString(3, be[i].getPartido());
            query.setString(4, be[i].getCandidato());
            query.executeUpdate();
        }
    }
    
    public void cerrarConexion() throws SQLException{
        con.close();
    }    
}
