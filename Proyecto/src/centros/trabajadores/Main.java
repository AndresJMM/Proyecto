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
import java.util.Date;


public class Main {
    
    private static boolean modificar;
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
    
    public static void rellenaDatos(String dni) throws SQLException{
        t = TrabajadorBD.getTrabajador(dni, c.getIdCentro());
        if(t!=null){
            vt.rellenar(t.getNombre(), t.getApe1(), t.getApe2(), t.getCalleTrab(), t.getPortalTrab(), t.getPisoTrab(), t.getManoTrab(), t.getTlfPersonal(), t.getMovilEmp(), t.getSalario(), t.getFechaNac());
            vt.modificar();
            modificar = true;
        }else{
            vt.rellenar(null, null, null, null, null, null, null, null, null, null, null);
            vt.nuevo();
            modificar = false;
        }
        if(t instanceof Administracion){
            vt.setTipo("Administracion");
        }else if(t instanceof Logistica){
            vt.setTipo("Logistica");
        }
    }
    
    public static void actualizarUsuario(String nombre, String ape1, String ape2, String calle, String portal, String piso, String mano, String tlfper, String movilemp, String salario, Date fecha, String tipo) throws SQLException{
        t.setNombre(nombre);
        t.setApe1(ape1);
        t.setApe2(ape2);
        t.setCalleTrab(calle);
        t.setPortalTrab(portal);
        t.setPisoTrab(piso);
        t.setManoTrab(mano);
        t.setTlfPersonal(tlfper);
        t.setMovilEmp(movilemp);
        System.out.print(salario);
        if(salario!=null && salario.length()>0)
            t.setSalario(Float.parseFloat(salario));
        t.setFechaNac(fecha);
        
        if((t instanceof Administracion && tipo.compareToIgnoreCase("Administracion")!=0) || (t instanceof Logistica && tipo.compareToIgnoreCase("Logistica")!=0)){
            TrabajadorBD.modificacion(t, tipo);
        }else
            TrabajadorBD.modificacion(t);
        
    }
    
    public static boolean modificar(){
        return modificar;
    }
    
    public static String getCentro(int index) throws SQLException{
        c = CentroBD.getCentro(listaIdCentros.get(index));
        return c.getNombreCen();
    }
    
    public static void close(){
        System.exit(0);
    }
    
    public static void acabar(){
        System.exit(0);
    }
    
    
}
