package centros.trabajadores;

import Vista.*;
import Modelo.UML.*;
import Modelo.BD.*;
import Excepciones.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Main {

    private static VistaCentro vl;
    private static VistaTrabajador vt;
    private static Centro c;
    private static Trabajador t;
    private static ArrayList <Integer> listaIdCentros;
    private static ArrayList <String> listaDNITrabajadores;
    
    
    public static void main(String[] args){
        vl = new VistaCentro();
        rellenaCentros();
        vl.setVisible(true);
        vl.setVisible(true);
    }   
    
    public static void rellenaCentros(){
        try{
            listaIdCentros = CentroBD.getIdCentros();
            vl.setCentros(listaIdCentros.toArray());
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
        }
    }
        
    public static void selectCentro(int index){
        vl.dispose();
        try{
            listaDNITrabajadores =TrabajadorBD.getTrabajadoresCentro(c.getIdCentro());
        }
        catch(Exception e){
            System.out.println("Problemas al realizar query: "+e);
        }
        mostrarPerfiles();
    }
    
    public static void mostrarPerfiles(){
        vt = new VistaTrabajador();
        vt.getReady();
    }
    
    public static boolean checkDNI(String dni){
        boolean r = false;
        for(int i = 0;i<listaDNITrabajadores.size();i++){
            if(listaDNITrabajadores.get(i).compareTo(dni)==0)
                r = true;
        }
        return r;
    }
    
    public static void rellenaDatos(String dni){
        //Sacar datos de trabajadorBD por DNI y rellenar datos en la ventana si existe en la tabla de trabajadores, o existe el trabajador en otro centro, avisar de que se creará uno nuevo si no existe
    }
    
    public static String getCentro(int index) throws SQLException{
        c = CentroBD.getCentro(listaIdCentros.get(index));
        return c.getNombreCen();
    }
    
    public static void acabar(){
        System.exit(0);
    }
    
    
}
