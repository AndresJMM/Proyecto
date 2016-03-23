package Modelo.UML;

public class Administracion  extends Trabajador{
    public Administracion(){ 
    }
    public Administracion(int idTrabajador, String DNI, String nombre, String ape1, String ape2, String movilEmp, String calleTrab, String portalTrab, String pisoTrab, String manoTrab){
        super(idTrabajador, DNI, nombre, ape1, ape2, movilEmp, calleTrab, portalTrab, pisoTrab, manoTrab);
    }
}
