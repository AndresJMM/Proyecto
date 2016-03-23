package Modelo.UML;

import java.util.Date;

public abstract class Trabajador {
   
    private int idTrabajador;
    private String DNI;
    private String nombre;
    private String ape1;
    private String ape2;
    private Date fechaNac;
    private Float salario;
    private String movilEmp;
    private String tlfPersonal;
    private String calle;
    private String portal;
    private String piso;
    private String mano;

    public Trabajador() {
        
    }
    
    public Trabajador(int idTrabajador, String DNI, String nombre, String ape1, String ape2, String movilEmp, String calleTrab, String portalTrab, String pisoTrab, String manoTrab) {
        this.idTrabajador = idTrabajador;
        this.DNI = DNI;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.movilEmp = movilEmp;
        this.calle = calleTrab;
        this.portal = portalTrab;
        this.piso = pisoTrab;
        this.mano = manoTrab;
    }

    public int getIdTrabajador() {
        return this.idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getDNI() {
        return this.DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return this.ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return this.ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public Date getFechaNac() {
        return this.fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Float getSalario() {
        return this.salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public String getMovilEmp() {
        return this.movilEmp;
    }

    public void setMovilEmp(String movilEmp) {
        this.movilEmp = movilEmp;
    }

    public String getTlfPersonal() {
        return this.tlfPersonal;
    }

    public void setTlfPersonal(String tlfPersonal) {
        this.tlfPersonal = tlfPersonal;
    }

    public String getCalleTrab() {
        return this.calle;
    }

    public void setCalleTrab(String calleTrab) {
        this.calle = calleTrab;
    }

    public String getPortalTrab() {
        return this.portal;
    }

    public void setPortalTrab(String portalTrab) {
        this.portal = portalTrab;
    }

    public String getPisoTrab() {
        return this.piso;
    }

    public void setPisoTrab(String pisoTrab) {
        this.piso = pisoTrab;
    }

    public String getManoTrab() {
        return this.mano;
    }

    public void setManoTrab(String manoTrab) {
        this.mano = manoTrab;
    }
}