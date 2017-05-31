/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdconexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adry
 */
public class Metodos {
    String nomBD;
    Connection conn;
    Statement stmt;

    public Metodos() {
    }

    public Metodos(String nomBD) throws ClassNotFoundException, SQLException {
        this.nomBD = nomBD;
        conectarBD();
    }
     public boolean conectarBD() throws ClassNotFoundException{
        try{ 
       Class.forName("org.sqlite.JDBC");
       conn=DriverManager.getConnection("jdbc:sqlite:"+nomBD+".db");
       conn.setAutoCommit(false);
       return true;
       }catch( SQLException e){
         return false;
       }
       }
     public void crearTabla() throws SQLException{
         stmt=conn.createStatement();
         String sql="Create table alumnos"
                 +"(dni text primary key not null,"
                 +"nombre text not null)";
         stmt.executeUpdate(sql);
     }
     public boolean insertarBD(String dni,String nombre) throws SQLException{
         try{
         stmt=conn.createStatement();
         String sql="Insert into dam1 values('"+dni+"','"+nombre+");";
         stmt.executeUpdate(sql);
         conn.commit();
         return true;
         }catch(SQLException e){
             return false;
         }
     }
     public void borrarBD(String dni) throws SQLException{
         stmt=conn.createStatement();
         String sql="delete from dam1 where dni='"+dni+"';";
         stmt.executeUpdate(sql);
         conn.commit();
     }
     public void consultar() throws SQLException{
       stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("select * from alumnos");
       while(rs.next()){
           System.out.println("dni="+rs.getString("dni"));
           System.out.println("nombre="+rs.getString("nombre"));
       }
    }
     public void modificarBD(String vDni,String nDni,String nNombre) throws SQLException{
          stmt=conn.createStatement();
          String sql="update dam1 set dni='"+nDni+"',nombre='"+nNombre+"' where dni='"+vDni+"';";
          stmt.executeUpdate(sql);
          conn.commit();
      }
     public void cerrarBD() throws SQLException{
         stmt.close();
         conn.close();
     }
}
