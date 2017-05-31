/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdconexionbd;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Main con un menu para elegir la accion a realizar
 * @author Adry
 */
public class CDConexionBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Metodos objBD=new Metodos("clase");
        if(objBD.conectarBD())
            System.out.println("Conectado con exito");
        else
            System.out.println("Error al conectar");
        int opcion;
        do{
            opcion=Integer.parseInt(JOptionPane.showInputDialog("Elige:\n1:Insertar fila\n2:Actualizar fila\n3:Eliminar fila\n4:Visualizar tabla\n0:Salir\n111:Crear tabla(solo si no se creo aun)"));
            switch(opcion){
                case 1:objBD.insertarBD(JOptionPane.showInputDialog("Dni"),JOptionPane.showInputDialog("Nombre"));
                break;
                case 2:objBD.modificarBD(JOptionPane.showInputDialog("Dni del alumno a actualizar"),JOptionPane.showInputDialog("Nuevo dni del alumno"),JOptionPane.showInputDialog("Nuevo nombre del alumno"));
                break;
                case 3:objBD.borrarBD(JOptionPane.showInputDialog("Dni del alumno a borrar"));
                break;
                case 4:objBD.consultar();
                break;
                case 111:objBD.crearTabla();
                break;
                case 0:objBD.cerrarBD();
                       System.exit(0);
            }
        }while(opcion!=10);
    }
    
}
