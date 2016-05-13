package Informe.Controlador;

import Informe.Vista.VistaInforme;
import javax.xml.transform.TransformerException;

public class Main {
    
    private static VistaInforme vm;
    private static Controladora con;
    private static boolean manual = false;
    
    public static void main(String[] args){
    }
    
    public static void abrirInforme(){
        vm = new VistaInforme();
        manual = true;
        vm.setVisible(true);
    }
    
    public static void crearInforme(String m, String a) throws TransformerException{
        con = new Controladora(m+"-"+a);
        con.runExample();
    }
    
    public static void crearInforme(String d) throws TransformerException{
        con = new Controladora(d);
        con.runExample();
    }
    
    public static void salir(){
        manual = false;
        vm.dispose();
    }
    
    public static void acabado(){
        javax.swing.JOptionPane.showMessageDialog(vm, "Informe realizado");
    }
    
    public static void partesVacio(){
        javax.swing.JOptionPane.showMessageDialog(vm, "No existen partes");
    }
    
    public static boolean esManual(){
        return manual;
    }
    
}
