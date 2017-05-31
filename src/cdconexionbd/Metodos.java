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
 * Clase con los metodos de la conexion
 * @author Adry
 */
public class Metodos {
    String nomBD;
    Connection conn;
    Statement stmt;
/**
 * Construcor sin parametros
 */
    public Metodos() {
    }
/**
 * Constructor con parametros que llama al metodo conectar
 * @param nomeBD
 * @throws ClassNotFoundException
 * @throws SQLException 
 */
    public Metodos(String nomeBD) throws ClassNotFoundException, SQLException {
        this.nomBD = nomeBD;
        conectarBD();
    }
    /**
     * Conecta la base de datos
     * @return true or false
     * @throws ClassNotFoundException 
     */
     public boolean conectarBD() throws ClassNotFoundException{
        try{ 
       conn=DriverManager.getConnection("jdbc:sqlite:"+nomBD+".db");
       conn.setAutoCommit(false);
       return true;
       }catch( SQLException e){
            System.err.println("No se conecta");
         return false;
       }
       }
     /**
      * Crea la tabla dam1
      * @throws SQLException 
      */
     public void crearTabla() throws SQLException{
         stmt=conn.createStatement();
         String sql="Create table dam1"
                 +"(dni text primary key not null,"
                 +"nombre text not null)";
         stmt.executeUpdate(sql);
     }
     /**
      * Inserta una fila a la tabla dam1
      * @param dni
      * @param nombre
      * @return true or false
      * @throws SQLException 
      */
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
     /**
      * Borra la fila elegida de la tambla dam1
      * @param dni
      * @throws SQLException 
      */
     public void borrarBD(String dni) throws SQLException{
         stmt=conn.createStatement();
         String sql="delete from dam1 where dni='"+dni+"';";
         stmt.executeUpdate(sql);
         conn.commit();
     }
     /**
      * Muestra el contenido de la tabla dam1
      * @throws SQLException 
      */
     public void consultar() throws SQLException{
       stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery("select * from dam1");
       while(rs.next()){
           System.out.println("dni="+rs.getString("dni"));
           System.out.println("nombre="+rs.getString("nombre"));
       }
    }
     /**
      * Modifica la fila elegida de la tabla dam1
      * @param vDni
      * @param nDni
      * @param nNombre
      * @throws SQLException 
      */
     public void modificarBD(String vDni,String nDni,String nNombre) throws SQLException{
          stmt=conn.createStatement();
          String sql="update dam1 set dni='"+nDni+"',nombre='"+nNombre+"' where dni='"+vDni+"';";
          stmt.executeUpdate(sql);
          conn.commit();
      }
     /**
      * Cierra la conexion con la base de datos
      * @throws SQLException 
      */
     public void cerrarBD() throws SQLException{
         stmt.close();
         conn.close();
     }
}
