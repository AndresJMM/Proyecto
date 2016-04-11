package Modelo.UML;

public class Administracion  extends Trabajador{
    public Administracion(){ 
    }
    public Administracion(Trabajador t){
        super(t.getIdTrabajador(), t.getDNI(), t.getNombre(), t.getApe1(), t.getApe2(), t.getMovilEmp(), t.getCalleTrab(), t.getPortalTrab(), t.getPisoTrab(), t.getManoTrab());
        this.setFechaNac(t.getFechaNac());
        this.setSalario(t.getSalario());
        this.setTlfPersonal(t.getTlfPersonal());
    }
    public Administracion(int idTrabajador, String DNI, String nombre, String ape1, String ape2, String movilEmp, String calleTrab, String portalTrab, String pisoTrab, String manoTrab){
        super(idTrabajador, DNI, nombre, ape1, ape2, movilEmp, calleTrab, portalTrab, pisoTrab, manoTrab);
    }
}
