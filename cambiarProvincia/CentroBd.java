/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;
import Vista.*;
import UML.Centro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author IFONS
 */
public class CentroBd extends GenericoBD{
    
	
	
	
	
	public static void cambiarProvincia( Integer id , String provincia )
	{
		try
        {
            abrirConexion();
            					
			CallableStatement cst = cn.prepareCall("{call cambiarProvincia (?,?)}");
             
            // Parametro 1 del procedimiento almacenado
            cst.setInt(1, id);
			// Parametro 2 del procedimiento almacenado
			cst.setString(2, provincia);
                
            // Ejecuta el procedimiento almacenado
            cst.execute();
        }
        catch(Exception e)
        {
			System.out.println("Errores.");
        }
        finally
        {
            cerrarConexion();
        } 
	}
	
	
	
	
    
    public static String recuperarNombreCentro( Integer id )
    {
        String nombre = null;
        String plantilla;
        try
        {
            abrirConexion();
            
            plantilla="SELECT nombre FROM Centro where idCentro = " + id ;
     
            System.out.println(plantilla);
            
            PreparedStatement ps=GenericoBD.getCon().prepareStatement(plantilla);
            
            ResultSet resultado = ps.executeQuery();
          
            while(  resultado.next() )
            {
                nombre = resultado.getString("nombre");
            }
            
            return nombre;   
        }
        catch(Exception e)
        {
            return null;
        }
        finally
        {
            cerrarConexion();
        }       
    }
    
    public static ArrayList<Integer> recuperarIDsCentros()
    {
        ArrayList <Integer> idscentros = new ArrayList<>();
        String plantilla;
        try
        {
            abrirConexion();
            
            plantilla="SELECT idCentro FROM Centro";

            PreparedStatement ps=GenericoBD.getCon().prepareStatement(plantilla);
            
            ResultSet resultado = ps.executeQuery();
            
            while(  resultado.next() )
            {                
                idscentros.add( resultado.getInt("idCentro") );  
            }    
            
            return idscentros;   
        }
        catch(Exception e)
        {
            return null;
        }
        finally
        {
            cerrarConexion();
        }       
    }
    public static void gente(){
            
    }
    
  
     
   
        
       
        
    
}
